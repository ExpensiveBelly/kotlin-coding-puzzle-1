package com.igorwojda.list.search.binarysearch

import org.amshove.kluent.shouldEqual
import org.junit.Test

@ExperimentalStdlibApi
private fun binarySearch(list: List<Char>, element: Char) =
    DeepRecursiveFunction<Pair<Int, Int>, Int> { (start, end) ->
        val mid = (start + end) / 2
        when {
            start > end -> -1
            list[mid] == element -> mid
            element > list[mid] -> callRecursive(Pair(mid + 1, end))
            else -> callRecursive(Pair(start, mid - 1))
        }
    }(Pair(0, list.size - 1))

@ExperimentalStdlibApi
class BinarySearchTest {
    @Test
    fun `index of A in A, B, C is 0`() {
        binarySearch(listOf('A', 'B', 'C'), 'A') shouldEqual 0
    }

    @Test
    fun `index of B in A, B, C is 1`() {
        binarySearch(listOf('A', 'B', 'C'), 'B') shouldEqual 1
    }

    @Test
    fun `index of C in A, B, C is 2`() {
        binarySearch(listOf('A', 'B', 'C'), 'C') shouldEqual 2
    }

    @Test
    fun `index of H in A, B, C is -1`() {
        binarySearch(listOf('A', 'B', 'C'), 'H') shouldEqual -1
    }

    @Test
    fun `index of A in A, B, C, D is 0`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'A') shouldEqual 0
    }

    @Test
    fun `index of B in A, B, C, D is 1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'B') shouldEqual 1
    }

    @Test
    fun `index of C in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'C') shouldEqual 2
    }

    @Test
    fun `index of D in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'D') shouldEqual 3
    }

    @Test
    fun `index of H in A, B, C, D is -1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'H') shouldEqual -1
    }
}
