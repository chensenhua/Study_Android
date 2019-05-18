package com.sen.study_android.leetCode;

public class MyLinkedList {


    private Node root;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        root = null;

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        Node temp = root;
        if (root == null||index<0) {
            return -1;
        }
        for (int i = 0; i < index; i++) {
            temp = temp.next;
            if (temp == null) {
                break;
            }
        }
        System.out.println("----get--->" + (temp != null ? temp.val : -1) + " -------");
        return temp != null ? temp.val : -1;

    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node header = new Node();
        header.val = val;
        if (root == null) {
            root = header;
        } else {
            Node temp = root;
            root = header;
            header.next = temp;
        }

        print("addAtHead");
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node tail = new Node();
        tail.val = val;
        if (root == null) {
            root = tail;
            return;
        }
        Node temp = root;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = tail;
        print("addAtTail");
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {

        if (index == 0) {
            addAtHead(val);
        } else if (root != null) {
            Node temp = root;
            Node insert = new Node();
            insert.val = val;
            int i = 0;
            for (; i < index - 1; i++) {
                if (temp == null) {
                    break;
                }
                temp = temp.next;
            }

            if (index - 1 == i) {
                Node n = temp.next;
                temp.next = insert;
                insert.next = n;

            }

        }
        print("addAtIndex");

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (root == null) {
            return;
        }


        if (index == 0) {
            root = root.next;
            return;
        }


        Node last = root;
        Node temp = root.next;
        for (int i = 0; i < index - 1; i++) {
            if (temp == null) {
                break;
            }
            last = last.next;
            temp = temp.next;
        }

        if (temp != null) {
            System.out.println("deleteAtIndex-->" + temp.val);
            last.next = temp.next;
            print("deleteAtIndex-->" + last.val);
        }

        print("deleteAtIndex");
    }

    void print(String name) {
        System.out.println("-------" + name + "_-------");
        Node n = root;
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }
}


class Node {
    public int val;
    public Node next;
}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */