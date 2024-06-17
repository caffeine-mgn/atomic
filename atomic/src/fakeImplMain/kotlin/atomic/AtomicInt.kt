package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicInt(@PublishedApi internal val native: InternalAtomicInt) {
    actual constructor(value: Int) : this(InternalAtomicInt(value))

    actual inline fun compareAndSet(expected: Int, new: Int): Boolean {
        if (native.value != expected) {
            return false
        }
        native.value = new
        return true
    }

    actual inline fun compareAndSwap(expected: Int, new: Int): Int {
        val oldValue = native.value
        if (oldValue != expected) {
            return native.value
        }
        native.value = new
        return oldValue
    }

    actual inline fun addAndGet(delta: Int): Int {
        native.value += delta
        return native.value
    }

    actual inline fun increment() = addAndGet(1)

    actual inline fun decrement() = addAndGet(-1)

    actual inline operator fun inc(): AtomicInt {
        increment()
        return this
    }

    actual inline operator fun dec(): AtomicInt {
        decrement()
        return this
    }

    actual inline fun getValue(): Int = native.value

    actual inline fun setValue(value: Int) {
        native.value = value
    }

    override fun toString(): String = "AtomicInt(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        setValue(value)
    }
}
