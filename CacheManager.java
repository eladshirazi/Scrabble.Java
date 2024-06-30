package test;


import java.util.HashSet;

public class CacheManager {
	int size;
    CacheReplacementPolicy CRP;
    HashSet<String> cache;

    public CacheManager(int size, CacheReplacementPolicy CRP) {
        this.size = size;
        this.CRP = CRP;
        cache=new HashSet<>();
    }

    public boolean query(String s){
        return cache.contains(s);
    }
    public void add(String s) {
        if(cache.size()>=size)
            cache.remove(CRP.remove());

        CRP.add(s);
        cache.add(s);
    }
}
