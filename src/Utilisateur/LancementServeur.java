package Utilisateur;


import java.util.ArrayList;

public class LancementServeur extends Thread {
	//Variables
	private SRV Serv;
	
	//Constructeur
	public LancementServeur(SRV s) {
		// Auto-generated constructor stub
		this.Serv = s;
	}
	
	
	//#################################################
	//## CORRESPOND A LA PHASE 3 DU MAIN UTILISATEUR ##
	//#################################################
	
	
	@Override
	public void run(){
		
		
		//Création d'un tableau de Threads
		ArrayList<Thread> tabT = new ArrayList<Thread>();
		int i=0;
		
		//Achaque fois qu'un client veut se connecter
		while (true) {
			
			Serv.acceptDem();
			//Création d'un objet de type Serveur
			Serveur sc = new Serveur(this.Serv);
			//Ajout d'un Thread à la liste
			tabT.add(sc);
			
			//Lancement du Thread
			
			//##################
			//## VOIR SERVEUR ##
			//##################
			
			tabT.get(i).start();
			
			i++;
			
			
		}
		
	}
}
