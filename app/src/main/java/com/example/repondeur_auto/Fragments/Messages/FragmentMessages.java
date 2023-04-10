package com.example.repondeur_auto.Fragments.Messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repondeur_auto.Fragments.Contacts.Contact;
import com.example.repondeur_auto.Fragments.Contacts.ContactRecyclerAdapter;
import com.example.repondeur_auto.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMessages extends Fragment {

    RecyclerView recyclerView;
    private MessageRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View result = inflater.inflate(R.layout.fragment_messages, container, false);


        TextView newMessage = result.findViewById(R.id.newMessage);
        Button addMessage = result.findViewById(R.id.addMessage);

        ArrayList<String> listeMessage = new ArrayList<>();

        listeMessage.add("test1");

        // Récupérer la RecyclerView depuis le layout
        recyclerView = result.findViewById(R.id.recycler_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Créer l'adapter avec une liste vide pour le moment
        adapter = new MessageRecyclerAdapter(listeMessage);
        recyclerView.setAdapter(adapter);

        // Récupérer les contacts et mettre à jour l'adapter
        adapter.setDataList(listeMessage);





        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listeMessage.add(newMessage.getText().toString());
                // Récupérer les contacts et mettre à jour l'adapter
                adapter.setDataList(listeMessage);
            }
        });
        return result;
    }

}


