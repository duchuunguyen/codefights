package edu.learning.codefights.tree;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class KthLargestInBST {
    public static int kthLargestInBST(Tree<Integer> t, int k) {
        Tree<Integer> current, pre;
        int count = 0;

        if (t == null)
            return 0;

        current = t;
        while (current != null) {
            if (current.left == null) {
                count++;
                if (count == k) {
                    return current.value;
                }
                current = current.right;
            } else {
                pre = current.left;
                while (pre.right != null && pre.right != current)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null;
                    count++;
                    if (count == k) {
                        return current.value;
                    }
                    current = current.right;
                }
            }
        }
        return t.value;
    }

    public static void main(String[] args) throws IOException {
        String treeStr = "{\n" +
                "    \"value\": 3,\n" +
                "    \"left\": {\n" +
                "        \"value\": 1,\n" +
                "        \"left\": null,\n" +
                "        \"right\": null\n" +
                "    },\n" +
                "    \"right\": {\n" +
                "        \"value\": 5,\n" +
                "        \"left\": {\n" +
                "            \"value\": 4,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        },\n" +
                "        \"right\": {\n" +
                "            \"value\": 6,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Tree<Integer> tree = mapper.readValue(treeStr, Tree.class);
        System.out.println(kthLargestInBST(tree, 4));
    }
}
