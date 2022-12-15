package com.example.bit603_a3_tonylawrie;

import static org.junit.Assert.*;

import android.util.Log;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class AddUserTest {
  // if test user doesn't exist
  // create a test user and add to the DB for testing purposes.
  //required to test validations for existing users
  private User testUser = new User();
  String username, password, doB, phone, empNum, address;


  @Before
  public void setUp() throws Exception {
    username = "test user";
    password = "P4$$w0rd";
    empNum = "69"; //nice
    doB = "10-10-1985";
    phone = "0276988123";
    address = "42 test address road";
  }

  //must be first test to run.
  @Test
  public void createUser_works() {
    //test that user is created. TODO
    testUser = AddUser.createUser(username, password, doB, empNum, phone, address);
    //check username
    MatcherAssert.assertThat("testUser.username should = username",
            Objects.equals(testUser.getUsername(), username));
    // check password
    MatcherAssert.assertThat("testUser.password should = password",
            Objects.equals(testUser.getPassword(), password));
    //check empNum
    MatcherAssert.assertThat("testUser.empNum should = empNum",
            Objects.equals(String.valueOf(testUser.getEmpNumber()), empNum));
    //check dob
    MatcherAssert.assertThat("testUser.dob should = doB",
            Objects.equals(testUser.getDOB(), doB));
    //phone
    MatcherAssert.assertThat("testUser.phone should = phone",
            Objects.equals(testUser.getPhone(), phone));
    //address
    MatcherAssert.assertThat("testUser.address should = address",
            Objects.equals(testUser.getAddress(), address));
  }

  @Test
  public void username_Validation_works() {
    //empty username
    MatcherAssert.assertThat("Should return false",
            !AddUser.isValid("", password, doB, empNum, phone, address));
    //null username
    MatcherAssert.assertThat("Should return false",
            !AddUser.isValid(null, password, doB, empNum, phone, address));
    //special characters
    MatcherAssert.assertThat("Should return false",
            !AddUser.isValid("'#invaliduser!'", password, doB, empNum, phone, address));
  }

  @Test
  public void password_Validation_Works() {
    //empty or null password
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, "", doB, empNum, phone, address));
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, null, doB, empNum, phone, address));
    //password Length too short
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, "short", doB, empNum, phone, address));
    //password Length too long
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, "thispasswordiswaytoolong", doB, empNum, phone, address));
  }

  @Test
  public void dob_Validation_Works() {
    //dob null check
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, "", empNum, phone, address));
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, null, empNum, phone, address));
  }

  @Test
  public void empNum_Validation_Works() {
    //empNum null check
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, "", phone, address));
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, null, phone, address));

    //empNum positive
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, "-4", phone, address));
  }

  @Test
  public void phone_Validation_Works(){
    // null check
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, empNum, "", address));
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, empNum, null, address));
  }

  @Test
  public void address_Validation_Works(){
    //empty check
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, empNum, phone, ""));
//    //special characters
    MatcherAssert.assertThat("should return false"
            , !AddUser.isValid(username, password, doB, empNum, phone, "!:'{%$@"));
  }
}