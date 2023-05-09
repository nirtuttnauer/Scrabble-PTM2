package test;


import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {
    LinkedHashSet<String> cache;

    public LRU() {
        this.cache = new LinkedHashSet<>();
    }

    @Override
    public void add(String word) {
        cache.remove(word);
        cache.add(word);
    }

    @Override
    public String remove() {
        String leastUsed = cache.iterator().next();
        cache.remove(leastUsed);
        return leastUsed;
    }
}
