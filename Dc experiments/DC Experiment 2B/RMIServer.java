import java.rmi.*;

public class RMIServer {
    public static void main(String[] args) {
        try {
            Concatenator obj = new ConcatenatorImpl();

            Naming.rebind("rmi://localhost/StringService", obj);

            System.out.println("Server is running and waiting for client...");
        } catch (Exception e) {
            System.out.println("Server exception: " + e);
        }
    }
}
