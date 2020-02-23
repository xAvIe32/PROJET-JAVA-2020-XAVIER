package Annuaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Annuaire{
	private int port;
	private ServerSocket socketAnn;
	private Socket service;
	//private HashMap<String, HashMap<String, int[]>> theMap = new HashMap<String, HashMap<String, int[]>>();
	int ComptServ;
	ArrayList<File_Servs_blocs> listOfFiles = new ArrayList<File_Servs_blocs>();
	
	

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
	
	public ArrayList<File_Servs_blocs>  getListOfFiles() {
		return listOfFiles;
	}

	public void addFileToList(File_Servs_blocs fsb) {
			
			for (File_Servs_blocs file_Servs_blocs : listOfFiles) {
				if (file_Servs_blocs.nomF != fsb.nomF) {
					listOfFiles.add(fsb);
				}
				
				//A FINIRE2ARZFSQDCQZEF
				//azefzaedfsqd
				//azefsdqfqsdfaze
				else {
					for (ArrayList<String> : file_Servs_blocs.getServs()) {
						
					}
				}
			}
	}
	
	
}
	
