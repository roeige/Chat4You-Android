package com.example.char4you_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.User;
import com.example.char4you_android.viewmodels.ContactsViewModel;

public class AddContactActivity extends AppCompatActivity {

    public static Context context;
    public static User user;
    public static ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        Intent i = getIntent();
        if (user == null) {
            user = (User) i.getSerializableExtra("user");
        }
        //pass view model with the intent.
//        if (contactsViewModel == null) {
//            contactsViewModel = (ContactsViewModel) i.getSerializableExtra("contactsViewModel");
//        }
        ContactsAPI contactsAPI = new ContactsAPI(user.getToken());
        contactsViewModel = contactViewModelFactory.getViewModel(this.getApplicationContext(), contactsAPI, user.getUsername());
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
            Contact contact = new Contact(contactIdStr, user.getUsername(), nickNameStr, serverAdrsStr);
            if (contactIdStr.isEmpty()) {
                Toast.makeText(AddContactActivity.this, "Please enter right contact Id", Toast.LENGTH_SHORT).show();
                return;
            }
            /**
             * NEED TO POST HERE with repository
             */
            contactsViewModel.add(contact);
            startActivity(new Intent(AddContactActivity.this, ChatScreenActivity.class).putExtra("user", user));
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