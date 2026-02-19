import java.net.*;
import java.io.*;
import java.util.*;

public class GroupMember {
    private static final String GROUP_ADDRESS = "230.0.0.1";
    private static final int PORT = 6789;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java GroupMember <YourName>");
            return;
        }
        String name = args[0];

        try {
            // Join the Group
            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
            MulticastSocket socket = new MulticastSocket(PORT);
            
            // In modern Java, we use SocketAddress to join
            NetworkInterface networkInterface =
                    NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

            socket.joinGroup(new InetSocketAddress(group, PORT), networkInterface);

            System.out.println(name + " has joined the group.");

            // Thread to receive messages from the group
            Thread receiver = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet =
                                new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                        String message =
                                new String(packet.getData(), 0, packet.getLength());

                        // Don't print our own messages again
                        if (!message.startsWith(name + ":")) {
                            System.out.println("\n[Group Message] " + message);
                            System.out.print("Type message: ");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Leaving group...");
                }
            });
            receiver.start();

            // Main thread to send messages
            Scanner scanner = new Scanner(System.in);
            System.out.print("Type message (or 'LEAVE' to exit): ");

            while (true) {
                String msg = scanner.nextLine();

                if (msg.equalsIgnoreCase("LEAVE")) {
                    socket.leaveGroup(
                            new InetSocketAddress(group, PORT),
                            networkInterface);
                    System.exit(0);
                }

                String fullMsg = name + ": " + msg;
                byte[] bytes = fullMsg.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(bytes, bytes.length, group, PORT);

                socket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
