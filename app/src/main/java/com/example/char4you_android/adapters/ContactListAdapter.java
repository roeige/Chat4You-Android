package com.example.char4you_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.char4you_android.R;
import com.example.char4you_android.entities.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    class ContactViewHolder extends RecyclerView.ViewHolder{
        private final TextView nickname;
        private final TextView date;
        private final TextView lastMessageContent;
        private final ImageView contactPic;

        private ContactViewHolder(View itemView){
            super(itemView);
            nickname = itemView.findViewById(R.id.nick);
            date = itemView.findViewById(R.id.date);
            lastMessageContent = itemView.findViewById(R.id.lastMessageContent);
            contactPic = itemView.findViewById(R.id.picture);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.contact_layout,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
          //  holder.date.setText(current.getLastdate().toString());
            holder.nickname.setText(current.getName());
           // holder.contactPic.setImageResource();
        }
    }

    @Override
    public int getItemCount() {
        if(contacts != null)
            return contacts.size();
        return 0;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> c){
        contacts = c;
        notifyDataSetChanged();
    }
}
