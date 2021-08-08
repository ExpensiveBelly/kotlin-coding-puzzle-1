package com.igorwojda.string.vowels

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

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

private class Test {
    @Test
    fun `"aeiouy" has 6 vowels`() {
        vowels("aeiouy") shouldBeEqualTo 6
    }

    @Test
    fun `"AEIOUY" has 6 vowels`() {
        vowels("AEIOUY") shouldBeEqualTo 6
    }

    @Test
    fun `"abcdefghijklmnopqrstuvwxyz" has 6 vovels`() {
        vowels("abcdefghijklmnopqrstuvwxyz") shouldBeEqualTo 6
    }

    @Test
    fun `"bcadfaghijkl" has 3 vovels`() {
        vowels("bcadfaghijkl") shouldBeEqualTo 3
    }

    @Test
    fun `"bcdfghjkl" has 0 vovels`() {
        vowels("bcdfghjkl") shouldBeEqualTo 0
    }
}
