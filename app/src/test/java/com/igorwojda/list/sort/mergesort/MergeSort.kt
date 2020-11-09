package com.igorwojda.list.sort.mergesort

import org.amshove.kluent.shouldEqual
import org.junit.Test

@ExperimentalStdlibApi
private fun mergeSort(list: List<Int>): List<Int> {
    fun merge(left: List<Int>, right: List<Int>): List<Int> =
        DeepRecursiveFunction<Pair<List<Int>, List<Int>>, List<Int>> { (leftList, rightList) ->
            when {
                leftList.isNotEmpty() && rightList.isNotEmpty() -> {
                    when {
                        leftList.first() <= rightList.first() -> listOf(leftList.first()) + callRecursive(
                            leftList.drop(1) to rightList
                        )
                        else -> listOf(rightList.first()) + callRecursive(
                            leftList to rightList.drop(1)
                        )
                    }
                }
                leftList.isNotEmpty() -> leftList
                rightList.isNotEmpty() -> rightList
                else -> emptyList()
            }

        }(left to right)

    return DeepRecursiveFunction<List<Int>, List<Int>> { elements ->
        val mid = elements.size / 2
        when {
            elements.size <= 1 -> elements
            else -> merge(
                callRecursive(elements.subList(0, mid)),
                callRecursive(elements.subList(mid, elements.size))
            )
        }
    }(list)
}

@ExperimentalStdlibApi
class MergeSortTest {
    @Test
    fun `merge sort empty list`() {
        mergeSort(mutableListOf()) shouldEqual listOf()
    }

    @Test
    fun `merge sort 7`() {
        mergeSort(mutableListOf(7)) shouldEqual listOf(7)
    }

    @Test
    fun `merge sort empty list 9, 3`() {
        mergeSort(mutableListOf(9, 3)) shouldEqual listOf(3, 9)
    }

    @Test
    fun `merge sort 5, 1, 4, 2`() {
        mergeSort(mutableListOf(5, 1, 4, 2)) shouldEqual listOf(1, 2, 4, 5)
    }

    @Test
    fun `merge sort 5, 2, 1, 8, 4, 7, 6, 3`() {
        mergeSort(mutableListOf(5, 2, 1, 8, 4, 7, 6, 3)) shouldEqual listOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `merge sort 17, 4, 12, 19, 80, 75, 16`() {
        mergeSort(mutableListOf(17, 4, 12, 19, 80, 75, 16)) shouldEqual listOf(4, 12, 16, 17, 19, 75, 80)
    }

    @Test
    fun `merge sort 11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27`() {
        val list = mutableListOf(11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27)
        val sorted = mutableListOf(6, 10, 11, 19, 20, 20, 23, 27, 30, 32, 40, 40, 41, 42, 43, 50)

        mergeSort(list) shouldEqual sorted
    }
}
