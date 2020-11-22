package com.example.azorafinaltest.ui.LocalData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.azorafinaltest.ui.Items.Constants;
import com.example.azorafinaltest.ui.Model.UserItems;

import java.util.ArrayList;

import static com.example.azorafinaltest.ui.Items.Constants.DATABASE_TABLE_NAME;
import static com.example.azorafinaltest.ui.Items.Constants.DATABASE_TABLE_NAME_IMAGES;
import static com.example.azorafinaltest.ui.Items.Constants.Image;
import static com.example.azorafinaltest.ui.Items.Constants.UID;
import static com.example.azorafinaltest.ui.Items.Constants.User;


public class DBHelper extends SQLiteOpenHelper {

    private Context context;


    public DBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_USERS);
        db.execSQL(Constants.CREATE_TABLE_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TABLE_USERS);
        db.execSQL(Constants.DROP_TABLE_IMAGES);

        onCreate(db);
    }
    public boolean insert(UserItems users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User, users.getUser());
        return db.insertWithOnConflict(Constants.DATABASE_TABLE_NAME, null, contentValues,SQLiteDatabase.CONFLICT_REPLACE)>0;
    }
    public boolean insertImage(UserItems users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UID, users.getUserId());
        contentValues.put(Image, users.getImage());
        return db.insertWithOnConflict(DATABASE_TABLE_NAME_IMAGES, null, contentValues,SQLiteDatabase.CONFLICT_REPLACE)>0;
    }
    public ArrayList<UserItems> getAll() {
        ArrayList<UserItems> newsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursors = db.rawQuery( "select * from "+DATABASE_TABLE_NAME_IMAGES+" INNER JOIN "+DATABASE_TABLE_NAME+" ON "+DATABASE_TABLE_NAME+"."+Constants.User_ID+" = "+DATABASE_TABLE_NAME_IMAGES+"."+Constants.UID+"", null );
        while (cursors.moveToNext()) {
            int userId = cursors.getInt(cursors.getColumnIndex(Constants.IMAGEID));
            int newsId = cursors.getInt(cursors.getColumnIndex(Constants.UID));
            String uName = cursors.getString(cursors.getColumnIndex(Constants.User));
            String uImage = cursors.getString(cursors.getColumnIndex(Constants.Image));
            UserItems greeting = new UserItems(userId, newsId, uName,uImage);
            newsList.add(greeting);
        }
        return newsList;
    }
}