package rmi.forum.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import rmi.forum.client.FenetreTchatClient;
import rmi.forum.client.InterfaceTchatClient;

public class SujetDiscussion extends UnicastRemoteObject implements Serializable, InterfaceSujetDiscussion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InterfaceTchatClient> client = new LinkedList<InterfaceTchatClient>();
	private String name;

	public SujetDiscussion(String name) throws RemoteException{
		this.setName(name);
	}

	@Override
	public synchronized void inscription(InterfaceTchatClient c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			this.getClient().add(c);
			System.out.println("Inscription ok");
		}catch(Exception e){
			System.out.println("Inscription echec");
		}
	}

	@Override
	public synchronized void desInscription(InterfaceTchatClient c)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{
			this.getClient().remove(c);
			System.out.println("Desinscription ok du client "+getClient().contains(c));
		}catch(Exception e){
			System.out.println("Desinscription echec");
		}
	}

	@Override
	public synchronized void diffuse(String nickName, String message) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			for (InterfaceTchatClient c : client) {
				c.affiche(nickName+": "+message+"\n");
				System.out.println("diffusion reussi"+" message :"+message);
			}
		}catch(Exception e){
			System.out.println("diffusion echoué");
			e.printStackTrace();
		}
		
	}

	public List<InterfaceTchatClient> getClient() {
		return client;
	}

	public void setClient(List<InterfaceTchatClient> client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
