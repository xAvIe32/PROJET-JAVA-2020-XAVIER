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
		//R�cup�ration du socket de service
		Socket sserv = an.getService();
		//R�ception du port sur lequel se connecte le serveur
		String portString = (String) an.recObject(sserv);
		System.out.println("Serveur connect� sur le port : " + portString);
		//r�ception de la liste de fichiers (sous forme d'un String"
		String list = (String) an.recObject(sserv);
		//Comptage du nombre de fichiers
		int nbFilesServ = an.countFiles(list);
			
		//pour chaque fichier
		for (int i=0; i<nbFilesServ; i++) {
			//R�ception du tableau des index des blocs du fichier
			int[] blocs = (int []) an.recObject(sserv);
			//Ajout de l'entr�e dans la HashMap
			an.addEntry(an.listeTab(list)[i], portString, blocs);
			
		}
		System.out.println(portString + " a fini");

		try {
			
			//Fermeture du socket
			sserv.close();
			//System.out.println("d�co de " + portString);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
