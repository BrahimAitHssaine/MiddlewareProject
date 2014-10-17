package rmi.forum.core;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class ServeurForum implements InterfaceServeurForum {

	/**
	 * 
	 */
	private final String musique = "Musique";
	private final String cinema = "Cinema";
	private final String sport = "Sport";
	public static Map<String,InterfaceSujetDiscussion> sujet = new Hashtable<String,InterfaceSujetDiscussion>();
	
	protected ServeurForum() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		sujet.put(musique, new SujetDiscussion(musique));
		sujet.put(cinema, new SujetDiscussion(cinema));
		sujet.put(sport, new SujetDiscussion(sport));
	}

	@Override
	public InterfaceSujetDiscussion obtientSujet(String titre)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{ return sujet.get(titre);}
		catch (NullPointerException e) {return null;}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		try {

			Registry reg =  LocateRegistry.getRegistry();
			
			ServeurForum serveurF = new ServeurForum();
			
			InterfaceServeurForum stub = (InterfaceServeurForum) UnicastRemoteObject.exportObject(serveurF, 0);
			
			reg.rebind("//Server",stub);
			
			System.out.println("#Initialisation du site serveur et enregistrement de son adresse externe.");
			
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("!L'initialisation du site serveur et l'enregistrement de son adresse externe ont échoué.");}
		
	}


}
