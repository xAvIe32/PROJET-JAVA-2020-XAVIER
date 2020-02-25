package Annuaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Annuaire{
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	//private HashMap<String, HashMap<String, int[]>> theMap = new HashMap<String, HashMap<String, int[]>>();
	int ComptServ;
	HashMap<String, int[]> serv_bl = new HashMap< String, int[]>();
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
	
//	public HashMap<String, HashMap<String, int[]>> getTheMap() {
//		return theMap;
//	}

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

	public int countFiles(String list) {
		String[] tabloList = list.split(" ");
		int nbFiles = tabloList.length;
		return nbFiles;
		
	}
	
//	public synchronized void fillMap(String Np, HashMap<String, int[]> fb) {
//		this.theMap.put(Np, fb);
//	}

	
	//Renvoi du socket de service
	public Socket getService() {
		return service;
	}

	public void setComptServ() {
		ComptServ++;
	}
	
	public int getComptServ() {
		return ComptServ;
	}
	
	
	
	
	public void addEntry(String name, String s, int[] b) {
		this.serv_bl.put(s, b);
		
		if (!filou.containsKey(name)) {
			filou.put(name, serv_bl);
		}
		
//		if (filou.putIfAbsent(name, serv_bl) == null) {
//			System.out.println("Insertion de la ligne pour : " + name );
//		}
//		else if (filou.get(name).putIfAbsent(s, b) != null) {
//			System.out.println("Insertion de la liojnlk,mplr : " + name );
//		}
//		else {
//			System.out.println("Erreur d'insertion");
//		}
		
		
		
	}
	
	
//	public ArrayList<File_Servs_blocs>  getListOfFiles() {
//		return listOfFiles;
//	}
//	
//	
//	public synchronized void addFileToList(File_Servs_blocs fsb) {
//			
//		ArrayList<String> Se = fsb.getServs();
//		ArrayList<Integer> Bl = fsb.getBlocs();
//		
//			for (File_Servs_blocs file_Servs_blocs : listOfFiles) {
//				if (file_Servs_blocs.nomF != fsb.nomF || file_Servs_blocs.nomF == null) {
//					listOfFiles.add(fsb);
//				}
//				
//				else {
//					for (int j=0; j<Bl.size(); j++) {
//						if(file_Servs_blocs.getServs().get(j) != Se.get(j)){
//							file_Servs_blocs.setServs(Se.get(j));
//						}
//						else {
//							for (int i=0; i<Bl.size(); i++) {
//								if (file_Servs_blocs.getBlocs().get(i) != Bl.get(i)) {
//									file_Servs_blocs.setBlocs(Bl.get(i));
//								}
//								
//							}
//						}
//					}
//				}
//					
//			}
//	}
//	
//	
//	public synchronized void test(File_Servs_blocs fsb) {
//		ArrayList<String> Se = fsb.getServs();
//		ArrayList<Integer> Bl = fsb.getBlocs();
//	
//		if (!this.listOfFiles.contains(fsb)) {
//			if (!this.listOfFiles.)
//			
//		}
//	}
//	public void afficherList() {
//		for (File_Servs_blocs file_Servs_blocs : listOfFiles) {
//			System.out.println(file_Servs_blocs.nomF);
//			for (String string : file_Servs_blocs.getServs()) {
//				System.out.println(string);
//				for (Integer integer : file_Servs_blocs.getBlocs()) {
//					System.out.println(integer);
//					
//				}
//			}
//			
//			
//		}
//	}
}
	
