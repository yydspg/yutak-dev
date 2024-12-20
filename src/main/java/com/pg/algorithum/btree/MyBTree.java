package com.pg.algorithum.btree;

public class MyBTree {

    private static class Node {
        // key
        int[] keys;
        // next node
        Node[] children;
        // current size
        int capacity;
        //weather leaf
        boolean leaf;
        // min child
        int t;

        public Node(int t){
            this.t = t;
            this.children = new Node[2 * t];
        }
    }
}
