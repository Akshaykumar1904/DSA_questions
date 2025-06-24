package git_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

public class sorting {
    public static void main(String[] args) {
//        System.out.println(thirdMax(new int[]{1,2,2}));
//        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
//        System.out.println(SearchInSorted(new int[]{5,1,3},5));
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4},3));

    }

    public static int thirdMax(int[] nums) {
        if (nums.length <= 2) {
            return nums[nums.length - 1];
        }
        Arrays.sort(nums);
        // Reverse the array manually
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i <= nums.length - 1) {
            if (i == 0) {
                stack.push(nums[i]);
            } else {
                if (stack.peek() != nums[i]) {
                    stack.push(nums[i]);
                }
            }
            i++;
        }
        if(stack.size()<3){
            return stack.peek();
        }
        int removed =0;
        int k=0;
        while(k<3 && !stack.isEmpty()){
            removed = stack.pop();
            k++;
        }
        return removed;
    }

    /*
    public static int lengthOfLIS(int[] nums) {
        int right =0;
        Stack<Integer> stack = new Stack<>();

        while(right <nums.length){
            if(!stack.isEmpty() && stack.peek()>=nums[right]){
                while(!stack.isEmpty() && stack.peek()>=nums[right]){
                    stack.pop();
                }
                stack.push(nums[right]);
            }else{
                stack.push(nums[right]);
            }
                right++;
        }

        return stack.size();
    }


    private static int helper(int[]nums,int index){
//        int element = nums[index];
        while(index<nums.length){
            if(!stack.isEmpty() && nums[index]<=stack.peek()){
                while(!stack.isEmpty() && nums[index]<=stack.peek()){
                    stack.pop();
                }
                stack.push(nums[index]);
            }else{
                stack.push(nums[index]);
            }
            index++;
        }
        return stack.size();
    }
    */

    public int lengthOfLIS(int[] nums) {
        List<Integer> subsequence = new ArrayList<>();

        for (int num : nums) {
            int position = findPosition(subsequence, num);

            if (position == subsequence.size()) {
                subsequence.add(num); // extend the subsequence
            } else {
                subsequence.set(position, num); // replace to keep it optimal
            }
        }

        return subsequence.size();
    }

    private static int findPosition(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left; // insertion point
    }


    public int search(int[] nums, int target) {
        int pivot = pivot(nums);
        if(pivot==-1){
            return binary(nums,0,nums.length-1,target);
        }
        int ans =0;
        if(target == nums[pivot]){
            return pivot;
        }else if( target>=nums[0]){
            ans = binary(nums,0,pivot-1,target);
        }
        else{
            ans =  binary(nums,pivot+1,nums.length-1,target);
        }
        return ans;

    }

    private static int binary(int[] nums, int front, int rear,int target) {
        int start =front;
        int end = rear;
        while(start<=end){
            int middle = start + (end-start)/2;
            if(nums[middle]==target)return middle;
            else if(nums[middle]<target){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }

        return -1;
    }

    private static int pivot(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while(start <=end ) {
            int middle = start + (end - start) / 2;
            if (middle < end && nums[middle] > nums[middle + 1]) {
                return middle;
            } else if (middle >start && nums[middle - 1] > nums[middle]) {
                return middle - 1;
            } else if (nums[middle] <= nums[start]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int high = sumArray(weights);
        int low = maxOfArray(weights);

        while(low<high){
            int mid = (low+high)/2;
            if(arraySplit(mid,days,weights)){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }

    private static boolean arraySplit(int mid, int days, int[] weights) {
        int count=1;
        int sum=0;
        for(int num:weights) {
            if (sum + num > mid) {
                count++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return count <=days;
    }

    private static int maxOfArray(int[]arr){
        int max =0;
        for(int num:arr){
            if(num>max){
                max=num;
            }
        }
        return max;
    }

    private static int sumArray(int[]arr){
        int max =0;
        for(int num:arr){
           max += num;
        }
        return max;
    }

}
