package leet.code.org;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution2 {
    int res;

    // parent = [-1,0,0,1,1,2], s = "abacbe"
    public int longestPath(int[] parent, String s) {
        res = 0;
        ArrayList<Integer>[] children = new ArrayList[parent.length];
        for (int i = 0; i < parent.length; i++)
            children[i] = new ArrayList<>();
        for (int i = 1; i < parent.length; i++)
            children[parent[i]].add(i);
        dfs(children, s, 0);
        return res;
    }

    private int dfs(ArrayList<Integer>[] children, String s, int i) {
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j : children[i]) {
            int cur = dfs(children, s, j);
            if (s.charAt(j) != s.charAt(i))
                queue.offer(-cur);
        }
        int big1 = queue.isEmpty() ? 0 : -queue.poll();
        int big2 = queue.isEmpty() ? 0 : -queue.poll();
        res = Math.max(res, big1 + big2 + 1);
        return big1 + 1;
    }
}
