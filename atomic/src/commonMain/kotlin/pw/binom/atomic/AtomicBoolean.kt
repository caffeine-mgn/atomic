package pw.binom.atomic

import kotlin.reflect.KProperty

/**
 * dsdf
 */
expect value class AtomicBoolean(val native: InternalAtomicBoolean) {
    constructor(value: Boolean)

    inline fun compareAndSet(
        expected: Boolean,
        new: Boolean,
    ): Boolean

    inline fun compareAndSwap(
        expected: Boolean,
        new: Boolean,
    ): Boolean

    inline fun getValue(): Boolean

    inline fun setValue(value: Boolean)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean)
}
