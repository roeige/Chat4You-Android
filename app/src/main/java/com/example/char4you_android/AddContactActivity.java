package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
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
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJvcmllbCIsImp0aSI6IjQ3Yzg0NDQ5LTk0OWUtNGY2MS1hMjFkLWExZjFhNTNlNWYzMSIsImlhdCI6IjE2NTQ2Mjg0ODQiLCJ1c2VyaWQiOiJvcmllbCIsImV4cCI6MTY1NDY1MDA4NCwiaXNzIjoiQ2hhdDRZb3UiLCJhdWQiOiJpZGsifQ.7zZM7odlebmIxQKGoYefRxnXi_Pgi3Gaid8OnhO9nTw";
        user = new User("oriel", "111111", "Oriel", token);
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        ContactsAPI contactsAPI = new ContactsAPI(token);
        setContentView(R.layout.add_contact);
        contactsAPI.get();
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
}