package com.igorwojda.list.coins

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test


private fun getCoins(amount: Int, coins: List<Int>): Int =
    coins.foldIndexed(
        IntArray(amount + 1).apply { set(0, 1) },
        { index: Int, acc: IntArray, _: Int ->
            acc.apply {
                indices.forEach { j ->
                    if (j >= coins[index]) {
                        acc[j] += acc[j - coins[index]]
                    }
                }
            }
        })[amount]

private class Test {
    @Test
    fun `4 wys`() {
        val actual: Int = getCoins(4, listOf(1, 2, 3))
        val expected = 4
        expected shouldBeEqualTo actual
    }

    @Test
    fun `one way`() {
        val actual: Int = getCoins(0, listOf(1, 2))
        val expected = 1
        expected shouldBeEqualTo actual
    }

    @Test
    fun `no coins returns 0`() {
        val actual: Int = getCoins(1, listOf())
        val expected = 0
        expected shouldBeEqualTo actual
    }

    @Test
    fun `big coins`() {
        val actual: Int = getCoins(5, listOf(25, 50))
        val expected = 0
        expected shouldBeEqualTo actual
    }

    @Test
    fun `big amount`() {
        val actual: Int = getCoins(50, listOf(5, 10))
        val expected = 6
        expected shouldBeEqualTo actual
    }

    @Test
    fun `a lot of change`() {
        val actual: Int = getCoins(100, listOf(1, 5, 10, 25, 50))
        val expected = 292
        expected shouldBeEqualTo actual
    }
}
