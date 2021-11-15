package com.example.systemnotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.systemnotes.Classes.GroupName;
import com.example.systemnotes.R;

import java.util.ArrayList;


public class GroupsAdapter extends ArrayAdapter<GroupName> {
    private Context _context;
    private int _resource;

    public GroupsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GroupName> objects) {
        super(context, resource, objects);
        this._context = context;
        this._resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return convertView;
    }
}
