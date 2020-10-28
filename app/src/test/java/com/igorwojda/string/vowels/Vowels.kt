package com.igorwojda.string.vowels

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun vowels(str: String): Int {
    return str.filter { char -> Vowels.values().any { it.value == char.toString().toLowerCase() } }.count()
}

enum class Vowels(val value: String) {
    A("a"),
    E("e"),
    I("i"),
    O("o"),
    U("u"),
    Y("y")
}

class VowelsTest {
    @Test
    fun `"aeiouy" has 6 vowels`() {
        vowels("aeiouy") shouldEqual 6
    }

    @Test
    fun `"AEIOUY" has 6 vowels`() {
        vowels("AEIOUY") shouldEqual 6
    }

    @Test
    fun `"abcdefghijklmnopqrstuvwxyz" has 6 vovels`() {
        vowels("abcdefghijklmnopqrstuvwxyz") shouldEqual 6
    }

    @Test
    fun `"bcadfaghijkl" has 3 vovels`() {
        vowels("bcadfaghijkl") shouldEqual 3
    }

    @Test
    fun `"bcdfghjkl" has 0 vovels`() {
        vowels("bcdfghjkl") shouldEqual 0
    }
}
