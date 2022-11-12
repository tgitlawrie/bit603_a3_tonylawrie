package com.example.bit603_a3_tonylawrie;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {
  private static String TAG = "InventoryFragment";
  public static final String fragmentID = "inventory";

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public InventoryFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment InventoryFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static InventoryFragment newInstance(String param1, String param2) {
    InventoryFragment fragment = new InventoryFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    List<Inventory> inventory = MainActivity.inventoryDb.inventoryDao().getInventory();
    for (Inventory item: inventory) {
      String name = item.getItem();
      String type = item.getType();
      String quantity = String.valueOf(item.getQuantity());
      Log.d(TAG, name + " | " + type + ": " + quantity);
    }
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_inventory, container, false);
  }
}