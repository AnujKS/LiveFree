package com.app.livefree.livefree.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.app.livefree.livefree.dbhelper.DbHelper;
import com.app.livefree.livefree.dbhelper.DbTableStrings;
import com.app.livefree.livefree.model.User;

public class UserModelDBHandler {


    private static DbHelper dbHelper;
    private static SQLiteDatabase db;

    public static void InsertProfile(Context context,User userModel){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbTableStrings.NAME,userModel.name);
            contentValues.put(DbTableStrings.PHONENUMBER,userModel.phone);
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();
            db.insert(DbTableStrings.TABLE_NAME_USER_MODEL,null,contentValues);
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean CheckIfUserDataExists(Context context){

        try {
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();

            User contact = new User();

            Cursor c = db.rawQuery("Select * from " + DbTableStrings.TABLE_NAME_USER_MODEL, null);
            if (c.moveToFirst()) {
                return true;
            }
            else{
                return false;
            }

        }
        catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static User ReturnValue(Context context){

        try {
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();

            User userModel = new User();

            Cursor c = db.rawQuery("Select * from " + DbTableStrings.TABLE_NAME_USER_MODEL , null);
            if (c.moveToFirst()) {
                userModel.name = c.getString(c.getColumnIndex(DbTableStrings.NAME));
                userModel.phone = c.getString(c.getColumnIndex(DbTableStrings.PHONENUMBER));
                return  userModel;
            }
            else{
                return null;
            }

        }
        catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static void UpdateUserData(Context context, User userModel){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbTableStrings.NAME,userModel.name);
            contentValues.put(DbTableStrings.PHONENUMBER,userModel.phone);

            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();
           // db.update(DbTableStrings.TABLE_NAME_USER_MODEL, contentValues, DbTableStrings. + " =" + userModel.ServerUserId, null);
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

