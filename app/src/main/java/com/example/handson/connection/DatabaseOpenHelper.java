package com.example.handson.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String name = "hands.db";
    private static final int version = 1;

    public DatabaseOpenHelper(@Nullable Context context, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pacientes(" +
                //"id integer primary key autoincrement, " +
                "cpf varchar(14) primary key, " +
                "name varchar(50), " +
                "age integer, " +
                "body_temp integer, " +
                "cough_days integer, " +
                "headache_days integer, " +
                "country_visit varchar(20), " +
                "diagnosis integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS pacientes";
        db.execSQL(sql);
        onCreate(db);
    }
}
