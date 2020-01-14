package com.zfb.zhifabao.common.factory.model.db;
import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME ,version = AppDataBase.VERSION)
public class AppDataBase {
    public static  final String NAME = "ZhiFaBaoDataBase";
    public  static  final int VERSION = 1;
}
