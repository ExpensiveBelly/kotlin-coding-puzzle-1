package com.igorwojda.integer.spiralmatrixgenerator

import com.ichipsea.kotlin.matrix.MutableMatrix
import com.ichipsea.kotlin.matrix.toMutableList
import com.ichipsea.kotlin.matrix.toMutableMatrix
import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun generateSpiralMatrix(n: Int): List<MutableList<Int>> {
    return spiral(
        matrix = (1..Math.pow(n.toDouble(), 2.toDouble()).toInt()).toMutableMatrix(n, n),
        initialCount = 0,
        start = 0,
        end = n - 1
    ).toMutableList()
        .chunked(n).map { it.toMutableList() }.also { println(it) }
}

fun spiral(matrix: MutableMatrix<Int>, initialCount: Int, start: Int, end: Int): MutableMatrix<Int> =
    if (start > end) matrix
    else {
        var count = initialCount
        (start until end + 1).forEach { index -> run { matrix[start, index] = ++count }.also { println(" 1 matrix[$start, $index] = $count") } }
        (start + 1 until end + 1).forEach { index -> run { matrix[index, end] = ++count }.also { println("2 matrix[$index, $end] = $count") } }
        (end - 1 downTo start).forEach { index -> run { matrix[end, index] = ++count }.also { println("3 matrix[$end, $index] = $count") } }
        (end - 1 downTo start + 1).forEach { index -> run { matrix[index, start] = ++count }.also { println("4 matrix[$index, $start] = $count") } }

        spiral(matrix, count, start + 1, end - 1)
    }

class SpiralMatrixGeneratorTest {
    @Test
    fun `generateSpiralMatrix generates a 2x2 matrix`() {
        val matrix = generateSpiralMatrix(2)
        matrix.size shouldEqual 2
        matrix[0] shouldEqual listOf(1, 2)
        matrix[1] shouldEqual listOf(4, 3)
    }

    @Test
    fun `generateSpiralMatrix generates a 3x3 matrix`() {
        val matrix = generateSpiralMatrix(3)
        matrix.size shouldEqual 3
        matrix[0] shouldEqual listOf(1, 2, 3)
        matrix[1] shouldEqual listOf(8, 9, 4)
        matrix[2] shouldEqual listOf(7, 6, 5)
    }

    @Test
    fun `generateSpiralMatrix generates a 4x4 matrix`() {
        val matrix = generateSpiralMatrix(4)
        matrix.size shouldEqual 4
        matrix[0] shouldEqual listOf(1, 2, 3, 4)
        matrix[1] shouldEqual listOf(12, 13, 14, 5)
        matrix[2] shouldEqual listOf(11, 16, 15, 6)
        matrix[3] shouldEqual listOf(10, 9, 8, 7)
    }
}
