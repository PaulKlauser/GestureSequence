package com.paulklauser.gesturedetector

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.paulklauser.gesturedetector.GestureSequence.Builder
import timber.log.Timber
import java.util.*

// Timeout between gestures in a sequence
private const val TIMEOUT_MILLIS = 1000L

/**
 * Create a sequence of gestures that will be recognized on a view when completed in the correct
 * order.
 *
 * See [Builder] for public interface
 */
@SuppressLint("ClickableViewAccessibility")
class GestureSequence private constructor(
    view: View,
    private val gestures: List<Gesture>,
    private val onSuccess: () -> Unit
) {
    private var gesturesQueue: Queue<Gesture> = ArrayDeque(gestures)
    private var timeStampMs = -1L

    val gestureDetector = GestureDetector(view.context, object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onShowPress(e: MotionEvent) {

        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Timber.d("Gestures Queue: $gesturesQueue")
            checkTime()
            if (gesturesQueue.poll() != Gesture.Tap) {
                eventComplete()
            }
            checkCompletion()
            return true
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Timber.d("Gestures Queue: $gesturesQueue")
            checkTime()
            if (gesturesQueue.poll() != Gesture.LongPress) {
                eventComplete()
            }
            checkCompletion()
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return true
        }
    })

    init {
        view.setOnTouchListener { _, event ->
            val consumed = gestureDetector.onTouchEvent(event)
            consumed
        }
    }

    private fun checkTime() {
        if (System.currentTimeMillis() > timeStampMs + TIMEOUT_MILLIS) {
            eventComplete()
        }
        timeStampMs = System.currentTimeMillis()
    }

    private fun checkCompletion() {
        if (gesturesQueue.isEmpty()) {
            onSuccess()
            eventComplete()
        }
    }

    private fun eventComplete() {
        gesturesQueue = ArrayDeque(gestures)
    }

    /**
     * Intended public interface for [GestureSequence]
     *
     * Eg. Builder(view)
     *      .tap()
     *      .tap()
     *      .longPress()
     *      .applyToView { action() }
     * Results in action() being invoked when view has been tapped twice and then long pressed
     */
    class Builder(private val view: View) {
        private val gestures = mutableListOf<Gesture>()

        fun tap(): Builder {
            gestures.add(Gesture.Tap)
            return this
        }

        fun longPress(): Builder {
            gestures.add(Gesture.LongPress)
            return this
        }

        fun applyToView(onSuccess: () -> Unit) {
            GestureSequence(
                view,
                gestures,
                onSuccess
            )
        }
    }

}