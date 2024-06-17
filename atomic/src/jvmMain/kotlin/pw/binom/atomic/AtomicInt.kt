package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
@JvmInline
actual value class AtomicInt(@PublishedApi internal val native: InternalAtomicInt) {
    actual constructor(value: Int) : this(InternalAtomicInt(value))

    actual inline fun compareAndSet(expected: Int, new: Int): Boolean =
        native.compareAndSet(expected, new)

    actual inline fun compareAndSwap(expected: Int, new: Int): Int =
        native.compareAndExchange(expected, new)

    actual inline fun addAndGet(delta: Int): Int =
        native.addAndGet(delta)

    actual inline fun increment(): Int = native.incrementAndGet()

    actual inline fun decrement(): Int = native.decrementAndGet()

    actual inline operator fun inc(): AtomicInt {
        native.incrementAndGet()
        return this
    }

    actual inline operator fun dec(): AtomicInt {
        native.decrementAndGet()
        return this
    }

    actual inline fun getValue(): Int = native.get()

    actual inline fun setValue(value: Int) {
        native.set(value)
    }

    override fun toString(): String = "AtomicInt(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        setValue(value)
    }
}
