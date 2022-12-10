package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ViewUsers extends AppCompatActivity {
  RecyclerView userRecycler;
  userAdapter adapter;
  List<User> users;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(0,0); // remove terrible animation on load
    if(getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_view_users);

    users = MainActivity.inventoryDb.inventoryDao().getUsers();

    userRecycler = findViewById(R.id.userRecycler);
    userRecycler.setLayoutManager(new LinearLayoutManager(this));
    adapter = new userAdapter(this,users);
    userRecycler.setAdapter(adapter);
  }
}