package com.example.bit603_a3_tonylawrie;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class AddItemTest {

  @Test
  public void Quantity_validation(){
    // validates that a quantity has been provided
    MatcherAssert.assertThat("Should return false",
            !AddItem.validated("cake", ""));
    MatcherAssert.assertThat("Should return false",
            !AddItem.validated("cake", "X"));
    MatcherAssert.assertThat("Should return false",
            !AddItem.validated("cake", "-4"));
    MatcherAssert.assertThat("Should return true",
            AddItem.validated("cake", "4"));
  }

  @Test
  public void ItemName_validation(){
    // check item name for special characters
    MatcherAssert.assertThat("Should return false",
            !AddItem.validated("X$%&!@#","1"));
    MatcherAssert.assertThat("Should return true",
            AddItem.validated("cake", 12));
  }
}