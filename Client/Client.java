package Client;

import java.rmi.registry.*;
import java.util.Scanner;

import RemoteInterface.Todo_interface;

public class Client {

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

    public static void main(String[] args) {

        // Create a scanner object
        Scanner sc = new Scanner(System.in);

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            System.out.println("Registry located");

            try {
                Todo_interface stub1 = (Todo_interface) registry.lookup("Todo1");
                System.out.println("Bound stub received");
                int choice = 1;
                while (choice >= 1 && choice <= 6) {
                    displayMenu();
                    choice = sc.nextInt();
                    System.out.println("You entered: " + choice);

                    switch (choice) {
                    // Display the Todo List
                    case 1:
                        stub1.display_todo_list();
                        ;
                        break;
                    // Display the Todo List for a certain client
                    case 2:
                        stub1.display_client_todo_list();
                        break;
                    // Display the Todo List for a certain date
                    case 3:
                        stub1.display_date_todo_list();
                        ;
                        break;
                    // Add a Todo to the list
                    case 4:
                        stub1.add_todo();
                        ;
                        break;
                    // Remove the Todos for a certain date
                    case 5:
                        stub1.remove_todo_date();
                        ;
                        break;
                    // Remove the Todos for a certain Client
                    case 6:
                        stub1.remove_todo_client();
                        ;
                        break;
                    default:
                        stub1.terminate_process();
                        break;
                    }

                }
            } catch (Exception e) {
                System.out.println("Bound stub couldnt be received");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Exception occured in Client: " + e.toString());
            e.printStackTrace();
        }

    }

}