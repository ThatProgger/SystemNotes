package com.example.systemnotes.Fragments.Level1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.systemnotes.Fragments.Level4.NewNote;
import com.example.systemnotes.Classes.NoteArticle;
import com.example.systemnotes.SQLiteDataBase.RequestToDB;
import com.example.systemnotes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddUserNote extends Fragment {
    private LinearLayout linearLayout;
    private List<String> noteTextList;
    private List <String> IDs;
    private final String requestSQL = "SELECT * FROM NOTES";
    private RequestToDB requestToDB;
    private List <NoteArticle> noteArticleList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestToDB = RequestToDB.getInstance();
        IDs = requestToDB.getData(requestSQL, 0);
        noteTextList = requestToDB.getData(requestSQL, 2);
        noteArticleList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = view.findViewById(R.id.notesLayout);

        for (int i=0; i!=noteTextList.size(); i++){
            noteArticleList.add(
                    new NoteArticle.Builder()
                    .setFragmentActivity(getActivity())
                    .setNoteText(noteTextList.get(i))
                    .setLinearLayout(linearLayout)
                    .setId(IDs.get(i))
                    .build()
            );
        }




        FloatingActionButton button = view.findViewById(R.id.floatingButtonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerIndetifier, new NewNote())
                        .addToBackStack("AddUserNote")
                        .commit();
            }
        });
    }
}