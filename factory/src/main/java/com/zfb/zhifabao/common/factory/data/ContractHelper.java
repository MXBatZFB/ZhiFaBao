package com.zfb.zhifabao.common.factory.data;

import android.provider.Settings;
import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.common.factory.model.db.ContractFileInfo;
import com.zfb.zhifabao.common.factory.model.db.ContractFileInfo_Table;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ContractHelper {

    public static void generateContract(CustomContractModel model ,final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<CustomContractResultModel>> call = service.generateContract(model);
        call.enqueue(new Callback<ResModel<CustomContractResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<CustomContractResultModel>> call, Response<ResModel<CustomContractResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<CustomContractResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }

    public static  void downContractFile(final String id , final String saveName, final DataSource.Callback<Boolean> callback){
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResponseBody> call = service.downContractFile(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.isSuccessful()) {
                    // writeResponseBodyToDisk 下载保存本地工具类
                    ContractFileInfo info = writeResponseBodyToDisk(response.body(),saveName);
                    if (info!=null) {
                        info.setContractId(id);
                        info.save();
                        callback.onDataLoaded(true);
                    } else {
                        callback.onDtaNotAvailable("下载失败！");
                    }
                } else {
                    callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }


    /**
     * 下载到本地
     *
     * @param body 内容
     * @return 成功或者失败
     */
    private static ContractFileInfo writeResponseBodyToDisk(ResponseBody body,String saveName) {
        ContractFileInfo info = new ContractFileInfo();
        try {

            //判断文件夹是否存在
            File contractDir = new File(Application.getCahceDirFile(),"contractDir");//跟目录一个文件夹
            if (!contractDir.exists()) {
                //不存在就创建出来
                contractDir.mkdirs();
            }
            //创建一个文件
            File futureContractFile = new File(contractDir.getAbsolutePath()+File.separator+saveName+".docx");
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(futureContractFile);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                //刷新
                outputStream.flush();
                info.setFileName(saveName);
                info.setTime(System.currentTimeMillis()+"");
                info.setLocalUrl(futureContractFile.getAbsolutePath());
                return info;
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static void getLocalContractFile(DataSource.Callback< List<ContractFileInfo>> callback) {
        List<ContractFileInfo> lists = new Select().from(ContractFileInfo.class).queryList();
        Log.e("delong",lists.toString());
        callback.onDataLoaded(lists);
    }
}
