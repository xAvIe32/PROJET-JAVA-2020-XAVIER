package Serveur;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

import Default.Commun;

public class SRV extends Commun {
	//Attributs de l'objet serveur
	private ServerSocket SocketServeur;
	private Socket service;
	private String file;
	
	//Constructeur
	public SRV(String path) {
		super(path);
	}

	  //------\\
	 //Méthodes\\
    //----------\\
	
	//Création du socket serveur
		public void creaServSock(int p) {
			try {
				this.SocketServeur = new ServerSocket(p);
			} catch (IOException e) {
				//Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	
	
	public String getfile() {
		return file;
	}
	
	public void setFile(String f) {
		this.file = f;
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
	
	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}
	
	public void setPort(int p) {
		this.port = p;
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
	
	
	public void fermerServSock() {
		try {
			this.SocketServeur.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String testInList(String l) {
		
		String name = new String();
		while (true) {
			
			name = this.saisieClavier();
			if (!l.contains(name)) {
				System.out.println("Ce fichier ne fait pas partie de la liste, veuillez recommencer la saisie");
			}
			else {
				break;
			}
		}
		
		return name;
		
		
		
	}
	
	
}
