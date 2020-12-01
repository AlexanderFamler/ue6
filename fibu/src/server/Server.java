package src.server;

import src.shared.FibuConfi;
import src.server.ServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        FibuConfi server = new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.bind("Server",server);
        System.out.println("Server started");
    }
}
