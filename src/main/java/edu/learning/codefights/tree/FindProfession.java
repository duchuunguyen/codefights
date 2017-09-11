package edu.learning.codefights.tree;

public class FindProfession {
    public static String findProfession(int level, int pos) {
        int timeToSwitch = 0;
        while (level > 1) {
            if ((pos - 1) % 2 == 1)
                timeToSwitch++;
            pos = (pos - 1) / 2 + 1;
            level--;
        }
        return timeToSwitch % 2 == 0 ? "Engineer" : "Doctor";
    }

    public static void main(String[] args) {
        int level = 3, pos = 3;
        System.out.println(findProfession(level, pos));
    }
}
