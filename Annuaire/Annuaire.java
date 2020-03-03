package Annuaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


public class Annuaire{
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	HashMap<String, HashMap<String, int[]>> filou = new HashMap<String, HashMap<String, int[]>>();
	

	public Annuaire(int p) {
		this.port = p; 
	}
	
	  //------\\
	 //Méthodes\\
	//----------\\
	
	public String[] listeTab(String s) {
		String[] tab = s.split(" ");
		return tab;
	}
	//Création du socket serveur
	public void creaServSock() {
		try {
			this.socketAnn = new ServerSocket(this.port);
		} catch (IOException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Accepter demande de connexion
	public void acceptDem() {
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
	
