package com.example.bit603_a3_tonylawrie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "User")
public class User {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "UserId")
  private int userId;

  @ColumnInfo(name = "UserName")
  private String username;

  @ColumnInfo(name = "Password")
  private String password;

  @ColumnInfo(name = "DOB")
  private String DOB;

  @ColumnInfo(name = "EmpNumber")
  private int empNumber;

  @ColumnInfo(name = "Phone")
  private String phone;

  @ColumnInfo(name = "Address")
  private String address;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDOB() {
    return DOB;
  }

  public void setDOB(String DOB) {
    this.DOB = DOB;
  }

  public int getEmpNumber() {
    return empNumber;
  }

  public void setEmpNumber(int empNumber) {
    this.empNumber = empNumber;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }




  public static User login(String username , String password) {
    //todo login logic
    User currentUser = new User();
    currentUser.setUsername(username);
    currentUser.setPassword(password);

    return currentUser;
  }
}


