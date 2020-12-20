package com.igorwojda.tree.binarysearchtree

import org.amshove.kluent.shouldEqual
import org.junit.Test

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
class BinarySearchTreeTest {
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

            root?.data shouldEqual 6
            root?.left?.data shouldEqual 3
            root?.left?.left?.data shouldEqual 1
            root?.left?.left?.left shouldEqual null
            root?.left?.left?.right shouldEqual null
            root?.left?.right?.data shouldEqual 5
            root?.left?.right?.left shouldEqual null
            root?.left?.right?.right shouldEqual null
            root?.right?.data shouldEqual 8
            root?.right?.left?.data shouldEqual 7
            root?.right?.left?.left shouldEqual null
            root?.right?.left?.right shouldEqual null
            root?.right?.right?.data shouldEqual 9
            root?.right?.right?.left shouldEqual null
            root?.right?.right?.right shouldEqual null
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

            root?.data shouldEqual 6
            root?.left?.data shouldEqual 3
            root?.left?.left shouldEqual null
            root?.left?.right shouldEqual null
            root?.right?.data shouldEqual 8
            root?.right?.left shouldEqual null
            root?.right?.right shouldEqual null
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

            contains(6) shouldEqual true
            contains(3) shouldEqual true
            contains(8) shouldEqual true
            contains(5) shouldEqual true
            contains(7) shouldEqual true
            contains(1) shouldEqual true
            contains(9) shouldEqual true
            contains(100) shouldEqual false
        }
    }
}
