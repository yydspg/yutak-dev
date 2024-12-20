package com.pg.proxy.jdkproxy;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProxyFactory {
    public static void main(String[] args) {

    }
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
    public int widthOfBinaryTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        Map<TreeNode,Integer> map = new HashMap<>();
        queue.offer(root);
        map.put(root,1);
        int ans = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            int begin = -1;
            int end = -1;
            for(int i = 0; i < size;i++){
                TreeNode out = queue.poll();
                int index = map.get(out);
                if(i == 0) begin = index;
                if(i == size -1) end = index;
                if(out.left != null) {
                    queue.offer(out.left);
                    map.put(out.left,index * 2);
                }
                if(out.right != null){
                    queue.offer(out.right);
                    map.put(out.right,index * 2 + 1);
                }
            }
            ans = Math.max(ans,end - begin + 1);
        }
        return ans;
    }
}
