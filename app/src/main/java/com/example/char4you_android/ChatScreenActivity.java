package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatScreenActivity.this, SettingActivity.class));
            }
        });

        RecyclerView listContacts = findViewById(R.id.listContacts);
        final ContactListAdapter adapter = new ContactListAdapter(this);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

        //hard coded contacts
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("1","Amit","1111"));
        contacts.add(new Contact("2","Oriel","1111"));
        contacts.add(new Contact("3","Roei","1111"));
        contacts.add(new Contact("4","Hen","1111"));
        contacts.add(new Contact("5","Noam","1111"));
        contacts.add(new Contact("6","Noa","1111"));
        contacts.add(new Contact("7","Hadar","1111"));
        contacts.add(new Contact("8","Daniel","1111"));
        contacts.add(new Contact("9","Tom","1111"));
        contacts.add(new Contact("10","Dani","1111"));
        contacts.add(new Contact("11","Yossi","1111"));
        contacts.add(new Contact("12","Noam","1111"));



        adapter.setContacts(contacts);

    }
}