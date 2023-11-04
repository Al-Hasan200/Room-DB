package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    //variable declaring
    Button update;
    EditText email, name;
    private User user;
    private DBHelper dbHelper;
    private UserDao userDao;
    //int u_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //find id
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        update = findViewById(R.id.update);

        //get instance database
        dbHelper = DBHelper.getInstance(this);
        //get user dao
        userDao = dbHelper.getDao();

        //get user details
        //user = (User) getIntent().getSerializableExtra("update");

        //set user details in edit text
        int u_id = Integer.parseInt(getIntent().getStringExtra("u_id"));
        name.setText(getIntent().getStringExtra("u_name"));
        email.setText(getIntent().getStringExtra("u_email"));

        //update data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String uName = name.getText().toString();
                //String uEmail = email.getText().toString();
                //User userUpdate = new User();
                userDao.updateData(u_id, name.getText().toString(), email.getText().toString());
                Toast.makeText(MainActivity2.this, "Data Updated...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}