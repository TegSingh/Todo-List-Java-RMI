package Client;

import java.rmi.registry.*;

import RemoteInterface.Todo_interface;

public class Client {
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            System.out.println("Registry located");

            try {
                Todo_interface stub1 = (Todo_interface) registry.lookup("Todo1");
                System.out.println("Bound stub received");
                stub1.add_todo();
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