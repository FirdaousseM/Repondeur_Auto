package com.example.repondeur_auto.Fragments.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.repondeur_auto.R;

import java.util.ArrayList;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder> {
    ArrayList<Contact> contactDataset;
    private Context context;
    public ContactRecyclerAdapter(Context context) {
        this.context = context;
        this.contactDataset = new ArrayList<>();
    }

    // Méthode pour ajouter des éléments à l'adapter
    public void setDataList(ArrayList<Contact> dataList) {
        this.contactDataset.clear();
        this.contactDataset.addAll(dataList);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos){
        Contact contact = contactDataset.get(pos);
        viewHolder.contactName.setText(contact.getName());
        viewHolder.contactNumber.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName, contactNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactNumber = (TextView) itemView.findViewById(R.id.contact_number);
        }
    }

}

