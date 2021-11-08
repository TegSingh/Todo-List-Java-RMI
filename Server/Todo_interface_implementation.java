package Server;

import java.rmi.RemoteException;

import RemoteInterface.Todo_interface;

// File to implement API methods
public class Todo_interface_implementation implements Todo_interface {

    public Todo_interface_implementation() {
        // constructor
    }

    // Method to display the entire list
    public void display_todo_list() throws RemoteException {
        System.out.println("Display Todo list method called");
    }

    // Method to display todo list for a client
    public void display_client_todo_list() throws RemoteException {
        System.out.println("Display Todo list method called for a certain client");
    }

    // Method to display todos for a certain data
    public void display_date_todo_list() throws RemoteException {
        System.out.println("Display Todo list method called a certain date");
    }

    // Method to add todo to the list
    public void add_todo() throws RemoteException {
        System.out.println("Method to add todo to the list");
    }

    // Method to remove todo for a client
    public void remove_todo_client() throws RemoteException {
        System.out.println("Method to remove todo for a certain client");
    }

    // Method to remove todo for a certain date
    public void remove_todo_date() throws RemoteException {
        System.out.println("Method to remove todo for a certain date");
    }

    // Method to display appropriate messages on termination
    public void terminate_process() throws RemoteException {
        System.out.println("Process will be terminated");
    }
}
