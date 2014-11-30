package rmi.forum.client;

import java.rmi.RemoteException;

public interface InterfaceSujetClient {
	public boolean estAbonner(String c, boolean bool)
			throws RemoteException ;
}
