package Annuaire;



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
		System.out.println("azeijrpazfjspdof");
		annu.afficherList();
		
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
