package com.andrea.listas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class GooglePlaces extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<HelperParser.POI> mPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        doGetPetition();
    }

    private void doGetPetition(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://pastebin.com/raw/Yagt2bwv";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        HelperParser myparser = new HelperParser();
                        mPlaces = myparser.parsePlaces(response);
                        for(int i = 0; i<mPlaces.size();i++){
                            Log.d(TAG, mPlaces.get(i).getName());
                            Log.d(TAG, mPlaces.get(i).getAddress());
                            Log.d(TAG, mPlaces.get(i).getUrlIcon());
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.getMessage());
                }
        });

        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }
}
