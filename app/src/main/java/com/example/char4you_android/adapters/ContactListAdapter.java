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
import com.example.char4you_android.Utils.Utils;
import com.example.char4you_android.entities.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView nickname;
        private final TextView date;
        private final TextView time;
        private final TextView lastMessageContent;
        private final ImageView contactPic;

        private ContactViewHolder(View itemView){
            super(itemView);
            nickname = itemView.findViewById(R.id.nick);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            lastMessageContent = itemView.findViewById(R.id.lastMessageContent);
            contactPic = itemView.findViewById(R.id.picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            mOnClickListener.onContactClick(position);
        }
    }

    private final LayoutInflater mInflater;
    private static List<Contact> contacts;
    private final ContactClickListener mOnClickListener;

    public ContactListAdapter(Context context, ContactClickListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.contact_layout,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
            int month = Integer.parseInt(current.getLastdate().substring(5, 7));
            holder.date.setText(Utils.getMonth(month) +" " + current.getLastdate().substring(8,10));
            holder.nickname.setText(current.getName());
            holder.time.setText(current.getLastdate().substring(11,13) + ':' + current.getLastdate().substring(14,16));
            String lastMessage = current.getLast();
            holder.lastMessageContent.setText(lastMessage);
           // holder.contactPic.setImageResource();
        }
    }


    @Override
    public int getItemCount() {
        if(contacts != null)
            return contacts.size();
        return 0;
    }

    public static List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> c){
        contacts = c;
        notifyDataSetChanged();
    }
}
