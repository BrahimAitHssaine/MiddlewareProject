package rmi.forum.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceAffichageClient extends Remote {
	public void affiche(String message) throws RemoteException;
}
