package com.igorwojda.string.ispalindrome.permutation

import com.igorwojda.string.ispalindrome.basic.isPalindrome
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun isPermutationPalindrome(str: String): Boolean =
    permutations(str).any { it.isPalindrome() }

private fun permutations(str: String) = permutations("", str)

private fun permutations(prefix: String, str: String, result: List<String> = emptyList()): List<String> {
    val n = str.length
    return if (n == 0) result + prefix
    else {
        (0 until n).map { i ->
            permutations(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }.flatten()
    }
}

private class Test {
    @Test
    fun `"gikig" is a palindrome`() {
        isPermutationPalindrome("gikig") shouldBeEqualTo true
    }

    @Test
    fun `"ookvk" is a palindrome`() {
        isPermutationPalindrome("ookvk") shouldBeEqualTo true
    }

    @Test
    fun `"sows" is a palindrome`() {
        isPermutationPalindrome("sows") shouldBeEqualTo false
    }

    @Test
    fun `"tami" is a palindrome`() {
        isPermutationPalindrome("tami") shouldBeEqualTo false
    }
}
