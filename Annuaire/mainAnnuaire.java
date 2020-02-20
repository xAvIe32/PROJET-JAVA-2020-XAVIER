package Annuaire;

public class mainAnnuaire {

	public static void main(String [] args) {
		Annuaire annu = new Annuaire(32370);
		
		
		annu.creaServSock();
		
//		while(true) {
//			annu.acceptDem();
//			Tc.start();
//			
//		}
		
		do {
			 Consommateur c = new Consommateur(annu);
			 Thread Tc = new Thread(c);
			 annu.acceptDem();
			 Tc.start();
		} while (annu.getService().isBound());
		
	
		
		
		
	}

}
