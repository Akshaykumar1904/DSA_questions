package git_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class arrays {
    public static void main(String[] args) {
//        int[]arr1 = {1,2,3,0,0,0};
//        int[]arr2={2,5,6};
//        mergeSorted(arr1,3,arr2,3);
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(majorityElement(new int[]{2,2,5,2,5,5,5,5}));
        System.out.println(Arrays.toString(intersect(new int[]{4,9,5},new int[]{4,9,4,9,6})));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> intersection = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num : nums1) {
            map.put(num,map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num)) {
                if (map.get(num) > 1) {
                    map.put(num, map.get(num) - 1);
                    intersection.add(num);
                } else {
                    map.remove(num);
                    intersection.add(num);
                }
            }
        }

        int[] ans = new int[intersection.size()];

        for(int i = 0; i < ans.length; i++) {
            ans[i] = intersection.get(i);
        }

        return ans;
    }

    public static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
    public static void mergeSorted(int[] nums1,int m,int[] nums2,int n){
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
