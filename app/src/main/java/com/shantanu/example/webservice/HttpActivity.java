package com.shantanu.example.webservice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpActivity extends AppCompatActivity {
    Button btnFetch;
    RecyclerView recyclerView;
    public String url = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";
    private ProgressDialog pDialog;
    private PostAdapter adapter;
    List<Posts> posts;
    ArrayList<Posts> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        btnFetch=(Button)findViewById(R.id.btn_fetch);

        recyclerView=(RecyclerView)findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(HttpActivity.this));
        posts=new ArrayList<>();
        adapter=new PostAdapter((ArrayList<Posts>) posts,HttpActivity.this);
        recyclerView.setAdapter(adapter);



         new FetchEmployeeDetails().execute(url);
    }

    public  void firstfunc(){

    }
    /*
    private List<Posts> getPostData() throws JSONException {
        try{
         String json=new HttpHelper().execute(url).get();
         JSONObject jb1=new JSONObject(json);
            JSONArray ja1=jb1.getJSONArray("posts");
            int length=((JSONArray) ja1).length();
            List<Posts> mypost=new ArrayList<>();
            Posts posts;
            for (int h=0;h<length;h++){
                JSONObject mo=((JSONArray) ja1).getJSONObject(h);
                posts=new Posts();
                posts.setName(mo.getString("name"));
                posts.setMessage(mo.getString("message"));
                posts.setProfileImage(mo.optString("profileImage"));
                mypost.add(posts);
            }
            return mypost;
        }
    }
    */

    private class FetchEmployeeDetails extends AsyncTask<String, String, String> {
        JSONObject response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(HttpActivity.this);
            pDialog.setMessage("Loading Data.. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL("https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return sb.toString();

        }

        @Override
        protected void onPostExecute(final String response) {
            super.onPostExecute(response);

            pDialog.dismiss();
            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();


            if (response != null) {
                try {
                    //posts=parseData(response);
                    //adapter.setDataSet(posts);
                    //recyclerView.setAdapter(adapter);
                    Posts postsobj;
                    JSONObject j = new JSONObject(response);
                    JSONArray JArray = j.getJSONArray("posts");
                     pList = new ArrayList<>();

                    //Populate the EmployeeDetails list from response
                    for (int i = 0; i < JArray.length(); i++) {
                        Posts employeeDetails = new Posts();
                        JSONObject object = JArray.getJSONObject(i);
                        Log.d("array",String.valueOf(i));
                        employeeDetails.setName(object.getString("name"));
                        Toast.makeText(getApplicationContext(),employeeDetails.getName(),Toast.LENGTH_SHORT).show();
                        employeeDetails.setMessage(object.getString("message"));
                        Toast.makeText(getApplicationContext(),"msg added",Toast.LENGTH_SHORT).show();

                        employeeDetails.setProfileImage(object.getString("profileImage"));
                        Toast.makeText(getApplicationContext(),"url added",Toast.LENGTH_SHORT).show();


                        pList.add(employeeDetails);
                    }
                    adapter=new PostAdapter(pList,getApplicationContext());

                    //Create an adapter with the EmployeeDetails List and set it to the LstView
                    adapter.notifyDataSetChanged();
                    //adapter = new PostAdapter((ArrayList<Posts>) pList,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(HttpActivity.this,
                        "Some error occurred while loading data",
                        Toast.LENGTH_LONG).show();

            }
        }

    }


}
