package Client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import Default.Commun;

public class CLI extends Commun {
	//Attributs de la classe CLI
	private String IPAddr;
	private Socket sock;
	
	//Constructeur
	public CLI(String path, String iPAddr) {
		super(path);
		IPAddr = iPAddr;
	} 
	
	
	  //############\\
     //## METHODES ##\\
	//################\\
	
	//Connection au serveur
	public void connect(int p) {
		try {
			//Création du socket client
			sock = new Socket(this.IPAddr, p);
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		}
	}

	
	//Fermeture du socket client
	public void fermer () {
		try {
			this.sock.close();
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	  //###########\\
     //## GETTERS ##\\
	//###############\\	
	
	//Retour du socket client
	public Socket getSock() {
		return sock;
	}

}
