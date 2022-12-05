package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class RemoveUser extends AppCompatActivity {

  private static final String TAG = "remove";

  AutoCompleteTextView userList;
  CardView userCard;
  Button removeBtn;
  TextView empNum, username, DoB, contact, address;
  List<String> usernames; // list of usernames for searching
  User removeUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(0, 0); // remove terrible animation on load
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_remove_user);

    //get list of all usernames for search function
    usernames = MainActivity.inventoryDb.inventoryDao().getUsernames();

    // populate autocomplete with usernames
    userList = findViewById(R.id.userList);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, usernames);
    userList.setAdapter(adapter);

    userCard = findViewById(R.id.remove_card);
    empNum = findViewById(R.id.rm_empNum_Text);
    username = findViewById(R.id.rm_username_text);
    DoB = findViewById(R.id.rm_DoB_text);
    contact = findViewById(R.id.rm_phone_text);
    address = findViewById(R.id.rm_address_text);

    removeBtn = findViewById(R.id.remove_btn);

    userList.setOnItemClickListener((adapterView, view, i, l) -> {
      String selectedUser = adapterView.getItemAtPosition(i).toString();
      Log.d(TAG, "onItemClick: " + selectedUser);
      closeKeyboard(); //close the keyboard
      //get user from db and assign to removeUser
      removeUser = MainActivity.inventoryDb.inventoryDao().getUser(selectedUser);
      // build strings
      String empNumStr = "Emp#: " + removeUser.getEmpNumber();
      String DoBStr = "D.O.B: " + removeUser.getDOB();
      String contactStr = "Contact: " + removeUser.getPhone();
      String addressStr = "Address: " + (removeUser.getAddress());

      userCard.setVisibility(View.VISIBLE); // make card visible
      // bind Strings to textViews
      empNum.setText(empNumStr);
      username.setText(removeUser.getUsername());
      DoB.setText(DoBStr);
      contact.setText(contactStr);
      address.setText(addressStr);
      Log.d(TAG, "remove user: " + removeUser.getUsername());
      Log.d(TAG, removeUser.getAddress());
    });


    removeBtn.setOnClickListener(view -> {
      // go to dialog for confirmation then delete
      callDeleteDialog(removeUser.getUsername());
    });
  }

  private void callDeleteDialog(String userToDelete) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Confirm user deletion")
            .setMessage("Are you sure you want to remove " + userToDelete)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Delete", (dialogInterface, i) -> {
              MainActivity.inventoryDb.inventoryDao().deleteUserByName(userToDelete); // delete user

              Toast toast = Toast.makeText(this, userToDelete + " has been deleted", Toast.LENGTH_SHORT);
              toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
              clearFields();
              toast.show();
            });
    builder.create().show();
  }

  private void clearFields() {
    userList.setText("");
    empNum.setText("");
    username.setText("");
    DoB.setText("");
    contact.setText("");
    address.setText("");
    userCard.setVisibility(View.GONE);
  }

  private void closeKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}