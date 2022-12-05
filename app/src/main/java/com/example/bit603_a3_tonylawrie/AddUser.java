package com.example.bit603_a3_tonylawrie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUser extends AppCompatActivity {
  private static final String TAG = "addUser";
  private static String errorMsg;
  EditText
          userNameInput,
          passwordInput,
          empNumberInput,
          DoBInput,
          phoneInput,
          addressInput;

  TextView errorText;
  Button addBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide(); //hide the action bar so it doesn't screw with layout
    }
    setContentView(R.layout.activity_add_user);

    // input fields
    userNameInput = findViewById(R.id.add_userName);
    passwordInput = findViewById(R.id.add_password);
    DoBInput = findViewById(R.id.add_dob);
    empNumberInput = findViewById(R.id.add_emp_number);
    phoneInput = findViewById(R.id.add_phone);
    addressInput = findViewById(R.id.add_address);

    //button and error message
    addBtn = findViewById(R.id.add_user_btn);
    errorText = findViewById(R.id.add_error);


    // event listener for date picker
    // onclick listener for item type
    DoBInput.setOnClickListener(view -> {
      callDoBPicker();
    });
    // on focus change listener for item type, makes it more responsive
    DoBInput.setOnFocusChangeListener((view, b) -> {
      if (b) {
        callDoBPicker();
      }
    });

    //add user button, call method to validate inputs,
    // if error count 0 call dialog for confirmation.
    // get all values as strings to parse in validation method
    addBtn.setOnClickListener(view -> {
      String userName = userNameInput.getText().toString();
      String password = passwordInput.getText().toString();
      String DoB = DoBInput.getText().toString();
      String empNum = empNumberInput.getText().toString();
      String phone = phoneInput.getText().toString();
      String address = addressInput.getText().toString();

      // reset error Msg
      errorText.setText("");
      errorMsg = "";
      // add to database if validation passes else show error message
      if (isValid(userName, password, DoB, empNum, phone, address)) {
        //create the user then pass result to addUser method to insert
        addUser(createUser(userName, password, DoB, empNum, phone, address));
      } else {
        errorText.setText(errorMsg);
      }
    });
  }// end of class

  private void addUser(User user) {
    //TODO clear fields after adding
    String userDetails = user.getUsername() + "\r\n"
            + "D.O.B: " + user.getDOB() + "\r\n"
            + "Employee #: " + user.getEmpNumber() + "\r\n"
            + "Phone: " + user.getPhone() + "\r\n"
            + "Address: " + user.getAddress();

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Create User")
            .setMessage("Are you sure you want to create the following user?\r\n" + userDetails);
    builder.setNegativeButton("Cancel", null);
    builder.setPositiveButton("Create", (dialogInterface, i) -> {
      MainActivity.inventoryDb.inventoryDao().addUser(user); // insert item on add click

      Toast toast = Toast.makeText(this, user.getUsername() + "s account has been added!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);
      toast.show();
    });
    builder.create().show();
  }

  //  create a user object from the params
  private User createUser(String userName, String password, String doB, String empNum, String phone, String address) {
    User newUser = new User();

    newUser.setUsername(userName.trim().toLowerCase());
    newUser.setPassword(password);
    newUser.setDOB(doB);
    newUser.setEmpNumber(Integer.parseInt(empNum));
    newUser.setPhone(phone);
    newUser.setAddress(address);

    return newUser;
  }

  private boolean isValid(String userName, String password, String doB, String empNum, String phone, String address) {
    Pattern p = Pattern.compile("[^A-Za-z0-9 ]"); // pattern to check for special characters

    //username and address validations
    // check item has been entered or exists
    if (userName == null || userName.equals("")) {
      errorMsg = "Please enter a username";
      return false;
    }

    userName = userName.toLowerCase(); // force lower case to prevent too similar usernames
    //check for special characters in username and address

    Matcher m = p.matcher(userName);
    boolean check = m.find();
    if (check) {
      errorMsg = "Username cannot contain special characters";
      return false;
    }

    //todo check if username exists in db

    //password validations
    if (password == null || password.equals("")) {
      errorMsg = "Please choose a password";
      return false;
    }
    //password should be between 6 and 20 characters
    if (password.length() < 6 || password.length() > 20) {
      errorMsg = "Password should be between 6 and 20 characters";
      return false;
    }

    //check if dob is input
    if (doB == null || doB.equals("")) {
      errorMsg = "Please enter a date of birth";
      return false;
    }


    // employee number validations
    // check if number entered
    if (empNum == null || empNum.equals("")) {
      errorMsg = "Please enter an employee number";
      return false;
    }
    // keyboard doesnt allow negative values, but just incase
    if (Integer.parseInt(empNum) <= 0) {
      errorMsg = "Employee number must be positive";
      return false;
    }
    //todo check if emp number exists in db

    // phone number validations
    if (phone == null || phone.equals("")) {
      errorMsg = "Please enter a contact number";
      return false;
    }
    if (!Patterns.PHONE.matcher(phone).matches()) {
      errorMsg = "Phone number must be 6-10 numbers and contain numbers only: 123 456 789";
      return false;
    }

    //check address has been entered
    if (address.equals("")) {
      errorMsg = "Please enter an address";
      return false;
    }
    m = p.matcher((address));
    check = m.find();
    if (check) {
      errorMsg = "Address cannot contain special characters";
      return false;
    }

    return true; // return true if all conditions pass
  }

  private void callDoBPicker() {
    final Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    //year1 month1 because of above variables
    DatePickerDialog dialog = new DatePickerDialog(this, (datePicker, year1, month1, dayOfMonth) -> {
      month1 = month1 + 1; // adding 1 to month to correct it.
      String date = year1 + "-" + month1 + "-" + dayOfMonth;
      DoBInput.setText(date);
    }, year, month, day);
    // prevents future dates being added
    dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
    dialog.show();
  }
}