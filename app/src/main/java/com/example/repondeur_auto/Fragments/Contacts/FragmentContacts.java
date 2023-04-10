package com.example.repondeur_auto.Fragments.Contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repondeur_auto.Fragments.Contacts.Contact;
import com.example.repondeur_auto.Fragments.Contacts.ContactRecyclerAdapter;
import com.example.repondeur_auto.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment {

    private ContentResolver contentResolver;
    public FragmentContacts(ContentResolver contResolver){
        this.contentResolver = contResolver;
    }

    ContactRecyclerAdapter adapter;
    private RecyclerView recyclerView;


    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        // Définir les colonnes que vous voulez récupérer pour chaque contact
        String[] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };

        // Récupérer tous les contacts
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Récupérer les données de chaque contact
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                boolean hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0;

                // Si le contact a un numéro de téléphone, récupérer les numéros de téléphone
                if (hasPhoneNumber) {
                    List<String> phoneNumbers = new ArrayList<>();
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor != null && phoneCursor.getCount() > 0) {
                        while (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phoneNumbers.add(phoneNumber);
                        }
                    }
                    if (phoneCursor != null) {
                        phoneCursor.close();
                    }
                    // Ajouter le contact à la liste des contacts
                    Contact contact = new Contact(name, phoneNumbers.get(0));
                    contacts.add(contact);
                }
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return contacts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Récupérer la RecyclerView depuis le layout
        recyclerView = view.findViewById(R.id.recycler_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Créer l'adapter avec une liste vide pour le moment
        adapter = new ContactRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        // Récupérer les contacts et mettre à jour l'adapter
        ArrayList<Contact> contacts = getContacts();
        adapter.setDataList(contacts);

        return view;
    }



}
