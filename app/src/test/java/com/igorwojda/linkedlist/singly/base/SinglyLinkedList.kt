package com.igorwojda.linkedlist.singly.base

import org.amshove.kluent.shouldEqual
import org.junit.Test

private class SinglyLinkedList<E> {
    var head: Node<E>? = null
    var size: Int = 0
    var first: Node<E>? = head
    var last: Node<E>? = head

    /**
     * If head == null then create node and assign it as head
     * If head != null then create a node, copy head in a temp node, assign head to the new node and assign head.next to the temp
     */
    fun insertFirst(data: E) {
        if (head == null) {
            val node = Node(data, null)
            head = node
            last = node
            first = head
        } else {
            val temp = head
            head = Node(data, temp)
            first = head
        }
        size++
    }

    fun clear() {
        head = null
        last = null
        first = null
        size = 0
    }

    fun removeFirst() {
        if (size == 1) {
            clear()
            return
        }
        if (head != null) {
            head = head?.next
            size--
            if (size == 0) last = null
        }
        first = head
    }

    fun removeLast() {
        if (size == 1) {
            clear()
            return
        }
        var temp = head
        while (temp?.next != null) {
            if (temp.next == last) {
                temp.next = null
                last = temp
                size--
            }
            temp = temp.next
        }
        if (size == 0) {
            clear()
        }
    }

    fun insertLast(data: E) {
        if (last == null) {
            insertFirst(data)
        } else {
            val temp = Node(data, null)
            last?.next = temp
            last = temp
            size++
        }
    }

    fun getAt(position: Int): Node<E>? {
        var temp = head
        repeat(position) { temp = temp?.next }
        return temp
    }
}

private data class Node<T>(
    var data: T,
    var next: Node<T>? = null
)

class SinglyLinkedListTest {
    @Test
    fun `when list is created head node is null`() {
        SinglyLinkedList<Int>().apply {
            head shouldEqual null
        }
    }

    @Test
    fun `append a node to the start of the list`() {
        SinglyLinkedList<Int>().apply {
            insertFirst(1)
            head?.data shouldEqual 1
            insertFirst(2)
            head?.data shouldEqual 2
        }
    }

    @Test
    fun `return the number of items in the linked list`() {
        SinglyLinkedList<Int>().apply {
            size shouldEqual 0
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            size shouldEqual 4
        }
    }

    @Test
    fun `return the first element`() {
        SinglyLinkedList<Int>().apply {
            insertFirst(1)
            first?.data shouldEqual 1
            insertFirst(2)
            first?.data shouldEqual 2
        }
    }

    @Test
    fun `return the last element`() {
        SinglyLinkedList<Int>().apply {
            insertFirst(2)
            last?.data shouldEqual 2
            last?.next shouldEqual null
            insertFirst(1)
            last?.data shouldEqual 2
            last?.next shouldEqual null
        }
    }

    @Test
    fun `empty the list`() {
        SinglyLinkedList<Int>().apply {
            size shouldEqual 0
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            insertFirst(1)
            size shouldEqual 4
            clear()
            size shouldEqual 0
        }
    }

    @Test
    fun `remove the first node when the list has a size of one`() {
        SinglyLinkedList<String>().apply {
            insertFirst("a")
            removeFirst()
            size shouldEqual 0
            first shouldEqual null
        }
    }

    @Test
    fun `remove the first node when the list has a size of three`() {
        SinglyLinkedList<String>().apply {
            insertFirst("c")
            insertFirst("b")
            insertFirst("a")
            removeFirst()
            size shouldEqual 2
            first?.data shouldEqual "b"
            removeFirst()
            size shouldEqual 1
            first?.data shouldEqual "c"
        }
    }

    @Test
    fun `remove the last node when list is empty`() {
        SinglyLinkedList<Any>().apply {
            removeLast()
        }
    }

    @Test
    fun `remove the last node when list is length 1`() {
        SinglyLinkedList<String>().apply {
            insertFirst("a")
            removeLast()
            head shouldEqual null
        }
    }

    @Test
    fun `remove the last node when list is length 2`() {
        SinglyLinkedList<String>().apply {
            insertFirst("b")
            insertFirst("a")
            removeLast()
            size shouldEqual 1
            head?.data shouldEqual "a"
        }
    }

    @Test
    fun `remove the last node when list is length 3`() {
        SinglyLinkedList<String>().apply {
            insertFirst("c")
            insertFirst("b")
            insertFirst("a")
            removeLast()
            size shouldEqual 2
            last?.data shouldEqual "b"
        }
    }

    @Test
    fun `add to the end of the list`() {
        SinglyLinkedList<String>().apply {
            insertFirst("a")
            insertLast("b")
            size shouldEqual 2
            last?.data shouldEqual "b"
        }
    }

    @Test
    fun `return the node at given index`() {
        SinglyLinkedList<Char>().apply {
            getAt(10) shouldEqual null

            insertLast('A')
            insertLast('B')
            insertLast('C')
            insertLast('D')

            getAt(0)?.data shouldEqual 'A'
            getAt(1)?.data shouldEqual 'B'
            getAt(2)?.data shouldEqual 'C'
            getAt(3)?.data shouldEqual 'D'
            getAt(4)?.data shouldEqual null
        }
    }
//
//    @Test
//    fun `set node data at index 0`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            setAt("new", 0)
//            getAt(0)?.data shouldEqual "new"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "c"
//        }
//    }
//
//    @Test
//    fun `set node data at index 1`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            setAt("new", 1)
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "new"
//            getAt(2)?.data shouldEqual "c"
//        }
//    }
//
//    @Test
//    fun `set node data at index 2`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            setAt("new", 2)
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "new"
//        }
//    }
//
//    @Test
//    fun `set node data at non existing index`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            setAt("new", 3)
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "c"
//        }
//    }
//
//    @Test
//    fun `remove from empty list`() {
//        SinglyLinkedList<Int>().apply {
//            removeAt(0)
//            removeAt(1)
//            removeAt(2)
//        }
//    }
//
//    @Test
//    fun `remove with index out of bounds`() {
//        SinglyLinkedList<String>().apply {
//            insertFirst("a")
//            removeAt(1)
//        }
//    }
//
//    @Test
//    fun `remove the first node`() {
//        SinglyLinkedList<Int>().apply {
//            insertLast(1)
//            insertLast(2)
//            insertLast(3)
//            insertLast(4)
//            getAt(0)?.data shouldEqual 1
//            removeAt(0)
//            getAt(0)?.data shouldEqual 2
//        }
//    }
//
//    @Test
//    fun `remove the node at given index`() {
//        SinglyLinkedList<Int>().apply {
//            insertLast(1)
//            insertLast(2)
//            insertLast(3)
//            insertLast(4)
//            getAt(1)?.data shouldEqual 2
//            removeAt(1)
//            getAt(1)?.data shouldEqual 3
//        }
//    }
//
//    @Test
//    fun `remove the last node`() {
//        SinglyLinkedList<Int>().apply {
//            insertLast(1)
//            insertLast(2)
//            insertLast(3)
//            insertLast(4)
//            getAt(3)?.data shouldEqual 4
//            removeAt(3)
//            getAt(3) shouldEqual null
//        }
//    }
//
//    @Test
//    fun `insert a new node with data at index 0 when the list is empty`() {
//        SinglyLinkedList<String>().apply {
//            insertAt("hi", 0)
//            first?.data shouldEqual "hi"
//        }
//    }
//
//    @Test
//    fun `insert a new node with data at index 0 when the list has elements`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            insertAt("hi", 0)
//            getAt(0)?.data shouldEqual "hi"
//            getAt(1)?.data shouldEqual "a"
//            getAt(2)?.data shouldEqual "b"
//            getAt(3)?.data shouldEqual "c"
//        }
//    }
//
//    @Test
//    fun `insert a new node with data at a middle index`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertLast("c")
//            insertLast("d")
//            insertAt("hi", 2)
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "hi"
//            getAt(3)?.data shouldEqual "c"
//            getAt(4)?.data shouldEqual "d"
//        }
//    }
//
//    @Test
//    fun `inserts a new node with data at a last index`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertAt("hi", 2)
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "hi"
//        }
//    }
//
//    @Test
//    fun `insert a new node when index is out of bounds`() {
//        SinglyLinkedList<String>().apply {
//            insertLast("a")
//            insertLast("b")
//            insertAt("hi", 30)
//
//            getAt(0)?.data shouldEqual "a"
//            getAt(1)?.data shouldEqual "b"
//            getAt(2)?.data shouldEqual "hi"
//        }
//    }
//
//    @Test
//    fun `sum all the nodes`() {
//        SinglyLinkedList<Int>().apply {
//            insertLast(1)
//            insertLast(2)
//            insertLast(3)
//            insertLast(4)
//
//            sumBy { it.data } shouldEqual 10
//        }
//    }
//
//    @Test
//    fun `add two empty lists`() {
//        val l1 = SinglyLinkedList<Int>()
//        val l2 = SinglyLinkedList<Int>()
//        val result = l1 + l2
//
//        result.size shouldEqual 0
//    }
//
//    @Test
//    fun `add two lists`() {
//        val l1 = SinglyLinkedList<Int>().apply {
//            insertLast(1)
//            insertLast(2)
//            insertLast(3)
//        }
//        val l2 = SinglyLinkedList<Int>().apply {
//            insertLast(4)
//            insertLast(5)
//            insertLast(6)
//            insertLast(7)
//        }
//        val result = l1 + l2
//
//        result.apply {
//            size shouldEqual 7
//            getAt(0)?.data shouldEqual 1
//            getAt(1)?.data shouldEqual 2
//            getAt(2)?.data shouldEqual 3
//            getAt(3)?.data shouldEqual 4
//            getAt(4)?.data shouldEqual 5
//            getAt(5)?.data shouldEqual 6
//            getAt(6)?.data shouldEqual 7
//        }
//    }
}
