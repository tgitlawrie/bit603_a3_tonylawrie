package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
  private static final String TAG = "login";

  public static InventoryDb inventoryDb; // init db
  public static final User admin = new User();
  public static boolean isAdmin = false;

  Button loginBtn;
  EditText usernameInput, passwordInput;
  TextView errorText;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0); // remove terrible animation on load
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_main);
    // assign database to inventory object
    inventoryDb = Room.databaseBuilder(getApplicationContext(), InventoryDb.class, "inventory")
            .allowMainThreadQueries().build();

    //hardcoded admin account, used User object for consistency
    admin.setUsername("Admin");
    admin.setPassword("CookieManagement84");

    loginBtn = findViewById(R.id.login_button);

    usernameInput = findViewById(R.id.usernameInput);
    passwordInput = findViewById(R.id.passwordInput);

    errorText = findViewById(R.id.loginErrorText);

    loginBtn.setOnClickListener(view -> {
      errorText.setText(""); // clear the text
      String userName = usernameInput.getText().toString();
      String password = passwordInput.getText().toString();

      User currentUser = MainActivity.inventoryDb.inventoryDao().getUser(userName);
      Log.d(TAG, "onCreate: " + currentUser);
      //if admin go to manage users activity
      // else if username and password match from db go to inventory
      // else display error
      if (userName.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
        isAdmin = true;
        overridePendingTransition(0, 0); // remove terrible animation on load
        startActivity(new Intent(getApplicationContext(), ManageUsers.class));
        overridePendingTransition(0, 0); // remove terrible animation on load
      }

      if (!(currentUser == null)){
        if (userName.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword())) {
          isAdmin = false;
          overridePendingTransition(0, 0); // remove terrible animation on load
          startActivity(new Intent(getApplicationContext(), ShowInventory.class));
          overridePendingTransition(0, 0); // remove terrible animation on load
        } else {
          // generic message for both username and password for added security,
          // if error message reads username incorrect this gives the user enough information to try guessing.
          errorText.setText(R.string.login_error);
          passwordInput.setText("");
        }
      }else{
        errorText.setText(R.string.login_error);
      }
    });
  }
}