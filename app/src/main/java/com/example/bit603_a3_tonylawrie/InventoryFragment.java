package com.example.bit603_a3_tonylawrie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class InventoryFragment extends Fragment {
  private static String TAG = "InventoryFragment";
  public static final String fragmentID = "inventory";



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
    List<Inventory> inventory = MainActivity.inventoryDb.inventoryDao().getInventory();

    RecyclerView recycler = view.findViewById(R.id.itemRecycler);
    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    recycler.setHasFixedSize(true);
    ItemAdapter itemAdapter = new ItemAdapter(getContext(), inventory);
    recycler.setAdapter(itemAdapter);
    itemAdapter.notifyDataSetChanged();


    for (Inventory item: inventory) {
      String name = item.getItem();
      String type = item.getType();
      String quantity = String.valueOf(item.getQuantity());
    }
  }
}