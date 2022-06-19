package com.example.char4you_android.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.char4you_android.entities.Message;
import com.example.char4you_android.entities.Transfer;
import com.example.char4you_android.repositories.MessagesRepository;

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
    private String server;

    public MessageAPI(String Ctoken, String server) {
        this.server = server;
        token = Ctoken;
        retrofit = new Retrofit.Builder()
                .baseUrl(server + "/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public String getServer() {
        return server;
    }

    public void get(MessagesRepository repository, String id) {
        Call<List<Message>> call = webServiceAPI.getMessages("Bearer " + token, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                repository.handleAPIData(response.code(), response.body());
            }


            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                Log.i("fail", "fail get messages");
            }
        });
    }

    public void transfer(Transfer transfer, Message message, MessagesRepository repository, WebServiceAPI api) {
        Call<Void> call;
        if (api != null) {
            call = api.transfer("Bearer " + token, transfer);
        } else {
            call = webServiceAPI.transfer("Bearer " + token, transfer);
        }
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("success", "success transfer action");
                repository.afterTransfer(transfer, message);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("fail", "fail transfer message");

            }
        });

    }

    public void post(String id, Message message, MessagesRepository repository) {
//        final boolean[] success = {false};
        Call<Void> call = webServiceAPI.createMessage("Bearer " + token, id, message);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.i("success", "success post message");
                repository.postHandle(message);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("fail", "fail post message");
            }
        });
    }
}
