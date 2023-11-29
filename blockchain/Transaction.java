package blockchain;

import java.security.*;

public class Transaction {
    private String sender;
    private String recipient;
    private String hash;
    private byte[] signature;

    public Transaction(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.hash = calculateHash();
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = sender + recipient + hash;
        this.signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean isValid(PublicKey publicKey) {
        String data = sender + recipient + hash;
        return StringUtil.verifyECDSASig(publicKey, data, signature);
    }

    private String calculateHash() {
        return StringUtil.applySha256(sender + recipient);
    }

    public String getHash() {
        return hash;
    }
}
