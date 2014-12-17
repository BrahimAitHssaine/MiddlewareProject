package rmi.forum.client;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rmi.forum.client.FenetreTchatClient;
import rmi.forum.server.InterfaceServeurForum;
import rmi.forum.server.InterfaceSujetDiscussion;
import rmi.forum.utils.Sujet;

public class FenetreSujetClient  extends JFrame {

	/**
	 * @param
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel maPanel = new JPanel();
	String nickName = "";
	Map<String,Sujet> sujetForum = new HashMap<String,Sujet>();
	static InterfaceServeurForum serverforum;
	
	public FenetreSujetClient() throws HeadlessException {
		this.setName("Sujets Forum ");
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		init();
	}

	public void init(){
		JButton Button1 = new JButton("Sport");
		maPanel.add(Button1);
		JButton Button2 = new JButton("Musique");
		maPanel.add(Button2);
		JButton Button3 = new JButton("Cinema");
		maPanel.add(Button3);
		Button1.addActionListener(new action("Sport"));
		Button2.addActionListener(new action("Musique"));
		Button3.addActionListener(new action("Cinema"));
	}
	class action implements ActionListener {
		String su;
		public action(String s){
			su = s;
		}
		@Override
		public void actionPerformed(ActionEvent v) {
			try {
					if(sujetForum.containsKey(v.getActionCommand())){
						estAbonner(sujetForum.get(v.getActionCommand()));
					}
					else{
						Sujet sujet = new Sujet(v.getActionCommand());
						sujetForum.put(sujet.getName(), sujet);
						estAbonner(sujetForum.get(sujet.getName()));
					}
			} catch (RemoteException e){
					e.printStackTrace();
				}
			}
		}
	
	public void estAbonner(Sujet s) throws RemoteException {
		InterfaceSujetDiscussion sd = null;
		if (!(s.getIsRegister())) {
			nickName = JOptionPane.showInputDialog(null, "Please enter a nickname", "Sujet "+s.getName(), JOptionPane.OK_OPTION);
			sd = serverforum.obtientSujet(s.getName());
			if(nickName != null){
			s.setFenetreTchatClient(new FenetreTchatClient("Forum "+s.getName(),sd, nickName));
			((FenetreTchatClient)s.getFenetreTchatClient()).frame.setVisible(true);
			try{
				((FenetreTchatClient)s.getFenetreTchatClient()).sujetDiscussion.inscription(s.getFenetreTchatClient());
				s.setRegister(true);
			}catch(Exception e){
				System.out.println("Erreur Inscription");
			}
			}
		} 
		else {
			try{
				((FenetreTchatClient)s.getFenetreTchatClient()).sujetDiscussion.desInscription(s.getFenetreTchatClient());	
				s.setRegister(false);
				((FenetreTchatClient)s.getFenetreTchatClient()).frame.dispose();
				sujetForum.remove(s.getName());
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Erreur Désinscription");
			}
		}
	}
	public static void main(String[] args) {
		FenetreSujetClient client = new FenetreSujetClient();
		client.add(maPanel);
		client.setVisible(true);
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
