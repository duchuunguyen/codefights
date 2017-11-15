package edu.learning.codefights.heap;


import java.util.Stack;

public class SimplifyPath {
    public static String simplifyPath(String path) {
        String[] splits = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < splits.length; i++) {
            if (splits[i] != null && !splits[i].isEmpty()) {
                if (splits[i].equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (!splits[i].equals(".")) {
                    stack.push(splits[i]);
                }
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        return new StringBuffer("/")
                .append(String.join("/", stack.toArray(new String[0])))
                .toString();
    }

    static public void main(String[] args) {
        System.out.println(simplifyPath("/home/a/./x/../b//c/"));
    }
}