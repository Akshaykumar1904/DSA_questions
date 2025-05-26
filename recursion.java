package git_practice;

import java.util.Arrays;

public class recursion {
    public static void main(String[]args){
        int[]array = new int[]{5,6};
        System.out.println(binaryRecursive(array,4,0,array.length-1));
    }


    static void triangleSum(int[]array ){
        if(array.length<=1){
            return;
        }
        int[]temp = new int[array.length-1];
        for (int i = 0; i <=array.length-2 ; i++) {
            temp[i] = array[i]+array[i+1];
        }
        triangleSum(temp);
        System.out.println(Arrays.toString(temp));
    }


    static int[] max_min(int []arr,int max,int min,int index){
        if(index == arr.length){
            return new int[]{min,max};
        }
        if(arr[index]>max){
            max = arr[index];
        }
        if(arr[index]<min){
            min = arr[index];
        }
        return max_min(arr,max,min,index+1);
    }

    static int binaryRecursive(int[]array,int target,int start,int end){
        if(start > end ){
            return -1;
        }

        int middle = start+(end-start)/2;
        if(array[middle]==target)return middle;
        else if(array[middle] < target){
            return binaryRecursive(array,target,middle+1,end);
        }
        return binaryRecursive(array,target,start,middle-1);

    }

}
