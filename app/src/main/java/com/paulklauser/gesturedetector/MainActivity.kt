package com.paulklauser.gesturedetector

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

private const val ARG_GESTURES = "gestures"

fun createActivityIntent(context: Context, vararg gestures: Gesture): Intent {
    return Intent(context, MainActivity::class.java)
        .putParcelableArrayListExtra(ARG_GESTURES, ArrayList(gestures.toList()))
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        val gestures: List<Gesture> =
            intent?.extras?.getParcelableArrayList(ARG_GESTURES)
                ?: listOf(Gesture.Tap, Gesture.Tap, Gesture.LongPress)

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