package com.example.char4you_android.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.adapters.MessageListAdapter;
import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.dao.MessageDao;
import com.example.char4you_android.entities.Message;

import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {
    private final MessageDao dao;
    private final MessagesListData MessagesListData;
    private final MessageAPI messagesAPI;
    private final MessageListAdapter messageListAdapter;
    private final String id;

    public MessagesRepository(Context context, MessageListAdapter adapter, MessageAPI api, String userId) {
        AppDB db = AppDB.getInstance(context);
        messageListAdapter = adapter;
        dao = db.messageDao();
        MessagesListData = new MessagesListData();
        //create instance of Messages API
        messagesAPI = api;
        id = userId;

    }

    public void add(Message message) {
        messagesAPI.post(messageListAdapter,id,message);
    }

    class MessagesListData extends MutableLiveData<List<Message>> {
        public MessagesListData() {
            super();
            setValue(new LinkedList<Message>());
        }

        class PrimeThread extends Thread {
            public void run() {
                MessagesListData.postValue(dao.index());
            }
        }

        PrimeThread t = new PrimeThread();

        @Override
        protected void onActive() {
            super.onActive();
            t.start();
            try {
                doInBackground();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //while we load Messages from local DB, we request new data from API.
        protected void doInBackground() throws InterruptedException {
            t.join();
            messagesAPI.get(messageListAdapter,id);
            MessagesListData.setValue(messageListAdapter.getMessages());
        }
    }

    public LiveData<List<Message>> getAll() {
        return MessagesListData;
    }
}
