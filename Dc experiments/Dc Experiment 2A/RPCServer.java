import java.io.*;
import java.net.*;

public class RPCServer {

    // Actual remote method
    public static int add(int a, int b) {
        System.out.println("[Server] Executing add(" + a + ", " + b + ")");
        return a + b;
    }

    // Server Stub
    static class ServerStub {
        public static String processRequest(String packedRequest) {

            System.out.println("[Server Stub] Unpacking arguments...");

            String[] parts = packedRequest.split(",");
            String methodName = parts[0];

            if (methodName.equals("add")) {
                int a = Integer.parseInt(parts[1]);
                int b = Integer.parseInt(parts[2]);

                int result = add(a, b);

                return "result," + result;
            }

            return "error,Unknown method";
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("RPC Server started on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n[Server] Client connected: "
                        + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                String packedRequest = in.readLine();
                System.out.println("[Server Stub] Received packed request: "
                        + packedRequest);

                String packedResponse =
                        ServerStub.processRequest(packedRequest);

                System.out.println("[Server Stub] Sending packed response: "
                        + packedResponse);

                out.println(packedResponse);

                in.close();
                out.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
