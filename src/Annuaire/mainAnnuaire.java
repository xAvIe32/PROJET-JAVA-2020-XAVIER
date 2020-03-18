package Annuaire;



import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class mainAnnuaire {

	@SuppressWarnings("rawtypes")
	public static void main(String [] args) throws InterruptedException {
		//Création de l'annuaire
		Annuaire annu = new Annuaire();
		//Saisie du nombre de serveurs
		System.out.println("nb Serv : ");
		annu.setNbServ();
		int nbServ = annu.getNbserv();
		//Création du tableau de threads de la taille du nombre de serveurs
		Thread[] tabThread = new Thread[nbServ];
		
		//Création du socket serveur de l'annuaire
		annu.creaServSock(32370);
		//Création d'un nouveau consommateur
		Consommateur c = new Consommateur(annu);
				
		
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
			   System.out.print("Serveur : "+ mentry2.getKey() + " & Blocs : ");
			   int[] blbl = annu.getMap().get(mentry.getKey()).get(mentry2.getKey());
			
			   int nbBl = 0;
			   int frst = blbl[0];
			   for(int i=0; i<blbl.length; i++) {
				   nbBl++;
			   }
			   System.out.println("[" + frst + " => " + nbBl +"]");
		   }    
		    
		}
		System.out.println("-------------------------------------------");
		
		
		annu.closeService();
		annu.closeServSock();
		
		
		annu.creaServSock(31300);
		//Création du tableau de threads de la taille du nombre de serveurs
		Thread[] tabThread2 = new Thread[nbServ];

		//Création d'un nouveau consommateur
		Download d = new Download(annu);
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("----- Téléchargement -----");
		System.out.println("--------------------------");
		
		System.out.println("j'attends " + nbServ + " Clients");
		
		
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
		
		//annu.closeServSock();	
	}
	
	
	

}
