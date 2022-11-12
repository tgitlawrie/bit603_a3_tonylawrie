package com.example.bit603_a3_tonylawrie;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface InventoryDao {
  @Query("SELECT * FROM Inventory")
  List<Inventory> getInventory();

  @Query("SELECT * FROM User")
  List<User> getUsers();

  @Insert
  void addItem(Inventory item); // maybe needs to be Inventory inventory

  @Insert
  void addUser(User user);


  //WARNING for development purposes only.
  @Query("DELETE FROM Inventory")
  public void resetInventory();
}
