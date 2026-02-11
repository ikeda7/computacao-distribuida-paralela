import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Task<T> extends Remote {
    T execute() throws RemoteException;
}