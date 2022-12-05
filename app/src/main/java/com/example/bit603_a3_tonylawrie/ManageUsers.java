package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import java.util.Objects;

public class ManageUsers extends AppCompatActivity {

    Button viewUsers, addUser, removeUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0,0);
    super.onCreate(savedInstanceState);
    if(getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_manage_users);

    addUser = findViewById(R.id.users_add_btn);
    viewUsers = findViewById(R.id.users_view_btn);
    removeUser = findViewById(R.id.users_remove_btn); //TODO

    addUser.setOnClickListener(view -> {
      startActivity(new Intent(this, AddUser.class));
    });
    viewUsers.setOnClickListener(view -> {
      startActivity(new Intent(this, ViewUsers.class));
    });
    removeUser.setOnClickListener(view -> {
      startActivity(new Intent(this, RemoveUser.class));
    });
  }
}