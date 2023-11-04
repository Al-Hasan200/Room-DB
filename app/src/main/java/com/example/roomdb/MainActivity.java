package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener{

    //variable declaring
    EditText name, email;
    Button add;
    RecyclerView recyclerView;
    Adapter adapter;
    DBHelper dbHelper;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find id
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyclerView);

        //get instance database
        dbHelper = DBHelper.getInstance(this);
        //get user dao
        userDao = dbHelper.getDao();

        //add data in recycler view
        adapter = new Adapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //add data in room database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edName = name.getText().toString();
                String edEmail = email.getText().toString();

                User user = new User(0, edName, edEmail);
                adapter.addUser(user);
                userDao.addData(user);

                //edit text clear
                name.setText("");
                email.setText("");

                Toast.makeText(MainActivity.this, "Data Added...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //fetch data
    public void fetchData(){
        adapter.clearData();
        List<User> userList = userDao.getAllData();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            adapter.addUser(user);
        }
    }

    //update data
    /*@Override
    public void onUpdate(int id, String uname, String uemail) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("u_id", String.valueOf(id));
        intent.putExtra("u_name", uname);
        intent.putExtra("u_email", uemail);
        startActivity(intent);
    }*/

    //delete data
    @Override
    public void onDelete(int id, int pos) {
        userDao.deleteData(id);
        adapter.removeDataRecyclerView(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //call fetchData method
        fetchData();
    }
}