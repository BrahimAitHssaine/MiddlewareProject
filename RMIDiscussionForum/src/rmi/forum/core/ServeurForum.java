package rmi.forum.core;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class ServeurForum extends UnicastRemoteObject implements InterfaceServeurForum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		
		try {

			LocateRegistry.createRegistry(8090);

			ServeurForum serveurF = new ServeurForum();

			Naming.bind("//localhost/serveurForum",serveurF);
			
			System.out.println("#Initialisation du site serveur et enregistrement de son adresse externe.");
			
			}
			catch(Exception e) {System.out.println("!L'initialisation du site serveur et l'enregistrement de son adresse externe ont échoué.");}
		
	}


}
