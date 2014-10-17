package rmi.forum.views;

import java.rmi.RemoteException;

import rmi.forum.interfaces.InterfaceAffichageClient;

public interface InterfaceClient {
	
	public boolean estAbonner(String c)
			throws RemoteException ;
	


}
