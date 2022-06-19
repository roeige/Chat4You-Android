package com.example.char4you_android.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.api.WebServiceAPI;
import com.example.char4you_android.dao.MessageDao;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.entities.Transfer;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesRepository {
    private final MessageDao dao;
    private final MessagesListData messagesListData;
    private final MessageAPI messagesAPI;
    private final String fromId;
    private final String toId;
    private final String contactServer;
    AppDB db;


    public MessagesRepository(Context context, MessageAPI api, String userId, String toId, String contactServer) {
        this.db = AppDB.getInstance(context);
        this.dao = db.messageDao();
        this.messagesListData = new MessagesListData();
        //create instance of Messages API
        this.fromId = userId;
        this.messagesAPI = api;
        this.messagesAPI.get(this, toId);
        this.toId = toId;
        this.contactServer = contactServer;
        //user of who we chat with right now.

    }

    public void add(String toId, @NonNull Message message) {
        // we first want to check if transfer succeeded. if so we activate post action.
        Transfer transfer = new Transfer(fromId, toId, message.getContent());
        WebServiceAPI webServiceAPI;
        if (messagesAPI.getServer().equals(this.contactServer)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(this.contactServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webServiceAPI = retrofit.create(WebServiceAPI.class);
        } else {
            webServiceAPI = null;
        }
        messagesAPI.transfer(transfer, message, this,webServiceAPI);
    }

    public void afterTransfer(Transfer transfer, Message message) {
        messagesAPI.post(transfer.getTo(), message, this);
    }

    /**
     * On new message sent
     *
     * @param message
     */
    public void postHandle(Message message) {
        messagesAPI.get(this, toId);
    }

    class MessagesListData extends MutableLiveData<List<Message>> {
        public MessagesListData() {
            super();
            setValue(new LinkedList<Message>());
        }

        class PrimeThread extends Thread {
            public void run() {
                messagesListData.postValue(dao.index(fromId, toId));
            }
        }

        PrimeThread t = new PrimeThread();

        @Override
        protected void onActive() {
            super.onActive();
            t.start();
            try {
                t.join();
                doInBackground();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //while we load Messages from local DB, we request new data from API.
        protected void doInBackground() throws InterruptedException {
            new Thread(() -> {
                //               dao.insertAll(messagesListData.getValue());
            }).start();

        }
    }

    public LiveData<List<Message>> getAll() {
        return messagesListData;
    }

    public void handleAPIData(int status, List<Message> messages) {
        if (status == 200) {
            messagesListData.setValue(messages);
            new Thread(() -> {
                dao.deleteAll(fromId, toId);
                dao.insertAll(updateMessagesFields(messages));
            }).start();
        }
    }

    private List<Message> updateMessagesFields(List<Message> messages) {
        for (Message message : messages) {
            message.setFromId(message.isSent() ? fromId : toId);
            message.setToId(message.isSent() ? toId : fromId);
        }
        return messages;
    }


    public void refresh() {
        this.messagesAPI.get(this, toId);
    }
}
