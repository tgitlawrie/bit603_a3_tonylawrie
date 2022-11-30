package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class ManageUsers extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0,0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_users);

    supportInvalidateOptionsMenu();
    invalidateOptionsMenu();
  }


}