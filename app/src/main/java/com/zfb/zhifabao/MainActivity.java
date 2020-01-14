package com.zfb.zhifabao;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zfb.zhifabao.activities.UserActivity;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.persistence.Account;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.flags.main.FindFragment;
import com.zfb.zhifabao.flags.main.HomeFragment;
import com.zfb.zhifabao.flags.main.MyFragment;
import com.zfb.zhifabao.flags.member.OpenMemberFragment;
import com.zfb.zhifabao.helper.NavHelper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 这是开发分支，用于智法宝开发
 */
public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener, CommonTrigger {
    private NavHelper mNavHelper;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private LocationManager locationManager;
    private String city;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected boolean initArgs(Bundle Bundle) {
        boolean tmp = Account.isComplete();

        Log.e("delong", "temp>>>>>>>>>>>>>>>>>>>>>" + Account.getUser().getPortrait());
        if (tmp) {
            return super.initArgs(Bundle);
        } else {
            UserActivity.show(this);
            finish();
            return false;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressWarnings("unchecked")
    @SuppressLint("ResourceType")
    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
        setStatuTrans();
        getLocation();

        mNavHelper = new NavHelper(this, getSupportFragmentManager(), R.id.fl_container);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab(HomeFragment.class, city))
                .add(R.id.action_find, new NavHelper.Tab(FindFragment.class, FindFragment.class.getName()))
                .add(R.id.action_my, new NavHelper.Tab(MyFragment.class, MyFragment.class.getName()))
                .add(Common.Constance.TO_OPEN_MEMBER_FRAGMENT, new NavHelper.Tab(OpenMemberFragment.class, OpenMemberFragment.class.getName()));
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        mNavHelper.performanceTab(R.id.action_home);
    }


    /**
     * 这是状态栏透明的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Menu menu = bottomNavigation.getMenu();
//        menu.performIdentifierAction(R.id.action_home,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return mNavHelper.performanceTab(menuItem.getItemId());
    }

    @Override
    public void triggerView(int flags) {
        mNavHelper.performanceTab(flags);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocation() {
        //1.获取位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        String locationProvider;
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            //GPS 定位的精准度比较高，但是非常耗电。
            Log.e("delong", "=====GPS_PROVIDER=====");
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {//Google服务被墙不可用
            //网络定位的精准度稍差，但耗电量比较少。
            Log.e("delong", "=====NETWORK_PROVIDER=====");
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Log.e("delong", "=====NO_PROVIDER=====");
            // 当没有可用的位置提供器时，弹出Toast提示用户
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.startActivity(intent);
            return;
        }
        //3.获取上次的位置，一般第一次运行，此值为null
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                // 显示当前设备的位置信息
                Log.e("delong", "==显示当前设备的位置信息==");
                showLocation(location);
            } else {//当GPS信号弱没获取到位置的时候可从网络获取
                Log.e("delong", "==Google服务被墙的解决办法==");
                getLngAndLatWithNetwork();//Google服务被墙的解决办法
            }
            // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
            //LocationManager 每隔 5 秒钟会检测一下位置的变化情况，当移动距离超过 10 米的时候，
            // 就会调用 LocationListener 的 onLocationChanged() 方法，并把新的位置信息作为参数传入。
            locationManager.requestLocationUpdates(locationProvider, 5000, 10, locationListener);
           // return;
        }

    }


    //获取经纬度
    private void showLocation(Location location) {
        double latitude = location.getLatitude();//纬度
        double longitude = location.getLongitude();//经度
       // getAddress(latitude, longitude);

    }

    private void getAddress(double latitude, double longitude) {
        //Geocoder通过经纬度获取具体信息
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> locationList = gc.getFromLocation(latitude, longitude, 1);

            if (locationList != null) {
                Address address = locationList.get(0);

                String countryName = address.getCountryName();//国家
                String countryCode = address.getCountryCode();
                String adminArea = address.getAdminArea();//省
                String locality = address.getLocality();//市
                String subAdminArea = address.getSubAdminArea();//区
                String featureName = address.getFeatureName();//街道

                for (int i = 0; address.getAddressLine(i) != null; i++) {
                    String addressLine = address.getAddressLine(i);
                    //街道名称:广东省深圳市罗湖区蔡屋围一街深圳瑞吉酒店
                    Log.e("delong", "addressLine=====" + addressLine);
                }

                String currentPosition = "latitude is " + latitude//22.545975
                        + "\n" + "longitude is " + longitude//114.101232
                        + "\n" + "countryName is " + countryName//null
                        + "\n" + "countryCode is " + countryCode//CN
                        + "\n" + "adminArea is " + adminArea//广东省
                        + "\n" + "locality is " + locality//深圳市
                        + "\n" + "subAdminArea is " + subAdminArea//null
                        + "\n" + "featureName is " + featureName;//蔡屋围一街深圳瑞吉酒店

                Log.e("delong", currentPosition);
                Application.showToast(locality);
                city = locality;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            System.out.println("==onLocationChanged==");
            showLocation(location);
        }
    };

    //从网络获取经纬度
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLngAndLatWithNetwork() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            showLocation(location);
            return;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("delong","requestCode==========================="+requestCode);
        if (requestCode==188){
            mNavHelper.performanceTab(R.id.action_my);
        }

    }
}
