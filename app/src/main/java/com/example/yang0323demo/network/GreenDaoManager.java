package com.example.yang0323demo.network;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;

public class GreenDaoManager {
    private static GreenDaoManager instance;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private GreenDaoManager(){

    }
    public static GreenDaoManager getInstance(){
        if (instance==null){
            synchronized (GreenDaoManager.class){
                if (instance==null){
                    instance = new GreenDaoManager();
                }
            }
        }
        return instance;
    }
    public void setDataBase(Context context){
        devOpenHelper = new DaoMaster.DevOpenHelper(context, "yang-db", null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
