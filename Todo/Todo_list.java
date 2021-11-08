package Todo;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Todo_list {

    private ArrayList<Todo_item> todo_items = new ArrayList<Todo_item>();

    public Todo_list() {

    }

    // Method to add an action item to the list
    public boolean add_todo(int id, String action_item, LocalDate dueDate) {

        // Create a Todo object
        Todo_item item = new Todo_item(id, action_item, dueDate);
        // Add the item to the list
        if (todo_items.add(item)) {
            return true;
        } else {
            return false;
        }

    }

    // Method to get the entire todo list
    public ArrayList<Todo_item> get_todo_list() {
        System.out.println("Get todo list method called from Todo package");
        return todo_items;
    }

    // Method to get Todo list filtered based on id (for client specific removal)
    public ArrayList<Todo_item> get_id_todo_list(int id) {
        ArrayList<Todo_item> return_todo_items = new ArrayList<Todo_item>();

        // Look for todos for a specific date by looping through the entire list
        for (Todo_item item : todo_items) {
            if (item.get_id() == id) {
                // Add
                return_todo_items.add(item);
            }
        }

        return return_todo_items;
    }

    // Method to return the action items due at a certain date
    public ArrayList<Todo_item> get_todos_for_date(LocalDate dueDate) {

        ArrayList<Todo_item> return_todo_items = new ArrayList<Todo_item>();

        // Look for todos for a specific date by looping through the entire list
        for (Todo_item item : todo_items) {
            if (item.get_dueDate().isEqual(dueDate)) {
                // Add
                return_todo_items.add(item);
            }
        }

        return return_todo_items;
    }

    // Method to remove an action item from the list
    public ArrayList<Todo_item> remove_todo_for_date(LocalDate dueDate) {

        ArrayList<Todo_item> deleted_todos = new ArrayList<>();

        // Declare an iterator to avoid java.util.concurrent modification exception
        Iterator<Todo_item> iterator = todo_items.iterator();

        // Get item to remove by looping through the entire list
        while (iterator.hasNext()) {
            Todo_item item = iterator.next();

            if (item.get_dueDate().isEqual(dueDate)) {
                System.out.println("Removing Item: " + item.toString());
                deleted_todos.add(item);
                iterator.remove();
            }
        }
        return deleted_todos;

    }

    // Method to remove an action item from the list
    public ArrayList<Todo_item> remove_todo_by_id(int client_id) {

        ArrayList<Todo_item> deleted_todos = new ArrayList<>();

        // Declare an iterator to avoid java.util.concurrent modification exception
        Iterator<Todo_item> iterator = todo_items.iterator();

        // Get item to remove by looping through the entire list
        while (iterator.hasNext()) {
            Todo_item item = iterator.next();

            if (item.get_id() == client_id) {
                System.out.println("Removing Item: " + item.toString());
                deleted_todos.add(item);
                iterator.remove();
            }
        }
        return deleted_todos;
    }

    // Method to display the entire list
    public void display_todo_list() {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Current To-do list: ");
        // Display all todos currently in the list
        for (Todo_item item : todo_items) {
            System.out.println(item.toString());
        }

        System.out.println("-----------------------------------------------------------------------------");

    }

}