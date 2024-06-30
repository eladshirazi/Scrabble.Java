package test;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LRU implements CacheReplacementPolicy {
    private final LinkedHashSet<String> cache;

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
        Iterator<String> itr = cache.iterator();
        String temp = itr.next();
        cache.remove(itr.next());

        return temp;
    }

}
