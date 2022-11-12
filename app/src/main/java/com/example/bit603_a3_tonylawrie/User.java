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

  @ColumnInfo(name = "DOB")
  private Date DOB;

  @ColumnInfo(name = "EmpNumber")
  private int empNumber;

  @ColumnInfo(name = "Phone")
  private String phone;

  @ColumnInfo(name = "Adress")
  private String address;

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

  public Date getDOB() {
    return DOB;
  }

  public void setDOB(Date DOB) {
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
}
