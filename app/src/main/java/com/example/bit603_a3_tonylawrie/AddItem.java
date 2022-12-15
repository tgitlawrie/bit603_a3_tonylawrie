package com.example.bit603_a3_tonylawrie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;

import android.os.Bundle;

import android.text.InputType;


import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddItem extends AppCompatActivity {

  TextView errorMsgText;
  EditText itemName, itemType, quantity;
  ImageButton addBtn;

  private static String errorMsg = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_add_item);

    errorMsgText = findViewById(R.id.add_item_error);
    itemName = findViewById(R.id.item_input);
    quantity = findViewById(R.id.quantity_input);
    itemType = findViewById(R.id.item_type_text);
    itemType.setInputType(InputType.TYPE_NULL); // suppresses keyboard
    addBtn = findViewById(R.id.add_img_btn);


    // onclick listener for item type
    itemType.setOnClickListener(view -> callItemDialog());
    // on focus change listener for item type, makes it more responsive
    itemType.setOnFocusChangeListener((view, b) -> {
      if (b) {
        callItemDialog();
      }
    });

    //add item button
    addBtn.setOnClickListener(view -> {
      errorMsgText.setText(""); // clear the error message

      // holders for inputs before validation
      String item = itemName.getText().toString();
      Object newQuantity = quantity.getText();

      if (validated(item, newQuantity)) {
        Inventory newItem = new Inventory();
        newItem.setItem(itemName.getText().toString());
        newItem.setType(itemType.getText().toString());
        newItem.setQuantity(Integer.parseInt(quantity.getText().toString()));

        addItemDialog(newItem);
      } else {
        errorMsgText.setText(errorMsg);
      }

    });
  }

  static boolean validated(String item, Object newQuantity) {
    // check item has been entered or exists
    if (item == null || item.equals("")) {
      errorMsg = "Please enter an item name";
      return false;
    }

    // check quantity can be parsed to integer
    try {
      Integer.parseInt(newQuantity.toString());
    } catch (Exception e) {
      errorMsg = "Enter valid Quantity";
      return false;
    }

    //check for special characters if item not empty
    Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
    Matcher m = p.matcher(item);
    boolean b = m.find();
    if (b) {
      errorMsg = "Enter valid item name";
      return false;
    }


    // keyboard doesn't allow negative values, but just in-case
    if (Integer.parseInt(newQuantity.toString()) <= 0) {
      errorMsg = "Please enter a valid quantity";
      return false;
    }

    return true;
  }

  private void callItemDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.item_alert_title)
            .setItems(Inventory.types, (dialogInterface, i) -> itemType.setText(Inventory.types[i]));
    builder.create().show();
  }

  private void addItemDialog(@NonNull Inventory newItem) {
    // Check if no view has focus: then hide keyboard
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    String newItemString = newItem.getItem() + "\r\n"
            + newItem.getType() + "  X " + newItem.getQuantity();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.add_item_title);
    builder.setMessage("are you sure you want to add: \r\n" + newItemString);
    builder.setNegativeButton("Cancel", null);
    builder.setPositiveButton("Add", (dialogInterface, i) -> {
      MainActivity.inventoryDb.inventoryDao().addItem(newItem); // insert item on add click

      Toast toast = Toast.makeText(this, newItem.getItem() + " has been added!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
      clearFields(); // clear the input fields after adding an item
    });
    builder.create().show();
  }

  private void clearFields() {
    itemName.setText("");
    itemType.setText("");
    quantity.setText("");
  }
}