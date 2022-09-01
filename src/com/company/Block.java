package com.company;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = this.calcularHash();
    }

    public String minerar(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix)
                .equals(prefixString)) {
            nonce++;
            hash = this.calcularHash();
        }
        return hash;
    }

    public String calcularHash() {
        String dataToHash = previousHash
                + timeStamp
                + nonce
                + data;
        MessageDigest digest;

        byte[] bytes = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            System.err.println(ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    @Override
    public String toString() {
        return "[nonce = "+ nonce+"] - [" + hash + "]";
    }
}
