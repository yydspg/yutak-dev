package com.pg;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        for(int i = 0; i < nums.length;i++){
            if(i >0 && nums[i] == nums[i-1]) continue;
            int t = -nums[i];
            l = i + 1;
            r = nums.length - 1;
            while(l < r){
                if(nums[l] + nums[r] == t){
                    ans.add(List.of(t,nums[l],nums[r]));
                    while(l < r && nums[l] == nums[l+1]) l++;
                    while(l < r && nums[r] == nums[r-1]) r--;
                }
                while(l < r && nums[l] + nums[r] < t) l++;
                while(l < r && nums[l] + nums[r] > t) r--;
            }
        }
        return ans;
    }
}
