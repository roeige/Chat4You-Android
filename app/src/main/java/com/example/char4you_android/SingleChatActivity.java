package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.User;

import java.io.Serializable;

public class SingleChatActivity extends AppCompatActivity implements Serializable {
    public static Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleChatActivity.this, SettingActivity.class));
            }
        });
        Intent i = getIntent();
        currentContact = (Contact) i.getSerializableExtra("contact");
    }
}