package com.androiddev.volleyrestapiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;

    private LinearLayoutCompat LLayout;
    private Button button;
    private RecyclerView recyclerView;
    private String url = "https://jsonplaceholder.typicode.com/posts";
    PostModel[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        context = getApplicationContext();
        activity = MainActivity.this;

        // Get the widget reference from XML layout
        LLayout = findViewById(R.id.coordinator_layout);
        button = findViewById(R.id.btn_do);
        recyclerView = findViewById(R.id.rv_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        // Set a click listener for button widget
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize a new StringRequest
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with response string
                                GsonBuilder builder = new GsonBuilder();
                                Gson gson = builder.create();
                                data = gson.fromJson(response,PostModel[].class);
                                MyAdapter adapter = new MyAdapter(data);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something when get error
                                Snackbar.make(LLayout,"Error...",Snackbar.LENGTH_LONG).show();
                            }
                        }
                );

                // Add StringRequest to the RequestQueue
                MySingleton.getInstance(context).addToRequestQueue(stringRequest);
            }
        });
    }
}
