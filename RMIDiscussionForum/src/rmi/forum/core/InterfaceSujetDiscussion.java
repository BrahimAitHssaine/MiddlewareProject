package rmi.forum.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceSujetDiscussion extends Remote {
	public void inscription(InterfaceAffichageClient c) throws RemoteException;
	public void desInscription(InterfaceAffichageClient c) throws RemoteException;
	public void diffuse(String message) throws RemoteException;
}
