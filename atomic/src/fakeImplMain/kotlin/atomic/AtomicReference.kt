package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicReference<T>(val native: InternalAtomicReference<T>) {
    actual constructor(value: T) : this(InternalAtomicReference(value))

    actual inline fun compareAndSet(expected: T, new: T): Boolean {
        if (native.value !== expected) {
            return false
        }
        native.value = new
        return true
    }

    actual inline fun compareAndSwap(expected: T, new: T): T {
        val oldValue = native.value
        if (oldValue !== expected) {
            return native.value
        }
        native.value = new
        return oldValue
    }

    actual inline fun getValue(): T = native.value

    actual inline fun setValue(value: T) {
        native.value = value
    }

    override fun toString(): String = "AtomicReference(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setValue(value)
    }
}
