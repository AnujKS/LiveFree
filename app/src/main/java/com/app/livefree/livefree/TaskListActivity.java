package com.app.livefree.livefree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.app.livefree.livefree.R;
import com.app.livefree.livefree.helper.TaskListAdapter;
import com.app.livefree.livefree.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class TaskListActivity extends AppCompatActivity {

    TaskListAdapter taskListAdapter;
    private List<Task> taskList;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        //list view
        mListView = (ListView) findViewById(R.id.listView_tasklist);
        taskList=new ArrayList<>();

        Task task1=new Task("Take Groceries",1);
        Task task2=new Task("Buy Medicine",3);
        taskList.add(task1);
        taskList.add(task2);

        taskListAdapter = new TaskListAdapter(this, taskList);
        mListView.setAdapter(taskListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasklist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_add)
        {
            Intent intent=new Intent(TaskListActivity.this,AddTaskActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
