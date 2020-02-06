import java.io.IOException;
import java.net.*;

public class SRV extends Commun {
	//Attributs de l'objet serveur
	private ServerSocket SocketServeur;
	private Socket service;
	
	//Constructeur
	public SRV(String path, int port) {
		super(path, port);
	}

	  //------\\
	 //Méthodes\\
    //----------\\
	
	//Création du socket serveur
	public void creaServSock() {
		try {
			SocketServeur = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//Accepter demande de connexion
	public void acceptDem() {
		try {
			//Création du socket de service
			this.service = this.SocketServeur.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
	
	//Fermeture du socket de service
	public void fermer() {
		try {
			this.service.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}
