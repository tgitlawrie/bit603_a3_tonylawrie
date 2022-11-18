package com.example.bit603_a3_tonylawrie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InventoryFragment extends Fragment {
  private static String TAG = "InventoryFragment";
  public static final String fragmentID = "inventory";

  BottomNavigationView inventoryNavigationView;
  Button nextButton;

  TextView listText;

  private int pageSize, page;
  List<Inventory> inventory;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_inventory, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    inventoryNavigationView = view.findViewById(R.id.inventoryNavigation);
    nextButton = view.findViewById(R.id.next);

    inventory = MainActivity.inventoryDb.inventoryDao().getInventory();

    listText = view.findViewById(R.id.inventory_list);

    pageSize = 5;
    page = 1;

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
          //WARNING! Resets inventory database, for development testing only
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
    if(inventory.size() <= 0){
      listText.setText(R.string.empty_inventory);
    } else{
      // call getItems() to get initial set of items
      setItems();
    }

  }

  // set 5 items at a time
  private void setItems() {
    listText.setText("");
    String item, type, quantity, outString;

    Log.d(TAG, "I: " + String.valueOf((page -1) * pageSize));
    Log.d(TAG, "page" + page);
    Log.d(TAG, "pagesize: " + pageSize);
    Log.d(TAG, "condition: " + page * pageSize);

    for(int i = (page -1) * pageSize; i < page * pageSize -1; i++){
      if( i < inventory.size() && i >= 0){
        Log.d(TAG, String.valueOf(i));
        item = inventory.get(i).getItem();
        type = inventory.get(i).getType();
        quantity = String.valueOf(inventory.get(i).getQuantity());

        outString = item + "\u0009" + type + "\u0009Quantity: " + quantity + "\r\n";
        listText.append(outString);
      }else{

      }
    }

  }


  private void deleteData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("Delete all Items")
            .setMessage("Are you Sure you want to delete all Items in the inventory?");
    //positive confirmation
    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
      // delete all data from the inventory
      MainActivity.inventoryDb.inventoryDao().resetInventory();
      Toast toast = Toast.makeText(getContext(), "Inventory deleted!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
      // replace fragment
      AppActivity.replaceFragment(new InventoryFragment(), fragmentID);
    });
    // negative confirmation
    builder.setNegativeButton("Cancel", null);

    AlertDialog confirmation = builder.create();
    confirmation.show();
  }


  public void addSampleData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("Confirm")
            .setMessage("This will add 20 sample items with random types and quantities to the Inventory, Are you sure you want to proceed?");
    //positive confirmation
    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
      // get sample list, iterate through and insert to db
      List<Inventory> sample = Inventory.getSampleData();
      for (Inventory item : sample) {
        MainActivity.inventoryDb.inventoryDao().addItem(item);
      }
      Toast toast = Toast.makeText(getContext(), "Sample Data added successfully!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
      // replace fragment
      AppActivity.replaceFragment(new InventoryFragment(), fragmentID);
    });
    // negative confirmation
    builder.setNegativeButton("Cancel", null);

    AlertDialog confirmation = builder.create();
    confirmation.show();
  }

}