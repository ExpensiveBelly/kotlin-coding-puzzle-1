package com.igorwojda.integer.fibonacci.recursivecached

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

private fun fibonacciSequenceRecursiveCached(
    n: Int,
    /*methodCache: MutableList<MethodCache> = mutableListOf()*/
    methodCache: MutableMap<Int, MethodCache> = mutableMapOf(
        0 to MethodCache(0, 0),
        1 to MethodCache(1, 1)
    )
): Int =
    methodCache.getOrPut(n, {
        MethodCache(
            n,
            fibonacciSequenceRecursiveCached(n - 1, methodCache) + fibonacciSequenceRecursiveCached(
                n - 2,
                methodCache
            )
        )
    }).result

private data class MethodCache(val n: Int, val result: Int)

private class Test {
    @Test
    fun `calculates correct fib value for 0`() {
        fibonacciSequenceRecursiveCached(0) shouldBeEqualTo 0
    }

    @Test
    fun `calculates correct fib value for 1`() {
        fibonacciSequenceRecursiveCached(1) shouldBeEqualTo 1
    }

    @Test
    fun `calculates correct fib value for 2`() {
        fibonacciSequenceRecursiveCached(2) shouldBeEqualTo 1
    }

    @Test
    fun `calculates correct fib value for 3`() {
        fibonacciSequenceRecursiveCached(3) shouldBeEqualTo 2
    }

    @Test
    fun `calculates correct fib value for 4`() {
        fibonacciSequenceRecursiveCached(4) shouldBeEqualTo 3
    }

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    fun `calculates correct fib value for 45`() {
        fibonacciSequenceRecursiveCached(45) shouldBeEqualTo 1134903170
    }
}
