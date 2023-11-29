package blockchain;

import java.util.ArrayList;
import java.security.PublicKey;


public class Block {
    private String hash;
    private String previousHash;
    private ArrayList<Transaction> transactions;
    private long timeStamp;
    private int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.transactions = new ArrayList<>();
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();

    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void mineBlock() {
        String target = new String(new char[3]).replace('\0', '0');
        while (!hash.substring(0, 3).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }

    public boolean validateAuthentication(String authToken, PublicKey publicKey) {
        for (Transaction transaction : transactions) {
            if (transaction.isValid(publicKey) && transaction.getHash().equals(authToken)) {
                return true;
            }
        }
        return false;
    }

    private String calculateHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString();
        return StringUtil.applySha256(dataToHash);
    }

    public String getHash() {
        return hash;
    }
}
