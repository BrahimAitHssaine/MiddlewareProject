package rmi.forum.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class FenetreSujetClient extends JFrame implements InterfaceClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel maPanel = new JPanel();
	private InterfaceSujetDiscussion sujetDiscussion;
	private boolean sportBool = false;
	private boolean musiqueBool = false;
	private boolean cinemaBool = false;
	FenetreDiscussionClient f ;
	static InterfaceServeurForum serverforum;
	String nickName = "";
	Map<String,FenetreDiscussionClient> mapFenetreClient = new Hashtable<String,FenetreDiscussionClient>();
	/**
	 * @throws HeadlessException
	 */
	public FenetreSujetClient() throws HeadlessException {
		super();
		setTitle("FenetreSujetClient ");
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
	}
	
	private void init(){
		JButton Button1 = new JButton("Sport");// The JButton name.
		maPanel.add(Button1);
		JButton Button2 = new JButton("Musique");// The JButton name.
		maPanel.add(Button2);
		JButton Button3 = new JButton("Cinema");// The JButton name.
		maPanel.add(Button3);
		Button1.addActionListener(new action("Sport"));
		Button2.addActionListener(new action("Musique"));
		Button3.addActionListener(new action("Cinema"));
	}

	/**
	 * @author Cyrille
	 *
	 */
	class action implements ActionListener {

		String sujet ;

		public action(String s) {
			sujet = s;
			System.out.println("je suis le sujet " + sujet);
		}

		@Override
		public void actionPerformed(ActionEvent v) {
			switch(v.getActionCommand()){
			case "Sport" : try {
								sportBool = estAbonner(v.getActionCommand(), sportBool);
							} catch (RemoteException e) {
									e.printStackTrace();
							  }
							break;
			case "Musique" : try {
								musiqueBool = estAbonner(v.getActionCommand(), musiqueBool);
							 } catch (RemoteException e) {
								 e.printStackTrace();
							   }
							break;
			case "Cinema" :
							try {
								cinemaBool = estAbonner(v.getActionCommand(), cinemaBool);
							} catch (RemoteException e) {
								e.printStackTrace();
							}
							break;
			default : System.out.println("Erreur sur récuperation du boolean");
			}
	}
	}
	public boolean estAbonner(String c, boolean abonne) throws RemoteException {
		// TODO Auto-generated method stub
		String sujet = c;
		if (abonne == false) {
			nickName = JOptionPane.showInputDialog("Please enter a nickname");
			System.out.println("Pseudo :"+nickName);
			sujetDiscussion = serverforum.obtientSujet(sujet);
			f = new FenetreDiscussionClient("Forum "+sujet,sujetDiscussion, nickName);
			f.frame.setVisible(true);
			mapFenetreClient.put(sujet, f);
			try{
				sujetDiscussion.inscription(f);
				abonne = true;
			}catch(Exception e){
				System.out.println("Erreur");
			}
		} 
		else {
			try{
				sujetDiscussion.desInscription(f);	
				abonne = false;
				mapFenetreClient.get(sujet).frame.dispose();
			}catch(Exception e){
				System.out.println("Erreur");
			}
		}
		return abonne;
	}
	public static void main(String[] args) {
		// TODO 1. Instancier une JFrame
		FenetreSujetClient maFenetre2 = new FenetreSujetClient();
		maFenetre2.add(maPanel);
		maFenetre2.setVisible(true);
		

		try {
			String name = "serveur";
			Registry registry = LocateRegistry.getRegistry(5000);
			serverforum = (InterfaceServeurForum) registry.lookup(name);
			System.out.println("Serveur trouvé");
		} catch (Exception e) {
			System.err.println("Connect Server exception:");
			e.printStackTrace();
		}

	}
}
