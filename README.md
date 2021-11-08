# Todo Application using Java RMI

### Instructions

Run the following command to compile all files in Client, Server, RemoteInterface, Todo packages

```bash
javac -d output Server/Server.java RemoteInterface/Todo_interface.java Server/Todo_interface_implementation.java Client/Client.java Todo/Todo_list.java Todo/Todo_test.java Todo/Todo_item.java
```

The command above stored everything with the same directory structure in a directory called output

Start the Java RMI Registry. This changes the terminal from bash to an rmiregsitry. This registry can accept Stubs for Remote Objects and is running on default port 1099. The following command can be used on Linux

```bash
rmiregistry 8000
```

Run the Server

```bash
java -Djava.security.policy=policy.txt -cp output/ Server.Server
```

Run the Client

```bash
java -Djava.security.policy=policy.txt -cp output/ Client.Client
```
