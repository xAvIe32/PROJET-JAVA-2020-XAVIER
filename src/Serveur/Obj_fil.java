package Serveur;

public class Obj_fil {
	private String name;
	private String port;
	private int[] tabIndex;
	
	public Obj_fil(String name, String port, int[] tabIndex) {
		super();
		this.name = name;
		this.port = port;
		this.tabIndex = tabIndex;
	}

	public String getName() {
		return name;
	}

	public String getPort() {
		return port;
	}

	public int[] getTabIndex() {
		return tabIndex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setTabIndex(int[] tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	

}
