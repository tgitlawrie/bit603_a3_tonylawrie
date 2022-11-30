package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "Login";

  public static InventoryDb inventoryDb; // init db
  public static final User admin = new User();
  public static User loggedUser;
  Button loginBtn;
  EditText usernameInput, passwordInput;
  TextView errorText;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(0,0); // remove terrible animation on load
    if(getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_main);

    // assign database to inventory object
    inventoryDb = Room.databaseBuilder(getApplicationContext(),InventoryDb.class,"inventory")
            .allowMainThreadQueries().build();

    //hardcoded admin account
    admin.setUsername("Admin");
    admin.setPassword("CookieManagement84");

    loginBtn = findViewById(R.id.login_button);

    usernameInput = findViewById(R.id.usernameInput);
    passwordInput = findViewById(R.id.passwordInput);

    errorText = findViewById(R.id.loginErrorText);

    loginBtn.setOnClickListener(view -> {
      String userName = usernameInput.getText().toString();
      String password = passwordInput.getText().toString();

      loggedUser = User.login(userName, password);

      // if admin go to manage users else go to inventory
      Log.d(TAG, "Logged in user: " + loggedUser.getUsername() + " ");
      Log.d(TAG, "admin: " + admin.getUsername());
      if(Objects.equals(loggedUser.getUsername(), admin.getUsername())){
        startActivity(new Intent(getApplicationContext(),ManageUsers.class));
        overridePendingTransition(0,0);
      }else{
        // if standard user go to inventory
        startActivity(new Intent(getApplicationContext(),ShowInventory.class));
        overridePendingTransition(0,0);
      }
    });

  }
}