package com.igorwojda.stack.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

// private class Stack<E> {
//    var elements: List<E> = emptyList()
//    val size
//        get() = elements.size
//
//    fun add(element: E) {
//        elements += element
//    }
//
//    fun remove(): E? {
//        return elements.lastOrNull()?.also { elements -= it }
//    }
//
//    fun peek(): E? {
//        return elements.lastOrNull()
//    }
//
//    fun isEmpty(): Boolean {
//        return elements.isEmpty()
//    }
// }

private class Stack<E> {
    private var elements: LinkedList<E> = LinkedList()
    val size
        get() = elements.size

    fun add(element: E) {
        elements.add(element)
    }

    fun remove(): E? {
        return if (elements.isEmpty()) null else elements.removeLast()
    }

    fun peek(): E? {
        return elements.lastOrNull()
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }
}

private class Test {
    @Test
    fun `stack can add and remove items`() {
        Stack<Int>().apply {
            add(1)
            remove() shouldBeEqualTo 1
            add(2)
            remove() shouldBeEqualTo 2
        }
    }

    @Test
    fun `stack can follows first in, last out`() {
        Stack<Int>().apply {
            add(1)
            add(2)
            add(3)
            remove() shouldBeEqualTo 3
            remove() shouldBeEqualTo 2
            remove() shouldBeEqualTo 1
        }
    }

    @Test
    fun `peek returns the first element but does not remove it`() {
        Stack<Char>().apply {
            add('A')
            add('B')
            add('C')
            peek() shouldBeEqualTo 'C'
            remove() shouldBeEqualTo 'C'
            peek() shouldBeEqualTo 'B'
            remove() shouldBeEqualTo 'B'
            peek() shouldBeEqualTo 'A'
            remove() shouldBeEqualTo 'A'
            peek() shouldBeEqualTo null
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `newly created stack is empty`() {
        Stack<Char>().apply {
            isEmpty() shouldBeEqualTo true
        }
    }

    @Test
    fun `stack is empty after removing all items`() {
        Stack<Char>().apply {
            add('A')
            add('B')
            add('C')
            peek()
            remove()
            peek()
            remove()
            peek()
            remove()

            isEmpty() shouldBeEqualTo true
        }
    }

    @Test
    fun `stack with items is not empty`() {
        Stack<Char>().apply {
            add('A')
            isEmpty() shouldBeEqualTo false
            add('B')
            isEmpty() shouldBeEqualTo false
            add('C')
            isEmpty() shouldBeEqualTo false
        }
    }

    @Test
    fun `stack has correct size`() {
        Stack<Char>().apply {
            size shouldBeEqualTo 0

            add('A')
            size shouldBeEqualTo 1

            add('B')
            size shouldBeEqualTo 2

            add('C')
            size shouldBeEqualTo 3

            remove()
            size shouldBeEqualTo 2

            remove()
            size shouldBeEqualTo 1

            remove()
            size shouldBeEqualTo 0
        }
    }

    @Test
    fun `remove item from empty stack`() {
        Stack<Char>().apply {
            remove()

            size shouldBeEqualTo 0
        }
    }
}
