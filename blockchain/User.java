package blockchain;

import java.security.*;

public class User {
    private String name;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public User(String name) {
        this.name = name;
        generateKeyPair();
    }

    public String getName() {
        return name;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String authenticate() {
        Transaction authTransaction = new Transaction("System", name + " authentication");
        authTransaction.generateSignature(privateKey);
        return authTransaction.getHash();
    }

    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime256v1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
