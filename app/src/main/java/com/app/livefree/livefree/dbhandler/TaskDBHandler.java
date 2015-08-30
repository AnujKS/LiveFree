package com.app.livefree.livefree.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.app.livefree.livefree.dbhelper.DbHelper;
import com.app.livefree.livefree.dbhelper.DbTableStrings;
import com.app.livefree.livefree.model.Task;
import com.app.livefree.livefree.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anujkumars on 8/30/2015.
 */
public class TaskDBHandler {
    private static DbHelper dbHelper;
    private static SQLiteDatabase db;

    public static void InsertTask(Context context,Task task){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbTableStrings.DESCRIPTION,task.getDescription());
            contentValues.put(DbTableStrings.PRIORITY,task.getPriority());
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();
            db.insert(DbTableStrings.TABLE_NAME_TASK,null,contentValues);
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static List<Task> GetAllTasks(Context context)
    {
        dbHelper = new DbHelper(context.getApplicationContext());
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from " + DbTableStrings.TABLE_NAME_TASK, null);

        int count = c.getCount();

        Task[] tasks = new Task[count];
        List<Task> taskList=new ArrayList<>();

        if (c.getCount() != 0) {
            if(c.getCount() != -1) {
                int i = 0;
                if (c.moveToLast()) {
                    do {
                        tasks[i] = new Task();
                        tasks[i].description = c.getString(c.getColumnIndex(DbTableStrings.DESCRIPTION));
                        tasks[i].priority = c.getString(c.getColumnIndex(DbTableStrings.PRIORITY));
                        taskList.add(tasks[i]);
                        i++;
                    } while (c.moveToPrevious());
                }
                return taskList;
            }
        }
        return null;
    }
}
