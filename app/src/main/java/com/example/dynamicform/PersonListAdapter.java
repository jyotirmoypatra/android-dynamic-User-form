package com.example.dynamicform;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Person> {
    private Context mContext;
    int mResource;
    ArrayList<Person> objects = null;


    public PersonListAdapter(Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String first = getItem(position).getFirstname();
        String last = getItem(position).getLastname();
        Person p = new Person(first, last);

        // View view = super.getView(position,convertView,parent);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvFirst = (TextView) convertView.findViewById(R.id.firstNameList);
        TextView tvLast = (TextView) convertView.findViewById(R.id.lastNameList);
        Button dltUSer = (Button) convertView.findViewById(R.id.deleteSingleUser);

        dltUSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDb = new DBHelper(getContext());
                String fname = getItem(position).getFirstname();

                boolean isDeleted = myDb.deleteEntry(fname);
                if (isDeleted) {
                    objects.remove(position);

                    notifyDataSetChanged();
                    Toast.makeText(getContext(), "Successfully Deeleted", Toast.LENGTH_LONG).show();
                }



            }
        });

        tvFirst.setText(first);
        tvLast.setText(last);
        if (position % 2 == 1) {

            convertView.setBackgroundColor(Color.WHITE);

        } else {
            //convertView.setBackgroundColor(Color.rgb(255, 192, 203));
            convertView.setBackgroundColor(Color.parseColor("#FFC0CB"));
        }

        return convertView;
    }
}
