package pw.binom.atomic

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
actual value class AtomicFloat(val native: InternalAtomicInt) {
    actual constructor(value: Float) : this(InternalAtomicInt(value.toBits()))

    actual inline fun compareAndSet(expected: Float, new: Float): Boolean =
        native.compareAndSet(expected.toBits(), new.toBits())

    actual inline fun getValue(): Float = Float.fromBits(native.value)

    actual inline fun setValue(value: Float) {
        native.value = value.toBits()
    }

    override fun toString(): String = "AtomicFloat(${getValue()})"

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): Float = getValue()
    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        setValue(value)
    }
}
