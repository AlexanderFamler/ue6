package server;

import shared.FibuConfi;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements FibuConfi {
    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject( this, 0);
    }

    @Override
    public void saveDataAsFile() throws RemoteException {


        List<String> bestellung = new ArrayList<>();  // ToDo ArrayList = Hol Daten als String vom RestService
        FileWriter writer = null;

        bestellung.add("Test");

        try {
            writer = new FileWriter("./fibu/Fibu.txt");

            for(String str: bestellung) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
