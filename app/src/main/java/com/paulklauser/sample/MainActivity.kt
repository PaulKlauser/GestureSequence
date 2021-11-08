package com.paulklauser.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.paulklauser.gesturesequence.GestureSequence
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

        GestureSequence.Builder(findViewById(R.id.hello_world))
            .tap()
            .tap()
            .longPress()
            .applyToView {
                findViewById<View>(R.id.success).visibility = View.VISIBLE
            }
    }
}