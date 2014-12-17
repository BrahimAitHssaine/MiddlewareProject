package rmi.forum.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import rmi.forum.server.InterfaceSujetDiscussion;

public class FenetreTchatClient extends UnicastRemoteObject implements
		InterfaceTchatClient {

	/**
	 * private JTextArea Afficheur; JTextField input;
	 */
	private static final long serialVersionUID = 1L;
	public InterfaceSujetDiscussion sujetDiscussion;
	JPanel bigPan = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel buttomPanel = new JPanel();
	JButton send = new JButton("Envoi");
	JTextArea afficheur = new JTextArea(12, 20);
	JScrollPane scrollPaneAfficheur = new JScrollPane(afficheur);
	JTextArea input = new JTextArea(2, 20);
	JScrollPane scrollPaneInput = new JScrollPane(input);
	public JFrame frame = new JFrame();
	String nickName;
	boolean isRegister;
	
	public FenetreTchatClient(String title,InterfaceSujetDiscussion SujetDiscussion, String nickName)throws RemoteException {
		this.sujetDiscussion = SujetDiscussion;
		frame.setTitle(title);
		frame.setSize(400, 300);
		this.afficheur.setEditable(false);
		this.nickName = nickName;
		this.isRegister = false;
		init();
	}

	private void init() {
		BorderLayout topLayout = new BorderLayout();
		topPanel.setLayout(topLayout);
		topPanel.add("Center", scrollPaneAfficheur);
		BorderLayout buttomLayout = new BorderLayout();
		buttomPanel.setLayout(buttomLayout);
		buttomPanel.add("North", scrollPaneInput);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.addKeyListener(new KeyListener() {
			String mess;
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					mess = input.getText();
					try {
						if(!(mess.isEmpty())){
							sujetDiscussion.diffuse(nickName, mess);
							input.setText("");
						}
					} catch (RemoteException ex) {
						ex.printStackTrace();
					}
					
				}
			}
		});
		
		
		buttomPanel.add("South", send);
		scrollPaneInput.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAfficheur.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		BorderLayout bigLayout = new BorderLayout();
		bigPan.setLayout(bigLayout);
		bigPan.add("North", topPanel);
		bigPan.add("South", buttomPanel);
		send.addActionListener(new action());
		frame.add(bigPan);
	}

	class action implements ActionListener {
		String message;
		public action() {

		}

		@Override
		public void actionPerformed(ActionEvent v) {
			message = input.getText();
			try {
				if(!(message.isEmpty())){
					sujetDiscussion.diffuse(nickName, message);
					input.setText("");
				}
			} catch (RemoteException e) {
				System.out.println("bad");
			}
		}

	}

	public String getNickName() {
		return nickName;
	}

	@Override
	public void affiche(String message) throws RemoteException {
		try {
			afficheur.append(message);
			System.out.println("message setter avec succes "
					+ afficheur.getText());
		} catch (Exception e) {
			System.out.println("erreur affichage console textarea");
		}
	}
}
