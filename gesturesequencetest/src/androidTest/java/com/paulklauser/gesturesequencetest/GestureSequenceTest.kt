package com.paulklauser.gesturesequencetest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.paulklauser.gesturesequence.Gesture
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GestureSequenceTest {

    @Test
    fun correct_tap_tap_long_press_sequence_succeeds() {
        ActivityScenario.launch<TestActivity>(
            createActivityIntent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                Gesture.Tap, Gesture.Tap, Gesture.LongPress
            )
        )

        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(longClick())

        onView(withId(R.id.success))
            .check(matches(isDisplayed()))
    }

    @Test
    fun incorrect_tap_tap_long_press_sequence_fails() {
        ActivityScenario.launch<TestActivity>(
            createActivityIntent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                Gesture.Tap, Gesture.Tap, Gesture.LongPress
            )
        )

        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())

        onView(withId(R.id.success))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun correct_tap_tap_tap_sequence_succeeds() {
        ActivityScenario.launch<TestActivity>(
            createActivityIntent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                Gesture.Tap, Gesture.Tap, Gesture.Tap
            )
        )

        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())

        onView(withId(R.id.success))
            .check(matches(isDisplayed()))
    }

    @Test
    fun tap_tap_long_press_sequence_with_timeout_after_one_tap_fails() {
        ActivityScenario.launch<TestActivity>(
            createActivityIntent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                Gesture.Tap, Gesture.Tap, Gesture.LongPress
            )
        )

        onView(withId(R.id.hello_world))
            .perform(click())
        Thread.sleep(1500)
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(longClick())

        onView(withId(R.id.success))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun tap_tap_long_press_sequence_with_timeout_after_one_tap_retry_succeeds() {
        ActivityScenario.launch<TestActivity>(
            createActivityIntent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                Gesture.Tap, Gesture.Tap, Gesture.LongPress
            )
        )

        onView(withId(R.id.hello_world))
            .perform(click())
        Thread.sleep(1500)
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(click())
        onView(withId(R.id.hello_world))
            .perform(longClick())

        onView(withId(R.id.success))
            .check(matches(isDisplayed()))
    }

}