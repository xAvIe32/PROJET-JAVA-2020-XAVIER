package Client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Default.Commun;
import Serveur.Obj_fil;

public class CLI extends Commun {
	//Attributs de la classe CLI
	private String IPAddr;
	private Socket sock;
	private ArrayList<Obj_fil> lisObj;
	
	//Constructeur
	public CLI(String path, String iPAddr, ArrayList<Obj_fil> lo) {
		super(path);
		IPAddr = iPAddr;
		this.lisObj = lo;
	} 
	
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	//Connection au serveur
	public void connect(int p) {
		
		try {
			//Création du socket client
			sock = new Socket(this.IPAddr, p);
			//SocketAddress sa = new InetSocketAddress(this.IPAddr, this.port);
			
//			while (true) {
//				sock.connect(sa);
//				if (sock.isConnected()) {
//					conn = true;
//					System.out.println(conn);
//					break;
//				}
//			}
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	public int getnbBlocks(String filename) {
		int a = this.lisObj.indexOf(filename);
		int nbblocks = lisObj.get(a).getTabIndex().length;
		return nbblocks;
	}
	
	
	
	
	//Fermeture du socket client
	public void fermer () {
		try {
			this.sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//Retour du socket client
	public Socket getSock() {
		return sock;
	}

	
	
	

}
