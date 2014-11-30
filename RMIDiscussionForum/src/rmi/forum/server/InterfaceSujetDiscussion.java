package rmi.forum.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rmi.forum.client.InterfaceTchatClient;

public interface InterfaceSujetDiscussion extends Remote {
	public void inscription(InterfaceTchatClient c) throws RemoteException;
	public void desInscription(InterfaceTchatClient c) throws RemoteException;
	public void diffuse(String nickName, String message) throws RemoteException;
	
}
