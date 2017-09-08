package edu.learning.codefights.tree;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class HasPathWithGivenSum {
    public static boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        if (t == null) {
            return s == 0;
        }
        Queue<Integer> pathValues = new ArrayDeque<>();
        pathValues.add(t.value);
        Queue<Tree<Integer>> paths = new ArrayDeque<>();
        paths.add(t);
        while (!paths.isEmpty()) {
            Tree<Integer> tree = paths.remove();
            Integer currentPathValue = pathValues.remove();
            if (tree.left == null && tree.right == null && currentPathValue == s) {
                return true;
            }
            Optional.ofNullable(tree.left)
                    .ifPresent(childTree -> {
                        paths.add(childTree);
                        pathValues.add(currentPathValue + childTree.value);
                    });
            Optional.ofNullable(tree.right)
                    .ifPresent(childTree -> {
                        paths.add(childTree);
                        pathValues.add(currentPathValue + childTree.value);
                    });
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String treeStr = "{\n" +
                "    \"value\": 5,\n" +
                "    \"left\": null,\n" +
                "    \"right\": null\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Tree<Integer> tree = mapper.readValue(treeStr, Tree.class);
        System.out.println(hasPathWithGivenSum(tree, 5));
    }
}
