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
    private List<Task> alertList;
    private LayoutInflater inflater;
    private Activity activity;

    public TaskListAdapter(Activity activity, List<Task> taskList) {
        this.alertList = alertList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alertList.size();
    }

    @Override
    public Object getItem(int position) {
        return alertList.get(position);
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

        TextView name = (TextView) convertView.findViewById(R.id.textview_taskdescription);
        TextView alertmsg = (TextView) convertView.findViewById(R.id.textview_tasktype);

        ImageView statusIcon = (ImageView) convertView.findViewById(R.id.alertlist_alert_icon);
        TextView notifyAgain = (TextView) convertView.findViewById(R.id.alertlist_notify_textview);

        name.setText(String.valueOf(alertList.get(position).getDeviceName()));
        alertmsg.setText(String.valueOf(alertList.get(position).getAlertMessage()));
        statusIcon.setImageResource(R.drawable.icon_warning);
        return convertView;

    }
}
