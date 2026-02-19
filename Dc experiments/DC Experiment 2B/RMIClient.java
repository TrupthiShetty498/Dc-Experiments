import java.rmi.*;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Concatenator stub =
                    (Concatenator) Naming.lookup("rmi://localhost/StringService");

            String result = stub.concat("Hello ", "Distributed World!");

            System.out.println("Response from Server: " + result);
        } catch (Exception e) {
            System.out.println("Client exception: " + e);
        }
    }
}
