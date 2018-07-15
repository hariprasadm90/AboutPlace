package com.hari.aboutplaceproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hari.aboutplaceproject.adapter.MyRecyclerViewAdapter;
import com.hari.aboutplaceproject.model.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mRecyclerViewAdapter;
    private List<ListItem> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        //Setting toolbar for Screen title
        setSupportActionBar(mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Setting up layoutmanager for Recyclerview to form List that scrolls vertically
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // Remember to CLEAR OUT old items before appending in the new ones
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void parseDataThroughVolley() {

        //Creating a String request to parse JSON data
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //dismiss
                    ListItem listItem;
                    JSONObject jsonObjectOne = new JSONObject(response);
                    String pageName = jsonObjectOne.getString("title");
                    mToolBar.setTitle(pageName);
                    JSONArray jsonArray = jsonObjectOne.getJSONArray("rows");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectTwo = jsonArray.getJSONObject(i);
                        String title = jsonObjectTwo.getString("title");
                        String description = jsonObjectTwo.getString("description");
                        String imageHref = jsonObjectTwo.getString("imageHref");

                        listItem = new ListItem(title, description, imageHref);
                        mDataList.add(listItem);
                    }

                    mRecyclerViewAdapter = new MyRecyclerViewAdapter(getApplicationContext(), mDataList);
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Response Error is : " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        //Volley request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the stringRequest to the RequestQueue of Volley
        requestQueue.add(stringRequest);

    }
}
