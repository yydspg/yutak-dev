package com.pg.algorithum.sort;

import java.util.concurrent.ThreadLocalRandom;

public class MyQuickSort {

    public static void quickSort(int[] nums,int left,int right){
        if(left >= right) return;
        int index = partition(nums,left,right);
        quickSort(nums,left, index -1);
        quickSort(nums, index +1,right);
    }
    public static int partition(int[] nums,int left,int right){
        int i = left;
        int j = right;
        int idx = ThreadLocalRandom.current().nextInt(right-left+1)+ left;
        swap(nums,i,idx);
        int pivot = nums[left];
        while(i<j){
            //只能先从右往左查找,最后交换的逻辑要相同
            while(i < j && nums[j] > pivot) j--;
            while(i < j && nums[i] <= pivot) i++;
            swap(nums,i,j);
        }
        swap(nums,i,left);
        return i;
    }
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] ints = {2, 3, 5, 6,1,4,9,2};
        quickSort(ints,0,ints.length-1);
        for(int a:ints){
            System.out.println(a);
        }
    }
}
