package com.apps.jakes.androidprogrammingchallenge;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView list;
    UsersAdapter adapter;
    ArrayList<Users> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);
        usersList = new ArrayList<Users>();

        new UsersAsynTask().execute("http://challenge.superfling.com/");
    }

    public class UsersAsynTask extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(params[0]);
                HttpResponse response = client.execute(post);
                int status = response.getStatusLine().getStatusCode();
                if(status == 200){
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONArray jObj = new JSONArray(data);
                    for(int i=0; i<jObj.length(); i++){
                        Users user = new Users();
                        JSONObject jRealObject = jObj.getJSONObject(i);

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
            super.onPostExecute(result);
            if(result == false){
                //show msg that data not pasrsed
            }
            UsersAdapter user = new UsersAdapter(getApplicationContext(), R.layout.row, usersList);
            list.setAdapter(adapter);

        }
    }

}
