package rmi.forum.views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rmi.forum.interfaces.InterfaceAffichageClient;
import rmi.forum.interfaces.InterfaceSujetDiscussion;

public class FenetreDiscussionClient extends JFrame implements InterfaceAffichageClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InterfaceSujetDiscussion sujetDiscussion;
	private JTextArea Afficheur;
	JTextField input;
	public FenetreDiscussionClient(String title) throws HeadlessException {
		super();
		JFrame maFenetre1 = new JFrame();
		JPanel maPanel = new JPanel();
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
	
		maFenetre1.setTitle(title);
		maFenetre1.setSize(300, 300);
		maFenetre1.setResizable(false);			
		maFenetre1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre1.setLocationRelativeTo(null);
		
	    input= new JTextField("taper votre message",25);
		JTextArea Afficheur=new JTextArea(5, 20) ;
		Afficheur.setEditable(false);
		BoundedRangeModel brm = input.getHorizontalVisibility();
		scrollBar.setModel(brm);
		
		JButton send = new JButton("Send");//The JButton name.
	
		maPanel.add(Afficheur);
		maPanel.add(input);
		maPanel.add(scrollBar);
		maPanel.add(send);
		maFenetre1.add(maPanel);
	
		send.addActionListener(new action());
		
	}
	
	class action implements ActionListener
	{
		String message;
		public action()
		{
			
		}
		@Override
		public void actionPerformed(ActionEvent v) {
			// TODO Auto-generated method stub
			message=input.getText();
			try {
				sujetDiscussion.diffuse(message);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	}

	@Override
	public void affiche(String message) throws RemoteException {
		// TODO Auto-generated method stub
		Afficheur.setText(message);
	}
}
