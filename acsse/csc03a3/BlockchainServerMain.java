package acsse.csc03a3;

public class BlockchainServerMain {
    public static void main(String[] args) {
        BlockchainServer server = new BlockchainServer();
        server.start(8080);
    }
}
