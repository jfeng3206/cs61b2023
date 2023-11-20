public class Palindrome{
    public Palindrome(){

    }
    public Deque<Character> wordToDeque(String word){
        if(word==null) {
            return null;
        }
        LinkedListDeque<Character> res = new LinkedListDeque<>();
        for(int i =0;i<word.length();i++){
            Character c = word.charAt(i);
            res.addLast(c);
        }
        return res;
    }

    public boolean isPalindrome(String word){
        if (word == ""||word.length()==1) {
            return true;
        }
        return isPalindromeHelper(wordToDeque(word));
    }

    private boolean isPalindromeHelper(Deque<Character> word){
        if(word.size()<=1){
            return true;
        }
        return (word.removeFirst()==word.removeLast()) && isPalindromeHelper(word);
    }
}
