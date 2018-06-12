package com.example.nugas.clientapp.network;

import com.example.nugas.clientapp.model.ChatMessage;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("get_bot")
    Call<JsonObject> getMessage(@Field("message") String msg);

}
