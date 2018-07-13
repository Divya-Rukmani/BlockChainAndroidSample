package com.blockchainandroidsample;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.security.MessageDigest;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 3;

    //Applies Sha256 to a string
    public static String applySha256(String input){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hashString = new StringBuffer();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hashString.append('0');
                hashString.append(hex);
            }
            return hashString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target(i.e if difficulty is 3 it will return "000" to the hash
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    //Returns the blockchain is valid or not by comparing blocks
    public static Boolean isChainValid() {
        Block previous_block;
        Block current_block;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i=1; i < blockchain.size(); i++) {
            current_block = blockchain.get(i);
            previous_block = blockchain.get(i-1);
            if(!current_block.hash.equals(current_block.calculateHash()) ){
                Log.d("Block","Current hashes are not equal");
                return false;
            }
            if(!previous_block.hash.equals(current_block.previousHash) ) {
                Log.d("Block","Previous hash and current has are not equal");
                return false;
            }

            //check if hash whether has applied mining
            if(!current_block.hash.substring( 0, difficulty).equals(hashTarget)) {
                Log.d("Block","This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}
