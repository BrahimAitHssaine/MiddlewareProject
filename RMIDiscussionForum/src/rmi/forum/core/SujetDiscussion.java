package rmi.forum.core;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class SujetDiscussion  implements InterfaceSujetDiscussion {

	private List<InterfaceAffichageClient> client = new LinkedList<InterfaceAffichageClient>();
	private String name;
	
	public SujetDiscussion(String name) {
		// TODO Auto-generated constructor stub
		this.setName(name);  
	}

	@Override
	public synchronized void inscription(InterfaceAffichageClient c) throws RemoteException {
		// TODO Auto-generated method stub
		this.getClient().add(c);
	}

	@Override
	public synchronized void desInscription(InterfaceAffichageClient c)
			throws RemoteException {
		// TODO Auto-generated method stub
		if(this.getClient().remove(c)) System.out.println("#La désinscription s'est effectuée avec succès.");
		else System.out.println("!La désinscription n'a pas abouti.");
	}

	@Override
	public synchronized void diffuse(String message) throws RemoteException {
		// TODO Auto-generated method stub
		for(InterfaceAffichageClient c : client){
			
			c.affiche(message);
			
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

	@Override
	public boolean estAbonner(InterfaceAffichageClient c)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
