package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Objects;

public class BloomFilter {
    final BitSet bits;
   ArrayList<MessageDigest> md = new ArrayList<>();

    public BloomFilter(int k, String...args) {
        bits = new BitSet(k);
        for (String hash : args){
            try {
                md.add(MessageDigest.getInstance(hash));
            }
            catch (NoSuchAlgorithmException e){
                throw new RuntimeException();
            }
        }

    }

    public void add(String w) {
        for (MessageDigest m: md) {
            BigInteger bi = new BigInteger(m.digest(w.getBytes()));
            bits.set((Math.abs(bi.intValue()) % bits.size()),true);
        }
    }

    public boolean contains(String w) {
        for (MessageDigest m: md) {
            BigInteger bi = new BigInteger(m.digest(w.getBytes()));
            if (!bits.get(Math.abs(bi.intValue()) % bits.size())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloomFilter that = (BloomFilter) o;
        return Objects.equals(bits, that.bits) && Objects.equals(md, that.md);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i =0 ;i< bits.length() ; i++){
            s.append(bits.get(i)? 1 : 0);
        }
        return s.toString();
    }
}
