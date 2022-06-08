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
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJpbmJhciIsImp0aSI6ImY0ZGE3NWMwLWIyMzMtNDgwNC05ZGZiLTcxNGU1MTliY2QyMiIsImlhdCI6IjE2NTQ2MzI2NTkiLCJ1c2VyaWQiOiJpbmJhciIsImV4cCI6MTY1NDY1NDI1OSwiaXNzIjoiQ2hhdDRZb3UiLCJhdWQiOiJpZGsifQ.F7hC3uObLvU4p1nq6Ghs_AZAzQVGTN8vyoSByF0nTg0";
        user = new User("inbar", "Inbar", token);
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