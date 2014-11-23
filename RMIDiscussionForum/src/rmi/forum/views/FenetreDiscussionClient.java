package rmi.forum.views;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class FenetreDiscussionClient extends JFrame implements InterfaceAffichageClient{

	/**
	 * 	private JTextArea Afficheur;
	 * JTextField input;
	*/
	private static final long serialVersionUID = 1L;
	private InterfaceSujetDiscussion sujetDiscussion;
	JPanel bigPan = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel buttomPanel = new JPanel();
	JButton send = new JButton("Envoi");
	JTextArea afficheur = new JTextArea(10, 20);;
	JTextField input = new JTextField();
	
	public FenetreDiscussionClient(String title,InterfaceSujetDiscussion SujetDiscussion ) throws HeadlessException {
		this.sujetDiscussion = SujetDiscussion;
		this.setTitle(title);
		this.setSize(400, 300);
		this.add(bigPan);
		init();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
private void init(){
		
		BorderLayout topLayout = new BorderLayout();
		topPanel.setLayout(topLayout);
		topPanel.add("Center", afficheur);
		
		BorderLayout buttomLayout = new BorderLayout();
		buttomPanel.setLayout(buttomLayout);
		buttomPanel.add("North", input);
		buttomPanel.add("South", send);
		
		BorderLayout bigLayout = new BorderLayout();
		bigPan.setLayout(bigLayout);
		bigPan.add("North", topPanel);
		bigPan.add("South", buttomPanel);
		afficheur.setEditable(true);
		send.addActionListener(new action());
		
	}
	
	class action implements ActionListener
	{
		String message;
		public action(){
			
		}
		@Override
		public void actionPerformed(ActionEvent v) {
			// TODO Auto-generated method stub
			message=input.getText();
			try {
				sujetDiscussion.diffuse(message);
				input.setText("");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("bad");
			}	
	}
	
	}
	@Override
	public void affiche(String message) throws RemoteException {
		try{
			afficheur.setText(message);
			System.out.println("message setter avec succes "+message);
		}catch(Exception e){
			System.out.println("erreur affichage console textarea");
		}
	}
}
