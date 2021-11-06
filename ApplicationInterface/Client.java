import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            // Get the stub for the remote interface to invoke remote methods
            Todo_interface stub = (Todo_interface) registry.lookup("Hello");

            // Call the sayHello method on the remote Server interface
            String response = stub.sayHello();
            System.out.println("response: " + response);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}