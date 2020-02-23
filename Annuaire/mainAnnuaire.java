package Annuaire;

import java.util.Iterator;
import java.util.Set;

public class mainAnnuaire {

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
		
		for (int i=0; i<annu.getListOfFiles().length; i++) {
			System.out.println("nomFichier " + annu.getListOfFiles()[i].getNomF());
			System.out.println("Serveurs");
			
			for (int j=0; j<annu.getListOfFiles()[i].getServs().length; j++) {
				System.out.println(annu.getListOfFiles()[i].getServs()[j]);
				System.out.print("Blocs : ");
				for (int k=0; k<annu.getListOfFiles()[i].getBlocs().length; k++) {
					System.out.print(annu.getListOfFiles()[i].getBlocs()[k] + " ");
				}
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
