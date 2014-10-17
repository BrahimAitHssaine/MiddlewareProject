package rmi.forum.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.xml.internal.rngom.xml.util.Naming;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;



public class FenetreSujetClient extends JFrame implements InterfaceClient{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static JPanel maPanel = new JPanel();
	private InterfaceSujetDiscussion sujetDiscussion;
	public FenetreSujetClient(String title) throws HeadlessException {
		
	}
	
	public FenetreSujetClient() {
		super();
		setTitle("FenetreSujetClient ");
		setSize(400, 300);
		setResizable(false);				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JButton Button1 = new JButton("Sport");//The JButton name.
		maPanel.add(Button1);
		JButton Button2 = new JButton("Musique");//The JButton name.
		maPanel.add(Button2);
		JButton Button3 = new JButton("Cinema");//The JButton name.
		maPanel.add(Button3);
		
		Button1.addActionListener(new action("Sport")) ;
		Button2.addActionListener(new action("Musique")) ;
		Button3.addActionListener(new action("Cinema")) ;

	}
	
	class action implements ActionListener
	{
		
		String sujet;
		
		public action(String s)
		{
			sujet=s;
			System.out.println("je suis le sujet "+ sujet );
		}
		
		@Override
		public void actionPerformed(ActionEvent v) {
			// TODO Auto-generated method stub
			try {
				if(estAbonner(sujet))
					System.out.println("je suis abonnee a ce sujet");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public boolean estAbonner(String c)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sujet = null;
		boolean abonne=false;
		FenetreDiscussionClient f  = new FenetreDiscussionClient(sujet);;
		if(abonne==false)
		{
			 f.setVisible(true);
			sujetDiscussion.inscription(f);
			
		}
		else
		{
			sujetDiscussion.desInscription(f);
			//fermer la fenetre .
		}
		
		return abonne;
	
	}


	
	public static void main(String[] args) {
		// TODO 1. Instancier une JFrame
		FenetreSujetClient maFenetre2 = new FenetreSujetClient();
		maFenetre2.add(maPanel);
		maFenetre2.setVisible(true);
		
		InterfaceServeurForum serverforum;
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        try {
        	 String name = "Server";
        	 Registry registry = LocateRegistry.getRegistry(args[0]);
             serverforum = (InterfaceServeurForum) registry.lookup(name);
             
        } catch (Exception e) {
            System.err.println("Connect Server exception:");
            e.printStackTrace();
        }
		
	}

	}
}
	
	

	


	
	

