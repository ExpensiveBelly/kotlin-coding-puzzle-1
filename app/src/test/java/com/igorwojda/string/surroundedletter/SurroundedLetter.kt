package com.igorwojda.string.surroundedletter

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun surroundedLetter(str: String) =
//    return str.mapNotNull { if (it.isLetter()) it else null }.map { "+$it+" }.all { it in str } //Wrong solution as it doesn't consider duplicates
    str.mapNotNull { if (it.isLetter()) it else null }.groupingBy { it }.eachCount().mapKeys { "+${it.key}+" }.all { entry -> str.sumBy { if (entry.key in str) 1 else 0 }.div(str.length) == entry.value }

class CaesarCipherTest {
    @Test
    fun `"a" return "false"`() {
        surroundedLetter("a") shouldEqual false
    }

    @Test
    fun `"+a+" return "true"`() {
        surroundedLetter("+a+") shouldEqual true
    }

    @Test
    fun `"a+" return "false"`() {
        surroundedLetter("a+") shouldEqual false
    }

    @Test
    fun `"+a" return "false"`() {
        surroundedLetter("+a") shouldEqual false
    }

    @Test
    fun `"+a+b+" return "true"`() {
        surroundedLetter("+a+b+") shouldEqual true
    }

    @Test
    fun `"+a++b+" return "true"`() {
        surroundedLetter("+a++b+") shouldEqual true
    }

    @Test
    fun `"+ab+" return "false"`() {
        surroundedLetter("+ab+") shouldEqual false
    }

    @Test
    fun `"a+b+" return "false"`() {
        surroundedLetter("a+b+") shouldEqual false
    }

    @Test
    fun `"+a+b" return "false"`() {
        surroundedLetter("+a+b") shouldEqual false
    }

    @Test
    fun `"+a+b+++c++d+e++" return "true"`() {
        surroundedLetter("+a+b+++c++d+e++") shouldEqual true
    }

    @Test
    fun `"+++a+d++de++e++" return "false"`() {
        surroundedLetter("+a+d++de++e+") shouldEqual false
    }
}
