# GestureSequence

A basic library that enables easy composition of gesture sequence recognition on a view.

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

## Gradle Dependency

Make sure you have `mavenCentral()` in your repositories block
```groovy
implementation 'io.github.paulklauser:gesturesequence:0.2'
```