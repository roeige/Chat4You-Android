package com.example.char4you_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.char4you_android.R;
import com.example.char4you_android.entities.Message;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mInflater;
    private List<Message> messages;
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.right_message_layout,parent,false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(messages != null){
            final Message current = messages.get(position);
            holder.msgText.setText(current.getContent());
        }

    }

    public void setMessages(List<Message> s){
        messages = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(messages != null)
            return  messages.size();
        return 0;
    }

    public MessageListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    class RightMessageViewHolder extends RecyclerView.ViewHolder{
        private final TextView msgText,timeText,dateText;

        private RightMessageViewHolder(View itemView){
            super(itemView);
            msgText = itemView.findViewById(R.id.msgTextRight);
            timeText = itemView.findViewById(R.id.timeTextRight);
            dateText = itemView.findViewById(R.id.dateTextRight);
        }

        void bind(Message message){
            msgText.setText(message.getContent());
//            timeText.setText();
//            dateText.setText();
        }
    }
    class LeftMessageViewHolder extends RecyclerView.ViewHolder{
        private final TextView msgText,timeText,dateText;

        private LeftMessageViewHolder(View itemView){
            super(itemView);
            msgText = itemView.findViewById(R.id.text_gchat_message_other);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            dateText = itemView.findViewById(R.id.text_gchat_date_other);
        }
    }


}
