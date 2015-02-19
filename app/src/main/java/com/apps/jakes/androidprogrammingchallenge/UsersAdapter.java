package com.apps.jakes.androidprogrammingchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends ArrayAdapter<Users> {

    ArrayList<Users> userlist;
    int Resource;
    LayoutInflater vi;
    ViewHolder holder;

    public UsersAdapter(Context context, int resource, ArrayList<Users> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userlist = objects;
        Resource = resource;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;
        if(v == null){
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder = new ViewHolder();
            holder.tvID = (TextView)v.findViewById(R.id.tvID);
            holder.tvImageID = (TextView)v.findViewById(R.id.tvImageID);
            holder.tvTitle = (TextView)v.findViewById(R.id.tvTitle);
            holder.tvUserID = (TextView)v.findViewById(R.id.tvUserID);
            holder.tvUserName = (TextView)v.findViewById(R.id.tvUserName);

            v.setTag(holder);
        } else {
            holder = (ViewHolder)v.getTag();

        }

        holder.tvID.setText(userlist.get(position).getID());
        holder.tvImageID.setText(userlist.get(position).getImageID());
        holder.tvTitle.setText(userlist.get(position).getTitle());
        holder.tvUserID.setText(userlist.get(position).getUserID());
        holder.tvUserName.setText(userlist.get(position).getUserName());


    return v;

    }

    static class ViewHolder {
        public TextView tvID;
        public TextView tvImageID;
        public TextView tvTitle;
        public TextView tvUserID;
        public TextView tvUserName;
    }
}
