package blockchain;

import java.util.ArrayList;
import java.security.PublicKey;


public class Blockchain {
    private ArrayList<Block> blockchain;

    public Blockchain() {
        this.blockchain = new ArrayList<>();
        addBlock(new Block("Genesis Block", "0"));
    }

    public void addBlock(Block block) {
        block.mineBlock();
        blockchain.add(block);
    }

    public boolean validateAuthentication(String authToken, PublicKey publicKey) {
        for (Block block : blockchain) {
            if (block.validateAuthentication(authToken, publicKey)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        Transaction registrationTransaction = new Transaction("System", user.getName() + " registration");
        registrationTransaction.generateSignature(user.getPrivateKey());

        Block newBlock = new Block(blockchain.get(blockchain.size() - 1).getHash());
        newBlock.addTransaction(registrationTransaction);
        addBlock(newBlock);
    }
}
