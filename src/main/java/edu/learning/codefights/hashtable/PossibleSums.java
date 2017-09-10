package edu.learning.codefights.hashtable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PossibleSums {
    public static int possibleSums(int[] coins, int[] quantity) {
        List<Map.Entry<Integer, Integer>> pairs = zip(coins, quantity);
        int maximum = pairs.stream()
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();
        Boolean[] arr = new Boolean[maximum + 1];
        Arrays.fill(arr, Boolean.FALSE);
        List<Boolean> dp = new ArrayList<>(Arrays.asList(arr));
        dp.set(0, Boolean.TRUE);
        pairs.stream().forEach(pair -> {
            int coin = pair.getKey();
            int q = pair.getValue();
            for (int i = 0; i < coin; i++) {
                int num = -1;
                for (int j = i; j < maximum + 1; j = j + coin) {
                    if (dp.get(j)) {
                        num = 0;
                    } else if (num >= 0) {
                        num++;
                    }
                    dp.set(j, 0 <= num && num <= q);
                }
            }
        });
        return (int) (dp.stream().filter(p -> p).count() - 1);
    }

    public static List<Map.Entry<Integer, Integer>> zip(int[] coins, int[] quantity) {
        return IntStream.range(0, coins.length)
                .mapToObj(i -> new AbstractMap.SimpleImmutableEntry<>(coins[i], quantity[i]))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] coins = {1, 2};
        int[] quantity = {50000, 2};

        System.out.println(possibleSums(coins, quantity));
    }
}
