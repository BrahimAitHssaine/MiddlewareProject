package rmi.forum.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import rmi.forum.interfaces.InterfaceServeurForum;
import rmi.forum.interfaces.InterfaceSujetDiscussion;



public class FenetreSujetClient extends JFrame{

	/**
	 * 
	 */
	

	private static final long serialVersionUID = 1L;
	public FenetreSujetClient(String title) throws HeadlessException {
		
	}
	static JPanel maPanel = new JPanel();
	private InterfaceSujetDiscussion sujetDiscussion;
	
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
		//	sujetDiscussion.inscription(S);
			System.out.println("je veux faire l'inscription de mon afficheur au sujet x ");
		}
		@Override
		public void actionPerformed(ActionEvent v) {
			// TODO Auto-generated method stub
			FenetreDiscussionClient f = new FenetreDiscussionClient(sujet);
		}
	}
	
	public static void main(String[] args) {
		// TODO 1. Instancier une JFrame
		FenetreSujetClient maFenetre2 = new FenetreSujetClient();
		// TODO 7. Afficher la JFrame
		maFenetre2.add(maPanel);
		maFenetre2.setVisible(true);
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

	
	

