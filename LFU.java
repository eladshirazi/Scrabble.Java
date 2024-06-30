package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private final LinkedHashMap<String, Integer> cache;

    public LFU() {
        this.cache = new LinkedHashMap<>();
    }

    @Override
    public void add(String word) {
        cache.put(word, cache.getOrDefault(word, 0) + 1);
    }

    @Override
    public String remove() {
        String leastFrequentlyUsed = null;
        int minCount = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : cache.entrySet()) {
            if (entry.getValue() < minCount) {
                leastFrequentlyUsed = entry.getKey();
                minCount = entry.getValue();
            }
        }
        cache.remove(leastFrequentlyUsed);
        return leastFrequentlyUsed;
    }
}

