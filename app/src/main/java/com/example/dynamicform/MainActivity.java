package com.example.dynamicform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    Button add;
    LinearLayout layout;
    RelativeLayout scrollView;
    Button save;
    EditText dFName, dLName;
    ListView showList;
    RelativeLayout userListLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"OnCreate:Started");

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);
        scrollView = findViewById(R.id.scrollview);
        dFName = findViewById(R.id.dFirstName);
        dLName = findViewById(R.id.dLastName);
        showList= findViewById(R.id.showList);
        userListLayout = findViewById(R.id.userListLayout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                addCard();

            }
        });

        ArrayList<Person> listItem = new ArrayList<>();


        save = findViewById(R.id.Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FNameValue = dFName.getText().toString();
                String LNameValue = dLName.getText().toString();


                if (!FNameValue.isEmpty() && !LNameValue.isEmpty()) {

                    Person p = new Person(FNameValue,LNameValue);
                    listItem.add(p);



                }else{
                    dFName.setError("Enter First Name");
                    dLName.setError("Enter Last Name");
                }



                View v;
                for (int i = 0; i < layout.getChildCount(); i++) {
                    v = layout.getChildAt(i);
                    EditText fName = v.findViewById(R.id.firstName);
                    EditText lName = v.findViewById(R.id.lastName);
                    String f= fName.getText().toString();
                    String l= lName.getText().toString();

                    if(!f.isEmpty() && !l.isEmpty()){
                        Person pp = new Person(f,l);
                        listItem.add(pp);

                    }else{
                        fName.setError("Enter First Name");
                        lName.setError("Enter Second Name");
                    }

                }

                PersonListAdapter  adapter = new PersonListAdapter(getApplicationContext(),R.layout.adapter_view_layout,listItem);
                showList.setAdapter(adapter);
                userListLayout.setVisibility(View.VISIBLE);


            }
        });
    }


    public void addCard() {

        final View view = getLayoutInflater().inflate(R.layout.row, null);

        TextView delete = view.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);

            }
        });

        layout.addView(view);

    }
}
