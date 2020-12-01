package src.server;

import src.shared.FibuConfi;

import java.rmi.RemoteException;

public class ServerImpl implements FibuConfi {
    @Override
    public Fahrradkonfiguration configuration(String lenkertypP, String materialP, String schaltungP, String griffP) throws RemoteException {
        return null;
    }
}
