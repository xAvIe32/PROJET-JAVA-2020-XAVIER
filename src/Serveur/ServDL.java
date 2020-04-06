package Serveur;


import java.util.ArrayList;

public class ServDL implements Runnable {
	//Variables
	private SRV Serv;
	
	//Constructeur
	public ServDL(SRV s) {
		// Auto-generated constructor stub
		this.Serv = s;
	}
	
	
	//#############################################
	//## CORRESPOND A LA PHASE 3 DU MAIN SERVEUR ##
	//#############################################
	
	
	@Override
	public void run(){
		
		//Création d'un objet de type ServConso
		ServConso sc = new ServConso(this.Serv);
		//Création d'un tableau de Threads
		ArrayList<Thread> tabT = new ArrayList<Thread>();
		int i=0;
		
		//Achaque fois qu'un client veut se connecter
		while (Serv.acceptDem()) {
			//Ajout d'un Thread à la liste
			tabT.add(sc);
			
			//Lancement du Thread
			
			//####################
			//## VOIR SERVCONSO ##
			//####################
			
			tabT.get(i).start();
			
			i++;
			
			
		}
		
	}
}
