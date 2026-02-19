import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        try {
            long t0 = System.currentTimeMillis();

            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            long serverTime = Long.parseLong(in.readLine());
            long t1 = System.currentTimeMillis();

            long roundTripDelay = (t1 - t0) / 2;
            long synchronizedTime = serverTime + roundTripDelay;

            System.out.println("Server time: " + serverTime);
            System.out.println("Round trip delay: " + roundTripDelay);
            System.out.println("Client synchronized time: " + synchronizedTime);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
