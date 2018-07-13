package com.blockchainandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import static com.blockchainandroidsample.Utils.isChainValid;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    String blockchainJson;
    public static int difficulty = 3;
    TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_result=(TextView) findViewById(R.id.txt_result);
        //Creating and validating blocks
        Log.d("Blocks->","\nAdding first block");
        addBlock(new Block("Transferring funds 1...", "0"));

        Log.d("Blocks->","\nAdding second block");
        addBlock(new Block("Transferring funds 2...",blockchain.get(blockchain.size()-1).hash));

        Log.d("Blocks->","\nAdding third block");
        addBlock(new Block("Transferring funds 3...",blockchain.get(blockchain.size()-1).hash));

        Log.d("Blocks->","\nBlockchain is Valid: " + isChainValid());
        //Returns the blockchain in JSONFORMAT
        blockchainJson = Utils.getJson(blockchain);
        Log.d("Blocks->",blockchainJson);

        txt_result.setText(blockchainJson);


    }

    //Adding blocks to the arrayList
    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

}
