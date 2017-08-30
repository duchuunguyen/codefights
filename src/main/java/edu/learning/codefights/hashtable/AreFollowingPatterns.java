package edu.learning.codefights.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duchuunguyen on 8/30/17.
 */
public class AreFollowingPatterns {
    public static boolean areFollowingPatterns(String[] strings, String[] patterns) {
        Map<String, String> stringAndPattern = new HashMap<>();
        Map<String, String> patternAndString = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            if (!stringAndPattern.containsKey(patterns[i])) {
                stringAndPattern.put(patterns[i], strings[i]);
            } else {
                if (!strings[i].equals(stringAndPattern.get(patterns[i]))) {
                    return false;
                }
            }

            if (!patternAndString.containsKey(strings[i])) {
                patternAndString.put(strings[i], patterns[i]);
            } else {
                if (!patterns[i].equals(patternAndString.get(strings[i]))) {
                    return false;
                }
            }
        }
        return true;
    }
}
