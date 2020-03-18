package Serveur;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Client.CLI;



public class mainServeur {

	private static int[] tabports;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException {
		//   Auto-generated method stub
		//Création d'un serveur
		SRV Serv = new SRV("D:\\Bureau\\");
		Serv.saisiePort(); 
		
		//CREATION DU SOCKET QUI ENVOIE A L'ANNUAIRE
		Socket conAnn = new Socket("127.0.0.1", 32370);
		
		//FONCTION QUI ENVOIE LE NUMERO DU PORT A L'ANNUAIRE
		String portstring= ""+Serv.getPort();
		Serv.sendObject(portstring, conAnn);
		
		//Saisie du répertoire qui contient les fichiers du serveur
		System.out.println("Veuillez saisir l'action à exécuter :");
		String finPath = Serv.saisieClavier();
		Serv.setpath(finPath);
		//Envoie de la liste
		String list = Serv.ListFiles(Serv.getPath());
		Serv.sendObject(list, conAnn);
		
		//Découpage des fichiers en blocs d'octets
		String[] files = Serv.nameFiles(list);
		for (String string : files) {
			byte[] fileByte = Serv.decoupFichier(new File(Serv.getPath()+"\\"+string));
			int[] tabIndex = new int[fileByte.length];
			
			//Récupération des index
			for (int i=0; i < fileByte.length; i++) {
				tabIndex[i] = i+1;
			}
			
			//Envoi des index des blocs
			Serv.sendObject(tabIndex, conAnn);
		}

	
		System.out.println("Veuillez attendre ");
		
		
		
		
		
		Socket conDown = null;	
		//Tant que la connexion n'est pas en place
		while (conDown == null) {
			try {
				//on se connecte puis on sort du for
				conDown = new Socket("127.0.0.1", 31300);
				
			} catch (UnknownHostException e) {
				
			} catch (IOException e) {
				
			}
			
		}
		
		
		
		
		//CREATION DU SOCKET QUI ENVOIE A L'ANNUAIRE
		System.out.println("");
		System.out.println("----- Phase de téléchargement -----");
		
		
		
		
		
		
		//Serv.saisiePort(); 
		//String portstring2= ""+Serv.getPort();
		
		//Envoi du port
		Serv.sendObject(portstring, conDown);
			
		
		
		
		
		
		System.out.println("Fichiers disponibles au téléchargement : ");
		
		//Envoie de la liste des fichiers
		Serv.sendObject(list, conDown);
		//Reception du nopmbre de fichiers concernés par ce serveur
		int count = (int) Serv.recObject(conDown);
		String downable = new String();
		
		ArrayList<Obj_fil> lisObj = new ArrayList<Obj_fil>();
		System.out.println("count = " + count);
		
		//Pour chaque fichier dispo
		for (int j=0; j<count; j++) {
			//Reception du nom de fichier
			downable = (String) Serv.recObject(conDown);

			
			//Réception de la ligne de la Hashmap pour ce serveur et ce nom de fichier
			HashMap<String, int[]> tabInd = (HashMap<String, int[]>) Serv.recObject(conDown);
			
			System.out.println(downable);
			
			//Parcours de la Hashmap
			Iterator it = tabInd.entrySet().iterator();
			while (it.hasNext()) {
				
				Map.Entry mentry = (Map.Entry)it.next();
				int[] blbl = tabInd.get(mentry.getKey());
				int nbBl = 0;
				int frst = blbl[0];
				
				for(int i=0; i<blbl.length; i++) {
				  	nbBl++;
				}
				
				//Ajout dans la liste d'objets d'un objet de type Onbj_fil avec le nnom, le port et la  liste d'index
				Obj_fil obj = new Obj_fil(downable,(String) mentry.getKey(), blbl);
				lisObj.add(obj);
				
				//Affichage
				System.out.print("Serveur : "+ obj.getPort() + " & Blocs : ");
				System.out.println("[" + frst + " => " + nbBl +"]");
				
			}
			//System.out.println(tabInd);

			
		}
		
		//fermeture du socket
		conDown.close();
		
		
		//Création d'un objet de type ServDL 
		ServDL s = new ServDL(Serv);
		
		//Création d'un nouveau socket serveur
		Serv.creaServSock(Serv.getPort());
		//Création d'un nousveau Thread
		Thread servo = new Thread(s);
		//Lancement d'un nouveau serveur
		servo.start();
		
		
		//Création d'un nouveau client
		CLI cl = new CLI(Serv.getPath() + finPath, "127.0.0.1", lisObj); 
		//Nouvelle liste de ports
		ArrayList<Integer> tabport = new ArrayList<Integer>(); 
		System.out.println("je suis démarré");
		String filedown = new String();
		int taille = 0;
		int nbServfile = 0;
		
		while(true) {
			//FONCTION QUI ENVOIE LE NUMERO DU PORT A L'ANNUAIRE		
			System.out.println("Entrez le nom du fichier à télécharger :");
			
			//Fonction de saisie
			filedown = Serv.saisieClavier();
			
			boolean test = false;
			
			String pp = new String();
			
			//Pour chaque objet dans la liste
			for (int i=0; i<lisObj.size(); i++) {
				//Si l'objet en cours de lecture a pour nom filedown
				if (lisObj.get(i).getName().contentEquals(filedown)) {
					//on récupère le port
					pp = lisObj.get(i).getPort();
					//conversion en int
					int portToCo = Integer.parseInt(pp);
					//Ajout du port dans la liste
					tabport.add(portToCo); 
					//Récupération de la taille du tableau d'index
					taille = lisObj.get(i).getTabIndex().length;
					//Pour sortir de la boucle
					test = true;
					nbServfile ++;
				}
				
			}
			
			if (test == true) {
				break;
			}
			else {
				System.out.println("Erreur de saisie");
			}
		
		}
		
		//Récupération des index minimums et maximums
		int sizeMin = 0;
		int sizeMax = 0;
		for (int i=0; i<tabport.size(); i++) {
			sizeMin = (i) + sizeMax;
			sizeMax = (i+1)*(taille/nbServfile);
			
			if (sizeMax > taille) {
				sizeMax = taille;
			}
			//Création d'un objet ConnectDL
			ConnectDL codl = new ConnectDL(cl, tabport.get(i), filedown, sizeMin, sizeMax);
			//Nouveau Thread
			Thread co = new Thread(codl);
			//Démarrage du Thread
			co.start();
		}
		
		
		
		
		
		
		
		
		
		
		
		//conAnn.close();
		/* AU LIEU D ENVOYER DES STRINGS? ENVOYER DIRECTEMENT UNE HMAP AVEC NOM FICHIER ET BLOCS*/
		
		
		
		
//		//Création du socket serveur
//		Serv.creaServSock();
//		//Acceptation de la connexion
//		Serv.acceptDem();
//		//Récupération du socket de service
//		Socket sserv = Serv.getService();
//		String commande = new String();
//		
//		
//		
//		
//		int i = 1;
//		while(i == 1)
//		{
//				//Réception de la demande du client
//				String ligne = Serv.receptionString(sserv);
//				System.out.println(ligne);
//				
//				//On appelle la fonction de séparation pour avoir que la commande
//				String[] result = ligne.split(" ");
//				commande = result[0];
//				System.out.println(commande);
//				
//				switch (commande) {
//				
//				//Cas de téléchargement
//				case "DOWNLOAD" :
//					String stringFile =  new String();
//					//Nom du fichier
//					String filename = result[1];
//					System.out.println("j'ai choisi le téléchargement");
//					//Création du fichier
//					File myFile = new File(Serv.getPath()+ finPath + filename);
//					
//					//Envoi du fichier s'il existe
//					if (myFile.exists()==true) {
//						//Conversion en String
//						stringFile = Serv.FileToString(myFile, filename);
//						//Envoi
//						Serv.envoieString("FILE£" + filename + "£" + stringFile, sserv);
//					}
//					else {
//						//Fermeture de la connexion si le fichier n'existe pas
//						Serv.envoieString("CLOSE£Le fichier n'existe pas", sserv);
//						i = 3000000;
//					}
//					
//					break;
//
//				//Cas de listage des fichiers
//				case "LIST":
//					
//					System.out.println("J'ai choisi la liste");
//					//Envoie de la liste
//					Serv.envoieString(Serv.ListFiles(Serv.getPath()+ finPath), sserv);
//					
//					break;					
//					
//				//Cas de demande de fermeture par le client
//				case "CLOSE":
//					System.out.println("Fermeture de la connexion");
//					//Envoie de la fermeture au client
//					Serv.envoieString("CLOSE£Déconnexion demandée par le Client", sserv);
//					i = 3000000;
//					break;
//
//				//Cas ou aucune commande n'est reconnue
//				default: 
//					System.out.println("La commande n'est pas reconnue");
//					//Envoie de l'erreur de commande
//					Serv.envoieString("FALSEACT£Action non reconnue", sserv);
//					break;
//				}
//		}
//		Serv.fermer();
	}
}
