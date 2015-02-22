package com.apps.jakes.androidprogrammingchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    private static final int DB_V = 1;
    private static final String DB_NAME = "Users";
    private static final String TB_Users = "'User'";
    private static final String KEY_ID = "'ID'";
    private static final String KEY_ImageID = "'ImageID'";
    private static final String KEY_Title = "'Title'";
    private static final String KEY_UserID = "'UserID'";
    private static final String KEY_UserName = "'UserName'";
    private final Context myContext;
    private final MainActivity mainActivity;


    public SQLite(Context context, MainActivity mainActivity) {
        super(context, DB_NAME, null, DB_V);
        this.myContext = context;
        this.mainActivity = mainActivity;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TB_Users + " (" + KEY_ID + " TEXT,"
                + KEY_ImageID + " TEXT," + KEY_Title + " TEXT," + KEY_UserID +
                " TEXT," + KEY_UserName + " TEXT" + ");";
        //CREATE TABLE 'User'('ID' TEXT,'ImageID' TEXT,'Title' TEXT,'UserID' TEXT,'UserName' TEXT)
        db.execSQL(CREATE_USERS_TABLE);
        //mainActivity.GetJSON();
        System.out.println("db probably has been made");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_Users);

        onCreate(db);
    }

    public void addUser(List<Users> usersList) {
    for (int i=0; i<usersList.size(); i++){
        String query = "INSERT INTO 'User' VALUES(" + DatabaseUtils.sqlEscapeString(usersList.get(i).ID) + "," + DatabaseUtils.sqlEscapeString(usersList.get(i).ImageID) + "," + DatabaseUtils.sqlEscapeString(usersList.get(i).Title) + "," + DatabaseUtils.sqlEscapeString(usersList.get(i).UserID) + "," + DatabaseUtils.sqlEscapeString(usersList.get(i).UserName) + ");";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);

    }
    }
}
