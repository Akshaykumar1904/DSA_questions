package git_practice;

import javax.xml.stream.events.Characters;
import java.util.*;

public class techniques {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 10, 11};
        int target = 9;
//        System.out.println(firstNegInt(new int[]{-1,25,-33,26,26,-8,-6,2}, 2));
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{-1,25,-33,26,26,-8,-6,2},3)));
//        System.out.println(minSubArrayLen(7,new int[]{2,3,1,2,3,4}));
        System.out.println(longestSubarray(new int[]{10,5,2,7,1,9},10));

//        System.out.println(lengthOfLongestSubstring("bbcna"));
//        System.out.println(longestKSubString("eceba",2));
//        System.out.println((totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4})));
    }

    //    two-pointers
    static int[] twoPairs(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int sum = arr[start] + arr[end];
            if (sum == target) return new int[]{start, end};
            if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{-1, -1};
    }

/*
    ListNode slow = head;
    ListNode fast = head;
    while(fast!=null && fast.next!=null){
    fast = fast.next.next;
    slow = slow.next;
    if(slow==fast)return true;
    }
    return false;



*/

    public static long maximumSubarraySum(int[] nums, int k) {
        int left = 0;
        int right = 0;
        long max = 0;
        long currentSum = 0;
        Set<Integer> set = new HashSet<>();

        while (right < nums.length) {
            if (!set.contains(nums[right])) {
                // Add to set & sum
                set.add(nums[right]);
                currentSum += nums[right];

                // ✅ Check if window size == k
                if ((right - left + 1) == k) {
                    max = Math.max(max, currentSum);

                    // Shrink a window from left
                    currentSum -= nums[left];
                    set.remove(nums[left]);
                    left++;
                }

                right++; // Expand window
            } else {
                currentSum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
        }
        return max;
    }

    public static List<Integer> firstNegInt(int []arr, int k) {
        int left = 0, right = 0;
        List<Integer> result = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>(); // Stores indices of negative numbers

        while (right < arr.length) {
            // Add current element to queue if it's negative
            if (arr[right] < 0) {
                queue.offer(arr[right]);
            }

            // If window size reached
            if (right - left + 1 == k) {
                // If queue not empty, first negative is at front
                if (!queue.isEmpty()) {
                    result.add(queue.peek());
                } else {
                    result.add(0);
                }

                // Slide window: if outgoing element is same as front, remove it
                if (!queue.isEmpty() && arr[left] == queue.peek()) {
                    queue.poll();
                }
                left++;
            }
            right++;
        }
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int left = 0; left < n; left++) {
            // Remove out-of-bound indices
            if (!deque.isEmpty() && deque.peekFirst() <= left - k)
                deque.pollFirst();

            // Remove smaller elements from back
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[left])
                deque.pollLast();

            deque.offerLast(left); // Store index

            if (left >= k - 1)
                result[left - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }


//    dynamic window
    public static int minSubArrayLen(int target, int[] nums) {
        int left=0;
        int right =0;
        int min= Integer.MAX_VALUE;
        int sum=0;
        while(right<=nums.length-1){
           sum += nums[right];
           while(sum>=target){
               min = Math.min(min,right-left+1);
               sum -= nums[left];
               left++;
           }
           right++;
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public static int lengthOfLongestSubstring(String s) {
        int left =0;
        int right =0;
        Set<Character> set = new HashSet<>();
        int max=Integer.MIN_VALUE;
        while(right < s.length()){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                max = Math.max(max,set.size());
                right++;
            }else{
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    public static int longestKSubString(String s,int k){
        if (k == 0 || s == null || s.isEmpty()) return 0;

        int left = 0, right = 0, maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            right++;

            // Shrink window if distinct characters exceed k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
//                map.put(leftChar, map.get(leftChar) - 1);
//                if (map.get(leftChar) == 0) {
//                    map.remove(leftChar);
//                }
                if(map.get(leftChar)==1){
                    map.remove(leftChar);
                }else{
                    map.put(leftChar,map.get(leftChar)-1);
                }
                left++;
            }

            // Update max length if window is valid
            if (map.size() <= k) {
                maxLen = Math.max(maxLen, right - left);
            }
        }

        return maxLen;
    }

    public static int totalFruit(int[] fruits) {
        int left=0;
        int right =0;
        Map<Integer,Integer> map =new HashMap<>();
        int max=Integer.MIN_VALUE;
        while(right<= fruits.length-1){
            map.put(fruits[right],map.getOrDefault(fruits[right],0)+1);
            while(map.size()>2){
                if(map.get(fruits[left])==1){
                    map.remove(fruits[left]);
                }else{
                    map.put(fruits[left],map.get(fruits[left])-1);
                }
                left++;
            }
            if(map.size()<=2){
                max = Math.max(max,right-left+1);
            }
            right++;
        }
        return max;
    }

    public static int longestSubarray(int[] nums, int target) {
        int left=0;
        int right =0;
        int min= Integer.MIN_VALUE;
        int sum=0;
        while(right<=nums.length-1){
            sum += nums[right];
            while(sum>=target){
                min = Math.max(min,right-left+1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return min==Integer.MIN_VALUE?0:min;
    }

}



