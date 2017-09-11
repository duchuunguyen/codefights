package edu.learning.codefights.tree;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;

public class IsTreeSymmetric {
    public static boolean isTreeSymmetric(Tree<Integer> t) {
        if (t == null || (t.left == null && t.right == null)) {
            return true;
        }
        LinkedList<Tree<Integer>> lTreeQueue = new LinkedList<>();
        LinkedList<Tree<Integer>> rTreeQueue = new LinkedList<>();
        lTreeQueue.add(t.left);
        rTreeQueue.add(t.right);
        while (lTreeQueue.size() > 0 && rTreeQueue.size() > 0) {
            Tree<Integer> lTree = lTreeQueue.remove();
            Tree<Integer> rTree = rTreeQueue.remove();
            if ((lTree == null || rTree == null) && !(lTree == null && rTree == null))
                return false;
            if (lTree != null && !lTree.value.equals(rTree.value))
                return false;
            if (lTree != null) {
                lTreeQueue.add(lTree.left);
                lTreeQueue.add(lTree.right);
            }
            if (rTree != null) {
                rTreeQueue.add(rTree.right);
                rTreeQueue.add(rTree.left);
            }
            if (lTreeQueue.size() != rTreeQueue.size())
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        String treeStr = "{\n" +
                "    \"value\": 1,\n" +
                "    \"left\": {\n" +
                "        \"value\": 2,\n" +
                "        \"left\": {\n" +
                "            \"value\": 3,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        },\n" +
                "        \"right\": {\n" +
                "            \"value\": 4,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        }\n" +
                "    },\n" +
                "    \"right\": {\n" +
                "        \"value\": 2,\n" +
                "        \"left\": {\n" +
                "            \"value\": 4,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        },\n" +
                "        \"right\": {\n" +
                "            \"value\": 3,\n" +
                "            \"left\": null,\n" +
                "            \"right\": null\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Tree<Integer> tree = mapper.readValue(treeStr, Tree.class);
        System.out.println(isTreeSymmetric(tree));
    }
}
