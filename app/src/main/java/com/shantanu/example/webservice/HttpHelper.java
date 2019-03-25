package com.shantanu.example.webservice;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper extends AsyncTask<String,String,String> {
    private HttpURLConnection connection;


    @Override
    protected String doInBackground(String... strings) {
        StringBuilder sb=new StringBuilder();
        try{
            URL url=new URL(strings[0]);
            connection=(HttpURLConnection)url.openConnection();
            InputStream in=new BufferedInputStream(connection.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return sb.toString();
    }
}
