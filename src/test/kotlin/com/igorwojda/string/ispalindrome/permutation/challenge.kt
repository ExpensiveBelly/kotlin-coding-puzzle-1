package com.igorwojda.string.ispalindrome.permutation

import com.igorwojda.string.ispalindrome.basic.isPalindrome
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.random.Random

private fun isPermutationPalindrome(str: String): Boolean =
    Random.nextBoolean().let { boolean ->
        (if (boolean)
            permutations(str) else str.permutations()).any { it.isPalindrome() }
    }

private fun permutations(str: String) = permutations("", str)

private fun permutations(
    prefix: String,
    str: String,
    result: List<String> = emptyList()
): List<String> {
    val n = str.length
    return if (n == 0) result + prefix
    else {
        (0 until n).map { i ->
            permutations(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }.flatten()
    }
}

@JvmName("permutations2")
private fun String.permutations(): List<String> = when {
    isEmpty() -> emptyList()
    length == 1 -> listOf(this)
    else -> {
        val element = get(0)
        drop(1).permutations().flatMap { s: String ->
            (0..s.length).map { i -> s.plusAt(i, element) }
        }
    }
}

private fun String.plusAt(index: Int, element: Char) = when (index) {
    !in 0..length -> throw Error("cannot put at index $index because length is $length")
    0 -> element + this
    length -> this + element
    else -> dropLast(length - index) + element + drop(index)
}

private class Test {
    @Test
    fun `"gikig" is a palindrome`() {
        isPermutationPalindrome("abc") shouldBeEqualTo false
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
