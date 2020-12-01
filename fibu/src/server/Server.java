package src.server;

import src.shared.FibuConfi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        FibuConfi server = (FibuConfi) new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.bind("Server",server);
        server.saveDataAsFile();
        System.out.println("Server started");
    }
}
