package rmi.forum.core;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class SujetDiscussion extends UnicastRemoteObject implements Serializable, InterfaceSujetDiscussion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InterfaceAffichageClient> client = new LinkedList<InterfaceAffichageClient>();
	private String name;

	public SujetDiscussion(String name) throws RemoteException{
		this.setName(name);
	}

	@Override
	public synchronized void inscription(InterfaceAffichageClient c)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{
			this.getClient().add(c);
			System.out.println("Inscription ok");
		}catch(Exception e){
			System.out.println("Inscription echec");
		}
	}

	@Override
	public synchronized void desInscription(InterfaceAffichageClient c)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{
			this.getClient().remove(c);
			System.out.println("Desinscription ok");
		}catch(Exception e){
			System.out.println("Desinscription echec");
		}
	}

	@Override
	public synchronized void diffuse(String message) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			for (InterfaceAffichageClient c : client) {
				c.affiche(message);
				System.out.println("diffusion reussi"+" message :"+message);
			}
		}catch(Exception e){
			System.out.println("diffusion echoué");
		}
		
	}

	public List<InterfaceAffichageClient> getClient() {
		return client;
	}

	public void setClient(List<InterfaceAffichageClient> client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
