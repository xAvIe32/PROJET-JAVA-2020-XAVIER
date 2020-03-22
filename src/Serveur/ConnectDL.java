package Serveur;



import java.io.IOException;
import java.util.ArrayList;

import Client.CLI;

public class ConnectDL implements Runnable{
	//Variables
	private CLI cl;
	private int port;
	private int min;
	private int max;
	private String fname;
	private int total;
	
	//Constructeur
	public ConnectDL(CLI cl, int p, String fname, int min, int max, int tot) {
		// Auto-generated constructor stub
		this.cl = cl;
		this.port = p;
		this.fname = fname;
		this.min = min;
		this.max = max;
		this.total = tot;
	}

	@Override
	public void run() {
		
		// Auto-generated method stub
		
		//cl.connect();
		//System.out.print("je suis co sur le serv : " + cl.getport());
//		System.out.println(this.portToCo);
		
		//Connection au serveur
		cl.connect(this.port);
		System.out.println("je suis co sur le serv : " + this.port);
		
		//Envoi du nom du fichier et des bornes de l'index
		cl.sendObject(this.fname + " " + this.min +" " + this.max, cl.getSock());
		
		
		//Réception du tableau contennat les données du fichier concordant avec les index
		ArrayList<Byte> res = (ArrayList<Byte>) cl.recObject(cl.getSock());
		
		
		System.out.println(res);
		
		System.out.println("j'ai reçu");
		
		byte[] b = new byte[res.size()];
		
		for(int i=0; i<res.size(); i++) {
			b[i] = res.get(i).byteValue();
		}
		
		cl.reconstitution(fname, min, max, b);
		
		
//		try {
//			cl.getSock().close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
	}

}
