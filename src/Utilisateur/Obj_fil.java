package Utilisateur;

public class Obj_fil {
	private String name;
	private String port;
	private int tabIndex;
	private String ipAddr;
	
	//Constructeur
	public Obj_fil(String name, String port, int tabIndex, String IP) {
		super();
		this.name = name;
		this.port = port;
		this.tabIndex = tabIndex;
		this.ipAddr = IP;
	}
	
	
	  //###########\\
     //## SETTERS ##\\
	//###############\\
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	  //###########\\
     //## GETTERS ##\\
	//###############\\	
	
	public String getName() {
		return name;
	}

	public String getPort() {
		return port;
	}

	public int getTabIndex() {
		return tabIndex;
	}
	
	public String getIP() {
		return this.ipAddr;
	}


	
	
	

}
