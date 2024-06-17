package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicDouble(val native: InternalAtomicLong) {
    actual constructor(value: Double) : this(InternalAtomicLong(value.toBits()))

    actual inline fun compareAndSet(expected: Double, new: Double): Boolean =
        native.compareAndSet(expected.toBits(), new.toBits())

    actual inline fun getValue(): Double = Double.fromBits(native.value)

    actual inline fun setValue(value: Double) {
        native.value = value.toBits()
    }

    override fun toString(): String = "AtomicDouble(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Double = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        setValue(value)
    }
}
