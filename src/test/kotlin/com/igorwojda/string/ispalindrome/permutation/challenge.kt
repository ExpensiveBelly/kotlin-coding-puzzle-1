package com.igorwojda.string.ispalindrome.permutation

import com.igorwojda.string.ispalindrome.basic.isPalindrome
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.random.Random

@ExperimentalStdlibApi
private fun isPermutationPalindrome(str: String): Boolean =
    Random.nextInt(3).let { randomInt ->
        (when (randomInt) {
            0 -> permutationsUnsafe(str)
            1 -> str.permutationsUnsafe()
            2 -> str.permutationsSafe()
            else -> throw IllegalArgumentException()
        }).any { it.isPalindrome() }
    }

private fun permutationsUnsafe(str: String) =
    permutationsUnsafe("", str)

private fun permutationsUnsafe(
    prefix: String,
    str: String,
    result: List<String> = emptyList()
): List<String> {
    val n = str.length
    return if (n == 0) result + prefix
    else {
        (0 until n).map { i ->
            permutationsUnsafe(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }.flatten()
    }
}

@JvmName("permutationsUnsafe2")
private fun String.permutationsUnsafe(): List<String> = when {
    isEmpty() -> emptyList()
    length == 1 -> listOf(this)
    else -> {
        val element = get(0)
        drop(1).permutationsUnsafe().flatMap { s: String ->
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

@ExperimentalStdlibApi
private fun String.permutationsSafe(): List<String> =
    DeepRecursiveFunction<String, List<String>> { string ->
        when {
            string.isEmpty() -> emptyList()
            string.length == 1 -> listOf(string)
            else -> {
                val element = string[0]
                string.drop(1).let {
                    callRecursive(it).flatMap { s: String ->
                        (0..s.length).map { i -> s.plusAt(i, element) }
                    }
                }
            }
        }
    }(this)

@ExperimentalStdlibApi
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
