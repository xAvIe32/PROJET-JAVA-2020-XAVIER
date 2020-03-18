package Serveur;


import java.io.File;

import Client.CLI;

public class ConnectDL implements Runnable{
	//Variables
	private CLI cl;
	private int port;
	private int min;
	private int max;
	private String fname;
	
	//Constructeur
	public ConnectDL(CLI cl, int p, String fname, int min, int max) {
		// Auto-generated constructor stub
		this.cl = cl;
		this.port = p;
		this.fname = fname;
		this.min = min;
		this.max = max;
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
		byte[] res = (byte[]) cl.recObject(cl.getSock());
	
		System.out.println(res);
		
		
		
		
		
		
		
	}

}
