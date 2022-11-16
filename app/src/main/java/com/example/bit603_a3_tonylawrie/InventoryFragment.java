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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InventoryFragment extends Fragment {
  private static String TAG = "InventoryFragment";
  public static final String fragmentID = "inventory";

  BottomNavigationView inventoryNavigationView;

  private int pageSize = 5;
  private int page = 1;

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

    List<Inventory> inventory = MainActivity.inventoryDb.inventoryDao().getInventory();
    List<Inventory> pagedList = new ArrayList<>();// list to store paged results


    //item selected listener
    inventoryNavigationView.setOnItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.next:
          pagedList.removeAll(inventory);
          nextPage();
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
          pagedList.removeAll(inventory);
          prevPage();
          break;
      }
      return true;
    });

    // only call paginate if inventory has items
    if (inventory.size() > 0) {
      for (int i = page * pageSize -pageSize; i < pageSize * page; i++) {
        pagedList.add(inventory.get(i));
      }
    }

    RecyclerView recycler = view.findViewById(R.id.itemRecycler);
    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    recycler.setHasFixedSize(true);
    ItemAdapter itemAdapter = new ItemAdapter(getContext(), pagedList);
    recycler.setAdapter(itemAdapter);
    itemAdapter.notifyDataSetChanged();
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

  private void nextPage() {
    page++;
  }

  private void prevPage() {
    page--;

  }
}