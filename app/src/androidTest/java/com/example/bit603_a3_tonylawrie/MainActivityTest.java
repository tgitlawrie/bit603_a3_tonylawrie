package com.example.bit603_a3_tonylawrie;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
          new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void mainActivityTest() {
    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatEditText = onView(
            allOf(withId(R.id.usernameInput),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText.perform(click());

    ViewInteraction appCompatEditText2 = onView(
            allOf(withId(R.id.usernameInput),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText2.perform(replaceText("Admin"), closeSoftKeyboard());

    ViewInteraction appCompatEditText3 = onView(
            allOf(withId(R.id.passwordInput),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            3),
                    isDisplayed()));
    appCompatEditText3.perform(replaceText("CookieManagement84"), closeSoftKeyboard());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction bottomNavigationItemView = onView(
            allOf(withId(R.id.add_item), withContentDescription("Add Item"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.MainNavigation),
                                    0),
                            1),
                    isDisplayed()));
    bottomNavigationItemView.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatEditText4 = onView(
            allOf(withId(R.id.item_input),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText4.perform(replaceText("test item"), closeSoftKeyboard());

    DataInteraction materialTextView = onData(anything())
            .inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
                    childAtPosition(
                            withId(com.google.android.material.R.id.contentPanel),
                            0)))
            .atPosition(1);
    materialTextView.perform(click());

    ViewInteraction appCompatEditText5 = onView(
            allOf(withId(R.id.quantity_input),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            3),
                    isDisplayed()));
    appCompatEditText5.perform(replaceText("4"), closeSoftKeyboard());

    ViewInteraction appCompatImageButton = onView(
            allOf(withId(R.id.add_img_btn), withContentDescription("ADD Button"),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            5),
                    isDisplayed()));
    appCompatImageButton.perform(click());

    ViewInteraction materialButton = onView(
            allOf(withId(android.R.id.button1), withText("Add"),
                    childAtPosition(
                            childAtPosition(
                                    withId(com.google.android.material.R.id.buttonPanel),
                                    0),
                            3)));
    materialButton.perform(scrollTo(), click());

    pressBack();

    ViewInteraction bottomNavigationItemView2 = onView(
            allOf(withId(R.id.inventory), withContentDescription("Inventory"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.MainNavigation),
                                    0),
                            2),
                    isDisplayed()));
    bottomNavigationItemView2.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction textView = onView(
            allOf(withId(R.id.inventory_list), withText("test item\ncookie?Quantity: 4\n\n"),
                    withParent(withParent(withId(android.R.id.content))),
                    isDisplayed()));
    textView.check(matches(withText("test item cookie?Quantity: 4  ")));
  }

  private static Matcher<View> childAtPosition(
          final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
                && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
