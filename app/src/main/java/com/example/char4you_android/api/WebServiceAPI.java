package com.example.char4you_android.api;

import com.example.char4you_android.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @GET("posts")
    Call<List<Contact>> getContacts();

    @POST("posts")
    Call<Void> createPost(@Body String contactId);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}