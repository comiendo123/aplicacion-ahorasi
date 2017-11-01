package com.grupo.inmueblesapp.RestApi;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class RestApi extends AsyncTask<String, String, String> {
    private OnCompleteLoadRest event;
    public void setOnCompleteLoadRest(OnCompleteLoadRest event){
        this.event = event;
    }
    @Override
    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... strings) {
        String finalResult = "";
        HttpURLConnection connection = null;
           try{
            URL service = new URL("https:swapi.co/api/people/?format=json");
            connection =(HttpURLConnection)service.openConnection();
            connection.setRequestMethod("GET");
            InputStream result = connection.getInputStream();
            finalResult = new Scanner(result, "UTF-8").useDelimiter("\\A").next();
            connection.disconnect();
           }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }
    @Override
    protected void onPostExecute(String result){
        try {
            JSONObject obj = new JSONObject(result);
            this.event.completeLoadRest(obj);
        }catch (JSONException e){
            e.printStackTrace();
        }
              }
}
