package leet.code.org;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        //int result = solution.longestPath(new int[]{-1,0,1,2,3,4}, "zzabab");
        int result = solution.longestPath(new int[]{-1,0,0,0}, "aabc");
        System.out.println("Result " + result);
    }
    int max = 0;
    public int longestPath(int[] parent, String s) {
        Node[] list = new Node[parent.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Node(s.charAt(i));
        }
        for (int i = 1; i < list.length; i++) {
            list[i].add(list[parent[i]]);
        }
        iterate(list[0]);
        return max;
    }

    private int iterate(Node root) {
        if (root == null) {
            return 0;
        }
        int myMax = 1;
        for (Node n : root.next) {
            int count = iterate(n);
            System.out.println("Root " + root + " n " + n + " count "+ count + " max " + max + " myMax " + myMax);
            if (n.c != root.c) {
                if (myMax != 1) {
                    max = Math.max(myMax + count, max);
                }
                myMax = Math.max(count + 1, myMax);
            }
        }
        max = Math.max(myMax, max);
        return myMax;
    }
}
