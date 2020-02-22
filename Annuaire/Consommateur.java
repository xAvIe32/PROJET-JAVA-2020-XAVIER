package Annuaire;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

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
			HashMap<String, int[]> fileBloc = (HashMap<String, int[]>) an.recObject(sserv);
			an.fillMap(portString, fileBloc);
			System.out.println(fileBloc);
			System.out.println(fileBloc.values());
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
