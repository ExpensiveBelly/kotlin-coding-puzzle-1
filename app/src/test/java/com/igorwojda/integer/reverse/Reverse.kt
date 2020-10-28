package com.igorwojda.integer.reverse

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun reverseInt(i: Int): Int {
    if (i.toString().count() == 1) return i
    val number = (if (i < 0) i.toString().drop(1) else i.toString()).toCharArray().reversed().dropWhile { it == '0' }.joinToString("").toInt()
    return if (i < 0) number.unaryMinus() else number
}

class ReverseIntTest {
    @Test
    fun `ReverseInt handles 0 as an input`() {
        reverseInt(0) shouldEqual 0
    }

    @Test
    fun `ReverseInt flips a positive number`() {
        reverseInt(5) shouldEqual 5
        reverseInt(15) shouldEqual 51
        reverseInt(90) shouldEqual 9
        reverseInt(700) shouldEqual 7
        reverseInt(2359) shouldEqual 9532
    }

    @Test
    fun `ReverseInt flips a negative number`() {
        reverseInt(-5) shouldEqual -5
        reverseInt(-15) shouldEqual -51
        reverseInt(-90) shouldEqual -9
        reverseInt(-700) shouldEqual -7
        reverseInt(-2359) shouldEqual -9532
    }
}
