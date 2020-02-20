package Serveur;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Default.Commun;

public class SRV extends Commun {
	//Attributs de l'objet serveur
	private ServerSocket SocketServeur;
	private Socket service;
	
	//Constructeur
	public SRV(String path) {
		super(path);
	}

	  //------\\
	 //Méthodes\\
    //----------\\
	
	//Création du socket serveur
	public void creaServSock() {
		try {
			this.SocketServeur = new ServerSocket(this.port);
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Saisie du port du serveur
	public void saisiePort() {
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
	public void acceptDem() {
		try {
			//Création du socket de service
			this.service = this.SocketServeur.accept();
		} catch (IOException e) {
			//   Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
	
	//Renvoi du port du serveur
	public int getPort() {
		return this.port;
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
}
