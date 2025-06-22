package git_practice;

import java.util.*;

public class techniques {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 10, 11};
        int target = 9;
//        System.out.println(firstNegInt(new int[]{-1,25,-33,26,26,-8,-6,2}, 2));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{-1,25,-33,26,26,-8,-6,2},3)));

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


}



