package com.example.bit603_a3_tonylawrie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Inventory.class}, version = 1)
public abstract class InventoryDb extends RoomDatabase {
  public abstract InventoryDao inventoryDao();
}
