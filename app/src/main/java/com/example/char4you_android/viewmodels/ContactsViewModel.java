package com.example.char4you_android.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.repositories.ContactsRepository;

import java.io.Serializable;
import java.util.List;

public class ContactsViewModel extends ViewModel implements Serializable {
    private final LiveData<List<Contact>> Contacts;
    private final ContactsRepository repository;

    public ContactsViewModel(Context context, ContactListAdapter adapter, ContactsAPI api) {
        repository = new ContactsRepository(context, adapter, api);
        Contacts = repository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return Contacts;
    }

    public boolean add(Contact contact) {
        return repository.add(contact);
    }

    public void delete(Contact contact) {
        repository.delete(contact);
    }

    public void reload() {
        repository.reload();
    }

}
