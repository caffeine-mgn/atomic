package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
@JvmInline
actual value class AtomicBoolean actual constructor(val native: InternalAtomicBoolean) {
    actual constructor(value: Boolean) : this(InternalAtomicBoolean(value))

    actual inline fun compareAndSet(expected: Boolean, new: Boolean): Boolean =
        native.compareAndSet(expected, new)

    actual inline fun compareAndSwap(expected: Boolean, new: Boolean): Boolean =
        native.compareAndSet(expected, new)

    actual inline fun getValue(): Boolean = native.get()

    actual inline fun setValue(value: Boolean) {
        native.set(value)
    }

    override fun toString(): String = "AtomicBoolean(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        setValue(value)
    }
}
