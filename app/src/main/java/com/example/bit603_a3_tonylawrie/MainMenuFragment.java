package com.example.bit603_a3_tonylawrie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class MainMenuFragment extends Fragment {
  BottomNavigationView MenuNavigationView;


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
        //TODO
      } else if (item.getItemId() == R.id.admin) {
      }
      return true;
    });
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