package Serveur;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class mainServeur {


	@SuppressWarnings({"rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException {
		
		
		//########################################################################
		//## DEBUT DE LA PHASE 1 (VOIR CLASSES MAIN ANNUAIRE PUIS CONSOMMATEUR) ##
		//########################################################################
		//La phase n°1 consiste à envoyer à l'annuaire la liste des fichiers avec leur
		//taille et le port du serveur.
		
		//Création d'un serveur
		SRV Serv = new SRV("D:\\Bureau\\");
		Serv.saisiePort(); 
		String ipAnn = new String();
		
		System.out.println("Veuillez saisir l'IP de l'annuaire :");
		ipAnn = Serv.saisieClavier();
		
		while(true) {
			//Test de la saisie pour voir si c'est une ip
			if (ipAnn.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")){
				System.out.println("Vous avez bien saisi une ip");
				break;
			}
			else {
				System.out.println("Ressaisissez");
				ipAnn = Serv.saisieClavier();
			}
		}
			
		
		//CREATION DU SOCKET QUI ENVOIE A L'ANNUAIRE
		Socket conAnn = new Socket(ipAnn, 32370);
		
		//FONCTION QUI ENVOIE LE NUMERO DU PORT A L'ANNUAIRE
		String portstring= ""+Serv.getPort();
		Serv.sendObject(portstring + " " + ipAnn, conAnn, Serv.getOutputS());
		
		//Saisie du répertoire qui contient les fichiers du serveur
		System.out.println("Veuillez saisir l'action à exécuter :");
		String finPath = Serv.saisieClavier();
		//Modification du chemin en ajoutant le dernier morceau
		Serv.setpath(finPath);
		
		//Envoie de la liste
		String list = Serv.ListFiles(Serv.getPath());
		Serv.sendObject(list, conAnn, Serv.getOutputS());
		
		//Découpage de la liste par nom de fichier
		String[] files = Serv.nameFiles(list);
		
		//Pour chaque fichier
		for (String string : files) {
			//Récupération de la taille
			int fileByte = Serv.getFTaille(new File(string));
			//Envoi de la taille du fichier à l'annuaire
			Serv.sendObject(fileByte - 1, conAnn, Serv.getOutputS());
		}

	
		System.out.println("Veuillez attendre ");
		//Fermeture du socket
		conAnn.close();
		
		
		//##################################################
		//## FIN DE LA PHASE 1 (VOIR CLASSE CONSOMMATEUR) ##
		//##################################################
		
		
		//Attente pour que tous les servuers aient fini de communiquer à l'annuaire
		Socket conDown = null;	
		//Tant que la connexion n'est pas en place
		while (conDown == null) {
			try {
				
				InetAddress IP = InetAddress.getLocalHost();
				
				System.out.println(IP);
				//on se connecte puis on sort du while
				conDown = new Socket(ipAnn, 31300);	
			} catch (UnknownHostException e) {
				
			} catch (IOException e) {
				
			}
		}
		
		
		//##################################################################
		//## DEBUT DE LA PHASE 2 (VOIR CLASSEs MAIN ANNUAIRE ET DOWNLOAD) ##
		//##################################################################
		//La phase n°2 consiste à recevoir depuis l'annuaire les fichiers
		//que les autres serveurs/clients lui proposent.
		
		System.out.println("");
		System.out.println("----- Phase de téléchargement -----");

		//Envoi du port
		Serv.sendObject(portstring, conDown, Serv.getOutputS());
		System.out.println("Fichiers disponibles au téléchargement : ");
		
		//Envoie de la liste des fichiers
		Serv.sendObject(list, conDown, Serv.getOutputS());
		
		//Reception du nopmbre de fichiers disponibles au téléchargement
		int count = (int) Serv.recObject(conDown, Serv.getInputS());
		
		
		String downable = new String();
		ArrayList<Obj_fil> lisObj = new ArrayList<Obj_fil>();
		System.out.println("count = " + count);
		
		//Pour chaque fichier dispo
		for (int j=0; j<count; j++) {
			
			System.out.println("--------------------------");
			//Reception du nom de fichier
			downable =(String) Serv.recObject(conDown, Serv.getInputS());
			System.out.println(downable);
			
			//Réception de la ligne de la Hashmap pour ce serveur et ce nom de fichier
			HashMap<String, Integer> tabInd = (HashMap<String, Integer>) Serv.recObject(conDown, Serv.getInputS());
			System.out.println(tabInd);
			
			//Parcours de la Hashmap
			Iterator it = tabInd.entrySet().iterator();
			while (it.hasNext()) {
				
				Map.Entry mentry = (Map.Entry)it.next();
				//Récupération de la taille du fichier
				int blbl = tabInd.get(mentry.getKey());
				
				String IPort = (String) mentry.getKey();
				String[] splIPort = IPort.split(" ");
				
				//Ajout dans la liste d'objets d'un objet de type Obj_fil avec le nom, le port et la taille
				Obj_fil obj = new Obj_fil(downable, splIPort[0], blbl, splIPort[1]);
				lisObj.add(obj);
				
				//Affichage
				System.out.print("Serveur : "+ obj.getPort() + " & Blocs : ");
				System.out.println("[" + 1 + " => " + blbl +"]");
				
			}
		}
		
		//fermeture du socket
		conDown.close();
		
		//##############################################
		//## FIN DE LA PHASE 2 (VOIR CLASSE DOWNLOAD) ##
		//##############################################
		
		
		//#########################
		//## DEBUT DE LA PHASE 3 ##
		//#########################
		//La phase 3 consiste à créer les Threads Serveurs et clients pour
		//permettre d'envoyer et de recevoir des Parties de fichiers en même temps
		
		
		//Création d'un nouveau socket serveur
		Serv.creaServSock(Serv.getPort());
		
		//Ce thread permettra la création de plusieurs serveurs 
		
		//########################
		//## VOIR CLASSE SERVDL ##
		//########################
		
		ServDL s = new ServDL(Serv);
		//Création d'un nousveau Thread
		Thread servo = new Thread(s);
		//Lancement d'un nouveau serveur
		servo.start();	
		
		
		//Nouvelle liste de ports pour savoir su quels serveurs se connecter
		ArrayList<Integer> tabport = new ArrayList<Integer>(); 
		String filedown = new String();
		int taille = 0;
		int nbServfile = 0;
		String ip = new String();
		while(true) {
			//FONCTION QUI ENVOIE LE NUMERO DU PORT A L'ANNUAIRE		
			System.out.println("Entrez le nom du fichier à télécharger :");
			
			//Fonction de saisie
			filedown = Serv.saisieClavier();
			
			boolean test = false;      
			String pp = new String();
			//Dans cette boucle on regarde chaque serveur qui possède le fichier que l'on veut
			//télécharger et on ajoute dans la liste le port
			
			//Pour chaque objet dans la liste
			for (int i=0; i<lisObj.size(); i++) {
				
				//Si l'objet en cours de lecture a pour nom celui qu'on a saisie et que ce n'est pas une partie de fichier
				if ((lisObj.get(i).getName().contains(filedown)) && (!lisObj.get(i).getName().contains("£"))) {
					//on récupère le port
					pp = lisObj.get(i).getPort();
					//conversion en int
					int portToCo = Integer.parseInt(pp);
					//Ajout du port dans la liste
					tabport.add(portToCo); 
					//Récupération de la taille du tableau d'index
					taille = lisObj.get(i).getTabIndex();
					ip = lisObj.get(i).getIP();
					//Pour sortir de la boucle
					test = true;
					nbServfile ++;
				}
				
			}
			//On sort du for des qu'on a récupéré le port du serveur qui nous intéresse
			if (test == true) {
				break;
			}
			else {
				//Si le nom saisi n'est pas dans la liste  de fichiers disponible, on refait la boucle
				System.out.println("Erreur de saisie");
			}
		
		}
		
		
		ArrayList<Thread> clithre = new ArrayList<Thread>();
		
		//Récupération des index minimums et maximums
		int sizeMin = 0;
		int sizeMax = 0;
		
		//Le but de cette boucle est de créer un nouveau client pour chaque serveur qui possède le fichier
		
		//Pour chaque port de la liste
		for (int i=0; i<tabport.size(); i++) {
			//définition du bloc minimal
			sizeMin = sizeMax +1;
			//Définition du bloc maximal
			sizeMax = (i+1)*(taille/nbServfile);
			
			//si le bloc max est supérieur au dernier bloc alors 
			//il prendra sa valeur
			if (sizeMax > taille) {
				sizeMax = taille;
			}
			
			
			//Création d'un thread client
			ConnectDL codl = new ConnectDL(Serv.getPath(), tabport.get(i), filedown, sizeMin, sizeMax, i, ip);
			clithre.add(codl);
			//Démarrage du Thread
			
			//###########################
			//## VOIR CLASSE CONNECTDL ##
			//###########################
			
			clithre.get(i).start();
		}
		
		
		//Attente de la fin de tous les threads clients
		//Pour chaque Thread Client
		for (int i=0; i<clithre.size(); i++) {
			try {
				//Si il est vivant
				if(clithre.get(i).isAlive()) {
					//alors on attend la fin
					clithre.get(i).join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//#######################
		//## FIN DE LA PHASE 3 ##
		//#######################
		
		
		
		
		//#########################
		//## DEBUT DE LA PHASE 4 ##
		//#########################
		//La phase 4 a pour but de réassembler le fichier une fois que toutes les parties
		//ont été reçues
		
		FileOutputStream fos;
		//Création du fichier réseultant
		File total = new File(Serv.getPath()+"\\"+filedown);
		//Ouverture du flux d'écriture pour ce fichier en mode append
		//pour écrire à chaque fois à la suite
		fos = new FileOutputStream(total, true);
		//Récupération de la liste des fichiers dans le répertoire
		String[] tabFiles = Serv.ListFiles(Serv.getPath()).split(" ");
		
		int compt = 0;
		//pour chaque fichier
		for (int i = 0; i<tabFiles.length; i++) {
			//Si c'est une partie (name£n°part)
			if (tabFiles[i].contains(filedown+"£")) {
				//Récupération du numéro de la partie
				String[] sa = tabFiles[i].split("£");
				int a = Integer.parseInt(sa[1]);
				
				//On vérifie que la partie soit bien celle qui doit suivre
				if (a == compt) {
					//Déclaration de la partie en tant que fichier
					File fb = new File(tabFiles[i]);
					//si elle existe
					if (fb.exists()) {
						//ouverture du flux de lecture de la partie
						FileInputStream fis = new FileInputStream(fb);
						//Ecriture dans le fichier résultant 
						fos.write(fis.readAllBytes());
						//fermeture du flux de lecture
						fis.close();
						//Suppression du fichier de partie
						fb.delete();
						//n° de la partie suivante
						compt++;
						
					}
				}
				
			}
		}
		
		//fermeture du flux d'écriture du fichier résultant
		fos.close();
		
		//test sur le fichier résultant
		if (total.exists()) {
			System.out.println("Reconstiution de " + filedown.toUpperCase() + " Réussie");
		}
		else {
			System.out.println("Echec de la reconstitution");
		}	
		
		//#######################
		//## FIN DE LA PHASE 4 ##
		//#######################
	}
}
