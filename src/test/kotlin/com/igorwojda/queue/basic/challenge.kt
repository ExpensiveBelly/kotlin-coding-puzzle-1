package com.igorwojda.queue.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

// private class Queue<E> {
//    var size = 0
//        private set
//        get() = elements.size
//
//    private var elements: List<E> = emptyList()
//
//    fun add(element: E) {
//        elements += element
//    }
//
//    fun remove(): E? = elements.firstOrNull()?.also { elements -= it }
//
//    fun peek(): E? = elements.firstOrNull()
//
//    fun isEmpty(): Boolean {
//        return elements.isEmpty()
//    }
// }

// private class Queue<E> {
//    var size = 0
//        private set
//        get() = elements.size
//
//    private var elements: LinkedList<E> = LinkedList()
//
//    fun add(element: E) {
//        elements.add(element)
//    }
//
//    fun remove(): E? = elements.firstOrNull()?.also { elements.remove(it) }
//
//    fun peek(): E? = elements.firstOrNull()
//
//    fun isEmpty(): Boolean {
//        return elements.isEmpty()
//    }
// }

private class Queue<E> {
    var size = 0
        private set
        get() = firsStack.size + secondStack.size

    private var firsStack: Stack<E> = Stack()
    private var secondStack: Stack<E> = Stack()

    fun add(element: E) {
        firsStack.push(element)
    }

    fun remove(): E? {
        while (firsStack.isNotEmpty()) {
            secondStack.push(firsStack.pop())
        }
        return if (secondStack.isNotEmpty()) secondStack.pop() else null
    }

    fun peek(): E? {
        while (firsStack.isNotEmpty()) {
            secondStack.push(firsStack.pop())
        }
        return if (secondStack.isNotEmpty()) secondStack.peek() else null
    }

    fun isEmpty(): Boolean {
        return firsStack.isEmpty() && secondStack.isEmpty()
    }
}

private class Test {
    @Test
    fun `can add elements to a queue`() {
        Queue<Int>().apply { add(1) }
    }

    @Test
    fun `can remove elements from empty queue`() {
        Queue<Int>().apply { remove() shouldBeEqualTo null }
    }

    @Test
    fun `can remove elements from a queue`() {
        Queue<String>().apply {
            add("ABC")
            remove()
        }
    }

    @Test
    fun `order of elements is maintained`() {
        Queue<Char>().apply {
            add('A')
            add('B')
            add('C')
            remove() shouldBeEqualTo 'A'
            remove() shouldBeEqualTo 'B'
            remove() shouldBeEqualTo 'C'
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `peek returns, but does not remove element`() {
        Queue<Int>().apply {
            add(1)
            add(2)
            peek() shouldBeEqualTo 1
            peek() shouldBeEqualTo 1
            remove() shouldBeEqualTo 1
            peek() shouldBeEqualTo 2
            remove() shouldBeEqualTo 2
            peek() shouldBeEqualTo null
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `isEmpty returns true`() {
        Queue<Int>().isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `isEmpty returns false`() {
        Queue<Int>().apply {
            add(1)
            isEmpty() shouldBeEqualTo false
        }
    }

    @Test
    fun `queue has correct size`() {
        Queue<Char>().apply {
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
    fun `remove item from empty queue`() {
        Queue<Int>().apply {
            remove()

            size shouldBeEqualTo 0
        }
    }
}
