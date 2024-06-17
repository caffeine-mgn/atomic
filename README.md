# atomic
Kotlin Common Atomic

## Description
Library Atomic Multiplatform implementation

## Get starting
Add to your `build.gradle.kts` file new dependency:
```kotlin
kotlin {
    api("pw.binom:atomic:0.0.4") // atomic library
}
```

## Example

```kotlin
import pw.binom.atomic.*

val value = AtomicInt(0)
println(value.increment())
println(value.addAndGet(1))
```