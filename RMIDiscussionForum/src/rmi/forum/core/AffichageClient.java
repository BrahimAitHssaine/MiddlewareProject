package rmi.forum.core;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;



public class AffichageClient implements InterfaceAffichageClient{

	private String IdClient; 
	private InterfaceSujetDiscussion sujetDiscussion;
	
	public AffichageClient(InterfaceSujetDiscussion sujetDiscussion, String idClient) {
		super();
		this.sujetDiscussion = sujetDiscussion;
		this.IdClient = idClient;
		
	}
	
	@Override
	public void affiche(String Message) throws RemoteException {
		// TODO Auto-generated method stub	
	}

	public static void main(String[] args) {
		// TODO 1
	
		 if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
	        try {
	            String name = "Server";
	            Registry registry = LocateRegistry.getRegistry(args[0]);
	            InterfaceServeurForum serverforum = (InterfaceServeurForum) registry.lookup(name);
	          
	        } catch (Exception e) {
	            System.err.println("ComputePi exception:");
	            e.printStackTrace();
	        }
	    }

	    	
	}

	


