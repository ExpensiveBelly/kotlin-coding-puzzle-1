package com.igorwojda.tree.binarysearchtree

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private class BinarySearchTree<E : Comparable<E>> {
    var root: BinaryNode<E>? = null
        private set

    @ExperimentalStdlibApi
    fun add(element: E) {
        if (!contains(element)) root = add(root, element)
    }

    @ExperimentalStdlibApi
    private fun add(node: BinaryNode<E>?, element: E): BinaryNode<E> =
        DeepRecursiveFunction<Pair<BinaryNode<E>?, E>, BinaryNode<E>> { (node, element) ->
            when {
                node == null -> BinaryNode(element, null, null)
                element < node.data -> node.copy(left = callRecursive(node.left to element))
                element > node.data -> node.copy(right = callRecursive(node.right to element))
                else -> node
            }
        }(node to element)

    fun contains(element: E): Boolean = contains(root, element)

    private tailrec fun contains(node: BinaryNode<E>?, element: E): Boolean = when {
        node == null -> false
        node.data == element -> true
        element < node.data -> contains(node.left, element)
        element > node.data -> contains(node.right, element)
        else -> false
    }

    fun isEmpty(): Boolean = root == null
}

private data class BinaryNode<E : Comparable<E>>(
    val data: E,
    var left: BinaryNode<E>? = null,
    var right: BinaryNode<E>? = null
)

@ExperimentalStdlibApi
private class Test {
    @Test
    fun `build valid binary search tree`() {
        // ----------Tree------------
        //
        //           6
        //         /   \
        //        3     8
        //       / \   / \
        //      1   5 7   9
        //
        // --------------------------

        BinarySearchTree<Int>().apply {
            add(6)
            add(3)
            add(8)
            add(5)
            add(7)
            add(1)
            add(9)

            root?.data shouldBeEqualTo 6
            root?.left?.data shouldBeEqualTo 3
            root?.left?.left?.data shouldBeEqualTo 1
            root?.left?.left?.left shouldBeEqualTo null
            root?.left?.left?.right shouldBeEqualTo null
            root?.left?.right?.data shouldBeEqualTo 5
            root?.left?.right?.left shouldBeEqualTo null
            root?.left?.right?.right shouldBeEqualTo null
            root?.right?.data shouldBeEqualTo 8
            root?.right?.left?.data shouldBeEqualTo 7
            root?.right?.left?.left shouldBeEqualTo null
            root?.right?.left?.right shouldBeEqualTo null
            root?.right?.right?.data shouldBeEqualTo 9
            root?.right?.right?.left shouldBeEqualTo null
            root?.right?.right?.right shouldBeEqualTo null
        }
    }

    @Test
    fun `duplicated value is not added`() {
        // ----------Tree------------
        //
        //           6
        //         /   \
        //        3     8
        //
        // --------------------------

        BinarySearchTree<Int>().apply {
            add(6)
            add(3)
            add(8)
            add(6)
            add(3)
            add(8)

            root?.data shouldBeEqualTo 6
            root?.left?.data shouldBeEqualTo 3
            root?.left?.left shouldBeEqualTo null
            root?.left?.right shouldBeEqualTo null
            root?.right?.data shouldBeEqualTo 8
            root?.right?.left shouldBeEqualTo null
            root?.right?.right shouldBeEqualTo null
        }
    }

    @Test
    fun `tree contains all elements`() {
        // ----------Tree------------
        //
        //           6
        //         /   \
        //        3     8
        //       / \   / \
        //      1   5 7   9
        //
        // --------------------------

        BinarySearchTree<Int>().apply {
            add(6)
            add(3)
            add(8)
            add(5)
            add(7)
            add(1)
            add(9)

            contains(6) shouldBeEqualTo true
            contains(3) shouldBeEqualTo true
            contains(8) shouldBeEqualTo true
            contains(5) shouldBeEqualTo true
            contains(7) shouldBeEqualTo true
            contains(1) shouldBeEqualTo true
            contains(9) shouldBeEqualTo true
            contains(100) shouldBeEqualTo false
        }
    }
}
