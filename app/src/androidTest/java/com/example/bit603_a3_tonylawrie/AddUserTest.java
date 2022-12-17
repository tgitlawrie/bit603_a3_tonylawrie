package com.example.bit603_a3_tonylawrie;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddUserTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
          new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void addUserTest() {
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

    ViewInteraction materialButton2 = onView(
            allOf(withId(R.id.users_add_btn), withText("Add User"),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            3),
                    isDisplayed()));
    materialButton2.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatEditText4 = onView(
            allOf(withId(R.id.add_userName),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText4.perform(click());

    ViewInteraction appCompatEditText5 = onView(
            allOf(withId(R.id.add_userName),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            2),
                    isDisplayed()));
    appCompatEditText5.perform(replaceText("test user"), closeSoftKeyboard());

    ViewInteraction appCompatEditText6 = onView(
            allOf(withId(R.id.add_password),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            3),
                    isDisplayed()));
    appCompatEditText6.perform(replaceText("password"), closeSoftKeyboard());

    ViewInteraction editDate = onView(
            allOf(withId(R.id.add_dob),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            4),
                    isDisplayed()));
    editDate.perform(replaceText("1985-05-12"), closeSoftKeyboard());

    ViewInteraction appCompatEditText7 = onView(
            allOf(withId(R.id.add_emp_number),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            5),
                    isDisplayed()));
    appCompatEditText7.perform(click());

    ViewInteraction appCompatEditText8 = onView(
            allOf(withId(R.id.add_emp_number),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            5),
                    isDisplayed()));
    appCompatEditText8.perform(replaceText("32"), closeSoftKeyboard());

    ViewInteraction appCompatEditText9 = onView(
            allOf(withId(R.id.add_phone),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            6),
                    isDisplayed()));
    appCompatEditText9.perform(click());

    ViewInteraction appCompatEditText10 = onView(
            allOf(withId(R.id.add_phone),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            6),
                    isDisplayed()));
    appCompatEditText10.perform(replaceText("123456789"), closeSoftKeyboard());

    ViewInteraction appCompatEditText11 = onView(
            allOf(withId(R.id.add_address),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            7),
                    isDisplayed()));
    appCompatEditText11.perform(click());

    ViewInteraction appCompatEditText12 = onView(
            allOf(withId(R.id.add_address),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            7),
                    isDisplayed()));
    appCompatEditText12.perform(replaceText("123 street"), closeSoftKeyboard());

    ViewInteraction materialButton4 = onView(
            allOf(withId(R.id.add_user_btn), withText("Add"),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            9),
                    isDisplayed()));
    materialButton4.perform(click());

    ViewInteraction materialButton5 = onView(
            allOf(withId(android.R.id.button1), withText("Create"),
                    childAtPosition(
                            childAtPosition(
                                    withId(com.google.android.material.R.id.buttonPanel),
                                    0),
                            3)));
    materialButton5.perform(scrollTo(), click());

    ViewInteraction bottomNavigationItemView = onView(
            allOf(withId(R.id.logout), withContentDescription("Logout"),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.MainNavigation),
                                    0),
                            0),
                    isDisplayed()));
    bottomNavigationItemView.perform(click());

    ViewInteraction materialButton6 = onView(
            allOf(withId(android.R.id.button1), withText("Yes"),
                    childAtPosition(
                            childAtPosition(
                                    withId(com.google.android.material.R.id.buttonPanel),
                                    0),
                            3)));
    materialButton6.perform(scrollTo(), click());
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
