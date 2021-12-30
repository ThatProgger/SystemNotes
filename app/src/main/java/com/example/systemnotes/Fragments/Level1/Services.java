package com.example.systemnotes.Fragments.Level1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.systemnotes.Adapters.CustomListAdapters;
import com.example.systemnotes.Classes.Utils;
import com.example.systemnotes.Classes.WifiInformation;
import com.example.systemnotes.CustomViews.PowerSignalWifi.PowerSignalWiFi;
import com.example.systemnotes.R;
import com.example.systemnotes.Threads.MeasuringWiFiSignalThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class Services extends Fragment {
    private final String TAG_Services = "TAG_Services";

    private List <WifiInformation> list;
    private CustomListAdapters customListAdapters;
    private PowerSignalWiFi powerSignalWiFi;
    private Context _context;
    private Thread workThread;
    private AtomicBoolean atomicBoolean;
    private Runnable runnable;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<WifiInformation>();

        list.add(new WifiInformation.Builder()
                .setType("DEVICE IP4 ADDRESS")
                .setInformation(Utils.getIPAddress(true))
                .setStatus("")
                .build());


        list.add(new WifiInformation.Builder()
                .setType("DEVICE MAC ADDRESS")
                .setInformation(Utils.getMACAddress("wlan0"))
                .setStatus("")
                .build());



        list.add(new WifiInformation.Builder()
                .setType("CURRENT BAND SPEED - GHz")
                .setInformation(String.valueOf(Utils.getCurrentFrequency(_context)))
                .setStatus("")
                .build());


        customListAdapters = new CustomListAdapters(_context, R.layout.list_view_layout, list);
        customListAdapters.setNotifyOnChange(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(customListAdapters);


        powerSignalWiFi = view.findViewById(R.id.powerWiFiSignal);

        atomicBoolean = new AtomicBoolean(true);
        runnable = new MeasuringWiFiSignalThread(powerSignalWiFi, _context, atomicBoolean);
        workThread = new Thread(runnable);

        workThread.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        atomicBoolean.set(false);
    }
}