package git_practice;

import Sorting_Searching.BinarySearchIn2d;

import java.util.Arrays;
import java.math.BigInteger;
public class maths {
    public static void main(String[] args) {
        System.out.println((numberCompliment(4)));
    }

    //not complete yet
    public static int numberCompliment(int num){
        BigInteger number = BigInteger.valueOf(num);
        StringBuilder numStr = new StringBuilder(number.toString(2));
        for (int i = 0; i <= numStr.length()-1; i++) {
            if(numStr.charAt(i)=='1'){
                numStr.setCharAt(i,'0');
            }else{
                numStr.setCharAt(i,'1');
            }
        }
        number = new BigInteger(String.valueOf(numStr));
        return number.intValue();
    }



    public static int hammingDistanceNaive(int x, int y) {
        int count =0;
        BigInteger a = BigInteger.valueOf(x);
        BigInteger b = BigInteger.valueOf(y);
        BigInteger max= (a.max(b));
        int length = max.bitLength();
        String first = String.format("%" +length+"s", a.toString(2)).replace(' ', '0');
        String second = String.format("%" +length+"s", b.toString(2)).replace(' ', '0');
        int i=first.length()-1;
        int j=second.length()-1;
        while(i>=0 && j>=0){
            if(first.charAt(i)!=second.charAt(j))count++;
            i--;
            j--;
        }
        while(j>=0){
            count++;
            j--;
        }
        while(i>=0){
            count++;
            i--;
        }
        return count;
    }

    public static int hammingDistanceBetter(int x,int y){
        BigInteger a = BigInteger.valueOf(x);
        BigInteger b = BigInteger.valueOf(y);
        return helperBigInt(a,b);
    }

    private static int helperBigInt(BigInteger a, BigInteger b) {
        return a.xor(b).bitCount();
    }


    public static int hammingDistanceBest(int x,int y){
        //make sure these numbers lie inside 2^31-1;
        return Integer.bitCount(x^y);
    }


    static int singleNumber(int[] nums){
        int sum = nums[0];
        for(int i=1;i<=nums.length-1;i++){
            sum ^= nums[i];
        }
        return sum;
    }


    static int countOneBits(int n){
        int sum=0;
        while(n>0){
            sum += ((n&1)==1) ? 1 :0;
            n= n>>1;
        }
        return sum;
    }

    static int[] countBitsArray(int n){
        int[]arr = new int[n+1];
        for (int i = 0; i <= n; i++) {
            arr[i] = Integer.bitCount(i);
        }
        return arr;
    }
}
