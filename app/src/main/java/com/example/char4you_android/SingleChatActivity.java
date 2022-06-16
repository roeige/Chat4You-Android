package com.example.char4you_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.char4you_android.adapters.MessageListAdapter;
import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.entities.User;
import com.example.char4you_android.viewmodels.MessageViewModel;

import java.io.Serializable;

public class SingleChatActivity extends AppCompatActivity implements Serializable {
    public static Contact currentContact;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshSingleChat);
        swipeRefreshLayout.setEnabled(false);
        ImageView settingsBtn = (ImageView) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(v -> startActivity(new Intent(SingleChatActivity.this, SettingActivity.class)));
        Intent i = getIntent();
        currentContact = (Contact) i.getSerializableExtra("contact");
        TextView ContactNickname = findViewById(R.id.ContactNickname);
        ContactNickname.setText(currentContact.getName());
        user = (User) i.getSerializableExtra("user");
        RecyclerView listMessages = findViewById(R.id.listMessages);
        final MessageListAdapter adapter = new MessageListAdapter(this);
        listMessages.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        listMessages.setLayoutManager(manager);
        MessageAPI messageAPI = new MessageAPI(user.getToken());
        MessageViewModel messageViewModel = new MessageViewModel(this.getApplicationContext(),
        messageAPI, user.getUsername(), currentContact.getId());
        //swipeRefreshLayout.setOnRefreshListener(messageViewModel::reload);
        messageViewModel.get().observe(this, messages -> {

            if(messages.size()>0) adapter.setMessages(messages);
            // messageViewModel.update();
            swipeRefreshLayout.setRefreshing(false);
        });
        Button sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(view -> {
            EditText msgBox = findViewById(R.id.msgBox);
            Message message = new Message(0, msgBox.getText().toString()
                    , null, true, user.getUsername(), currentContact.getId());

            messageViewModel.add(currentContact.getId(), message);
            msgBox.setText(null);
//            messageAPI.get(adapter, currentContact.getId());
        });

    }
}