package com.example.systemnotes.Fragments.Level1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.systemnotes.R;

import java.util.List;


public class Notes extends Fragment {
    private String TAG_Notes = "TAG_Notes";
    private List<View> cards;
    private int counter = 0;
    private LinearLayout linearLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutNotes);
        View custom = getLayoutInflater().inflate(R.layout.custom_card_view, null, false);
        View custom2 = getLayoutInflater().inflate(R.layout.custom_card_view, null, false);
        View custom4 = getLayoutInflater().inflate(R.layout.custom_card_view, null, false);
        View custom5 = getLayoutInflater().inflate(R.layout.custom_card_view, null, false);
        View custom6 = getLayoutInflater().inflate(R.layout.custom_card_view, null, false);




        linearLayout.addView(custom);
        linearLayout.addView(custom2);
        linearLayout.addView(custom4);
        linearLayout.addView(custom5);
        linearLayout.addView(custom6);






        super.onViewCreated(view, savedInstanceState);
    }

}