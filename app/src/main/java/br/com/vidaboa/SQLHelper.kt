package br.com.vidaboa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_USER (" +
                    "$TABLE_USE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$TABLE_USE_NAME VARCHAR(100) NOT NULL," +
                    "$TABLE_USE_EMAIL VARCHAR(200) NOT NULL," +
                    "$TABLE_USE_DATE DATETIME," +
                    "$TABLE_USE_CELLPHONE VARCHAR(20)," +
                    "$TABLE_USE_PASSWORD VARCHAR(250) NOT NULL," +
                    "$TABLE_USE_TYPE INT NULL"+
                    ")"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {
        TODO("Not yet implemented")
    }

}