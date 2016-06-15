package com.example.dllo.mirror.model.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.mirror.model.db.DaoMaster;
import com.example.dllo.mirror.model.db.DaoSession;
import com.example.dllo.mirror.model.db.Users;
import com.example.dllo.mirror.model.db.UsersDao;

/**
 * Created by dllo on 16/6/15.
 */
public class GreenDaoSingleton {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UsersDao usersDao;
    private Context context;
    private DaoMaster.DevOpenHelper helper;


    private static GreenDaoSingleton ourInstance = new GreenDaoSingleton();

    public static GreenDaoSingleton getOurInstance() {
        return ourInstance;
    }

    private GreenDaoSingleton() {
        context = MyApplication.getContext();
    }

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "Users.db", null);
        }
        return helper;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public UsersDao getUsersDao() {

        if (usersDao == null) {
            usersDao = getDaoSession().getUsersDao();
        }
        return usersDao;
    }


}
