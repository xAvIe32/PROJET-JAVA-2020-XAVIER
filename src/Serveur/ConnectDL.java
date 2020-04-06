package Serveur;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import Client.CLI;

public class ConnectDL extends Thread{
	//Variables
	private CLI cl;
	private int port;
	private int min;
	private int max;
	private String fname;
	private int numC;
	
	//Constructeur
	public ConnectDL(String pa, int p, String fname, int min, int max, int n, String ip) {
		// Auto-generated constructor stub
		this.cl = new CLI(pa, ip);
		this.port = p;
		this.fname = fname;
		this.min = min-1;
		this.max = max;
		this.numC = n;
	}
	
	
	//#############################################
	//## CORRESPOND A LA PHASE 3 DU MAIN SERVEUR ##
	//#############################################
	
	//###################
	//## PARTIE CLIENT ##
	//###################
	
	//Cette partie correspond à la réception d'une partie d'un fichier depuis un serveur
	
	@Override
	public void run() {
		
		//Connection au serveur
		cl.connect(this.port);
		System.out.println("je suis co sur le serv : " + this.port);
		
		//Envoi du nom du fichier et des bornes de l'index
		cl.sendObject(this.fname + " " + this.min + " " + this.max + " " + this.numC, cl.getSock(), cl.getOutputS());
		
		//Réception de la partie
		try {
			//Ouverture d'un flux de données de lecture
			DataInputStream dis = new DataInputStream(cl.getSock().getInputStream());
			//Ouverture d'un flux d'écriture dans un fichier
			OutputStream fos = new FileOutputStream(cl.getPath()+"\\"+fname+"£"+this.numC);
			//Ecriture dans le fichier par bloc de 4ko
			byte[] buffer = new byte[4 * 1024];
			int byteRead;
			while ((byteRead = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, byteRead);
				
			}
			//Fermeture des flux
			dis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("j'ai reçu sur le port " + this.port + " : " + fname+"£"+this.numC);
		
			
		
	}

}
