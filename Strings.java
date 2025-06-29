package git_practice;

import javax.sql.rowset.spi.SyncResolver;
import java.util.*;

public class Strings {
    public static void main(String[] args) {
//        System.out.println(isPalindrome(" "));
//        System.out.println(strStr("nothing","sad"));
//        System.out.println(reverseWords("  hello world  "));
//        System.out.println(validPalindrome("abca"));
//        System.out.println(isAnagram("anagram","naaagrm"));
//        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        System.out.println(findAnagrams("abab","ab"));
        System.out.println(minSteps("leetcode","practice"));



    }

    public static boolean isPalindrome(String s) {
        int left = 0;int right = s.length() - 1;

        while (left <= right) {
            // Skip non-alphanumeric characters
            if(Character.isLetterOrDigit(s.charAt(left))){
               left++;

            }
            if((left <= right && !Character.isLetterOrDigit(s.charAt(right)))){
                right++;
            }

            // Compare characters case-insensitively
            if (left<=right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }


    public static int strStr(String given,String toFind){
        int i=0;
        String search ="";
        while(i<=given.length()-1 && i+toFind.length()<= given.length()){
            search = given.substring(i,i+toFind.length());
            if(toFind.equals(search)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static String reverseWords(String s) {
    String another = s.trim().replaceAll("\\s+", " ");
    String[]words = another.split(" ");
    StringBuilder sb = new StringBuilder();
    for(int i= words.length-1;i>=0;i--){
        sb.append(words[i]);
        if(i!=0)sb.append(" ");
    }
    return sb.toString();
    }

    public static boolean validPalindrome(String s) {
        int start =0;
        int end = s.length()-1;
        while(start<=end){
            if(s.charAt(start)!= s.charAt(end)){
                return isPalindromeString(s, start + 1, end) || isPalindromeString(s, start, end- 1);
            }
            else{
                start++;
                end--;
            }
        }
        return true;

    }



    private static boolean isPalindromeString(String s,int start,int end){
        while(start<=end){
            if(s.charAt(start) == s.charAt(end)){
                start ++;
                end --;
            }else{
                return false;
            }
        }
        return true;
    }


    public static  boolean isAnagram(String s, String t) {
        /*
        ---Less Efficient---
        List<Character> list = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();

        for(int i=0;i<=s.length()-1;i++){
            list.add(s.charAt(i));
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }

        for(int i=0;i<=t.length()-1;i++){
            list2.add(t.charAt(i));
        }
        Collections.sort(list2);
        StringBuilder sb2 = new StringBuilder();
        for (Character c : list2) {
            sb2.append(c);
        }
        return sb.toString().contentEquals(sb2);
        */

        /* let's try hashmaps
        if(s.length()!=t.length())return false;
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for(int i=0;i<=s.length();i++){
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i),0)+1);
            map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i),0)+1);
        }
        return map1.equals(map2);
         */

        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // for lowercase a-z

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }


    public static List<List<String>> groupAnagrams(String[] strs) {
       /*
        List<List<String>> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<=strs.length-1;i++){
            List<String> innerList = new ArrayList<>();
            if(!set.contains(i)){
            innerList.add(strs[i]);
            set.add(i);
                for (int j = i+1; j <=strs.length-1; j++) {
                    if(!set.contains(j)) {
                        if (checkAnagram(strs[i], strs[j])) {
                            set.add(j);
                            innerList.add(strs[j]);
                        }
                    }
                }
                if(!innerList.isEmpty()){
                    list.add(innerList);
                }
            }
        }
        return list;
        */
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /*
    private static boolean checkAnagram(String s,String t){
        if(s.length()!=t.length())return false;
        int[]arr=new int[26];
        for(int i=0;i<=s.length()-1;i++){
            arr[s.charAt(i)-'a']--;
            arr[t.charAt(i)-'a']++;
        }

        for(int ch:arr){
            if(ch!=0)return false;
        }
        return true;
    }

     */

    public static List<Integer> findAnagrams(String s, String p) {
        /*
        List<Integer> list = new ArrayList<>();
        String subString;
        char[] ch = p.toCharArray();
        Arrays.sort(ch);
        p= new String(ch);
        for(int i=0;i<=s.length()-1;i++){
            if(i+p.length()<=s.length()){
            subString = s.substring(i,i+p.length());
                    char[] ch2 = subString.toCharArray();
                    Arrays.sort(ch2);
                    String key = new String(ch2);
                    if(key.equals(p)){
                        list.add(i);
                    }
            }
        }
        return list;

         */
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        int m = p.length();

        for (int i = 0; i < m; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(pCount, sCount)) res.add(0);

        for (int i = m; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;               // add new char
            sCount[s.charAt(i - m) - 'a']--;           // remove old char

            if (Arrays.equals(pCount, sCount)) {
                res.add(i - m + 1);
            }
        }

        return res;
    }


    public static int minSteps(String s, String t) {
        /*
        Map<Character,Integer> map = new HashMap<>();
        int count=0;
        for(int i=0;i<=s.length()-1;i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i=0;i<=t.length()-1;i++){
            if(map.containsKey(t.charAt(i))){
                map.put(t.charAt(i),map.get(t.charAt(i))-1);
                if(map.get(t.charAt(i))==0) {
                    map.remove(t.charAt(i));
                }
            }
        }

        for(int values: map.values()){
            count+= values;
        }
        return count;
         */
        int count=0;
        int [] array = new int[26];
        for(int i=0;i<=s.length()-1;i++){
            array[s.charAt(i)-'a']++;
        }
        for(int i=0;i<=s.length()-1;i++){
            array[s.charAt(i)-'a']--;
        }
        for(int ch :array){
            if(ch != 0)count++;
        }
        return count;
    }

    /*
    private static int[] minStepAnagram(String s, String t){

        int[] count = new int[26]; // for lowercase a-z

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        /*
        for (int c : count) {
            if (c != 0) return false;
        }

        return count;
    }
    */



}
