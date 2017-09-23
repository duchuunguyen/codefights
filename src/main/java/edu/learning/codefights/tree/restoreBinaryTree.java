package edu.learning.codefights.tree;

import java.util.Stack;

/**
 * Created by duchuunguyen on 9/23/17.
 */
public class restoreBinaryTree {
    public static Tree<Integer> restoreBinaryTree(int[] inorder, int[] preorder) {
        int i = 0,j = 0;
        Tree<Integer> root = new Tree<>(100001);
        Tree<Integer> pp = null, ptr = root;
        Stack<Tree<Integer>> sn = new Stack<>();
        sn.push(root);
        while (j < inorder.length) {
            if (sn.peek().value.equals(inorder[j])) {
                pp = sn.peek();
                sn.pop();
                j++;
            } else if (pp != null) {
                ptr = new Tree<>(preorder[i]);
                pp.right = ptr;
                pp = null;
                sn.push(ptr);
                i++;
            } else {
                ptr = new Tree<>(preorder[i]);
                sn.peek().left = ptr;
                sn.push(ptr);
                i++;
            }
        }
        ptr = root.left;
        return ptr;
    }

    public static void main(String[] args) {
        int[] inorder = {4, 2, 7, 1, 5, 3, 6};
        int[] preorder = {1, 2, 4, 7, 3, 5, 6};
        Tree<Integer> tree = restoreBinaryTree(inorder, preorder);
        System.out.println();
    }
}
