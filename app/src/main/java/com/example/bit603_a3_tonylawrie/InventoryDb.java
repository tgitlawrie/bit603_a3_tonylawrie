package com.example.bit603_a3_tonylawrie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverters;

@Database(entities = {User.class, Inventory.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class InventoryDb extends RoomDatabase {
  public abstract InventoryDao inventoryDao();
}
