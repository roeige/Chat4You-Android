package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.api.WebServiceAPI;
import com.example.char4you_android.entities.Contact;

public class AddContactActivity extends AppCompatActivity {
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContactsAPI contactsAPI = new ContactsAPI();
        context = getApplicationContext();
        setContentView(R.layout.add_contact);
        TextView contactIdn = findViewById(R.id.contactIdn);
        String contactIdStr = contactIdn.getText().toString();
        Button btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v -> {
            if (contactIdStr.isEmpty()) {
                Toast.makeText(AddContactActivity.this, "Please enter right contact Id", Toast.LENGTH_SHORT).show();
                return;
            }
            contactsAPI.post(contactIdStr);
        });
    }


}