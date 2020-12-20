package com.igorwojda.queue.basic

import org.amshove.kluent.shouldEqual
import org.junit.Test
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

class QueueTest {
    @Test
    fun `can add elements to a queue`() {
        Queue<Int>().apply { add(1) }
    }

    @Test
    fun `can remove elements from empty queue`() {
        Queue<Int>().apply { remove() shouldEqual null }
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
            remove() shouldEqual 'A'
            remove() shouldEqual 'B'
            remove() shouldEqual 'C'
            remove() shouldEqual null
        }
    }

    @Test
    fun `peek returns, but does not remove element`() {
        Queue<Int>().apply {
            add(1)
            add(2)
            peek() shouldEqual 1
            peek() shouldEqual 1
            remove() shouldEqual 1
            peek() shouldEqual 2
            remove() shouldEqual 2
            peek() shouldEqual null
            remove() shouldEqual null
        }
    }

    @Test
    fun `isEmpty returns true`() {
        Queue<Int>().isEmpty() shouldEqual true
    }

    @Test
    fun `isEmpty returns false`() {
        Queue<Int>().apply {
            add(1)
            isEmpty() shouldEqual false
        }
    }

    @Test
    fun `queue has correct size`() {
        Queue<Char>().apply {
            size shouldEqual 0

            add('A')
            size shouldEqual 1

            add('B')
            size shouldEqual 2

            add('C')
            size shouldEqual 3

            remove()
            size shouldEqual 2

            remove()
            size shouldEqual 1

            remove()
            size shouldEqual 0
        }
    }
}
