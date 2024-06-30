package test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class Dictionary {
    CacheManager LRUCache;
    CacheManager LFUCache;
    BloomFilter BLMFilter;
    String[] files;

    public Dictionary(String... fileNames) {
        LRUCache = new CacheManager(400, new LRU());
        LFUCache = new CacheManager(100, new LFU());
        BLMFilter = new BloomFilter(256, "MD5", "SHA1");
        files=fileNames;

        for (String fileName : fileNames) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String[] Words;
            while (scanner.hasNext()) {
                Words = scanner.next().split(" ");
                for (String word : Words)
                    BLMFilter.add(word);
            }
            scanner.close();

        }
    }

    public boolean query(String word){
        if(LRUCache.query(word))
            return true;
        if(LFUCache.query(word))
            return false;
        if(BLMFilter.contains(word))
        {
            LRUCache.add(word);
            return true;
        }
        LFUCache.add(word);
        return false;
    }

    public boolean challenge(String word){
        try {
            if(IOSearcher.search(word,files))
            {
                LRUCache.add(word);
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            LFUCache.add(word);
            return false;

    }




}