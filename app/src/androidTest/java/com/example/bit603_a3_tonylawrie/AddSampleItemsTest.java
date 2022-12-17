package com.example.bit603_a3_tonylawrie;


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

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
public class AddSampleItemsTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
          new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void addSampleItemsTest() {
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

    ViewInteraction materialButton = onView(
            allOf(withId(R.id.login_button), withText("Login"), withContentDescription("initiate login"),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            4),
                    isDisplayed()));
    materialButton.perform(click());

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

    ViewInteraction bottomNavigationItemView3 = onView(
            allOf(withId(R.id.add_test_data), withContentDescription("Add Test Items"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.inventoryNavigation),
                                    0),
                            1),
                    isDisplayed()));
    bottomNavigationItemView3.perform(click());

    ViewInteraction materialButton2 = onView(
            allOf(withId(android.R.id.button1), withText("Yes"),
                    childAtPosition(
                            childAtPosition(
                                    withId(com.google.android.material.R.id.buttonPanel),
                                    0),
                            3)));
    materialButton2.perform(scrollTo(), click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction textView = onView(
            allOf(withId(R.id.itemCountText), withText("Items: 20"),
                    withParent(withParent(withId(android.R.id.content))),
                    isDisplayed()));
    textView.check(matches(withText("Items: 20")));

    ViewInteraction textView2 = onView(
            allOf(withId(R.id.page_counter), withText("1/4"),
                    withParent(withParent(withId(android.R.id.content))),
                    isDisplayed()));
    textView2.check(matches(withText("1/4")));

    ViewInteraction bottomNavigationItemView4 = onView(
            allOf(withId(R.id.logout), withContentDescription("Logout"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.MainNavigation),
                                    0),
                            0),
                    isDisplayed()));
    bottomNavigationItemView4.perform(click());

    ViewInteraction materialButton3 = onView(
            allOf(withId(android.R.id.button1), withText("Yes"),
                    childAtPosition(
                            childAtPosition(
                                    withId(com.google.android.material.R.id.buttonPanel),
                                    0),
                            3)));
    materialButton3.perform(scrollTo(), click());
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
