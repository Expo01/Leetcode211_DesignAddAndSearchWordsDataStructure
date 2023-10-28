public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}




// THIS IS WHAT I WAS TRYING TO WRITE FOR THE SEARCH METHOD
class WordDictionary {
    private WordDictionary[] children;
    boolean isEndOfWord;
    // Initialize your data structure here.
    public WordDictionary() {
        children = new WordDictionary[26];
        isEndOfWord = false;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        WordDictionary curr = this;
        for(char c: word.toCharArray()){
            if(curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new WordDictionary();
            curr = curr.children[c - 'a'];
        }
        curr.isEndOfWord = true;
    }

    // Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        WordDictionary curr = this;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(c == '.'){
                for(WordDictionary ch: curr.children)
                    if(ch != null && ch.search(word.substring(i+1))) return true;
                return false;
            }
            if(curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr != null && curr.isEndOfWord;
    }
}









// MY IDEA IS MOSTLY RIGHT. I'M JUST NOT GOOD YET AT WRITING RECURSION
//
//class Node{
//    Node[] children = new Node[26];
//    boolean isWord = false;
//
//}
//
//class WordDictionary {
//    Node root;
//
//    public WordDictionary() {
//        this.root = new Node();
//    }
//
//    public void addWord(String word) {
//        Node temp = root;
//        for(char c: word.toCharArray()){
//            if(temp.children[c-'a'] == null){
//                temp.children[c-'a'] = new Node();
//            }
//            temp = temp.children[c-'a'];
//        }
//        temp.isWord = true;
//    }
//
//    // missed part of the problem. issue is '.' can be used in word to replace any char. difficulty is that we don't know
//    // which array index to continue on in. example. trie includes words: dad, did, duds. If searched word is
//    // "d.ds", when temp assigned to child node of char[] field of 'd' node, how do we know  whether to assign node to
//    // 'a', 'i', or 'u'. if we assigned 'a' or 'i', neither has child node of 's' and would return false. need to check
//    // all child nodes which is costly...
//    // not recursion because if we call "d.ds" then --> "ds" if say it was "os" not "ds" we'd be searching for s index
//    // from original root. sounds like i need a helper method to pass a new head which will be the char before '.'
//    // where for 'd' in 'duds' we will test search on all existing children. so we have have 'did'. 'does' 'dump' 'duds'
//    // and terminate did and does. then dump also terminates and returns false to OG search method after 'm' encountered
//    public boolean search(String word) {
//        Node temp = root;
//        for(int i = 0; i < word.length(); i++){
//            char c = word.charAt(i);
//            if( c == '.'){
//                for(Node n : root.children){
//                    if(n != null){
//                        helperSearch() // search one by one etc, but problem is if two consecutive dots thn the helper
//                        // method has to call itself recurisvely...
//                    }
//                }
//            }
//            if(temp.children[c-'a'] == null){
//                return false;
//            }
//            temp = temp.children[c-'a'];
//        }
//        return temp.isWord;
//    }
//
//    private boolean helperSearch(Node newRoot, String subString){
//        Node temp = root;
//        for(int i = 0; i < subString.length(); i++){
//            char c = subString.charAt(i);
//            if(temp.children[c-'a'] == null){
//                return false;
//            }
//            temp = temp.children[c-'a'];
//        }
//        return temp.isWord;
//
//
//    }
//}