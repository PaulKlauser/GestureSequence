# GestureSequence

Playground for building a library to enable easy composition of gesture sequence recognition on a view.

Basic API looks like:

```kotlin
// Perform action() when the view is tapped twice followed by a long press
GestureSequence.Builder(view)
    .tap()
    .tap()
    .longPress()
    .applyToView { action() }
```

Supported gestures:
* Tap
* Long press