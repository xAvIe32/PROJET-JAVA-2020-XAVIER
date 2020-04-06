package Annuaire;

import java.io.IOException;
import java.net.Socket;

public class Consommateur implements Runnable{
	private Annuaire an;
	
	//Constructeur
	public Consommateur(Annuaire a) {
		this.an = a;
	}

	//#############################################
	//## CORRESPOND A LA PHASE 1 DU MAIN SERVEUR ##
	//#############################################
	
	@Override
	public void run() {
		//Récupération du socket de service
		Socket sserv = an.getService();
		//Réception du port sur lequel se connecte le serveur
		String portString = (String) an.recObject(sserv);
		String[] splIPort = portString.split(" ");
		System.out.println("Serv " +  splIPort[1] + " co sur le port " + splIPort[1]);
		//réception de la liste de fichiers (sous forme d'un String"
		String list = (String) an.recObject(sserv);
		//Comptage du nombre de fichiers
		int nbFilesServ = an.countFiles(list);
		
		//pour chaque fichier
		for (int i=0; i<nbFilesServ; i++) {
			int bloc = (int) an.recObject(sserv);
			//Ajout de l'entrée dans la HashMap
			an.addEntry(an.listeTab(list)[i], portString, bloc);
			
		}
		
		System.out.println("Serveur " + splIPort[1] + " a fini");

		try {
			
			//Fermeture du socket
			sserv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
