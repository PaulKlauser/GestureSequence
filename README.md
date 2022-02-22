# GestureSequence

A basic library that enables easy composition of gesture sequence recognition on a view.

## Inspiration
This was inspired by the existence of [UILongPressGestureRecognizer](https://developer.apple.com/documentation/uikit/uilongpressgesturerecognizer) on iOS

## Usage

```kotlin
// Perform action() when the view is tapped twice followed by a long press
GestureSequence.Builder(view)
    .tap()
    .tap()
    .longPress()
    .applyToView { action() }
```

### Supported gestures:
* Tap
* Long press

## Gradle Dependency

Make sure you have `mavenCentral()` in your repositories block
```groovy
implementation 'io.github.paulklauser:gesturesequence:0.2'
```