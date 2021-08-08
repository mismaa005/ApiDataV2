package com.example.apidatav2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static final String URL_WS = "http://datamall2.mytransport.sg/ltaodataservice/CarParkAvailabilityv2?$skip=590";
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<Example> mExampleList;
    private RequestQueue mQueue;
    private EditText edit;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        edit = findViewById(R.id.en_data_input);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        parseJSON();
        swipeRefreshLayout = findViewById(R.id.swipe_refresh); // to enable the recycler view to refresh if we pull it down.
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mExampleList.clear();
            mExampleAdapter.notifyDataSetChanged();
            parseJSON();
            mExampleAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });

    }

    // To obtain the data from API
    private void parseJSON() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_WS, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("value");
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject hit = jsonArray.getJSONObject(i);
                            String cpID =  hit.getString("CarParkID");
                            String areea = hit.getString("Area"+"");
                            String develop = hit.getString("Development");
                            String locat = hit.getString("Location");
                            int avail = hit.getInt("AvailableLots");
                            String type = hit.getString("LotType");
                            String agy = hit.getString("Agency");

                            mExampleList.add(new Example(cpID,areea,develop,locat,avail,type,agy));
                        }
                        mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                        mRecyclerView.setAdapter(mExampleAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace)
        {
            public Map<String, String> getHeaders() throws AuthFailureError{
            HashMap<String, String> headers = new HashMap<>();
            headers.put("AccountKey","6nj+aU1FSimPnLVkchMAVQ==");
            headers.put("Content-Type","application/json");
            return headers;

            }
        };
        mQueue.add(request);
    }
}