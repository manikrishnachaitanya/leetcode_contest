package leet.code.org;

import java.util.*;

public class Solution {
    StringBuilder sb;
    List<Integer> bestResults = new ArrayList<>();

    //[-1,0,1,2,3,4]
    //"zzabab"

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.longestPath(new int[]{-1,0,1,2,3,4}, "zzabab");
        System.out.println("Result " + result);
    }

    public int longestPath(int[] parent, String s) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        sb = new StringBuilder(s);
        int size = parent.length;
        for (int i =0; i< size; i++)
        {
            if (parent[i] == -1)
            {
                map.put(i,  new HashSet<>());
                continue;
            }

            Set<Integer> set = map.getOrDefault(i, new HashSet<>());
            set.add(parent[i]);
            map.put(i, set);

            //Set<Integer> set2 = map.getOrDefault(parent[i], new HashSet<>());
            //set2.add(i);
            //map.put(parent[i], set2);
        }

        // Print the map
        printMap(map);

        for (Integer key : map.keySet())
        {
            if ( key == 5)
            recFun(key, map, new ArrayList<>(), null, new HashSet<>());
        }

        int maxResult = 1;
        for(Integer result : bestResults)
        {
            maxResult = Math.max(maxResult, result);
        }
        return maxResult;
    }

    private void printMap(Map<Integer, Set<Integer>> map) {
        for (Integer key : map.keySet())
        {
            System.out.print(" " + key +"->" + getSet(map.get(key)));
        }
        System.out.println( );
    }

    public String getSet(Set<Integer> set)
    {
        StringBuilder res = new StringBuilder(" ");
        if (set != null)
        for(Integer val : set)
            res.append ( val+",");
        return res.toString();
    }

    public String getCharSet(List<Character> set)
    {
        StringBuilder res = new StringBuilder(" ");
        if (set != null)
            for(Character val : set)
                res.append ( val+",");
        return res.toString();
    }

    public void recFun(int node, Map<Integer, Set<Integer>> map, List<Character> listSoFar, Character previous, Set<Integer> traversedSet)
    {

        boolean hasConn = false;
        Set<Integer> connections = map.get(node);
        System.out.println("Node " + node + " T " + getSet(traversedSet) + " Conns "+ getSet(connections) + " chars "+ getCharSet(listSoFar));
        for (Integer conn : connections)
        {
            if (!traversedSet.contains(node))
            {
                hasConn = true;

                List<Character> newSetSoFar = new ArrayList<>();
                newSetSoFar.addAll(listSoFar);
                if (previous == null || Character.compare(sb.charAt(node), previous) != 0)
                {
                    newSetSoFar.add(sb.charAt(node));

                    Set<Integer> newTraversedSet = new HashSet<>();
                    newTraversedSet.addAll(traversedSet);
                    newTraversedSet.add(node);
                    recFun(conn, map, newSetSoFar, sb.charAt(node), newTraversedSet);
                }
                else // if duplicate element comes in, then break and add to the result
                {
                    System.out.println("Dup: Node " + node + " Result " + newSetSoFar.size() + " T " + getSet(traversedSet) );
                    bestResults.add(newSetSoFar.size());
                    continue;
                }
            }
        }

        if (!hasConn) // if no connections left, return
        {
            bestResults.add(listSoFar.size());
            System.out.println("No more conns Node " + node + " Result " + listSoFar.size()  + " T " + getSet(traversedSet));
            return;
        }
    }
}
