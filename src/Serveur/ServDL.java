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
	
	
	@Override
	public void run(){
		//Cr�ation d'un objet de type ServConso
		ServConso sc = new ServConso(this.Serv);
		//Cr�ation d'un tableau de Threads
		ArrayList<Thread> tabT = new ArrayList<Thread>();
		
		
		int i=0;
		while (Serv.acceptDem()) {
			//Si la connexion est accept�e on lance un thread
			tabT.add(new Thread(sc));
			tabT.get(i).start();
			i++;
		}
		
//		System.out.println("Serv DL est sorti");
	}
	
	
}
