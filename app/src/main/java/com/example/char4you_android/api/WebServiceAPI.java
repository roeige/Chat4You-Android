package com.example.char4you_android.api;

import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Invite;
import com.example.char4you_android.entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @GET("contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String token);

    @POST("contacts")
    Call<Void> createContact(@Header("Authorization") String token, @Body Contact contactId);

    @POST("invitations")
    Call<Void> inviteContact(@Header("Authorization")String token,@Body Invite invite);

    @DELETE("contacts/{id}")
    Call<Void> deletePost(@Header("Authorization") String token, @Path("id") String id);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("id") String id);
}