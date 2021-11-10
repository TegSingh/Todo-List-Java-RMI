package Client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
        System.out.println("7. Download file with Todos to local package");
        System.out.println("8. Download file with Todos to local package");
        System.out.println("Choose an INTEGER between 1-8: ");
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
                while (choice >= 1 && choice <= 8) {

                    displayMenu();
                    choice = sc.nextInt();
                    System.out.println("You entered: " + choice);

                    switch (choice) {

                    // Display the Todo List
                    case 1:
                        System.out.println("Printing the Todo List: ");
                        ArrayList<Todo_item> list1 = stub1.get_todo_list(id);

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
                        ArrayList<Todo_item> list3 = stub1.get_date_todo_list(id, dueDate3);
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

                        boolean success5 = stub1.remove_todo_date(id, dueDate5);
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

                    // Get the Todos stored in a file from the client
                    case 7:
                        System.out.println("Downloading file...");
                        byte[] todo_file = stub1.get_todo_file(id);
                        File todoFile = new File("Client/todo_list.txt");
                        BufferedOutputStream output = new BufferedOutputStream(
                                new FileOutputStream(todoFile.getPath()));
                        output.write(todo_file, 0, todo_file.length);
                        output.flush();
                        output.close();

                        // Check if file has been downloaded successfully4
                        if (todoFile.exists()) {
                            System.out.println("File Downloaded Successfully");
                        } else {
                            System.out.println("Error downloading file");
                        }
                        break;

                    // Get the Todos for a client in a file
                    case 8:
                        System.out.println("Downloading Client Todo file...");
                        byte[] todo_file8 = stub1.get_todo_file_client(id);
                        String path_name = "Client/todo_list_" + id + ".txt";
                        File todoFile8 = new File(path_name);
                        BufferedOutputStream output8 = new BufferedOutputStream(
                                new FileOutputStream(todoFile8.getPath()));
                        output8.write(todo_file8, 0, todo_file8.length);
                        output8.flush();
                        output8.close();

                        // Check if file has been downloaded successfully4
                        if (todoFile8.exists()) {
                            System.out.println("File Downloaded Successfully");
                        } else {
                            System.out.println("Error downloading file");
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
                stub1.terminate_process(id);
            }

        } catch (Exception e) {
            System.out.println("Exception occured in Client: " + e.toString());
            e.printStackTrace();
        }

    }

}