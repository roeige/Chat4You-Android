package com.example.char4you_android.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.repositories.ContactsRepository;

import java.io.Serializable;
import java.util.List;

public class ContactsViewModel extends ViewModel implements Serializable {
    private final LiveData<List<Contact>> Contacts;
    private final ContactsRepository repository;
    private final String userId;

    public ContactsViewModel(Context context, ContactsAPI api, String userId,String server) {
        this.repository = new ContactsRepository(context, api, userId,server);
        this.Contacts = this.repository.getAll();
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void refresh() {
        this.repository.refresh();
    }

    public LiveData<List<Contact>> get() {
        return Contacts;
    }

    public void add(Contact contact,String token) {
        this.repository.add(contact,token);
    }

    public ContactsRepository getRepository() {
        return repository;
    }
}
