package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect value class AtomicDouble(val native: InternalAtomicLong) {
    constructor(value: Double)

    inline fun getValue(): Double
    inline fun setValue(value: Double)
    inline fun compareAndSet(expected: Double, new: Double): Boolean

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Double
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double)
}
