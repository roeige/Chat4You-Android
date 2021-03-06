package com.example.char4you_android.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.dao.MessageDao;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.entities.Transfer;

import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {
    private final MessageDao dao;
    private final MessagesListData messagesListData;
    private final MessageAPI messagesAPI;
    private final String fromId;
    private final String toId;
    AppDB db;


    public MessagesRepository(Context context, MessageAPI api, String userId, String toId) {
        this.db = AppDB.getInstance(context);
        this.dao = db.messageDao();
        this.messagesListData = new MessagesListData();
        //create instance of Messages API
        this.fromId = userId;
        this.messagesAPI = api;
        this.messagesAPI.get(this, toId);
        this.toId = toId;
        //user of who we chat with right now.

    }

    public void add(String toId, @NonNull Message message,String contactServer,String token) {
        messagesAPI.post(toId, message, this,contactServer,token);
    }

    public void afterPost(String toId, Message message,String contactServer,String token) {
        Transfer transfer = new Transfer(fromId, toId, message.getContent());
        new MessageAPI(token,contactServer).transfer(transfer, message, this);
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
