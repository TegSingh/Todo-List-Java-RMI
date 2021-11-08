package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import Todo.Todo_item;

public interface Todo_interface extends Remote {

    // Define API here
    public ArrayList<Todo_item> get_todo_list() throws RemoteException;

    public ArrayList<Todo_item> get_client_todo_list(int client_id) throws RemoteException;

    public ArrayList<Todo_item> get_date_todo_list(LocalDate date) throws RemoteException;

    public boolean add_todo(String action_item, LocalDate date) throws RemoteException;

    public boolean remove_todo_date(LocalDate date) throws RemoteException;

    public boolean remove_todo_client() throws RemoteException;

    public void terminate_process() throws RemoteException;

}