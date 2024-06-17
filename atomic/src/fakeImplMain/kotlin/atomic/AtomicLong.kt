package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicLong(val native: InternalAtomicLong) {
    actual constructor(value: Long) : this(InternalAtomicLong(value))

    actual inline fun compareAndSet(expected: Long, new: Long): Boolean {
        if (native.value != expected) {
            return false
        }
        native.value = new
        return true
    }

    actual inline fun compareAndSwap(expected: Long, new: Long): Long {
        val oldValue = native.value
        if (oldValue != expected) {
            return native.value
        }
        native.value = new
        return oldValue
    }

    actual inline fun addAndGet(delta: Long): Long {
        native.value += delta
        return native.value
    }

    actual inline fun increment() {
        addAndGet(1)
    }

    actual inline fun decrement() {
        addAndGet(-1)
    }

    actual inline operator fun inc(): AtomicLong {
        increment()
        return this
    }

    actual inline operator fun dec(): AtomicLong {
        decrement()
        return this
    }

    actual inline fun getValue(): Long = native.value

    actual inline fun setValue(value: Long) {
        native.value = value
    }

    override fun toString(): String = "AtomicLong(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Long = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        setValue(value)
    }
}
