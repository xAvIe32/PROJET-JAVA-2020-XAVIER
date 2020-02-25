package Annuaire;

import java.io.IOException;
import java.net.Socket;

public class Consommateur implements Runnable{
	private Annuaire an;
	public Consommateur(Annuaire a) {
		this.an = a;
	}

	@Override
	public void run() {
		Socket sserv = an.getService();
		String portString = (String) an.recObject(sserv);
		int port = Integer.parseInt(portString);
		System.out.println("Serveur connecté sur le port : " + port);
		String list = (String) an.recObject(sserv);
		System.out.println("serv " + port + " : "+ list);
		int nbFilesServ = an.countFiles(list);
			

		for (int i=0; i<nbFilesServ; i++) {
			
			int[] blocs = (int []) an.recObject(sserv);
			System.out.println(blocs);
			
			an.addEntry(an.listeTab(list)[i], portString, blocs);
			
			
		}	
		System.out.println(portString + " a fini");
		
		try {
			sserv.close();
			System.out.println("déco de " + portString);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}

}
