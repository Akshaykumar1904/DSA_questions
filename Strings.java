package git_practice;

public class Strings {
    public static void main(String[] args) {
//        System.out.println(isPalindrome(" "));
//        System.out.println(strStr("nothing","sad"));
//        System.out.println(reverseWords("  hello world  "));
        System.out.println(validPalindrome("abca"));
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
}
