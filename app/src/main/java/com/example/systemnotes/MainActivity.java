package com.example.systemnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.systemnotes.Fragments.Level1.AddUserNote;
import com.example.systemnotes.SQLiteDataBase.DatabaseHelper;
import com.example.systemnotes.SQLiteDataBase.RequestToDB;
import com.example.systemnotes.Fragments.Level1.Notes;
import com.example.systemnotes.Fragments.Level1.Services;
import com.example.systemnotes.Fragments.Level1.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private final String TAG_MainActivity = "MainActivityTAG";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestToDB requestToDB = RequestToDB.getInstance();
        requestToDB.init(getApplicationContext());


         if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerIndetifier, new Notes())
                    .commit();
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonNavigationMenuIdentifier);
        bottomNavigationView.setOnItemSelectedListener(bottonNavigationListener);



    }



    /*
        Listener's classes
     */


   private NavigationBarView.OnItemSelectedListener bottonNavigationListener = new NavigationBarView.OnItemSelectedListener() {
        private String TAG_BottonNavigationListener = "BottonNavigationListener";
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.item_book:
                      openNotes();
                    break;
                case R.id.item_add:
                    openAdd();
                    break;
                case R.id.item_start:
                    openSearch();
                    break;
                case R.id.item_settings:
                    openSettings();
                    break;
            }
            return true;
        }

        private void openNotes(){
            openFragment(new Notes());
        }

        private void openAdd(){
            openFragment(new AddUserNote());
        }

        private void openSearch (){
            openFragment(new Services());
        }

        private void openSettings(){
            openFragment(new Settings());
        }

        private void openFragment (final Fragment fragment){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerIndetifier, fragment)
                    .commit();
        }
    };


}