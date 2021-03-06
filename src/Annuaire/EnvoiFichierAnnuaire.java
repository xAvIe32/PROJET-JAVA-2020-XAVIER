package Annuaire;

import java.io.IOException;
import java.net.Socket;

public class EnvoiFichierAnnuaire implements Runnable{
	private Annuaire an;
	
	//Constructeur
	public EnvoiFichierAnnuaire(Annuaire a) {
		this.an = a;
	}

	//#################################################
	//## CORRESPOND A LA PHASE 1 DU MAIN UTILISATEUR ##
	//#################################################
	
	@Override
	public void run() {
		//R�cup�ration du socket de service
		Socket sserv = an.getService();
		//R�ception du port sur lequel se connecte le serveur
		String portString = (String) an.recObject(sserv);
		String[] splIPort = portString.split(" ");
		System.out.println("Serv " +  splIPort[0] + " connect� et son adresse est " + splIPort[1]);
		//r�ception de la liste de fichiers (sous forme d'un String"
		String list = (String) an.recObject(sserv);
		//Comptage du nombre de fichiers
		int nbFilesServ = an.countFiles(list);
		
		//pour chaque fichier
		for (int i=0; i<nbFilesServ; i++) {
			int bloc = (int) an.recObject(sserv);
			//Ajout de l'entr�e dans la HashMap
			an.addEntry(an.listeTab(list)[i], portString, bloc);
			
		}
		
		System.out.println("Serveur " + splIPort[0] + " a fini");

		try {
			
			//Fermeture du socket
			sserv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
