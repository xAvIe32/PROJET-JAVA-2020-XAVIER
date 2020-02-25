package Annuaire;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class mainAnnuaire {

	@SuppressWarnings("rawtypes")
	public static void main(String [] args) throws InterruptedException {
		Annuaire annu = new Annuaire(32370);
		System.out.println("nb Serv : ");
		int nbServ = annu.saisieInt();
		Thread[] tabThread = new Thread[nbServ];
		
		
		annu.creaServSock();
		Consommateur c = new Consommateur(annu);
		//mainServeur mS = new mainServeur();
		
		for (int i=0; i<nbServ; i++) {
			annu.acceptDem();
			tabThread[i] = new Thread(c);	
			tabThread[i].start();
		}
		
//		for (int i=0; i<tabThread.length; i++) {
//			tabThread[i].start();
//		}
		
		for (int i=0; i<tabThread.length; i++) {
			tabThread[i].join();
		}
		
		System.out.println(annu.filou);
		
		
		
		
		
		System.out.println("azeijrpazfjspdof");
		
		 Set set = annu.filou.entrySet();
	     Iterator iterator = set.iterator();
	     
	     while(iterator.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iterator.next();
	        System.out.println("Fichier : "+ mentry.getKey());
	        
	        Set set2 = annu.filou.get(mentry.getKey()).entrySet();
	        Iterator iterator2 = set2.iterator();
	        while(iterator2.hasNext()) {
	        	Map.Entry mentry2 = (Map.Entry)iterator2.next();
	        	System.out.print("Serveur : "+ mentry2.getKey() + " & Blocs : ");
	        	int[] blbl = annu.filou.get(mentry.getKey()).get(mentry2.getKey());

	        	int nbBl = 0;
	        	int frst = blbl[0];
	        	for(int i=0; i<blbl.length; i++) {
	        		nbBl++;
	        	}
	        	System.out.println("[" + frst + " => " + nbBl +"]");
	        }
	        
	        
	     }
		

		
		//System.out.println(annu.getTheMap());
		
//		Set<String> listKeys=annu.getTheMap().keySet();  // Obtenir la liste des clés
//		Iterator<String> iterateur=listKeys.iterator();
//		// Parcourir les clés et afficher les entrées de chaque clé;
//		while(iterateur.hasNext())
//		{
//			Object key= iterateur.next();
//			System.out.println (key+"=>"+annu.getTheMap().get(key));
//		}
		// Afficher une entrée spécifique
		//System.out.println(annu.getTheMap().get("Lundi"));
	}

}
