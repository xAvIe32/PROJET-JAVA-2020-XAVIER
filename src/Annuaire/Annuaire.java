package Annuaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


public class Annuaire{
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	private HashMap<String, HashMap<String, int[]>> filou = new HashMap<String, HashMap<String, int[]>>();
	private int nbServ;
	
	public Annuaire() { 
	}
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	
	
	
	public String[] listeTab(String s) {
		String[] tab = s.split(" ");
		return tab;
	}
	
	
	//Création du socket serveur
	public void creaServSock(int p) {
		try {
			this.socketAnn = new ServerSocket(p);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Fermeture du socket server
	public void closeServSock() {
		try {
			this.socketAnn.close();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Fermeture du socket de service
	public void closeService() {
		try {
			this.service.close();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Retour du port
	public int getPort() {
		return this.port;
	}
	
	//Accepter demande de connexion
	public synchronized void acceptDem() {
		try {
			//Création du socket de service
			this.service = this.socketAnn.accept();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	
	//Fonction de réception des objets
	public Object recObject(Socket sock) {
		Object rec = new Object();
		try {
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			rec = ois.readObject();
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		return rec;
	}
	
	//Envoie d'objet
	public void sendObject(Object obj, Socket sock) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(obj);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	//Fonction de saisie et de vérification des entiers
	public int saisieInt() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean chiffre = false;
		int valeur = 0;
		//Tant que la saisie n'est pas un nombre, on recommence
	    do {
	    	//saisie clavier
	    	String saisie = sc.nextLine();
	    	//Vérification du nombre
	    	try {
	    		valeur = Integer.parseInt(saisie);
	    		chiffre = true;
	    	}
	    	//Si la saisie n'est pas un nombre
	    	catch (NumberFormatException e) {
	    		System.out.println("Ce n'est pas un nombre, veuillez recommencer");
	    	} 
	    } while (chiffre != true);
	    
	    return valeur;
	}

	
	
	public void setNbServ() {
		this.nbServ = this.saisieInt();
	}
	
	
	public int getNbserv() {
		return this.nbServ;
	}
	
	//Calcul du nombre de fichiers dans une liste
	public int countFiles(String list) {
		String[] tabloList = list.split(" ");
		int nbFiles = tabloList.length;
		return nbFiles;
		
	}
		
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}

	
	public HashMap<String, HashMap<String, int[]>> getMap() {
		return this.filou;
	}
	
	//Ajout d'une entrée dans la Hashmap contenant pour chaque fichier les serveurs qui
	//en ont des blocs ainsi que les blocs qu'ils possèdent
	public void addEntry(String name, String s, int[] b) {
		HashMap<String, int[]> servBl = new HashMap<String, int[]>();
		servBl.put(s, b);
		//Si le nom du fichier n'existe pas dedans
		if (filou.containsKey(name)) {
			
			//Si le nom du serveut n'existe pas
			if (filou.get(name).containsKey(s)) {
				
				if (filou.get(name).get(s) != b) {
					//Ajout du nom de serveur et du tableau d'entiers
					filou.get(name).put(s, b);
				}
				
			}
			
			else {
				//Ajout du nom de serveur et du tableau d'entiers
				filou.get(name).put(s, b);
			}
			
		}
		else {
			//ajout du nom du fichier et de la HashMap contenant le nom du serveur 
			//et le tableau d'entiers
			filou.put(name,  servBl);
		}
			
	
	}			
}
	
