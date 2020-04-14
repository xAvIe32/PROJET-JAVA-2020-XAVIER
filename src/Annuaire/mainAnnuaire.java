package Annuaire;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class mainAnnuaire {

	@SuppressWarnings("rawtypes")
	public static void main(String [] args) throws InterruptedException {
		
		//#################################################
		//## CORRESPOND A LA PHASE 1 DU MAIN UTILISATEUR ##
		//#################################################
		
		//Création de l'annuaire
		Annuaire annu = new Annuaire();
		//Saisie du nombre de serveurs
		System.out.println("Nombre d'utilisateurs : ");
		//Saisie du nombre de serveurs
		annu.setNbServ();
		int nbServ = annu.getNbserv();
		//Création du tableau de threads de la taille du nombre de serveurs
		Thread[] tabThread = new Thread[nbServ];
		
		String addr = new String();
		try {
			
			//Récupération de l'adresse IP
			InetAddress IP = InetAddress.getLocalHost();
			String IPStr = IP+"";
			//Séparation du nom de la machine
			String[] splIP = IPStr.split("/");
			addr = splIP[1]; 
			//Affichage de l'adresse IP
			System.out.println("L'adresse IP de l'annuaire est : " + addr);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		//Création du socket serveur de l'annuaire
		annu.creaServSock(32370);
		//Création d'un nouveau consommateur
		EnvoiFichierAnnuaire c = new EnvoiFichierAnnuaire(annu);
				
		
		//Pour chaque serveur 
		for (int i=0; i<nbServ; i++) {
			//Acceptation de la demande de connexion
			annu.acceptDem();
			//Création d'un nouveau thread
			tabThread[i] = new Thread(c);
			//Lancement du thread
			tabThread[i].start();
		}
		
		//Attente de la fin de tous les threads pour continuer
		for (int i=0; i<tabThread.length; i++) {
			tabThread[i].join();
		}
		
	
		
		System.out.println("");
		//Affichage de la HashMap contenant les noms de fichiers avec
		//les noms de fichiers et les serveurs + les index des blocs
		//Flemme de commenter +, c'est trop le bordel... :'(
		Set set = annu.getMap().entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
		   Map.Entry mentry = (Map.Entry)iterator.next();
		   System.out.println("-------------------------------------------");
		   System.out.println("Fichier : "+ mentry.getKey());
		   
		   Set set2 = annu.getMap().get(mentry.getKey()).entrySet();
		   Iterator iterator2 = set2.iterator();
		   while(iterator2.hasNext()) {
			   Map.Entry mentry2 = (Map.Entry)iterator2.next();
			   //Separation du port et de l'ip
			   String portIP = (String) mentry2.getKey();
			   String[] splIPort = portIP.split(" ");
			   System.out.print("Serveur : "+ splIPort[0] + " & Blocs : ");
			   
			   int blbl = annu.getMap().get(mentry.getKey()).get(mentry2.getKey());	   
			   System.out.println("[" + 1 + " => " + blbl +"]");
		   }    
		    
		}
		System.out.println("-------------------------------------------");
		
		//Fermeture des sockets
		annu.closeService();
		annu.closeServSock();
		
		
		//#################################################
		//## CORRESPOND A LA PHASE 2 DU MAIN UTILISATEUR ##
		//#################################################

		//Ouverture d'un Deuxième socket serveur
		annu.creaServSock(31300);
		//Création du tableau de threads de la taille du nombre de serveurs
		Thread[] tabThread2 = new Thread[nbServ];

		//Création d'un nouveau consommateur
		RecupListFichiers d = new RecupListFichiers(annu);
		
		
		//Pour chaque serveur 
		for (int i=0; i<nbServ; i++) {
			//Création d'un nouveau thread
			tabThread2[i] = new Thread(d);
			//Lancement du thread
			tabThread2[i].start();
		}
		
		//Attente de la fin de tous les threads pour continuer
		for (int i=0; i<tabThread.length; i++) {
			tabThread2[i].join();
		}
	}
	
	
	

}
