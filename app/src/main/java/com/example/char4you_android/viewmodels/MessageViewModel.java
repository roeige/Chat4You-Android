package com.example.char4you_android.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.char4you_android.adapters.MessageListAdapter;
import com.example.char4you_android.api.MessageAPI;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.repositories.MessagesRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {
    private MessagesRepository mRepository;
    private LiveData<List<Message>> messages;

    public MessageViewModel(Context context, MessageListAdapter adapter, MessageAPI api, String userId){
        mRepository = new MessagesRepository(context,adapter,api,userId);
        messages = mRepository.getAll();
    }

    public LiveData<List<Message>> get() {return messages;}

    public void add(Message message) {mRepository.add(message);}

    public void delete(Message message) {mRepository.delete(message);}

    public void reload(){mRepository.reload();}

}
