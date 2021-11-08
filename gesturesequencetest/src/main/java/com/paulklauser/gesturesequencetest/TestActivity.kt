package com.paulklauser.gesturesequencetest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.paulklauser.gesturesequence.Gesture
import com.paulklauser.gesturesequence.GestureSequence

private const val ARG_GESTURES = "gestures"

fun createActivityIntent(context: Context, vararg gestures: Gesture): Intent {
    return Intent(context, TestActivity::class.java)
        .putParcelableArrayListExtra(ARG_GESTURES, ArrayList(gestures.toList()))
}

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gestures: List<Gesture> =
            intent?.extras?.getParcelableArrayList(ARG_GESTURES)
                ?: throw IllegalArgumentException("No gestures passed to the test activity!")

        GestureSequence.Builder(findViewById(R.id.hello_world))
            .apply {
                gestures.forEach { gesture ->
                    when (gesture) {
                        Gesture.LongPress -> longPress()
                        Gesture.Tap -> tap()
                    }
                }
            }
            .applyToView {
                findViewById<View>(R.id.success).visibility = View.VISIBLE
            }
    }
}