package rmi.forum.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceTchatClient extends Remote {
	public void affiche(String message) throws RemoteException;
}
