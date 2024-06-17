package pw.binom.atomic

import kotlin.time.Duration
import kotlin.time.TimeSource

inline fun <T> AtomicBoolean.synchronize(func: () -> T): T {
    lock()
    try {
        return func()
    } finally {
        unlock()
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun AtomicBoolean.lock(timeout: Duration) {
    val bb = TimeSource.Monotonic.markNow()
    while (true) {
        if (compareAndSet(false, true)) {
            break
        }
        if (bb.elapsedNow() > timeout) {
            throw RuntimeException("Timeout $timeout")
        }
    }
}

fun AtomicBoolean.tryLock() = compareAndSet(false, true)

@Suppress("NOTHING_TO_INLINE")
inline fun AtomicBoolean.lock() {
    while (true) {
        if (tryLock()) {
            break
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun AtomicBoolean.unlock() {
    setValue(false)
}
