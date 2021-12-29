package com.example.systemnotes.Fragments.Level1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.systemnotes.Classes.Groups;
import com.example.systemnotes.SQLiteDataBase.RequestToDB;
import com.example.systemnotes.R;

import java.util.ArrayList;
import java.util.List;


public class Notes extends Fragment {
    private String TAG_Notes = "TAG_Notes";
    private List <String> nameGroup;
    private List <String> annotation;
    private RequestToDB requestToDB;
    private LinearLayout linearLayout;
    private List <Groups> mainList;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestToDB = RequestToDB.getInstance();
        mainList = new ArrayList<>();
        nameGroup = requestToDB.getData("SELECT * FROM MAIN", 1);
        annotation = requestToDB.getData("SELECT * FROM MAIN", 2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         linearLayout = view.findViewById(R.id.linearLayoutNotes);

         for (int i = 0; i!= nameGroup.size(); i++){
             mainList.add(
                     new Groups.Builder()
                     .setView(getLayoutInflater().inflate(R.layout.custom_group_layout, null, false))
                     .setId(i)
                     .setNameGroup(nameGroup.get(i))
                     .setAnnotation(annotation.get(i))
                     .setFragmentActivity(getActivity())
                     .build()
             );
         }


         for (Groups g : mainList){
             linearLayout.addView(g.getView());
         }

         super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onStop() {
        Log.d(TAG_Notes, "OnStop");
        super.onStop();
        linearLayout.removeAllViews();
        mainList = new ArrayList<>();
    }
}


