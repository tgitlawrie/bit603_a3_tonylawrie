package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  Button loginBtn;
  EditText usernameInput, passwordInput;
  TextView errorText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_main);

    loginBtn = findViewById(R.id.login_button);

    usernameInput = findViewById(R.id.usernameInput);
    passwordInput = findViewById(R.id.passwordInput);

    errorText = findViewById(R.id.loginErrorText);

    loginBtn.setOnClickListener(view -> {

      // if standard user go to inventory
      startActivity(new Intent(getApplicationContext(),AppActivity.class));
      overridePendingTransition(0,0);

      //if admin go to admin menu
    });

  }
}