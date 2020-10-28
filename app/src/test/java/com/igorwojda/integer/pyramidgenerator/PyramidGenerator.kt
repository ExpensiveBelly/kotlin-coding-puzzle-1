package com.igorwojda.integer.pyramidgenerator

import org.amshove.kluent.shouldEqual
import org.junit.Test

fun generatePyramid(n: Int): List<String> {
    return (1..n).fold(emptyList()) { acc: List<String>, i: Int -> acc + listOf(" ".repeat(n - i) + "#".repeat((acc.lastOrNull()?.count { it == '#' }?.plus(2) ?: 1)) + " ".repeat(n - i)) }
}

class PyramidGeneratorTest {

    @Test
    fun `pyramid n = 2`() {
        generatePyramid(2).also {
            it.size shouldEqual 2
            it[0] shouldEqual " # "
            it[1] shouldEqual "###"
        }
    }

    @Test
    fun `pyramid n = 3`() {
        generatePyramid(3).also {
            it.size shouldEqual 3
            it[0] shouldEqual "  #  "
            it[1] shouldEqual " ### "
            it[2] shouldEqual "#####"
        }
    }

    @Test
    fun `pyramid n = 4`() {
        generatePyramid(4).also {
            it.size shouldEqual 4
            it[0] shouldEqual "   #   "
            it[1] shouldEqual "  ###  "
            it[2] shouldEqual " ##### "
            it[3] shouldEqual "#######"
        }
    }
}
