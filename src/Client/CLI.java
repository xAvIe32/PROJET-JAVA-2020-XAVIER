package Client;
import java.io.File;
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
	private ArrayList<Byte> recon;
	
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
	
	
	public synchronized void reconstitution(String fname, int min, int max, byte[] res) {
//		if (max > recon.size()) {
//			recon.
//		}
		int k=0;
		for (int j = min-1; j<max; j++) {
			
			this.recon.set(j, res[k]);
			
			k++;
		}
	}
	
	
	
	
	
	
	public void initArray(int a) {
		this.recon = new ArrayList<Byte>(a);
		for (int i = 0; i<a; i++) {
			this.recon.add(null);
		}
	}
	
	
	
	public ArrayList<Byte> getRecon(){
		return recon;
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
