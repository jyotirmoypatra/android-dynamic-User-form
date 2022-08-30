package com.example.dynamicform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends ArrayAdapter<Person> {
    private static final String TAG="PersonListAdapter";
    private Context mContext;
    int mResource;
    public PersonListAdapter(Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String first = getItem(position).getFirstname();
        String last = getItem(position).getLastname();
        Person p = new Person(first,last);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvFirst = (TextView) convertView.findViewById(R.id.firstNameList);
        TextView tvLast =(TextView)  convertView.findViewById(R.id.lastNameList);

        tvFirst.setText(first);
        tvLast.setText(last);
        return convertView;
    }
}
