package rmi.forum.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServeurForum extends Remote {
	public InterfaceSujetDiscussion obtientSujet(String titre) throws RemoteException;
}
