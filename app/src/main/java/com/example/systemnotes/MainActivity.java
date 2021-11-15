package com.example.systemnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.systemnotes.Fragments.Level1.Add;
import com.example.systemnotes.Fragments.Level1.Notes;
import com.example.systemnotes.Fragments.Level1.Search;
import com.example.systemnotes.Fragments.Level1.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private final String TAG_MainActivity = "MainActivityTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
            Log.d(TAG_BottonNavigationListener, "BottonNavagationListener --> started");

            switch (item.getItemId()){
                case R.id.item_book:
                    Log.d(TAG_BottonNavigationListener, "--> openNotes()");
                    openNotes();
                    break;
                case R.id.item_add:
                    Log.d(TAG_BottonNavigationListener, "--> openAdd()");
                    openAdd();
                    break;
                case R.id.item_start:
                    Log.d(TAG_BottonNavigationListener, "--> openSearch()");
                    openSearch();
                    break;
                case R.id.item_settings:
                    Log.d(TAG_BottonNavigationListener, "--> openSettings()");
                    openSettings();
                    break;
            }

            Log.d(TAG_BottonNavigationListener, "BottonNavagationListener --> completed successfully");
            return true;
        }

        private void openNotes(){
            Log.d(TAG_BottonNavigationListener, "--> openFragment()");
            openFragment(new Notes());
        }

        private void openAdd(){
            Log.d(TAG_BottonNavigationListener, "--> openFragment()");
            openFragment(new Add());
        }

        private void openSearch (){
            Log.d(TAG_BottonNavigationListener, "--> openFragment()");
            openFragment(new Search());
        }

        private void openSettings(){
            Log.d(TAG_BottonNavigationListener, "--> openFragment()");
            openFragment(new Settings());
        }

        private void openFragment (final Fragment fragment){
            Log.d(TAG_BottonNavigationListener, "openFragment() -> Started");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerIndetifier, fragment)
                    .commit();

            Log.d(TAG_BottonNavigationListener, "openFragment() --> completed successfully");
        }
    };


}