package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ShowInventory extends AppCompatActivity {
  private static String TAG = "InventoryFragment";

  BottomNavigationView inventoryNavigationView;
  Menu inventoryMenu;
  MenuItem previous,next;
  TextView listText;
  private int pageSize, page, lastpage;
  List<Inventory> inventory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_show_inventory);
    inventoryNavigationView = findViewById(R.id.inventoryNavigation);
    inventoryMenu = inventoryNavigationView.getMenu();

    inventory = MainActivity.inventoryDb.inventoryDao().getInventory();

    listText = findViewById(R.id.inventory_list);
    previous = inventoryMenu.findItem(R.id.previous);
    next = inventoryMenu.findItem(R.id.next);

    pageSize = 5;
    page = 1;


    //initial call to update menu to set the disable options
    update();
    //item selected listener
    inventoryNavigationView.setOnItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.next:
          page++;
          setItems();
          break;
        case R.id.add_item:
          break;
        case R.id.add_test_data:
          addSampleData();
          break;
        case R.id.delete_items:
          deleteData();
          break;
        case R.id.previous:
          page--;
          setItems();
          break;
      }
      return true;
    });

    //  if inventory is empty display message otherwise get 1st set of data
    if (inventory.size() <= 0) {
      listText.setText(R.string.empty_inventory);
    } else {
      // call getItems() to get initial set of items
      setItems();
    }
  }

  // set 5 items at a time
  private void setItems() {
    Log.d(TAG, "setItems: called");
    listText.setText("");
    String item, type, quantity, outString;

//    Log.d(TAG, "I: " + String.valueOf((page - 1) * pageSize));
//    Log.d(TAG, "page" + page);
//    Log.d(TAG, "pagesize: " + pageSize);
//    Log.d(TAG, "condition: " + page * pageSize);

    for (int i = (page - 1) * pageSize; i < page * pageSize - 1; i++) {
      if (i < inventory.size() && i >= 0) {
//        Log.d(TAG, String.valueOf(i));
        item = inventory.get(i).getItem();
        type = inventory.get(i).getType();
        quantity = String.valueOf(inventory.get(i).getQuantity());

        outString = item + "\u0009" + type + "\u0009Quantity: " + quantity + "\r\n";
        listText.append(outString);
      }
    }
    update();
  }


  private void deleteData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Delete all Items")
            .setMessage("Are you Sure you want to delete all Items in the inventory?");
    //positive confirmation
    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
      // delete all data from the inventory
      MainActivity.inventoryDb.inventoryDao().resetInventory();
      Toast toast = Toast.makeText(this, "Inventory deleted!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
      setItems();
    });
    // negative confirmation
    builder.setNegativeButton("Cancel", null);
    AlertDialog confirmation = builder.create();
    confirmation.show();

  }


  private void addSampleData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Confirm")
            .setMessage("This will add 20 sample items with random types and quantities to the Inventory, Are you sure you want to proceed?");
    //positive confirmation
    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
      // get sample list, iterate through and insert to db
      List<Inventory> sample = Inventory.getSampleData();
      for (Inventory item : sample) {
        MainActivity.inventoryDb.inventoryDao().addItem(item);
      }
      Toast toast = Toast.makeText(this, "Sample Data added successfully!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
      setItems();
    });
    // negative confirmation
    builder.setNegativeButton("Cancel", null);

    AlertDialog confirmation = builder.create();
    confirmation.show();

  }

  private void update() {
    lastpage = inventory.size() / pageSize;

    if (page <= 1) {
      previous.setEnabled(false);
    } else {
      previous.setEnabled(true);
    }

    if (page >= lastpage) {
      next.setEnabled(false);
    } else {
      next.setEnabled(true);
    }
  }
}