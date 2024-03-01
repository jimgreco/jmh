package org.example;

public class IntrusiveLinkedList {

    private IntrusiveLinkedListItem head;

    public IntrusiveLinkedList() {
    }

    public void insertOrder(IntrusiveLinkedListItem order) {
        IntrusiveLinkedListItem prev = null;
        var next = head;
        while (next != null
                && ((order.buy && order.price <= next.price)
                || (!order.buy && order.price >= next.price))) {
            prev = next;
            next = next.next;
        }
        order.prev = prev;
        order.next = next;
        if (next != null) {
            next.prev = order;
        }
        if (prev == null) {
            head = order;
        } else {
            prev.next = order;
        }
    }

    public void removeOrder(IntrusiveLinkedListItem order) {
        if (order == head) {
            head = order.next;
        }
        if (order.prev != null) {
            order.prev.next = order.next;
        }
        if (order.next != null) {
            order.next.prev = order.prev;
        }
        order.prev = null;
        order.next = null;
    }

    public void removeOrder(int id) {
        IntrusiveLinkedListItem prev = null;
        var current = head;
        while (current != null) {
            if (current.id == id) {
                if (prev != null) {
                    prev.next = current.next;
                }
                if (current.next != null) {
                    current.next.prev = prev;
                }
                if (current == head) {
                    head = current.next;
                }
                current.prev = null;
                current.next = null;
                break;
            }
            prev = current;
            current = current.next;
        }
    }
}
