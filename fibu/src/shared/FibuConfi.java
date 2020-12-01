package shared;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FibuConfi extends Remote
{
    void saveDataAsFile () throws RemoteException;
}
