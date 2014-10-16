package rmi.forum.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class ServeurForum extends UnicastRemoteObject implements InterfaceServeurForum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServeurForum() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public InterfaceSujetDiscussion obtientSujet(String titre)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
