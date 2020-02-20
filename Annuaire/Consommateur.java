package Annuaire;

import java.net.Socket;

public class Consommateur implements Runnable{
	private Annuaire an;
	public Consommateur(Annuaire a) {
		this.an = a;
	}

	@Override
	public void run() {
		Socket sserv = an.getService();
		String portString = an.receptionString(sserv);
		int hmapmescouilles = Integer.parseInt(portString);
		System.out.println("Serveur connecté sur le port : " + hmapmescouilles);
		String list = an.receptionString(sserv);
		System.out.println("serv " + hmapmescouilles + " : "+ list);
		
	}

}
