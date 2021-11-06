import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Todo_interface extends Remote {
    String sayHello() throws RemoteException;
}