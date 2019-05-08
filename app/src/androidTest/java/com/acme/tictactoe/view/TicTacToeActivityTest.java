package com.acme.tictactoe.view;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acme.tictactoe.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static org.junit.Assert.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TicTacToeActivityTest {

    private TicTacToeView view;

    @Rule
    public ActivityTestRule<TicTacToeActivity> activityTestRule
            = new ActivityTestRule<TicTacToeActivity>(TicTacToeActivity.class);
    @Before
    public void setUp() throws Exception {
        this.view = view;
    }

    @After
    public void tearDown() throws Exception {

    }

    // ViewGroup 내 Child 버튼 View 찾기
    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) return false;
                ViewGroup parent = (ViewGroup) view.getParent();

                return parentMatcher.matches(parent)
                        && parent.getChildCount() > childPosition
                        && parent.getChildAt(childPosition).equals(view);
            }
        };
    }
    /**
     *   X | O | X
     *   O | X | O |
     *   X |  |
     **/
    @Test
    public void testCaseXWin() {
        onView(nthChildOf(withId(R.id.buttonGrid), 0)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 1)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 2)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 3)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 4)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 5)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 6)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("X")));
        onView(withId(R.id.winnerLabel)).check(matches(withText("Winner")));
        onView(withId(R.id.action_reset)).perform(click());
    }

    /**
     *   X | O | X
     *       O | X |
     *     | O |
     **/
    @Test
    public void testCaseOWin() {
        onView(nthChildOf(withId(R.id.buttonGrid), 0)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 1)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 2)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 4)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 5)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 7)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("O")));
        onView(withId(R.id.winnerLabel)).check(matches(withText("Winner")));
        onView(withId(R.id.action_reset)).perform(click());

    }

    @Test
    public void testCaseXWinAfterReset() {
        onView(nthChildOf(withId(R.id.buttonGrid), 0)).perform(click());
        onView(nthChildOf(withId(R.id.buttonGrid), 4)).perform(click());
        onView(nthChildOf(withId(R.id.buttonGrid), 8)).perform(click());
        onView(withId(R.id.action_reset)).perform(click());

        onView(nthChildOf(withId(R.id.buttonGrid), 0)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 1)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 2)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 3)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 4)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 5)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("")));

        onView(nthChildOf(withId(R.id.buttonGrid), 6)).perform(click());
        onView(withId(R.id.winnerPlayerLabel)).check(matches(withText("X")));
        onView(withId(R.id.winnerLabel)).check(matches(withText("Winner")));
    }
}