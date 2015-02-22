package com.apps.jakes.androidprogrammingchallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends ArrayAdapter<Users> {

    List<Users> userlist;
    int Resource;
    LayoutInflater vi;
    ViewHolder holder;


    public UsersAdapter(Context context, int resource, List<Users> objects) {
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
            holder.ivImageID = (ImageView)v.findViewById(R.id.ivImageID);
            holder.tvTitle = (TextView)v.findViewById(R.id.tvTitle);
            holder.tvUserID = (TextView)v.findViewById(R.id.tvUserID);
            holder.tvUserName = (TextView)v.findViewById(R.id.tvUserName);

            v.setTag(holder);
        } else {
            holder = (ViewHolder)v.getTag();

        }

        holder.tvID.setText(userlist.get(position).getID());
        holder.ivImageID.setImageResource(R.drawable.ic_launcher);
        new DownloadImageTask(holder.ivImageID).execute("http://challenge.superfling.com/photos/" + userlist.get(position).getImageID());
        holder.tvTitle.setText(userlist.get(position).getTitle());
        holder.tvUserID.setText(userlist.get(position).getUserID());
        holder.tvUserName.setText(userlist.get(position).getUserName());


    return v;

    }

    static class ViewHolder {
        public TextView tvID;
        public ImageView ivImageID;
        public TextView tvTitle;
        public TextView tvUserID;
        public TextView tvUserName;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}

