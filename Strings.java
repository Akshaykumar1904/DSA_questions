package git_practice;

public class Strings {
    public static void main(String[] args) {
        System.out.println(isPalindrome(" "));
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
}
