package com.andrea.listas;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HelperParser {
    private final String TAG = getClass().getSimpleName();
    public class POI{
        private String mName;
        private Double mRating;
        private Integer mNumberUserRating;
        private String mUrlIcon;
        private String mAddress;
        private Double mLatitude;
        private Double mLongitude;

        public POI(String mName, Double mRating, Integer mNumberUserRating, String mUrlIcon, String mAddress, Double mLatitude, Double mLongitude) {
            this.mName = mName;
            this.mRating = mRating;
            this.mNumberUserRating = mNumberUserRating;
            this.mUrlIcon = mUrlIcon;
            this.mAddress = mAddress;
            this.mLatitude = mLatitude;
            this.mLongitude = mLongitude;
        }

        public String getName() {
            return mName;
        }

        public Double getRating() {
            return mRating;
        }

        public Integer getNumberUserRating() {
            return mNumberUserRating;
        }

        public String getUrlIcon() {
            return mUrlIcon;
        }

        public String getAddress() {
            return mAddress;
        }

        public Double getLatitude() {
            return mLatitude;
        }

        public Double getLongitude() {
            return mLongitude;
        }
    }

    public ArrayList<POI> parsePlaces(String content){
        ArrayList<POI> lplaces = new ArrayList<POI>();

        JSONArray array;
        JSONObject json = null;

        try {
            json = new JSONObject(content);
            array = json.getJSONArray("results");

            for(int i = 0; i < array.length();i++){
                JSONObject node = array.getJSONObject(i);
                POI pnode = parsePlace (node);

                lplaces.add(pnode);
            }
            return lplaces;
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
    }

    private POI parsePlace(JSONObject jsonData){
        String name = "", urlIcon = "", address="", photoReference="";
        Double latitude = 0.0, longitude= 0.0, rating= 0.0;
        Integer numberUserRating = 0;


            try {
                if(jsonData.has("name"))
                    name = jsonData.getString("name");
                if(jsonData.has("icon"))
                    urlIcon = jsonData.getString("icon");
                if(jsonData.has("vicinity"))
                    address = jsonData.getString("vicinity");
                if(jsonData.has("rating"))
                    rating = jsonData.getDouble("rating");
                if(jsonData.has("user_ratings_total"))
                    numberUserRating = jsonData.getInt("user_ratings_total");
                if(jsonData.has("geometry")){
                    JSONObject geo = jsonData.getJSONObject("geometry");
                    if(geo.has("location")){
                        JSONObject loc = geo.getJSONObject("location");
                        if(loc.has("lat"))
                            latitude = loc.getDouble("lat");
                        if(loc.has("lng"))
                            longitude = loc.getDouble("lng");
                    }
                }
                POI nuevoPOI = new POI(name,rating,numberUserRating,urlIcon,address,latitude,longitude);
                return nuevoPOI;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

    }
}
