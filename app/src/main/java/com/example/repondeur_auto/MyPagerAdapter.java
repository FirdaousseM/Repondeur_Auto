package com.example.repondeur_auto;

import android.content.ContentResolver;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.repondeur_auto.Fragments.Contacts.FragmentContacts;
import com.example.repondeur_auto.Fragments.Messages.FragmentMessages;
import com.example.repondeur_auto.Fragments.FragmentEnvoi;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ContentResolver contentResolver;
    public MyPagerAdapter(@NonNull FragmentManager fm, ContentResolver contentResolver) {
        super(fm);
        this.contentResolver = contentResolver;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentContacts(this.contentResolver);
            case 1:
                return new FragmentMessages();
            case 2:
                return new FragmentEnvoi();
            default:
                return null;

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Mes contacts";
            case 1 :
                return "Messages programmés";
            case 2 :
                return "Envoi message";
            default:
                return "";

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}





