package com.example.char4you_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.example.char4you_android.DB.AppDB;
import com.example.char4you_android.RegisterActivity;
import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.dao.ContactsDao;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Invite;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ContactsRepository implements Serializable {
    private final ContactsDao dao;
    private final ContactsListData contactsListData;
    private final ContactsAPI contactsAPI;
    private final String userId;
    private final AppDB db;
    private String currentServer;

    public ContactsRepository(Context context, ContactsAPI api, String userId,String currentServer) {
        this.db = AppDB.getInstance(context);
        this.dao = db.contactsDao();
        this.contactsListData = new ContactsListData();
        //create instance of contacts API
        this.contactsAPI = api;
        this.contactsAPI.get(this);
        this.userId = userId;
        this.currentServer = currentServer;
    }

    public void add(Contact contact,String token) {
        //We want to check if invite first succeeded.
        new ContactsAPI(token,contact.getServer()).inviteContact(new Invite(contact.getOwnerId(), contact.getId(), currentServer), this, contact);
    }

    public void afterInvite(Contact contact) {
        contact.setOwnerId(userId);
        contactsAPI.post(contact, this);
    }

    public void postHandle() {
        contactsAPI.get(this);
    }


    class ContactsListData extends MutableLiveData<List<Contact>> implements Serializable{
        public ContactsListData() {
            super();
            setValue(new LinkedList<Contact>());
        }

        class PrimeThread extends Thread {
            public void run() {
                List<Contact> a = dao.index(userId);
                List<Contact> allList = dao.getAll();
                contactsListData.postValue(dao.index(userId));
            }
        }


        @Override
        protected void onActive() {
            super.onActive();
            PrimeThread t = new PrimeThread();
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void handleAPIData(int status, List<Contact> contacts) {
        if (status == 200) {
            this.contactsListData.setValue(contacts);
            new Thread(() -> {
                this.dao.deleteAll(this.userId);
                this.dao.insertAll(updateContactsFields(contacts));
            }).start();
        }
    }

    private List<Contact> updateContactsFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setOwnerId(userId);
        }
        return contacts;
    }

    public void refresh() {
        this.contactsAPI.get(this);
    }

    public LiveData<List<Contact>> getAll() {
        return contactsListData;
    }
}
