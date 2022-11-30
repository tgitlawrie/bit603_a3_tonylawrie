package com.example.bit603_a3_tonylawrie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class MainMenuFragment extends Fragment {
  BottomNavigationView MenuNavigationView;
  private static final String TAG = "menu";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_main_menu, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // code goes here
    MenuNavigationView = view.findViewById(R.id.MainNavigation);

    MenuNavigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.logout) {
        // logout
        logout();
      } else if (item.getItemId() == R.id.add_item) {
        startActivity(new Intent(getActivity(), AddItem.class));
      } else if (item.getItemId() == R.id.admin) {
        startActivity(new Intent(getActivity(),ManageUsers.class));
      }else if (item.getItemId() == R.id.inventory) {
        startActivity(new Intent(getActivity(), ShowInventory.class));
      }
      return true;
    });
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    setHasOptionsMenu(true);
  }

  @Override
  public void onPrepareOptionsMenu(@NonNull Menu menu) {
    super.onPrepareOptionsMenu(menu);
    Log.d(TAG, "onPrepareOptionsMenu: called");
    if(Objects.equals(MainActivity.loggedUser.getUsername(), MainActivity.admin.getUsername())){
      MenuNavigationView.getMenu().findItem(R.id.admin).setEnabled(true);
      Log.d(TAG, "onPrepareOptionsMenu: if statement");
    }


  }



  private void logout() {
    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
    builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Yes", (dialogInterface, i) -> startActivity(new Intent(getActivity(), MainActivity.class)));
    AlertDialog confirmation = builder.create();
    confirmation.show();
  }
}