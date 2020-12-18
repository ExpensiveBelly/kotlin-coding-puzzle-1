package com.igorwojda.tree.classic.traversal

import org.amshove.kluent.shouldEqual
import org.junit.Test
import kotlin.math.max

private class BinarySearchTree<E : Comparable<E>> {
    var root: BinaryNode<E>? = null
        private set

    fun add(element: E) {
        val newNode = BinaryNode(element)

        if (root == null) {
            root = newNode
            return
        }

        var current: BinaryNode<E> = root ?: return

        while (true) {
            when {
                current.data == element -> {
                    return
                }
                element < current.data -> {
                    if (current.left == null) {
                        current.left = newNode
                        return
                    }

                    current.left?.let { current = it }
                }
                element > current.data -> {
                    if (current.right == null) {
                        current.right = newNode
                        return
                    }

                    current.right?.let { current = it }
                }
            }
        }
    }

    fun contains(element: E): Boolean {
        var current = root

        while (true) {
            if (current == null) {
                break
            } else if (current.data == element) {
                return true
            } else if (element < current.data) {
                current = current.left
            } else if (element > current.data) {
                current = current.right
            }
        }

        return false
    }

    fun isEmpty() = root == null

    fun traverseBreathFirst(): List<E> {
        val list: MutableList<E> = mutableListOf()
        val height: Int = height(root)
        return if (height == 0) emptyList()
        else {
            for (i in 1..height) {
                printLevel(i, root!!, list)
            }
            list
        }
    }

    private fun printLevel(level: Int, node: BinaryNode<E>, mutableList: MutableList<E>) {
        if (level == 1) mutableList.add(node.data)
        else {
            node.left?.let { printLevel(level - 1, it, mutableList) }
            node.right?.let { printLevel(level - 1, it, mutableList) }
        }
    }

    private fun height(node: BinaryNode<E>?): Int {
        if (node == null) return 0
        val leftHeight = 1 + (node.left?.let { height(it) } ?: 0)
        val rightHeight = 1 + (node.right?.let { height(it) } ?: 0)

        return max(leftHeight, rightHeight)
    }


    @ExperimentalStdlibApi
    fun traverseDepthFirstPreOrder(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                listOf(node.data) + callRecursive(node.left) + callRecursive(node.right)
            } ?: emptyList()
        }(root)

    @ExperimentalStdlibApi
    fun traverseDepthFirstInOrder(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                callRecursive(node.left) + node.data + callRecursive(node.right)
            } ?: emptyList()
        }(root)

    @ExperimentalStdlibApi
    fun traverseDepthFirstPostOrder(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                callRecursive(node.left) + callRecursive(node.right) + node.data
            } ?: emptyList()
        }(root)

    @ExperimentalStdlibApi
    fun traverseDepthFirstPreOrderReversed(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                listOf(node.data) + callRecursive(node.right) + callRecursive(node.left)
            } ?: emptyList()
        }(root)

    @ExperimentalStdlibApi
    fun traverseDepthFirstInOrderReversed(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                callRecursive(node.right) + node.data + callRecursive(node.left)
            } ?: emptyList()
        }(root)

    @ExperimentalStdlibApi
    fun traverseDepthFirstPostOrderReverse(): List<E> =
        DeepRecursiveFunction<BinaryNode<E>?, List<E>> { binaryNode ->
            binaryNode?.let { node ->
                callRecursive(node.right) + callRecursive(node.left) + node.data
            } ?: emptyList()
        }(root)

}

private data class BinaryNode<E : Comparable<E>>(
    val data: E,
    var left: BinaryNode<E>? = null,
    var right: BinaryNode<E>? = null
)

/*
We can use queue as helper class to implement breath first traversal. This is not most optimal queue implementation,
however it's enough for this task. Check "Queue puzzle" solution for more details and more efficient queue
implementation.
*/
private class Queue<E> {
    private val list = mutableListOf<E>()

    fun add(element: E) {
        list.add(element)
    }

    fun remove() = if (list.isEmpty()) null else list.removeAt(0)

    fun peek() = list.firstOrNull()

    fun isEmpty() = list.isEmpty()

    fun isNotEmpty() = list.isNotEmpty()

    val size get() = list.size
}

@ExperimentalStdlibApi
class TreeTest {
    @Test
    fun `traverse breath first`() {
        getTree().traverseBreathFirst() shouldEqual listOf('F', 'B', 'G', 'A', 'D', 'I', 'C', 'E', 'H')
    }

    @Test
    fun `traverse depth first pre order`() {
        getTree().traverseDepthFirstPreOrder() shouldEqual listOf('F', 'B', 'A', 'D', 'C', 'E', 'G', 'I', 'H')
    }

    @Test
    fun `traverse depth first in order`() {
        getTree().traverseDepthFirstInOrder() shouldEqual listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I')
    }

    @Test
    fun `traverse depth first post order`() {
        getTree().traverseDepthFirstPostOrder() shouldEqual listOf('A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F')
    }

    @Test
    fun `traverse depth first pre order reversed`() {
        getTree().traverseDepthFirstPreOrderReversed() shouldEqual listOf('F', 'G', 'I', 'H', 'B', 'D', 'E', 'C', 'A')
    }

    @Test
    fun `traverse depth first in order reversed`() {
        getTree().traverseDepthFirstInOrderReversed() shouldEqual listOf('I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A')
    }

    @Test
    fun `traverse depth first post order reverse`() {
        getTree().traverseDepthFirstPostOrderReverse() shouldEqual listOf('H', 'I', 'G', 'E', 'C', 'D', 'A', 'B', 'F')
    }

    // ---------Tree------------
    //
    //           F
    //         /   \
    //        B     G
    //       / \     \
    //      A   D     I
    //         / \   /
    //        C   E H
    //
    // --------------------------
    private fun getTree() = BinarySearchTree<Char>().apply {
        add('F')
        add('B')
        add('A')
        add('D')
        add('C')
        add('E')
        add('G')
        add('I')
        add('H')
    }
}
