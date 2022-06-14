package com.example.char4you_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Invite;
import com.example.char4you_android.entities.User;

public class AddContactActivity extends AppCompatActivity {

    public static Context context;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJpbmJhciIsImp0aSI6IjkxZmE0ODJkLWMzMDUtNDI4MC05Mjk0LWZlYTU0MTYwMTcyYiIsImlhdCI6IjE2NTQ2ODE2NzEiLCJ1c2VyaWQiOiJpbmJhciIsImV4cCI6MTY1NDcwMzI3MSwiaXNzIjoiQ2hhdDRZb3UiLCJhdWQiOiJpZGsifQ.ST3gOGD5zVNlfQzyogTEoRV-k7xYivyhxi4YvSpxBnQ";
        user = new User("inbar", "Inbar", token);
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        // calling the action bar
        // ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
//        assert actionBar != null;
//        actionBar.setDisplayHomeAsUpEnabled(true);
        ContactsAPI contactsAPI = new ContactsAPI(user.getToken());
        setContentView(R.layout.add_contact);
        ImageView imgFavorite = (ImageView) findViewById(R.id.settings_button);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddContactActivity.this, SettingActivity.class));
            }
        });
        Button btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v -> {
            /*
              Construct the contact
             */
            TextView contactIdn = findViewById(R.id.contactIdn);
            TextView serverAddress = findViewById(R.id.serverAddress);
            TextView nickName = findViewById(R.id.nickName);
            String contactIdStr = contactIdn.getText().toString();
            String serverAdrsStr = serverAddress.getText().toString();
            String nickNameStr = nickName.getText().toString();
            Contact contact = new Contact(contactIdStr, nickNameStr, serverAdrsStr);
            if (contactIdStr.isEmpty()) {
                Toast.makeText(AddContactActivity.this, "Please enter right contact Id", Toast.LENGTH_SHORT).show();
                return;
            }
            contactsAPI.post(contact);
            contactsAPI.inviteContact(new Invite(user.getUsername(), contactIdStr, serverAdrsStr));
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}