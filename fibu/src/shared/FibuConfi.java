package src.shared;


import src.server.Fahrradkonfiguration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FibuConfi extends Remote
{
    Fahrradkonfiguration configuration (String lenkertypP, String materialP, String schaltungP, String griffP) throws RemoteException;
}
