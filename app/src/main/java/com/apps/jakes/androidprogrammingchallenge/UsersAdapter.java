package com.apps.jakes.androidprogrammingchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends ArrayAdapter<Users> {

    ArrayList<Users> ArrylistUsers;
    int Resource;
    Context context;
    LayoutInflater vi;

    public UsersAdapter(Context context, int resource, ArrayList<Users> objects) {
        super(context, resource, objects);

        ArrylistUsers = objects;
        Resource = resource;
        this.context = context;

        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = vi.inflate(Resource, null);
            holder = new ViewHolder();
            holder.tvID = (TextView)convertView.findViewById(R.id.tvID);
            holder.tvImageID = (TextView)convertView.findViewById(R.id.tvImageID);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            holder.tvUserID = (TextView)convertView.findViewById(R.id.tvUserID);
            holder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();

        }




    return convertView;

    }

    static class ViewHolder {
        public TextView tvID;
        public TextView tvImageID;
        public TextView tvTitle;
        public TextView tvUserID;
        public TextView tvUserName;
    }
}
