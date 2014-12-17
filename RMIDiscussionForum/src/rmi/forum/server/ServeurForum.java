package rmi.forum.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import org.omg.CORBA.Current;

public class ServeurForum extends UnicastRemoteObject implements InterfaceServeurForum ,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final String musique = "Musique";
	private final String cinema = "Cinema";
	private final String sport = "Sport";
	public static Map<String,InterfaceSujetDiscussion> sujet = new Hashtable<String,InterfaceSujetDiscussion>();
	
	protected ServeurForum() throws RemoteException {
		super();
		sujet.put(musique, new SujetDiscussion(musique));
		sujet.put(cinema, new SujetDiscussion(cinema));
		sujet.put(sport, new SujetDiscussion(sport));
	}
	@Override
	public InterfaceSujetDiscussion obtientSujet(String titre)
			throws RemoteException {
		try{ return sujet.get(titre);}
		catch (NullPointerException e) {return null;}
		
	}
	public static void main(String[] args) {
		try {
			
			ServeurForum serveur = new ServeurForum();
			Registry registry = LocateRegistry.createRegistry(5000);
			registry.rebind("serveur", serveur);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(" :> Initialisation du site serveur et enregistrement de son adresse externe. \n");
					System.out.print("\t");
					for(int i = 0; i < 30 ; i++){
						System.out.print("*");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("\n");
					System.out.println(" :> Serveur démarré et en attente de connexions");
				}
			}, "Initializer").start();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println(" :< L'initialisation du site serveur et l'enregistrement de son adresse externe à échoué.");}
	}


}
