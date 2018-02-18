package com.freelance.ahmed.tourguideapp.Interfaces;

import com.freelance.ahmed.tourguideapp.Models.Places;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmed on 2/18/2018.
 */

public interface ApiInterface {


    @GET("TourGuideApp/login.php")
    Call<Places> getPlaces(@Query("category") String Key);

}
