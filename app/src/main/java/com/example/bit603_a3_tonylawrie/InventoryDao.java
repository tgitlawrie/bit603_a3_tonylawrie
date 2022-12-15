package com.example.bit603_a3_tonylawrie;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface InventoryDao {
  @Query("SELECT * FROM Inventory")
  List<Inventory> getInventory();

  @Query("SELECT * FROM User")
  List<User> getUsers();

  @Query("SELECT username FROM User")
  List<String> getUsernames();
  
  @Query("SELECT * FROM User WHERE username = :user")
  User getUser(String user);

  @Insert
  void addItem(Inventory item); // maybe needs to be Inventory inventory

  @Insert
  void addUser(User user);

  @Query("DELETE FROM user WHERE username = :user")
  void deleteUserByName(String user);

  //WARNING for development purposes only.
  @Query("DELETE FROM Inventory")
  void resetInventory();

  @Query("SELECT * FROM User WHERE EmpNumber = :num")
  User getUserByEmpNum(int num);
}
