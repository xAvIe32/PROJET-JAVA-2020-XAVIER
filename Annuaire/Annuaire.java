package Annuaire;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Annuaire {
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	
	
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
	
	//Réception d'une chaine de caractères
	public String receptionString(Socket sock) {
		
		String ligne = new String();
		try {
			//Création d'un Buffer 
			BufferedReader reader = new BufferedReader (new InputStreamReader(sock.getInputStream()));
			//Lecture du buffer
			ligne = reader.readLine();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		
		return ligne;
	}
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
		
}
