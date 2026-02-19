import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected to server: localhost:8080");
            System.out.println("Type messages (type 'bye' to exit):");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));

            String userMsg, serverMsg;

            while (true) {
                System.out.print("You: ");
                userMsg = userInput.readLine();
                out.println(userMsg);

                if (userMsg.equalsIgnoreCase("bye")) {
                    break;
                }

                serverMsg = in.readLine();
                System.out.println("Server received: " + serverMsg);

                if (serverMsg.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Disconnected from server.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
