package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect value class AtomicFloat(val native: InternalAtomicInt) {
    constructor(value: Float)

    inline fun getValue(): Float
    inline fun setValue(value: Float)
    inline fun compareAndSet(expected: Float, new: Float): Boolean

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Float
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float)
}
