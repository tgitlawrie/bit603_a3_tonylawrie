package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {

//  Fragment fragment;
  FragmentManager fragmentManager;
  FragmentTransaction fragmentTransaction;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_app);
    //load inventory fragment
    addFragment(new InventoryFragment());

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    bottomNavigationView.setSelectedItemId(R.id.inventory); // sets active fragment icon

    //item selected listener
    bottomNavigationView.setOnItemSelectedListener(item -> {
      switch (item.getItemId()){
        case R.id.logout:
          startActivity(new Intent(getApplicationContext(),MainActivity.class));
          overridePendingTransition(0,0);
          break;
        case R.id.add_item:
          replaceFragment(new AddItemFragment());
          break;
        case R.id.inventory:
          replaceFragment(new InventoryFragment());
          break;
      }
      return true;
    });
  }

  // method to add initial fragment
  public void addFragment(Fragment fragment){
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.app_container,fragment);
    fragmentTransaction.commit();
  }

  // replace fragment
  public void replaceFragment(Fragment fragment){
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.replace(R.id.app_container,fragment);
    fragmentTransaction.commit();
  }

}