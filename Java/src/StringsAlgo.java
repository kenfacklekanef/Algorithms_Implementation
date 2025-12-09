import java.util.*;

public class StringsAlgo {

    public static int[] prefix(String s){
        int n=s.length(), pi[]=new int[n];
        for (int i=1;i<n;i++){
            int j=pi[i-1];
            while (j>0 && s.charAt(i)!=s.charAt(j)) j=pi[j-1];
            if (s.charAt(i)==s.charAt(j)) j++;
            pi[i]=j;
        }
        return pi;
    }

    public static List<Integer> kmp(String text, String pat){
        List<Integer> res = new ArrayList<>();
        int[] pi = prefix(pat);
        int j=0;
        for (int i=0;i<text.length();i++){
            while (j>0 && text.charAt(i)!=pat.charAt(j)) j=pi[j-1];
            if (text.charAt(i)==pat.charAt(j)) j++;
            if (j==pat.length()){
                res.add(i-j+1);
                j=pi[j-1];
            }
        }
        return res;
    }

    static class Trie {
        static class Node { Map<Character,Node> next=new HashMap<>(); boolean end=false;}
        Node root=new Node();
        void insert(String s){
            Node c=root;
            for (char x:s.toCharArray()){
                c.next.putIfAbsent(x,new Node());
                c=c.next.get(x);
            }
            c.end=true;
        }
        boolean search(String s){
            Node c=root;
            for (char x:s.toCharArray()){
                c=c.next.get(x);
                if (c==null) return false;
            }
            return c.end;
        }
    }
}
