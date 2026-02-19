import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Time Server running...");

            while (true) {
                Socket socket = server.accept();

                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);

                long serverTime = System.currentTimeMillis();
                out.println(serverTime);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
