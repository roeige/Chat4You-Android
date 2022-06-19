package com.example.char4you_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.char4you_android.R;
import com.example.char4you_android.Utils.Utils;
import com.example.char4you_android.entities.Message;

import java.util.ArrayList;
import java.util.List;


public class MessageListAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mInflater;
    public final ArrayList<Message> messages;
    private static final int View_left = 1;
    private static final int View_right = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == View_right) {
            View itemView = mInflater.inflate(R.layout.right_message_layout, parent, false);
            return new RightMessageViewHolder(itemView);
        } else if (viewType == View_left) {
            View itemView = mInflater.inflate(R.layout.left_message_layout, parent, false);
            return new LeftMessageViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            switch (holder.getItemViewType()) {
                case View_left:
                    ((LeftMessageViewHolder) holder).bind(current, position);
                    break;
                case View_right:
                    ((RightMessageViewHolder) holder).bind(current, position);
            }
        }
    }

    public void setMessages(List<Message> s) {
        messages.clear();
        messages.addAll(s);
        notifyDataSetChanged();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public int getItemViewType(int positon) {
        Message message = messages.get(positon);
        if (message.isSent()) {
            return View_right;
        }
        return View_left;
    }

    @Override
    public int getItemCount() {
        if (messages != null)
            return messages.size();
        return 0;
    }

    public MessageListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        messages = new ArrayList<>();
    }

    class RightMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView msgText, timeText, dateText;

        private RightMessageViewHolder(View itemView) {
            super(itemView);
            msgText = itemView.findViewById(R.id.msgTextRight);
            timeText = itemView.findViewById(R.id.timeTextRight);
            dateText = itemView.findViewById(R.id.dateTextRight);
        }

        void bind(Message message, int position) {
            msgText.setText(message.getContent());
            timeText.setText(message.getCreated().substring(11, 13) + ':' + message.getCreated().substring(14, 16));
            String date = Utils.getMonth(Integer.parseInt(message.getCreated().substring(5, 7)))
                    + " " +
                    message.getCreated().substring(8, 10);
            if (position == 0) {
                dateText.setText(date);
                return;
            }
            String prevDate = Utils.getMonth(Integer.parseInt(messages.get(position - 1)
                    .getCreated().substring(5, 7)))
                    + " " +
                    messages.get(position - 1).getCreated().substring(8, 10);
            if (date.equals(prevDate))
                dateText.setText("");
            else
                dateText.setText(date);
        }
    }

    class LeftMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView msgText, timeText, dateText;

        private LeftMessageViewHolder(View itemView) {
            super(itemView);
            msgText = itemView.findViewById(R.id.text_gchat_message_other);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            dateText = itemView.findViewById(R.id.text_gchat_date_other);
        }

        void bind(Message message, int position) {
            msgText.setText(message.getContent());
            timeText.setText(message.getCreated().substring(11, 13) + ':' + message.getCreated().substring(14, 16));
            String date = Utils.getMonth(Integer.parseInt(message.getCreated().substring(5, 7)))
                    + " " +
                    message.getCreated().substring(8, 10);
            if (position == 0) {
                dateText.setText(date);
                return;
            }
            String prevDate = Utils.getMonth(Integer.parseInt(messages.get(position - 1)
                    .getCreated().substring(5, 7)))
                    + " " +
                    messages.get(position - 1).getCreated().substring(8, 10);
            if (date.equals(prevDate))
                dateText.setText("");
            else
                dateText.setText(date);
        }
    }


}
