package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Todo_interface extends Remote {

    // Define API here
    public void display_todo_list() throws RemoteException;

    public void display_client_todo_list() throws RemoteException;

    public void display_date_todo_list() throws RemoteException;

    public void add_todo() throws RemoteException;

    public void remove_todo_date() throws RemoteException;

    public void remove_todo_client() throws RemoteException;

    public void terminate_process() throws RemoteException;

}