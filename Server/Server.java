package Server;

import java.rmi.server.UnicastRemoteObject;

import RemoteInterface.Todo_interface;

import java.rmi.registry.*;

public class Server {
    public static void main(String[] args) {
        try {

            // Set server to localhost
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            Todo_interface_implementation t1 = new Todo_interface_implementation();
            Todo_interface stub1 = (Todo_interface) UnicastRemoteObject.exportObject(t1, 1099);
            System.out.println("SERVER: Services interface exported and stub generated");

            // Bind the remote object's stub in the registry
            try {

                Registry registry = LocateRegistry.createRegistry(1099);
                System.out.println("SERVER: Registry created");
                registry.rebind("Todo1", stub1);
                System.out.println("SERVER: Stub bound");

            } catch (Exception e) {
                System.out.println("Could not bind");
            }

            System.out.println("SERVER: Server ready");

        } catch (Exception e) {
            System.out.println("SERVER: Exception occured: " + e.toString());
            e.printStackTrace();
        }
    }
}
