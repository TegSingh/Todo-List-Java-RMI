package Server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.namespace.QName;

import RemoteInterface.Todo_interface;

import Todo.Todo_item;
import Todo.Todo_list;

// File to implement API methods
public class Todo_interface_implementation implements Todo_interface, Serializable {

    // Create a static variable todo_list to be shared for all clients
    private static Todo_list todo_list = new Todo_list();

    private static ArrayList<Integer> active_client_list = new ArrayList<>();

    // constructor
    public Todo_interface_implementation() {

    }

    // Method to Add and validate client
    public boolean update_client(int id) {

        // Check if ID already exists
        for (Integer client_id : active_client_list) {
            if (id == client_id) {
                return false;
            }
        }

        // Add the new client to the list of active clients
        active_client_list.add(id);

        // Display all the active clients
        System.out.print("SERVER: Active Clients:- ");
        for (Integer client_id : active_client_list) {
            System.out.print(client_id + ", ");
        }

        System.out.println();
        return true;
    }

    // Method to display the entire list
    public ArrayList<Todo_item> get_todo_list(int id) throws RemoteException {
        System.out.println("CLIENT ID " + id + ": Requested Display Complete List");
        ArrayList<Todo_item> return_list = todo_list.get_todo_list();
        return return_list;
    }

    // Method to display todo list for a client
    public ArrayList<Todo_item> get_client_todo_list(int id) throws RemoteException {
        System.out.println("CLIENT ID " + id + ": Requested Client Specific Todo List");
        return todo_list.get_id_todo_list(id);
    }

    // Method to display todos for a certain data
    public ArrayList<Todo_item> get_date_todo_list(int id, LocalDate date) throws RemoteException {
        System.out.println("CLIENT ID " + id + ": Requested Date Specific Todo List");
        return todo_list.get_todos_for_date(date);
    }

    // Method to add todo to the list
    public boolean add_todo(int id, String action_item, LocalDate date) throws RemoteException {
        System.out.println("CLIENT ID " + id + ": Adding Action Item to the List");
        boolean success = todo_list.add_todo(id, action_item, date);
        return success;
    }

    // Method to remove todo for a client
    public boolean remove_todo_client(int id) throws RemoteException {

        System.out.println("CLIENT ID " + id + ": Requested Deletion of its Todos");
        ArrayList<Todo_item> deleted_todos = todo_list.remove_todo_by_id(id);

        if (deleted_todos.size() != 0) {
            // System.out.println("Deleted Todos: ");
            // display_list(deleted_todos);
            System.out.println("CLIENT ID " + id + ": Number of todos deleted: " + deleted_todos.size());
            return true;
        } else {
            System.out.println("CLIENT ID " + id + ": No items found to delete");
            return false;
        }
    }

    // Method to remove todo for a certain date
    public boolean remove_todo_date(int id, LocalDate date) throws RemoteException {

        System.out.println("CLIENT ID " + id + ": Requested Date specific deletion of Todos");
        ArrayList<Todo_item> deleted_todos = todo_list.remove_todo_for_date(date);

        if (deleted_todos.size() != 0) {

            // System.out.println("Deleted Todos: ");
            // display_list(deleted_todos);

            System.out.println("CLIENT ID " + id + ": Number of todos deleted: " + deleted_todos.size());
            return true;
        } else {
            System.out.println("CLIENT ID " + id + ": No items found to delete");
            return false;
        }
    }

    // Method to display appropriate messages on termination
    public void terminate_process(int id) throws RemoteException {

        System.out.println("Client ID " + id + ": Process will be terminated");

        // Remove the id from the list of active lists
        for (int i = 0; i < active_client_list.size(); i++) {
            if (id == active_client_list.get(i)) {
                active_client_list.remove(i);
            }
        }

        // Display all the active clients
        System.out.print("SERVER: Active Clients:- ");
        for (Integer client_id : active_client_list) {
            System.out.print(client_id + ", ");
        }
        System.out.println();

    }

    // Method to display the list passed as parameter
    public static void display_list(ArrayList<Todo_item> list) {
        System.out.println("------------------------------------");
        System.out.println("Your Requested Todo List: ");
        for (Todo_item item : list) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------");
    }

    // Method to send a file generated in server to client. The file contains a list
    // of all the Todos
    public byte[] get_todo_file(int id) {

        // Creating the File using File Writer
        System.out.println("Client ID " + id + ": Requested Todo File");
        ArrayList<Todo_item> todo_items = todo_list.get_todo_list();
        try {
            FileWriter todo_file = new FileWriter("Server/Todo_list.txt");
            for (Todo_item item : todo_items) {
                // Write the Todo string to the file
                todo_file.write(item.toString());
                todo_file.write(System.getProperty("line.separator"));
            }

            todo_file.close();

        } catch (IOException e) {
            System.out.println("SERVER: Error opening the file writer");
            e.printStackTrace();
            return null;
        }

        // Write file data to input stream
        try {
            File todo_file_bytestream = new File("Server/Todo_list.txt");
            byte buffer[] = new byte[(int) todo_file_bytestream.length()];
            BufferedInputStream input_stream = new BufferedInputStream(new FileInputStream(todo_file_bytestream));
            input_stream.read(buffer, 0, buffer.length);
            input_stream.close();
            return buffer;
        } catch (IOException e) {
            System.out.println("SERVER: Error could not write to File stream for Client " + id);
            return null;
        }

    }

    // Method to download todo file for client
    public byte[] get_todo_file_client(int id) {
        ArrayList<Todo_item> client_todo_items = todo_list.get_id_todo_list(id);

        // Creating the File using File Writer
        System.out.println("Client ID " + id + ": Requested Client Specific Todo File ");

        try {
            FileWriter todo_file = new FileWriter("Server/Todo_list_" + id + ".txt");
            for (Todo_item item : client_todo_items) {
                // Write the Todo string to the file
                todo_file.write(item.toString());
                todo_file.write(System.getProperty("line.separator"));
            }

            todo_file.close();

        } catch (IOException e) {
            System.out.println("SERVER: Error opening the file writer");
            e.printStackTrace();
            return null;
        }

        // Write file data to input stream
        try {
            String path_name = "Server/Todo_list_" + id + ".txt";
            File todo_file_bytestream = new File(path_name);
            byte buffer[] = new byte[(int) todo_file_bytestream.length()];
            BufferedInputStream input_stream = new BufferedInputStream(new FileInputStream(todo_file_bytestream));
            input_stream.read(buffer, 0, buffer.length);
            input_stream.close();
            return buffer;
        } catch (IOException e) {
            System.out.println("SERVER: Error could not write to File stream for Client " + id);
            return null;
        }

    }

}
