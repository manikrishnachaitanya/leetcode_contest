package leet.code.org;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class
 */
public class Node {
    char c;
    List<Node> next = new ArrayList<>();
    public Node(char c) {
        this.c = c;
    }

    public void add(Node n) {
        n.next.add(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                 c +
                '}';
    }
}
