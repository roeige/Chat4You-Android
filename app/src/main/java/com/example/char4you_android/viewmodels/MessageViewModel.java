package com.example.char4you_android.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.repositories.MessagesRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {
    private MessagesRepository mRepository;
    private LiveData<List<Message>> messages;

    public MessageViewModel(Context context, MessageAPI api, String fromId, String toId) {
        mRepository = new MessagesRepository(context, api, fromId, toId);
        messages = mRepository.getAll();
    }


    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(String id, Message message) {
        mRepository.add(id, message);
    }


}
