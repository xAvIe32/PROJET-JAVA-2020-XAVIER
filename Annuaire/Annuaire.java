package Annuaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Annuaire{
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	private HashMap<String, HashMap<String, int[]>> theMap = new HashMap<String, HashMap<String, int[]>>();
	
	public Annuaire(int p) {
		this.port = p;
	}
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	//Création du socket serveur
	public void creaServSock() {
		try {
			this.socketAnn = new ServerSocket(this.port);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Accepter demande de connexion
	public void acceptDem() {
		try {
			//Création du socket de service
			this.service = this.socketAnn.accept();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();

		}
	}
	

	
	public Object recObject(Socket sock) {
		Object rec = new Object();
		try {
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			rec = ois.readObject();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		return rec;
	}
	
	public HashMap<String, HashMap<String, int[]>> getTheMap() {
		return theMap;
	}

	

	public int countFiles(String list) {
		String[] tabloList = list.split(" ");
		int nbFiles = tabloList.length;
		return nbFiles;
		
	}
	
	public synchronized void fillMap(String Np, HashMap<String, int[]> fb) {
		this.theMap.put(Np, fb);
	}
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
		
}
