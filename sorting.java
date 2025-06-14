package git_practice;

import java.util.Arrays;
import java.util.Stack;

public class sorting {
    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{1,2,2}));
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
}
