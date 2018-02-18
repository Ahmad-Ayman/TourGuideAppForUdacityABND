package com.freelance.ahmed.tourguideapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/18/2018.
 */

public class Places {
    @SerializedName("data")
    @Expose
    private List<PlacesData> data;

    public Places() {
        this.data = new ArrayList<>();
    }

    public List<PlacesData> getResults() {
        return data;
    }

    public class PlacesData {
        @SerializedName("Name")
        private String mName;
        @SerializedName("Pic")
        private String mPic;
        @SerializedName("Bio")
        private String mBio;
        @SerializedName("Longi")
        private String mLongi;
        @SerializedName("Lati")
        private String mLati;


        public String getmName() {
            return mName;
        }

        public String getmPic() {
            return mPic;
        }

        public String getmBio() {
            return mBio;
        }

        public String getmLati() {
            return mLati;
        }

        public String getmLongi() {
            return mLongi;
        }
    }
}
