package com.example.bit603_a3_tonylawrie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ShowInventory extends AppCompatActivity {

  BottomNavigationView inventoryNavigationView;
  Menu inventoryMenu;
  MenuItem previous, next;
  TextView listText, pageCount,itemCountText;
  private int pageSize, page, itemCount;
  private int lastPage;
  List<Inventory> inventory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0,0);
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_show_inventory);
    inventoryNavigationView = findViewById(R.id.inventoryNavigation);
    inventoryMenu = inventoryNavigationView.getMenu();

    inventory = MainActivity.inventoryDb.inventoryDao().getInventory();

    listText = findViewById(R.id.inventory_list);
    itemCountText = findViewById(R.id.itemCountText);
    previous = inventoryMenu.findItem(R.id.previous);
    next = inventoryMenu.findItem(R.id.next);
    pageCount = findViewById(R.id.page_counter);

    pageSize = 5;
    page = 1;

    // checks to see if an extra page is required to show additional items
    if(inventory.size() % pageSize == 0){
      lastPage = inventory.size() / pageSize;
    }else{
      lastPage = inventory.size() / pageSize +1;
    }

    itemCount = inventory.size();

    String itemCountString = "Items: " + itemCount;
    itemCountText.setText(itemCountString);

    //initial call to update menu to set the disable options
    update();
    //item selected listener
    inventoryNavigationView.setOnItemSelectedListener(item -> {
      if(item.getItemId() == R.id.next) {
        page++;
        setItems();
      } else if(item.getItemId() == R.id.add_test_data){
        addSampleData();
      } else if(item.getItemId() == R.id.delete_items){
        deleteData();
      } else if(item.getItemId() == R.id.previous){
        page--;
        setItems();
      }
      return true;
    });
    // call setItems() to get initial set of items
    setItems();
  }

  @Override
  public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
    super.onPrepareOptionsMenu(menu);

    return true;
  }

  // set 5 items at a time
  private void setItems() {
    if (inventory.size() <= 0) {
      listText.setText(R.string.empty_inventory);
    } else {
      listText.setText("");
      String item;
      String type;
      String quantity;
      Spanned outString; //using Html.fromHtml to format the text nicely

      for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
        if (i < itemCount && i >= 0) {
          item = inventory.get(i).getItem();
          type = inventory.get(i).getType();
          quantity = String.valueOf(inventory.get(i).getQuantity());

          outString = Html.fromHtml("<b>"+item + "</b><br>" + "" + type + "&emsp;Quantity: " + quantity + "<br><br>");
          listText.append(outString);
        }
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
      refreshActivity();

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
      refreshActivity();
    });
    // negative confirmation
    builder.setNegativeButton("Cancel", null);
    AlertDialog confirmation = builder.create();
    confirmation.show();
  }

  private void update() {
    String pageText = page + "/" + lastPage;
    pageCount.setText(pageText);
    if (inventory.size() <= 0) {
      listText.setText(R.string.empty_inventory);
    }
    // if on first page set disable previous
    previous.setEnabled(page > 1);
    // if on last page disable next
    next.setEnabled(page < lastPage);
  }

  private void refreshActivity() {
    finish();
    overridePendingTransition(0, 0);
    startActivity(getIntent());
    overridePendingTransition(0, 0);
  }
}