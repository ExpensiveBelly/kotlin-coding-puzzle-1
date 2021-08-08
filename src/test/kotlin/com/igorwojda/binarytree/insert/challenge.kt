package com.igorwojda.binarytree.insert

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private data class Node<E : Comparable<E>>(
    var data: E,
    var left: Node<E>? = null,
    var right: Node<E>? = null
) {
    fun insert(e: E) {
        if (e == data) return

        if (e < data) {
            if (left == null) left = Node(e) else left?.insert(e)
        } else {
            if (right == null) right = Node(e) else right?.insert(e)
        }
    }

    fun contains(e: E): Boolean {
        if (e == data) return true

        return when {
            e < data -> when (left) {
                null -> false
                else -> left!!.contains(e)
            }
            right == null -> false
            else -> right!!.contains(e)
        }
    }
}

private class Test {
    @Test
    fun `can insert correctly`() {
        // -- -------Tree------------
        //
        //           10
        //         /   \
        //        5     15
        //                \
        //                 17
        //
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(17)

        node.left?.data shouldBeEqualTo 5
        node.right?.data shouldBeEqualTo 15
        node.right?.right?.data shouldBeEqualTo 17
    }

    @Test
    fun `contains returns true when value is found`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //      / \
        //    -5   3
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(20)
        node.insert(0)
        node.insert(-5)
        node.insert(3)

        node.contains(3) shouldBeEqualTo true
    }

    @Test
    fun `contains returns false if value not found`() {
        // -- -------Tree------------
        //
        //           10
        //          /  \
        //         5    15
        //        /       \
        //       0         20
        //      / \
        //    -5   3
        // --------------------------

        val node = Node(10)
        node.insert(5)
        node.insert(15)
        node.insert(20)
        node.insert(0)
        node.insert(-5)
        node.insert(3)

        node.contains(9999) shouldBeEqualTo false
    }
}
