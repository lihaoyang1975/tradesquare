package com.hermanlee.tradesquare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
/**
 * A simplied implementation of singly linked list
 */
public class Node implements Serializable {
    private static final long serialVersionUID = 9071882494276075255L;

    private int val;
    private Node next;

    public Node() {
        // default constructor for deserialization
    }

    public Node(final int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(final int val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(final Node next) {
        this.next = next;
    }

    @JsonIgnore
    public int getSize() {
        int size = 1;
        Node cur = getNext();
        while (cur != null) {
            size++;
            cur = cur.getNext();
        }
        return size;
    }

    /**
     * given an array, convert it to a linked list
     * @param numbers an array
     * @return a linked list
     */
    public static Node fromArray(int[] numbers) {
        if (numbers.length == 0) {
            return null;
        }

        Node head = new Node(numbers[0]);
        Node cur = head;
        int length = numbers.length;
        for (int i = 1; i < length; i++) {
            cur.setNext(new Node(numbers[i]));
            cur = cur.getNext();
        }

        return head;
    }
}
