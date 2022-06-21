package com.example.char4you_android.api;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.example.char4you_android.AddContactActivity;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Invite;
import com.example.char4you_android.repositories.ContactsRepository;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsAPI implements Serializable {
    //    private MutableLiveData<List<Post>> postListData;
//
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    public static String token;
    private static ContactsAPI api;
    private String serverUrl;

    public ContactsAPI(String Ctoken,String server) {
        if(server.startsWith("localhost")) server = "10.0.2.2:7019";
        serverUrl = "http://"+server+"/api/";
        token = Ctoken;
        retrofit = new Retrofit.Builder()
                .baseUrl(this.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public void get(ContactsRepository repository) {
        Call<List<Contact>> call = webServiceAPI.getContacts("Bearer " + token);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                Log.i("success", "success get action");
                repository.handleAPIData(response.code(), response.body());
            }


            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
                Log.i("fail", "fail");
            }
        });
    }

    public void post(Contact contact, ContactsRepository repository) {

        Call<Void> call = webServiceAPI.createContact("Bearer " + token, contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(AddContactActivity.context, "Contact added successfully", Toast.LENGTH_LONG).show();
                repository.postHandle();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                String responseString = "Error : " + t.toString();
                Toast.makeText(AddContactActivity.context, responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inviteContact(Invite invite, ContactsRepository repository, Contact contact) {
        Call<Void> call = webServiceAPI.inviteContact("Bearer " + token, invite);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(AddContactActivity.context, "Contact added you aswell", Toast.LENGTH_LONG).show();
                repository.afterInvite(contact);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                String responseString = "Error : " + t.toString();
                Toast.makeText(AddContactActivity.context, responseString, Toast.LENGTH_LONG).show();
            }
        });


    }

}