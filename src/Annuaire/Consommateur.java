package Annuaire;

import java.io.IOException;
import java.net.Socket;

public class Consommateur implements Runnable{
	private Annuaire an;
	
	//Constructeur
	public Consommateur(Annuaire a) {
		this.an = a;
	}

	@Override
	public void run() {
		//Récupération du socket de service
		Socket sserv = an.getService();
		//Réception du port sur lequel se connecte le serveur
		String portString = (String) an.recObject(sserv);
		System.out.println("Serveur connecté sur le port : " + portString);
		//réception de la liste de fichiers (sous forme d'un String"
		String list = (String) an.recObject(sserv);
		//Comptage du nombre de fichiers
		int nbFilesServ = an.countFiles(list);
			
		//pour chaque fichier
		for (int i=0; i<nbFilesServ; i++) {
			//Réception du tableau des index des blocs du fichier
			int[] blocs = (int []) an.recObject(sserv);
			//Ajout de l'entrée dans la HashMap
			an.addEntry(an.listeTab(list)[i], portString, blocs);
			
		}
		System.out.println(portString + " a fini");

		try {
			
			//Fermeture du socket
			sserv.close();
			//System.out.println("déco de " + portString);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
