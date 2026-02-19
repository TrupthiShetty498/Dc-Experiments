import java.io.*;
import java.net.*;

public class RPCClient {

    // Client Stub
    static class ClientStub {
        private String host;
        private int port;

        public ClientStub(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public int callAdd(int a, int b) throws IOException {

            System.out.println("[Client Stub] Packing arguments: (" + a + ", " + b + ")");

            String packedRequest = "add," + a + "," + b;
            System.out.println("[Client Stub] Packed request: " + packedRequest);

            Socket socket = new Socket(host, port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.println("[Client Stub] Sending to server...");
            out.println(packedRequest);

            String packedResponse = in.readLine();
            System.out.println("[Client Stub] Received packed response: "
                    + packedResponse);

            System.out.println("[Client Stub] Unpacking result...");

            String[] parts = packedResponse.split(",");
            int result = Integer.parseInt(parts[1]);

            in.close();
            out.close();
            socket.close();

            return result;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== RPC Client Starting ===\n");

            ClientStub stub = new ClientStub("localhost", 12345);

            System.out.println("[Client] Calling remote procedure add(5, 3)");
            int result = stub.callAdd(5, 3);

            System.out.println("\n[Client] Result received: " + result);
            System.out.println("=== RPC Call Complete ===");

            System.out.println("\n\n[Client] Calling remote procedure add(10, 20)");
            result = stub.callAdd(10, 20);

            System.out.println("\n[Client] Result received: " + result);
            System.out.println("=== RPC Call Complete ===");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
