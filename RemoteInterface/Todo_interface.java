package RemoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import Todo.Todo_item;

public interface Todo_interface extends Remote {

    // Define API here
    public ArrayList<Todo_item> get_todo_list(int id) throws RemoteException;

    public ArrayList<Todo_item> get_client_todo_list(int client_id) throws RemoteException;

    public ArrayList<Todo_item> get_date_todo_list(int id, LocalDate date) throws RemoteException;

    public boolean add_todo(int id, String action_item, LocalDate date) throws RemoteException;

    public boolean remove_todo_date(int id, LocalDate date) throws RemoteException;

    public boolean remove_todo_client(int id) throws RemoteException;

    public void terminate_process(int id) throws RemoteException; // New feature

    public boolean update_client(int id) throws RemoteException; // New feature

    public byte[] get_todo_file(int id) throws RemoteException; // New feature

    public byte[] get_todo_file_client(int id) throws RemoteException; // New feature

}