package com.example.repondeur_auto.Fragments.Messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repondeur_auto.Fragments.Contacts.Contact;
import com.example.repondeur_auto.Fragments.Contacts.ContactRecyclerAdapter;
import com.example.repondeur_auto.R;

import java.util.ArrayList;
import java.util.List;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {
    ArrayList<String> messageList;
    public MessageRecyclerAdapter(ArrayList<String> messageList){
        this.messageList = messageList;
    }

    // Méthode pour ajouter des éléments à l'adapter
    public void setDataList(ArrayList<String> dataList) {
        this.messageList.clear();
        this.messageList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String message = messageList.get(position);
        holder.message.setText(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_content);
        }
    }
}

