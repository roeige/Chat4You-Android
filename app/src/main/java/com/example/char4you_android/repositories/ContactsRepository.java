package com.example.char4you_android.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.adapters.ContactListAdapter;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.dao.ContactsDao;
import com.example.char4you_android.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private final ContactsDao dao;
    private final ContactsListData contactsListData;
    private final ContactsAPI contactsAPI;
    private final ContactListAdapter contactListAdapter;

    public ContactsRepository(Context context, ContactListAdapter adapter, ContactsAPI api) {
        AppDB db = AppDB.getInstance(context);
        contactListAdapter = adapter;
        dao = db.contactsDao();
        contactsListData = new ContactsListData();
        //create instance of contacts API
        contactsAPI = api;

    }

    class ContactsListData extends MutableLiveData<List<Contact>> {
        public ContactsListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        class PrimeThread extends Thread {
            public void run() {
                contactsListData.setValue(dao.index());
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

        //while we load contacts from local DB, we request new data from API.
        protected void doInBackground() throws InterruptedException {
            t.join();
            contactsAPI.get(contactListAdapter);
            contactsListData.setValue(ContactListAdapter.getContacts());
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactsListData;
    }
}
