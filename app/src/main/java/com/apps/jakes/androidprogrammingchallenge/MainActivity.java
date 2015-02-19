package com.apps.jakes.androidprogrammingchallenge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {


    UsersAdapter adapter;
    ArrayList<Users> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersList = new ArrayList<Users>();
        new UsersAsynTask().execute("http://challenge.superfling.com/");
        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new UsersAdapter(getApplicationContext(), R.layout.row, usersList);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Toast.makeText(getApplicationContext(), usersList.get(position).getID(), Toast.LENGTH_LONG).show();
            }
        });


    }

    class UsersAsynTask extends AsyncTask<String, Void, Boolean>{

        ProgressDialog dialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading");
            dialog.setTitle("connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                HttpGet post = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(post);
                int status = response.getStatusLine().getStatusCode();
                if(status == 200){
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    //System.out.println("Here is the data:" + data);

                    JSONArray jsono = new JSONArray(data);
                    //JSONArray jObj = jsono.getJSONArray("");
                    for(int i=0; i<jsono.length(); i++){

                        JSONObject jRealObject = jsono.getJSONObject(i);
                        Users user = new Users();
                        user.setID(jRealObject.getString("ID"));
                        user.setImageID(jRealObject.getString("ImageID"));
                        user.setTitle(jRealObject.getString("Title"));
                        user.setUserID(jRealObject.getString("UserID"));
                        user.setUserName(jRealObject.getString("UserName"));

                        usersList.add(user);
                    }

                    return true;
                }

            }catch (ClientProtocolException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result){
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(!result){
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
            }

        }
    }

}
