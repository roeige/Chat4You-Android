package com.example.char4you_android;

import android.content.Context;

import com.example.char4you_android.api.ContactsAPI;
import com.example.char4you_android.viewmodels.ContactsViewModel;

public class contactViewModelFactory {
    static ContactsViewModel viewModel;


    public static ContactsViewModel getViewModel(Context context, ContactsAPI api, String userId) {
        if (viewModel == null) {
            viewModel = new ContactsViewModel(context, api, userId);
        }
        return viewModel;
    }
}
