package Client;

import java.rmi.registry.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import RemoteInterface.Todo_interface;

import Todo.Todo_item;
import Todo.Todo_list;
import java.util.Random;

public class Client {

    public Client() {
    }

    // Method to display the Menu
    public static void displayMenu() {
        System.out.println("-------------------MENU-------------------");
        System.out.println("1. Display TO-DO List");
        System.out.println("2. Display TO-DO List for a certain Client");
        System.out.println("3. Display TO-DO List for a certain Date");
        System.out.println("4. Add a TO-DO to the List");
        System.out.println("5. Remove TO-DOs for a certain Date from the List");
        System.out.println("6. Remove TO-DOs for a certain client from the List");
        System.out.println("Choose an INTEGER between 1-6: ");
    }

    // Method to display the list passed as parameter
    public static void display_list(ArrayList<Todo_item> list) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Your Requested Todo List: ");
        for (Todo_item item : list) {
            System.out.println(item.toString());
        }
        System.out.println("-------------------------------------------------------------");
    }

    public static void main(String[] args) {

        // Create a scanner object
        Scanner sc = new Scanner(System.in);

        Random rn = new Random();
        // Use a random 4 digit ID for client
        int id = rn.nextInt(9000) + 1000;

        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            System.out.println("Registry located");
            Todo_interface stub1 = (Todo_interface) registry.lookup("Todo1");
            System.out.println("Bound stub received");

            // Print the Client ID
            System.out.println("ASSIGNED CLIENT ID: " + id);
            stub1.update_client(id);
            try {

                int choice = 1;
                while (choice >= 1 && choice <= 6) {

                    displayMenu();
                    choice = sc.nextInt();
                    System.out.println("You entered: " + choice);

                    switch (choice) {

                    // Display the Todo List
                    case 1:
                        System.out.println("Printing the Todo List: ");
                        ArrayList<Todo_item> list1 = stub1.get_todo_list();

                        display_list(list1);

                        break;

                    // Display the Todo List for a certain client
                    case 2:
                        // FIGURE OUT HOW TO AUTO RECEIVE CLIENT ID USE THE FOLLOWING CODE TILL THEN
                        ArrayList<Todo_item> list2 = stub1.get_client_todo_list(id);
                        display_list(list2);
                        break;

                    // Display the Todo List for a certain date
                    case 3:
                        Scanner in3 = new Scanner(System.in);

                        // Get input for date
                        System.out.println("Enter year: ");
                        int year3 = Integer.parseInt(in3.nextLine());
                        System.out.println("Enter month: ");
                        int month3 = Integer.parseInt(in3.nextLine());
                        System.out.println("Enter day: ");
                        int day3 = Integer.parseInt(in3.nextLine());
                        LocalDate dueDate3 = LocalDate.of(year3, month3, day3);

                        // Get the list from the server
                        ArrayList<Todo_item> list3 = stub1.get_date_todo_list(dueDate3);
                        // Display the received list
                        display_list(list3);

                        break;

                    // Add a Todo to the list
                    case 4:
                        // Get information from the client
                        Scanner in4 = new Scanner(System.in);
                        System.out.println("Enter Todo Name: ");
                        String action_item4 = in4.nextLine();

                        System.out.println("Enter year: ");
                        int year4 = Integer.parseInt(in4.nextLine());
                        System.out.println("Enter month: ");
                        int month4 = Integer.parseInt(in4.nextLine());
                        System.out.println("Enter day: ");
                        int day4 = Integer.parseInt(in4.nextLine());
                        LocalDate dueDate4 = LocalDate.of(year4, month4, day4);

                        boolean success4 = stub1.add_todo(id, action_item4, dueDate4);

                        if (success4) {
                            System.out.println("Todo added successfully");
                        } else {
                            System.out.println("Could not add Todo");
                        }

                        break;

                    // Remove the Todos for a certain date
                    case 5:

                        Scanner in5 = new Scanner(System.in);

                        // Get input for date
                        System.out.println("Enter year: ");
                        int year5 = Integer.parseInt(in5.nextLine());
                        System.out.println("Enter month: ");
                        int month5 = Integer.parseInt(in5.nextLine());
                        System.out.println("Enter day: ");
                        int day5 = Integer.parseInt(in5.nextLine());
                        LocalDate dueDate5 = LocalDate.of(year5, month5, day5);

                        boolean success5 = stub1.remove_todo_date(dueDate5);
                        if (success5) {
                            System.out.println("Items removed successfully");
                        } else {
                            System.out.println("Nothing to remove");
                        }
                        break;

                    // Remove the Todos for a certain Client
                    case 6:
                        boolean success6 = stub1.remove_todo_client(id);
                        if (success6) {
                            System.out.println("Items removed successfully");
                        } else {
                            System.out.println("Nothing to remove");
                        }
                        break;

                    // Wrong input: terminate process
                    default:
                        stub1.terminate_process(id);
                        break;
                    }

                }
            } catch (Exception e) {
                System.out.println("Exception occured in Switch case");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Exception occured in Client: " + e.toString());
            e.printStackTrace();
        }

    }

}