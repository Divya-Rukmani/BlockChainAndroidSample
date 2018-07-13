package com.blockchainandroidsample;

import java.util.Date;

public class Block {
	
	public String hash;// Hash key generated using SHA-256
	public String previousHash; //inital value will be 0
	private String data; //our data will be a simple message.
	private long timestamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	//Calculate hash
	public String calculateHash() {
		String calculatedhash = Utils.applySha256(
				previousHash +
				Long.toString(timestamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}

	//Create a string with difficulty  to that of hash
	public void mineBlock(int difficulty) {
		String target = Utils.getDificultyString(difficulty);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
	}
	
}
