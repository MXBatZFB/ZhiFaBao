package com.zfb.zhifabao.common.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;

public class Account {
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_COMPANY_NAME = "KEY_COMPANY_NAME";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String KEY_PORTRAIT = "KEY_PORTRAIT";
    private static final String KEY_IS_MEMBER = "KEY_IS_MEMBER";
    private static final String KEY_IS_ENTER = "KEY_IS_ENTER";
    private static final String KEY_PUSH_REGISTER_ID = "KEY_PUSH_REGISTER_ID";
    private static String token;
    private static String userName;
    private static String account;
    private static String portrait;
    private static int memberType;
    private static boolean isEnter;


    private static void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(), Context.MODE_PRIVATE);
        sp.edit().putString(KEY_USER_NAME, userName)
                .putString(KEY_TOKEN, token)
                .putString(KEY_ACCOUNT, account)
                .putString(KEY_PORTRAIT, portrait)
                .putInt(KEY_IS_MEMBER, memberType)
                .putBoolean(KEY_IS_ENTER, isEnter)
                .commit();
    }

    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(), Context.MODE_PRIVATE);
        token = sp.getString(KEY_TOKEN, "");
        account = sp.getString(KEY_ACCOUNT, "");
        userName = sp.getString(KEY_USER_NAME, "");
        portrait = sp.getString(KEY_PORTRAIT, "");
        memberType =sp.getInt(KEY_IS_MEMBER,0);
    }




    public static boolean isLogin() {
        return !TextUtils.isEmpty(token)
                && !TextUtils.isEmpty(account);
    }

    public static boolean isComplete() {
        if (isLogin()) {
            return !TextUtils.isEmpty(userName)
                    && !TextUtils.isEmpty(portrait);
        }
        return false;
    }

    public static void completeInfo(UserInfo model) {
        Account.userName = model.getNickname();
        Account.portrait = model.getPortrait();
        Account.account = model.getPhone();
        Account.memberType = model.getMemberType();
        save(Factory.app());
    }

    public static void outLogin() {
        Account.token = null;
        Account.account = null;
        Account.userName = null;
        Account.portrait = null;
        save(Factory.app());
    }

    public static void login(UserInfo model) {
        Account.token = model.getToken();
        Account.account = model.getPhone();
        Account.userName = model.getNickname();
        Account.portrait = model.getPortrait();
        save(Factory.app());
    }

    public static UserInfo getUser() {
        return new UserInfo(userName, portrait, memberType);
    }

    public static String getAccount() {
        return account;
    }

    public static String getToken() {
        return token;
    }
}
