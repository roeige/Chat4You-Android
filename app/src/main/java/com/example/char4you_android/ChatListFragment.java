//package com.example.char4you_android;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.char4you_android.R;
//import com.example.char4you_android.adapters.ContactListAdapter;
//import com.example.char4you_android.api.ContactsAPI;
//
//public class ChatListFragment extends Fragment {
//    public ChatListFragment() {
//
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.contactslist_layout, container, false);
//        setupRecyclerView(rootView);
//        return rootView;
//    }
////
////    private void setupRecyclerView(View rootView) {
////        RecyclerView listContacts = rootView.findViewById(R.id.listContacts);
////        final ContactListAdapter adapter = new ContactListAdapter(getContext(), );
////        listContacts.setAdapter(adapter);
////        LinearLayoutManager manager = new LinearLayoutManager(this);
////        manager.setReverseLayout(true);
////        listContacts.setLayoutManager(manager);
////
////        ContactsAPI contactsAPI = new ContactsAPI(user.getToken());
////        contactsAPI.get(adapter);
////
////
////    }
//
//}
