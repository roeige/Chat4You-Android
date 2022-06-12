package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.char4you_android.adapters.ContactClickListener;
import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity implements Serializable, ContactClickListener {

    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setEnabled(false);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");


        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatScreenActivity.this, SettingActivity.class));
            }
        });
        RecyclerView listContacts = findViewById(R.id.listContacts);
        final ContactListAdapter adapter = new ContactListAdapter(this,this);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

        ContactsAPI contactsAPI = new ContactsAPI(user.getToken());
        contactsAPI.get(adapter);



    }

    @Override
    public void onContactClick(int positon) {
        List<Contact> contactList = ContactListAdapter.getContacts();
        Contact curContact = contactList.get(positon);
        startActivity(new Intent(ChatScreenActivity.this,SingleChatActivity.class)
                .putExtra("contact",curContact));
    }
}