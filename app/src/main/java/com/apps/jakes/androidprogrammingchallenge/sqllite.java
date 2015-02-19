package com.apps.jakes.androidprogrammingchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Jakes on 19/02/2015.
 */
public class sqllite extends SQLiteOpenHelper {

    private static final int DB_V = 1;
    private static final String DB_NAME = "Users";
    private static final String TB_Users = "User";
    private static final String KEY_ID = "ID";
    private static final String KEY_ImageID = "ImageID";
    private static final String KEY_Title = "Title";
    private static final String KEY_UserID = "UserID";
    private static final String KEY_UserName = "UserName";

    public sqllite(Context context) {
        super(context, DB_NAME, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE" + TB_Users + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                                    + KEY_ImageID + " INTEGER," + KEY_Title + " TEXT," + KEY_UserID +
                                    " INTEGER," + KEY_UserName + " TEXT," + ")";
        db.execSQL(CREATE_USERS_TABLE);
        System.out.println("db probably has been made");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TB_Users);

    onCreate(db);
    }

    public void addUser(Users user) {
    SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getID());
        values.put(KEY_ImageID, user.getImageID());
        values.put(KEY_Title, user.getTitle());
        values.put(KEY_UserID, user.getUserID());
        values.put(KEY_UserName, user.getUserName());

        db.insert(TB_Users, null, values);
        db.close();
    }
}
