package com.example.vollyframework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

public class MainActivity extends AppCompatActivity {

    //1 - constatnt feild
    private static final String URL = "https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2 string request in volly library
        // 1st parameter - URL 2nd-response  3rd - error]

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CODE",response);
                //4 To make gson object we have a class gson builder
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                //5 gsons method to parse the response
                User[] users =gson.fromJson(response,User[].class);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(MainActivity.this, "Somthing went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //3 we have to make a queue and put the request in the queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
}