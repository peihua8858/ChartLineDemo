package com.peihua.chartline.component

import kotlin.random.Random

private fun mod(a: Float, b: Float): Float {
    val mod = a % b
    return if (mod >= 0) mod else mod + b
}

private fun differenceModulo(a: Float, b: Float, c: Float): Float {
    return mod(mod(a, c) - mod(b, c), c)
}

internal fun getProgressionLastElement(start: Float, end: Float, step: Float): Float = when {
    step > 0f -> if (start >= end) end else end - differenceModulo(end, start, step)
    step < 0f -> if (start <= end) end else end + differenceModulo(start, end, -step)
    else -> throw kotlin.IllegalArgumentException("Step is zero.")
}

public abstract class FloatIterator : Iterator<Float> {
    final override fun next(): Float = nextFloat()


    /**
     * Returns the next element in the iteration without boxing conversion.
     * @throws NoSuchElementException if the iteration has no next element.
     */
    public abstract fun nextFloat(): Float
}

internal class FloatProgressionIterator(first: Float, last: Float, val step: Float) : FloatIterator() {
    private val finalElement: Float = last
    private var hasNext: Boolean = if (step > 0) first <= last else first >= last
    private var next: Float = if (hasNext) first else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun nextFloat(): Float {
        val value = next
        if (value == finalElement) {
            if (!hasNext) throw kotlin.NoSuchElementException()
            hasNext = false
        } else {
            next += step
        }
        return value
    }
}

public open class FloatProgression
internal constructor
    (
    start: Float,
    endInclusive: Float,
    step: Float,
) : Iterable<Float> {
    init {
        if (step == 0f) throw kotlin.IllegalArgumentException("Step must be non-zero.")
        if (step == Float.MIN_VALUE) throw kotlin.IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.")
    }

    /**
     * The first element in the progression.
     */
    public val first: Float = start

    /**
     * The last element in the progression.
     */
    public val last: Float = getProgressionLastElement(start, endInclusive, step)

    /**
     * The step of the progression.
     */
    public val step: Float = step

    override fun iterator(): FloatIterator = FloatProgressionIterator(first, last, step)

    /**
     * Checks if the progression is empty.
     *
     * Progression with a positive step is empty if its first element is greater than the last element.
     * Progression with a negative step is empty if its first element is less than the last element.
     */
    public open fun isEmpty(): Boolean = if (step > 0) first > last else first < last

    override fun equals(other: Any?): Boolean =
        other is FloatProgression && (isEmpty() && other.isEmpty() ||
                first == other.first && last == other.last && step == other.step)

    override fun hashCode(): Int =
        (if (isEmpty()) -1 else (31 * (31 * first + last) + step)).toInt()

    override fun toString(): String = if (step > 0) "$first..$last step $step" else "$first downTo $last step ${-step}"

    public companion object {
        /**
         * Creates IntProgression within the specified bounds of a closed range.
         *
         * The progression starts with the [rangeStart] value and goes toward the [rangeEnd] value not excluding it, with the specified [step].
         * In order to go backwards the [step] must be negative.
         *
         * [step] must be greater than `Int.MIN_VALUE` and not equal to zero.
         */
        public fun fromClosedRange(rangeStart: Float, rangeEnd: Float, step: Float): FloatProgression =
            FloatProgression(rangeStart, rangeEnd, step)
    }
}

class FloatRange(start: Float, endInclusive: Float) : FloatProgression(start, endInclusive, 1f),
    ClosedRange<Float>, OpenEndRange<Float> {
    override val start: Float get() = first
    override val endInclusive: Float get() = last

    @Deprecated("Can throw an exception when it's impossible to represent the value with Int type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin("1.9")
    override val endExclusive: Float
        get() {
            if (last == Float.MAX_VALUE) error("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.")
            return last + 1
        }

    override fun contains(value: Float): Boolean = value in first..last

    /**
     * Checks whether the range is empty.
     *
     * The range is empty if its start value is greater than the end value.
     */
    override fun isEmpty(): Boolean = first > last

    override fun equals(other: Any?): Boolean =
        other is FloatRange && (isEmpty() && other.isEmpty())

    override fun hashCode(): Int =
        (if (isEmpty()) -1 else (31 * first + last)).toInt()

    override fun toString(): String = "$first..$last"

    public companion object {
        /** An empty range of values of type Int. */
        public val EMPTY: FloatRange = FloatRange(1f, 0f)
    }
}

public operator fun Float.rangeUntil(other: Short): FloatRange = FloatRange(this, other.toFloat())

public operator fun Float.rangeTo(that: Float): FloatRange = FloatRange(this, that)
public fun FloatRange.random(): Float {
    return random(Random)
}

public fun FloatRange.random(random: Random): Float {
    try {
        return random.nextFloat(this)
    } catch (e: IllegalArgumentException) {
        throw NoSuchElementException(e.message)
    }
}

public fun Random.nextFloat(range: FloatRange): Float = when {
    range.isEmpty() -> throw IllegalArgumentException("Cannot get random in empty range: $range")
    range.last < Float.MAX_VALUE -> nextDouble(range.first.toDouble(), (range.last + 1).toDouble()).toFloat()
    range.first > Float.MIN_VALUE -> (nextDouble((range.first - 1).toDouble(), range.last.toDouble()) + 1).toFloat()
    else -> nextFloat()
}