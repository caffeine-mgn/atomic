package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicBoolean(val native: InternalAtomicBoolean) {
    actual constructor(value: Boolean) : this(InternalAtomicBoolean(if (value) 1 else 0))

    actual inline fun compareAndSet(expected: Boolean, new: Boolean): Boolean =
        native.compareAndSet(if (expected) 1 else 0, if (new) 1 else 0)

    actual inline fun compareAndSwap(expected: Boolean, new: Boolean): Boolean =
        native.compareAndExchange(if (expected) 1 else 0, if (new) 1 else 0) > 0

    actual inline fun getValue(): Boolean = native.value > 0

    actual inline fun setValue(value: Boolean) {
        native.value = if (value) 1 else 0
    }

    override fun toString(): String = "AtomicBoolean(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        setValue(value)
    }
}
