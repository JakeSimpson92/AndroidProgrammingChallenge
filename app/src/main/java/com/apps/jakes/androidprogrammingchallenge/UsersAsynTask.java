package com.apps.jakes.androidprogrammingchallenge;

import android.app.ProgressDialog;
import android.os.AsyncTask;

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

class UsersAsynTask extends AsyncTask<String, Void, Boolean> {

    ProgressDialog dialog;
    ArrayList<Users> usersList;

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
    }
}