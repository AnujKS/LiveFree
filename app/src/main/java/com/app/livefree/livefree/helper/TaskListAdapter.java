package com.app.livefree.livefree.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.livefree.livefree.R;
import com.app.livefree.livefree.model.Task;

import java.util.List;

/**
 * Created by anujkumars on 8/29/2015.
 */
public class TaskListAdapter extends BaseAdapter {
    private List<Task> taskList;
    private LayoutInflater inflater;
    private Activity activity;

    public TaskListAdapter(Activity activity, List<Task> taskList) {
        this.taskList = taskList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.listview_tasklist_row_item, null);

        TextView description = (TextView) convertView.findViewById(R.id.textview_taskdescription);
        TextView type = (TextView) convertView.findViewById(R.id.textview_tasktype);

        ImageView statusIcon = (ImageView) convertView.findViewById(R.id.tasklist_work_icon);

        description.setText(String.valueOf(taskList.get(position).getDescription()));
        type.setText(String.valueOf(taskList.get(position).getPriority()));
        return convertView;

    }
}
