package com.example.bit603_a3_tonylawrie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity(tableName = "Inventory")
public class Inventory {
  //declared static so this can be used in add item menu
  public static String[] types = {"biscuit", "cookie", "cake", "ingredient", "other"};


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "ItemID")
  private int itemID;

  @ColumnInfo(name = "Item")
  private String item;

  @ColumnInfo(name = "Type")
  private String type;

  @ColumnInfo(name = "Quantity")
  private int quantity;

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getItemID() {
    return itemID;
  }

  public void setItemID(int itemID) {
    this.itemID = itemID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  //adds set of test items
  // works through preset item array, assign random type and random quantity
  public static List<Inventory> getSampleData(){
    List<Inventory> sample = new ArrayList<>();
    String[] items = {"flour","pound cake","baking soda","apple muffin","chocolate chip","macaroon","custard slice","milk","eggs",
      "cocoa","blueberry muffin","salt","chocolate brownie","tracking cookie","cheese cake","carrot cake","mixed berries","sugar","shortbread","cream"};

    for (String item: items) {
      final Inventory randomItem = new Inventory();
      // add item name
      randomItem.setItem(item);

      // add random type
      int randType = new Random().nextInt(types.length);
      randomItem.setType(types[randType]);

      // add random number between 0 and 30
      int randQuantity = new Random().nextInt(31);
      randomItem.setQuantity(randQuantity);

      sample.add(randomItem);
    }

    return sample;
  }
}
