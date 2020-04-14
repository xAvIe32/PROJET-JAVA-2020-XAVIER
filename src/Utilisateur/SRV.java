package Utilisateur;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Default.Commun;

public class SRV extends Commun {
	//Attributs de l'objet serveur
	private ServerSocket SocketServeur;
	private Socket service;
	
	
	  //############\\
     //## METHODES ##\\
	//################\\
	
	//Création du socket serveur
	public void creaServSock(int p) {
		try {
			this.SocketServeur = new ServerSocket(p);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	//Saisie du port du serveur
	public void saisiePort() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean chiffre = false;
		int noport = 0;
		//Tant que la saisie n'est pas un nombre, on recommence
	    do {
	    	//saisie clavier
	    	System.out.println("n°port : ");
	    	String saisie = sc.nextLine();
	    	//Vérification du nombre
	    	try {
	    		noport = Integer.parseInt(saisie);
	    		chiffre = true;
	    	}
	    	//Si la saisie n'est pas un nombre
	    	catch (NumberFormatException e) {
	    		System.out.println("Ce n'est pas un nombre, veuillez recommencer");
	    	} 
	    } while (chiffre != true);
	    this.port = noport;
	    System.out.println("Serveur ouvert sur le port " + this.port);
	}

	
	//Accepter demande de connexion
	public boolean acceptDem() {
		try {
			//Création du socket de service
			this.service = this.SocketServeur.accept();
			
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();

		}
		return this.service.isConnected();
	}
	
	
	//Fermeture du socket de service
	public void fermer() {
		try {
			this.service.close();
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	
	//Fermeture du socket serveur
	public void fermerServSock() {
		try {
			this.SocketServeur.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	  //###########\\
     //## GETTERS ##\\
	//###############\\	
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
	//Renvoi du port du serveur
	public int getPort() {
		return this.port;
	}
		
}
