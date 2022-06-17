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

    public ContactsViewModel(Context context, ContactsAPI api, String userId) {
        this.repository = new ContactsRepository(context, api, userId);
        this.Contacts = this.repository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return Contacts;
    }

    public void add(Contact contact) {
        this.repository.add(contact);
    }


}
