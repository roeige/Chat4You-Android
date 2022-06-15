package com.example.char4you_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.adapters.ContactClickListener;
import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.User;

import java.io.Serializable;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity implements Serializable, ContactClickListener {

    public static User user;
    public static AppDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setEnabled(false);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB")
                .allowMainThreadQueries().build();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatScreenActivity.this, SettingActivity.class).putExtra("roomDB", db));
            }
        });
        RecyclerView listContacts = findViewById(R.id.listContacts);
        final ContactListAdapter adapter = new ContactListAdapter(this, this);
        listContacts.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        listContacts.setLayoutManager(manager);

        ContactsAPI contactsAPI = new ContactsAPI(user.getToken());
        contactsAPI.get(adapter);

        Button addNewContact = findViewById(R.id.btnAddNewContact);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatScreenActivity.this, AddContactActivity.class).putExtra("user", user));
            }
        });

    }

    @Override
    public void onContactClick(int positon) {
        List<Contact> contactList = ContactListAdapter.getContacts();
        Contact curContact = contactList.get(positon);
        startActivity(new Intent(ChatScreenActivity.this, SingleChatActivity.class)
                .putExtra("contact", curContact).putExtra("user", user).putExtra("roomDB", db));
    }
}
