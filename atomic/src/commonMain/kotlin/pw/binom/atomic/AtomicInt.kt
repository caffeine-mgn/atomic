package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect value class AtomicInt(@PublishedApi internal val native: InternalAtomicInt) {
    constructor(value: Int)

    inline fun compareAndSet(expected: Int, new: Int): Boolean
    inline fun compareAndSwap(expected: Int, new: Int): Int
    inline fun addAndGet(delta: Int): Int
    inline fun increment(): Int
    inline fun decrement(): Int
    inline operator fun inc(): AtomicInt
    inline operator fun dec(): AtomicInt
    inline fun getValue(): Int
    inline fun setValue(value: Int)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int)
}
