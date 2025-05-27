package git_practice;

import java.util.Arrays;

public class recursion {
    public static void main(String[]args){
        char[]array = {'h','a','n','n','a','h'};
        reverse_charArrayOne(array,0,array.length-1);
//        System.out.println(Arrays.toString(array));
//        printNo(9);
//        System.out.println(lengthString("GEEKSFORGEEKS",0,0));
        System.out.println(specialFibo(1,3,2,6));

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

    static void findFirst(String s,int index){
        if(index==s.length()){
            return;
        }
        if(s.charAt(index)>=65 && s.charAt(index)<=97){
            System.out.println(s.charAt(index));
            return;
        }
        findFirst(s,index+1);
    }

    static void reverse_charArrayOne(char[]array,int start,int end){
        if(start>=end)return;
        char temp = array[start];
        array[start]=array[end];
        array[end]=temp;
        reverse_charArrayOne(array,start+1,end-1);
    }

    public static void printNo(int n){
        helper(1,n);
    }

    private static void helper(int first, int n) {
        if(first==n) {
            System.out.println(first);
            return;
        };
        System.out.println(first);
        helper(first+1,n);
    }


    static int fibo(int n){
        int ans =0;
        if(n<=1)return n;
        ans = fibo(n-1)+fibo(n-2);
        return ans;
    }

    /*
    static int specialFibo(int a,int b,int n){
        if(n==0)return a;
        if(n==1)return b;
        int ans;
        ans = specialFibo(a,b,n-1) ^ specialFibo(a,b,n-2);
        return ans;
    }

     */

    static int lengthString(String s,int given,int length){
        if(given==s.length()){
            return length;
        }
        if(s.charAt(given)!=' '){
            length++;
        }
        return lengthString(s,given+1,length);
    }


    static int specialFibo(int a,int b,int c,int n){
        if(n==1){
            return a;
        }
        if(n==2)return b;
        if(n==3)return c;

        int ans =0;
        ans = specialFibo(a,b,c,n-1) + specialFibo(a,b,c,n-2)+ specialFibo(a,b,c,n-3);
        return ans;

    }


}
