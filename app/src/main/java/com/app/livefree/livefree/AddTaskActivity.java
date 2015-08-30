package com.app.livefree.livefree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;

import com.app.livefree.livefree.R;
import com.app.livefree.livefree.dbhandler.TaskDBHandler;
import com.app.livefree.livefree.model.Task;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class AddTaskActivity extends AppCompatActivity {
    EditText description;
    EditText priority;
    TimePicker time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        description=(EditText)findViewById(R.id.input_description_edittext);
        priority=(EditText)findViewById(R.id.input_prioroty_edittext);
        time=(TimePicker)findViewById(R.id.timePicker);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addtask, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_save)
        {
            Task task =new Task();
            task.setDescription(description.getText().toString());
            task.setPriority(priority.getText().toString());
            task.setTaskTime(time.toString());
            TaskDBHandler.InsertTask(getApplicationContext(), task);
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
