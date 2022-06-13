package com.example.char4you_android.api;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.char4you_android.AddContactActivity;
import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.adapters.MessageListAdapter;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    public static String token;

    public MessageAPI(String Ctoken) {
//        this.postListData = postListData;
//        this.dao = dao;
        token = Ctoken;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7019/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public void get(final MessageListAdapter adapter,String id) {
        Call<List<Message>> call = webServiceAPI.getMessages("Bearer " + token,id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                adapter.setMessages(response.body());
            }


            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                Log.i("fail","fail");
            }
        });
    }

    public void post(Contact contact) {
        Call<Void> call = webServiceAPI.createContact("Bearer " + token, contact);
        call.enqueue(new Callback<Void>() {
            Boolean returnVal;

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(AddContactActivity.context, "Data added to API", Toast.LENGTH_LONG).show();
                String responseString = "Response Code : " + response.code();
                Toast.makeText(AddContactActivity.context, responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                String responseString = "Error : " + t.toString();
                Toast.makeText(AddContactActivity.context, responseString, Toast.LENGTH_LONG).show();
            }
        });
    }
}
