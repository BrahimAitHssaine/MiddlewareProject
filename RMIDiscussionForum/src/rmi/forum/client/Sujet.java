package rmi.forum.client;

public class Sujet {
	private String name;
	private boolean isRegister;
	private InterfaceTchatClient fenetreTchatClient;
	
	public Sujet(String name) {
		this.name = name;
		this.isRegister = false;
		this.fenetreTchatClient = null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}
	public void setFenetreTchatClient(InterfaceTchatClient fenetreTchatClient) {
		this.fenetreTchatClient = fenetreTchatClient;
	}
	public InterfaceTchatClient getFenetreTchatClient() {
		return fenetreTchatClient;
	}
	public String getName() {
		return name;
	}
	public boolean getIsRegister() {
		return isRegister;
	}
	
	
}
