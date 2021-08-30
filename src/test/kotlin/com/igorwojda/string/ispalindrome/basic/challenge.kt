package com.igorwojda.string.ispalindrome.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

fun String.isPalindrome(): Boolean {
    return this == reversed()
}

private class Test {

    @Test
    fun `"aba" is a palindrome`() {
        "aba".isPalindrome() shouldBeEqualTo true
    }

    @Test
    fun `" aba" is not a palindrome`() {
        " aba".isPalindrome() shouldBeEqualTo false
    }

    @Test
    fun `"aba " is not a palindrome`() {
        "aba ".isPalindrome() shouldBeEqualTo false
    }

    @Test
    fun `"greetings" is not a palindrome`() {
        "greetings".isPalindrome() shouldBeEqualTo false
    }

    @Test
    fun `"1000000001" a palindrome`() {
        "1000000001".isPalindrome() shouldBeEqualTo true
    }

    @Test
    fun `"Fish hsif" is not a palindrome`() {
        "Fish hsif".isPalindrome() shouldBeEqualTo false
    }

    @Test
    fun `"pennep" a palindrome`() {
        "pennep".isPalindrome() shouldBeEqualTo true
    }
}
