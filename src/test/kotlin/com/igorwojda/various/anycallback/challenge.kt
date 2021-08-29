package com.igorwojda.various.anycallback

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun anyCallback(list: List<Int>, callback: (Int) -> Boolean): Boolean {
    return list.any(callback)
}

private class Test {
    @Test
    fun `any callback returns true`() {
        val callback: ((Int) -> Boolean) = { it > 3 }
        anyCallback(listOf(1, 2, 3, 4), callback) shouldBeEqualTo true
    }

    @Test
    fun `any callback returns false`() {
        val x = sequenceOf(1, 2, 3).filter { print("$it "); it >= 2 }
        print("before sum ")
//        println(x.sum())
    }
}
