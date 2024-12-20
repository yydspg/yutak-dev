package com.pg.algorithum.lru;

public class MyLru<K,V> {

    private MyNode<K,V> head;
    private MyNode<K,V> tail;
    private int size;
    private int capacity;
    private static class MyNode<K,V> {
        K key;
        V value;
        MyNode<K,V> prev;
        MyNode<K,V> next;
        public MyNode(){}
        public MyNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public final void put(K key, V value) {

    }
    public final V get(K key) {
        return null;
    }
    private void remove(MyNode<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void toHead(MyNode<K,V> node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }
    private void moveToHead(MyNode<K,V> node) {
        remove(node);
        toHead(node);
    }
    private MyNode<K,V> dropTail(){
        MyNode<K,V> node = tail.prev;
        remove(node);
        return node;
    }
}
