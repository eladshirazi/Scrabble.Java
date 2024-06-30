package test;

import java.security.MessageDigest;
import java.util.BitSet;
import java.util.Arrays;
import java.math.BigInteger;


public class BloomFilter {
    private final int size;
    private final MessageDigest[] digests;
    private final BitSet bits;

    public BloomFilter(int size, String... algs) {
        this.size = size;
        this.digests = new MessageDigest[algs.length];
        try {
            for (int i = 0; i < algs.length; i++) {
                this.digests[i] = MessageDigest.getInstance(algs[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.bits = new BitSet(size);
    }

    public void add(String value) {
        for (MessageDigest digest : digests) {
            byte[] hash = digest.digest(value.getBytes());
            int index = Math.abs(new BigInteger(1, hash).intValue()) % size;
            bits.set(index, true);
        }
    }

    public boolean contains(String value) {
        for (MessageDigest digest : digests) {
            byte[] hash = digest.digest(value.getBytes());
            int index = Math.abs(new BigInteger(1, hash).intValue()) % size;
            if (!bits.get(index)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder(bits.length());
        for (int i = 0; i < bits.length(); i++)
            str.append(bits.get(i) ? "1" : "0");

        return str.toString();
    }
}


