package Server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import RemoteInterface.Todo_interface;

import Todo.Todo_item;
import Todo.Todo_list;

// File to implement API methods
public class Todo_interface_implementation implements Todo_interface, Serializable {

    // Create a static variable todo_list to be shared for all clients
    private static Todo_list todo_list = new Todo_list();

    // constructor
    public Todo_interface_implementation() {

    }

    // Method to display the entire list
    public ArrayList<Todo_item> get_todo_list() throws RemoteException {
        System.out.println("Get Todo list method called");
        ArrayList<Todo_item> return_list = todo_list.get_todo_list();
        System.out.println("Complete Todo List received size: " + return_list.size());
        for (Todo_item item : return_list) {
            System.out.println(item.toString());
        }
        return return_list;
    }

    // Method to display todo list for a client
    public ArrayList<Todo_item> get_client_todo_list(int id) throws RemoteException {
        System.out.println("Display Todo list method called for a certain client");
        return todo_list.get_id_todo_list(id);
    }

    // Method to display todos for a certain data
    public ArrayList<Todo_item> get_date_todo_list(LocalDate date) throws RemoteException {
        System.out.println("Display Todo list method called a certain date");
        return todo_list.get_todos_for_date(date);
    }

    // Method to add todo to the list
    public boolean add_todo(String action_item, LocalDate date) throws RemoteException {
        System.out.println("Method to add todo to the list");
        boolean success = todo_list.add_todo(1000, action_item, date);
        System.out.println("Todo added to the list");
        return success;
    }

    // Method to remove todo for a client
    public boolean remove_todo_client() throws RemoteException {
        System.out.println("Method to remove todo for a certain client");
        return true;
    }

    // Method to remove todo for a certain date
    public boolean remove_todo_date() throws RemoteException {
        System.out.println("Method to remove todo for a certain date");
        return true;
    }

    // Method to display appropriate messages on termination
    public void terminate_process() throws RemoteException {
        System.out.println("Process will be terminated");
    }

}
