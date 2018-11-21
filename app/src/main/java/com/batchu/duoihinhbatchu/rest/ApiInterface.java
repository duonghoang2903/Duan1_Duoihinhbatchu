package com.batchu.duoihinhbatchu.rest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("media/{id}/")
    Call<JsonElement> getImage(@Path(value = "id",encoded = true) int id);

    @GET("media/")
    Call<JsonElement> getAllIdQuest(@Query("per_page") int per_page, @Query("page") int page);
}
