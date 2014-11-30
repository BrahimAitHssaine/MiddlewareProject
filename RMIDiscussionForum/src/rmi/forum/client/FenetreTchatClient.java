package rmi.forum.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	InterfaceSujetDiscussion sujetDiscussion;
	JPanel bigPan = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel buttomPanel = new JPanel();
	JButton send = new JButton("Envoi");
	JTextArea afficheur = new JTextArea(12, 20);
	JScrollPane scrollPane = new JScrollPane(afficheur);
	JTextArea input = new JTextArea(2, 20);
	JFrame frame = new JFrame();
	String nickName;

	public FenetreTchatClient(String title,InterfaceSujetDiscussion SujetDiscussion, String nickName)throws RemoteException {
		this.sujetDiscussion = SujetDiscussion;
		frame.setTitle(title);
		frame.setSize(400, 300);
		this.afficheur.setEditable(false);
		this.nickName = nickName;
		init();
	}

	private void init() {
		BorderLayout topLayout = new BorderLayout();
		topPanel.setLayout(topLayout);
		topPanel.add("Center", scrollPane);
		BorderLayout buttomLayout = new BorderLayout();
		buttomPanel.setLayout(buttomLayout);
		buttomPanel.add("North", input);
		buttomPanel.add("South", send);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
			// TODO Auto-generated method stub
			message = input.getText();
			try {
				sujetDiscussion.diffuse(nickName, message);
				input.setText("");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
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
