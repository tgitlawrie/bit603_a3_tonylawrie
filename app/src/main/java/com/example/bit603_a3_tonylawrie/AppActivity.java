package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AppActivity extends AppCompatActivity {

  //  Fragment fragment;
  FragmentManager fragmentManager;
  FragmentTransaction fragmentTransaction;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_app);
    //load inventory fragment
    addFragment(new InventoryFragment(), InventoryFragment.fragmentID);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    bottomNavigationView.setSelectedItemId(R.id.inventory); // sets active fragment icon

    //item selected listener
    bottomNavigationView.setOnItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.logout:
          startActivity(new Intent(getApplicationContext(), MainActivity.class));
          overridePendingTransition(0, 0);
          break;
        case R.id.add_item:
          replaceFragment(new AddItemFragment(), AddItemFragment.fragmentID);
          break;
        case R.id.inventory:
          replaceFragment(new InventoryFragment(), InventoryFragment.fragmentID);
          break;
        case R.id.add_test_data:
          addSampleData();
          redrawFragment(InventoryFragment.fragmentID);
          break;
          case R.id.delete_items:
            //WARNING! Resets inventory database, for development testing only
            MainActivity.inventoryDb.inventoryDao().resetInventory(); //TODO Confirm
            replaceFragment(new InventoryFragment(), InventoryFragment.fragmentID);
            break;
      }
      return true;
    });
  }

  private void redrawFragment(String ID) {
    fragmentManager = getSupportFragmentManager();
    Fragment currentFragment = fragmentManager.findFragmentByTag(ID);
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.detach(currentFragment);
    fragmentTransaction.attach(currentFragment);
    fragmentTransaction.commit();
  }

  // method to add initial fragment
  public void addFragment(Fragment fragment, String ID) {
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.app_container, fragment, ID);
    fragmentTransaction.commit();
  }

  // replace fragment
  public void replaceFragment(Fragment fragment, String ID) {
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.replace(R.id.app_container, fragment, ID);
    fragmentTransaction.commit();
  }

  public void addSampleData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Confirm")
            .setMessage("This will add 20 sample items with random types and quantities to the Inventory, Are you sure you want to proceed?");
    //positive confirmation
    builder.setPositiveButton("Yes",(dialogInterface, i) -> {
      // get sample list, iterate through and insert to db
      List<Inventory> sample = Inventory.getSampleData();
      for(Inventory item: sample){
        MainActivity.inventoryDb.inventoryDao().addItem(item);
        Toast toast = Toast.makeText(getBaseContext(), "Sample Data added successfully!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
        toast.show();

        // Reload Inventory Fragment
        replaceFragment(new InventoryFragment(), InventoryFragment.fragmentID);
      }
    });
    // negative confirmation
    builder.setNegativeButton("Cancel",(dialogInterface, i) -> {}); // do nothing
    AlertDialog confirmation = builder.create();
    confirmation.show();
  }

}