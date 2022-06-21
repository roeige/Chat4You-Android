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

public class FirebaseAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    public static String token;

    public FirebaseAPI(String Ctoken,String server) {
        token = Ctoken;
        if(server.startsWith("localhost")) server = "10.0.2.2:7019";
        server = "http://"+server;
        retrofit = new Retrofit.Builder()
                .baseUrl(server+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public void post(String firebaseToken) {
        Call<Void> call = webServiceAPI.firebaseOnConnect("Bearer " + token, firebaseToken);
        call.enqueue(new Callback<Void>() {
            Boolean returnVal;

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success22","success post message");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("fail22","fail post message");
            }
        });
    }
}
