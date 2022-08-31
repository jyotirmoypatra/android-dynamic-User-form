package com.example.dynamicform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button add;
    LinearLayout layout;
    RelativeLayout scrollView;
    Button save;
    EditText dFName, dLName;
    ListView showList;
    RelativeLayout mainLayout, userListLayout;
    private DBHelper databaseHelper;
    Button deleteUserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObjects();


        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);
        scrollView = findViewById(R.id.scrollview);
        dFName = findViewById(R.id.dFirstName);
        dLName = findViewById(R.id.dLastName);
        showList = findViewById(R.id.showList);
        userListLayout = findViewById(R.id.userListLayout);
        mainLayout = findViewById(R.id.mainLayout);
        deleteUserList=findViewById(R.id.deleteUserList);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                addCard();

            }
        });

        ArrayList<Person> listItem = new ArrayList<>();


        save = findViewById(R.id.Save);
        fetchAllUser();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FNameValue = dFName.getText().toString().trim();
                String LNameValue = dLName.getText().toString().trim();


                if (!FNameValue.isEmpty() && !LNameValue.isEmpty()) {
                    /*  code for database */
                    Person p = new Person(FNameValue,LNameValue);

                       databaseHelper.addUser(p);
                       Snackbar.make(mainLayout, getString(R.string.success), Snackbar.LENGTH_LONG).show();
                    dLName.setText("");
                    dFName.setText("");

                } else {
                    dFName.setError("Enter First Name");
                    dLName.setError("Enter Last Name");
                }
                View v;
                for (int i = 0; i < layout.getChildCount(); i++) {
                    v = layout.getChildAt(i);
                    EditText fName = v.findViewById(R.id.firstName);
                    EditText lName = v.findViewById(R.id.lastName);
                    String f = fName.getText().toString().trim();
                    String l = lName.getText().toString().trim();

                    if (!f.isEmpty() && !l.isEmpty()) {
                        Person p = new Person(f,l);
                        databaseHelper.addUser(p);
                        Snackbar.make(mainLayout, getString(R.string.success), Snackbar.LENGTH_LONG).show();

                        fName.setText("");
                        lName.setText("");

                    } else {
                        fName.setError("Enter First Name");
                        lName.setError("Enter Second Name");
                    }

                }
               ArrayList<Person> p = new ArrayList<Person>();
                p=databaseHelper.getAllUser();

                PersonListAdapter adapter = new PersonListAdapter(getApplicationContext(), R.layout.adapter_view_layout, p);
                showList.setAdapter(adapter);
                userListLayout.setVisibility(View.VISIBLE);


            }
        });

        deleteUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllUser();
            }
        });
    }

    private void deleteAllUser() {
      databaseHelper.deleteAllUser();
      fetchAllUser();
    }

    private  void fetchAllUser() {
        ArrayList<Person> p = new ArrayList<Person>();
        p=databaseHelper.getAllUser();

        PersonListAdapter adapter = new PersonListAdapter(getApplicationContext(), R.layout.adapter_view_layout, p);
        showList.setAdapter(adapter);
    }

    private void initObjects() {

        databaseHelper = new DBHelper(getApplicationContext());

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
