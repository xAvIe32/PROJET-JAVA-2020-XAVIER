package Client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Default.Commun;

public class CLI extends Commun {
	//Attributs de la classe CLI
	String IPAddr;
	Socket sock;
	int port;

	//Constructeur
	public CLI(String path, int port, String iPAddr) {
		super(path);
		IPAddr = iPAddr;
		this.port = port;
	} 
	
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	//Connection au serveur
	public void connect() {
		try {
			//Création du socket client
			sock = new Socket(this.IPAddr, this.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch 
			e.printStackTrace();
		}
	}
	
	
	//Fermeture du socket client
	public void fermer () {
		try {
			this.sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Retour du socket client
	public Socket getSock() {
		return sock;
	}

	
	
	

}
