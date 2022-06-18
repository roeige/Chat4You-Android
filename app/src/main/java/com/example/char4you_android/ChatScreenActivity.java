package com.example.char4you_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.adapters.ContactClickListener;
import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.api.FirebaseAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.User;
import com.example.char4you_android.viewmodels.ContactsViewModel;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.Serializable;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity implements Serializable, ContactClickListener {

    public static User user;
    public static AppDB db;
    public static ContactsViewModel cViewModel;
    ImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.chat4u.onMessageReceived");
        ChatScreenActivity.MyBroadcastReceiver receiver = new ChatScreenActivity.MyBroadcastReceiver();
        registerReceiver(receiver, intentFilter);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setEnabled(false);
        Intent i = getIntent();

        user = (User) i.getSerializableExtra("user");
        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);
        profilePicture = (ImageView) findViewById(R.id.picture);
        getPicture(user.getUsername());
        RecyclerView listContacts = findViewById(R.id.listContacts);
        final ContactListAdapter adapter = new ContactListAdapter(this, this);
        listContacts.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        listContacts.setLayoutManager(manager);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String server = preferences.getString("server","http://10.0.2.2:7019");
        ContactsAPI contactsAPI = new ContactsAPI(user.getToken(),server);
        cViewModel = contactViewModelFactory.getViewModel(this.getApplicationContext(), contactsAPI, user.getUsername());
        Button addNewContact = findViewById(R.id.btnAddNewContact);
        cViewModel.get().observe(this, contacts -> {
            if (contacts.size() > 0) adapter.setContacts(contacts);
            swipeRefreshLayout.setRefreshing(false);
        });
        settingsBtn.setOnClickListener(v ->
                startActivity(new Intent(ChatScreenActivity.this, SettingActivity.class)));
        addNewContact.setOnClickListener(view ->
                startActivity(new Intent(ChatScreenActivity.this, AddContactActivity.class).
                        putExtra("user", user)));

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatScreenActivity.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            FirebaseAPI firebaseAPI = new FirebaseAPI(user.getToken(),server);
            firebaseAPI.post(newToken);
        });

    }

    @Override
    public void onContactClick(int positon) {
        List<Contact> contactList = ContactListAdapter.getContacts();
        Contact curContact = contactList.get(positon);
        startActivity(new Intent(ChatScreenActivity.this, SingleChatActivity.class)
                .putExtra("contact", curContact).putExtra("user", user));
    }

    private void getPicture(String username) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String encodedPhoto = preferences.getString(username, "");
        if(encodedPhoto==""){
            Uri imgUri= Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "chatlogo");
            profilePicture.setImageURI(null);
            profilePicture.setImageURI(imgUri);
            return;
        }
        byte[] decodedByte = Base64.decode(encodedPhoto, 0);
        Bitmap bitmap =  BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
        profilePicture.setImageBitmap(bitmap);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //need to update contacts
            cViewModel.refresh();
        }
    }
}