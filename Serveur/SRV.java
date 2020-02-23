package Serveur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
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
	
	
	//Découpage du fchier en blocs de 4ko
	public byte[] decoupFichier(File f){
		FileInputStream fis = null;
		//Déclaration du tableau d'octets
		byte[] fileByte = new byte[(int) f.length()];
		
		try {
			fis = new FileInputStream(f);
			fileByte = fis.readAllBytes();
			fis.close();
		} catch (FileNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileByte;
	}
	
	public HashMap<String, int[]> hmapCrea(String filname, byte[] file) {
		int[] nbBlocs = new int[file.length];
		
		HashMap<String, int[]> mapNomBloc = new HashMap<String, int[]>();
		for (int i=0; i<file.length; i++) {
			nbBlocs[i] = i+1;
		}
		mapNomBloc.put(filname, nbBlocs);
		return mapNomBloc;
		
	}
}
