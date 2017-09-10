package edu.learning.codefights.hashtable;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SwapLexOrder {
    public static String swapLexOrder(String str, int[][] pairs) {
        if (pairs.length == 0) {
            return str;
        }
        Map<Integer, List<Integer>> verticesMap = transformPair(str.length(), pairs);
        List<List<Integer>> components = buildComponents(str.length(), verticesMap);
        char[] chars = str.toCharArray();
        components.forEach(component -> biggestStringForComponent(str, chars, component));
        return new String(chars);
    }
    public static void biggestStringForComponent(String str, char[] input, List<Integer> component) {
        List<Character> chars = component.stream()
                .map(str::charAt)
                .collect(Collectors.toList());
        chars.sort(Comparator.reverseOrder());
        for (int i = 0; i < component.size(); i++) {
            input[component.get(i)] = chars.get(i);
        }
    }

    public static List<List<Integer>> buildComponents(int numOfVertices, Map<Integer, List<Integer>> verticesMap) {
        boolean[] visited = new boolean[numOfVertices];
        List<List<Integer>> components = new ArrayList<>();
        //Arrays.fill(visited, false);

        for (int i = 0; i < numOfVertices; i++) {
            if (!visited[i]) {
                // dfs
                Queue<Integer> queue = new ArrayDeque<>();
                queue.add(i);
                visited[i] = true;
                List<Integer> component = new ArrayList<>();
                while(!queue.isEmpty()) {
                    Integer vertex = queue.remove();
                    component.add(vertex);
                    List<Integer> linkedVertices = verticesMap.containsKey(vertex) ? verticesMap.get(vertex) : new ArrayList<>();
                    linkedVertices.forEach(v -> {
                        if (!visited[v]) {
                            queue.add(v);
                            visited[v] = true;
                        }
                    });
                }
                if (component.size() >= 2) {
                    component.sort(Comparator.naturalOrder());
                    components.add(component);
                }
            }
        }
        return components;
    }

    public static Map<Integer, List<Integer>> transformPair(int numOfVertices, int[][] pairs) {
        return IntStream.range(0, numOfVertices)
                .mapToObj(vertex -> {
                    return new AbstractMap.SimpleImmutableEntry<>(vertex, Arrays.stream(pairs)
                            .filter(pair -> pair[0] - 1 == vertex || pair[1] - 1 == vertex)
                            .map(pair -> {
                                if (pair[0] - 1 == vertex) {
                                    return pair[1] - 1;
                                } else {
                                    return pair[0] - 1;
                                }
                            })
                            .collect(Collectors.toList()));
                }).collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue));
    }

    public static void main(String[] args) {
        String str = "acxrabdz";
        int[][] pairs = {{1,3}, {6,8}, {3,8}, {2,7}};
        System.out.println(swapLexOrder(str, pairs));
    }
}
