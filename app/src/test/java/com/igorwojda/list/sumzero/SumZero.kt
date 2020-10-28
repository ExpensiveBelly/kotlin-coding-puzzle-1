package com.igorwojda.list.sumzero

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun sumZero(list: List<Int>): Pair<Int, Int>? {
    if (list.isEmpty()) return null
    return list.toSet().combinations(2).map { Pair(it.first(), it.elementAt(1)) }.filter { it.first + it.second == 0 }.minBy { list.indexOf(it.first) }
}

/**
 * https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit/blob/master/src/main/java/com/marcinmoskala/math/CombinationsExt.kt
 */

fun <T> Set<T>.combinations(combinationSize: Int): Set<Set<T>> = when {
    combinationSize < 0 -> throw Error("combinationSize cannot be smaller then 0. It is equal to $combinationSize")
    combinationSize == 0 -> setOf(setOf())
    combinationSize >= size -> setOf(toSet())
    else -> powerset()
        .filter { it.size == combinationSize }
        .toSet()
}

fun <T> Collection<T>.powerset(): Set<Set<T>> = powerset(this, setOf(setOf()))

private tailrec fun <T> powerset(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    left.isEmpty() -> acc
    else -> powerset(left.drop(1), acc + acc.map { it + left.first() })
}

class SumZeroTest {
    @Test
    fun `sumZero empty list return null`() {
        sumZero(listOf()) shouldEqual null
    }

    @Test
    fun `sumZero 1, 2 return null`() {
        sumZero(listOf(1, 2)) shouldEqual null
    }

    @Test
    fun `sumZero 1, 2, 4, 7 return null`() {
        sumZero(listOf(1, 2, 4, 7)) shouldEqual null
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 3, 5 return Pair(-3, 3)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 3, 5)) shouldEqual Pair(-3, 3)
    }

    @Test
    fun `sumZero -4, -3, -2, 1, 2, 5 return Pair(-2, 2)`() {
        sumZero(listOf(-4, -3, -2, 1, 2, 5)) shouldEqual Pair(-2, 2)
    }
}
