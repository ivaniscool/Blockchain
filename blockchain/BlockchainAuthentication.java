package blockchain;

public class BlockchainAuthentication {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        User user1 = new User("Alice");
        blockchain.addUser(user1);
        String authToken = user1.authenticate();
        System.out.println("Authentication token: " + authToken);
        boolean isValid = blockchain.validateAuthentication(authToken, user1.getPublicKey());
        System.out.println("Authentication is valid: " + isValid);
    }
}
