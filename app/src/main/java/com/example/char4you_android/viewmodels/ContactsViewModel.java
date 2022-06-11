package com.example.char4you_android.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.char4you_android.entities.Contact;

import java.util.List;

public class ContactsViewModel extends ViewModel {
    private LiveData<List<Contact>> Contacts;
//    private ContactsRepository mContacts;
//
//    public ContactsViewModel(){
//        mContacts = new ContactsRepository();
//        Contacts = mContacts.getAll();
//    }
//
//    public LiveData<List<Contact>> get() { return Contacts;}
//    public void add(Contact contact) {mContacts.add(contact);}
//    public void reload() {mContacts.reload();}

}
