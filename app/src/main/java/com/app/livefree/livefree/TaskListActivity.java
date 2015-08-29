package com.app.livefree.livefree;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.app.livefree.livefree.R;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class TaskListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasklist, menu);
        return true;
    }


}
