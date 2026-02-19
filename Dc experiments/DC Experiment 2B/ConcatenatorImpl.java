import java.rmi.*;
import java.rmi.server.*;

public class ConcatenatorImpl extends UnicastRemoteObject implements Concatenator {

    public ConcatenatorImpl() throws RemoteException {
        super();
    }

    public String concat(String s1, String s2) throws RemoteException {
        return s1 + s2;
    }
}
