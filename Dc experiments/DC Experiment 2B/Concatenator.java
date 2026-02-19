import java.rmi.*;

public interface Concatenator extends Remote {
    String concat(String s1, String s2) throws RemoteException;
}
