package com.igorwojda.integer.fibonacci.recursivecached

import org.amshove.kluent.shouldEqual
import org.junit.Test

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

class FibonacciSeriesRecursiveMethodCacheTest {
    @Test
    fun `calculates correct fib value for 0`() {
        fibonacciSequenceRecursiveCached(0) shouldEqual 0
    }

    @Test
    fun `calculates correct fib value for 1`() {
        fibonacciSequenceRecursiveCached(1) shouldEqual 1
    }

    @Test
    fun `calculates correct fib value for 2`() {
        fibonacciSequenceRecursiveCached(2) shouldEqual 1
    }

    @Test
    fun `calculates correct fib value for 3`() {
        fibonacciSequenceRecursiveCached(3) shouldEqual 2
    }

    @Test
    fun `calculates correct fib value for 4`() {
        fibonacciSequenceRecursiveCached(4) shouldEqual 3
    }

    @Test(timeout = 500)
    fun `calculates correct fib value for 45`() {
        fibonacciSequenceRecursiveCached(45) shouldEqual 1134903170
    }
}
