package com.example.char4you_android.api;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.char4you_android.Chat4You;
import com.example.char4you_android.R;
import com.example.char4you_android.entities.Contact;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsAPI {
    //    private MutableLiveData<List<Post>> postListData;
//
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactsAPI() {
//        this.postListData = postListData;
//        this.dao = dao;
        retrofit = new Retrofit.Builder()
                .baseUrl(Chat4You.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                List<Contact> contactList = response.body();
            }


            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {

            }
        });
    }

    public void post(String contactId) {
        Call<Void> call = webServiceAPI.createPost(contactId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(Chat4You.context, "Data added to API", Toast.LENGTH_LONG).show();
                String responseString = "Response Code : " + response.code();
                Toast.makeText(Chat4You.context, responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                String responseString = "Error : " + t.toString();
                Toast.makeText(Chat4You.context, responseString, Toast.LENGTH_LONG).show();

            }
        });

    }

}