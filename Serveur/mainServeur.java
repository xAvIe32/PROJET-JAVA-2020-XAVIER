package Serveur;
import java.io.*;
import java.net.*;

public class mainServeur {

	public static void main(String[] args) throws IOException {
		//   Auto-generated method stub
		//Création d'un serveur
		SRV Serv = new SRV("C:\\Users\\Xavier\\Desktop\\");
		Serv.saisiePort();
		
		//CREATION DU SOCKET QUI ENVOIE A L'ANNUAIRE
		Socket conAnn = new Socket("127.0.0.1", 32370);
		
		//FONCTION QUI ENVOIE LE NUMERO DU PORT A L'ANNUAIRE
		String portstring= ""+Serv.getPort();
		
		Serv.sendObject(portstring, conAnn);
		
		String finPath = Serv.saisieClavier();
		String list = Serv.ListFiles(Serv.getPath()+ finPath);
		Serv.sendObject(list, conAnn);
		
		String[] files = Serv.nameFiles(list);
		for (String string : files) {
			byte[] fileByte = Serv.decoupFichier(new File(Serv.getPath()+finPath+"\\"+string));
			int[] tabIndex = new int[fileByte.length];
			for (int i=0; i<tabIndex.length; i++) {
				tabIndex[i] = i+1;
			}
			Serv.sendObject(tabIndex, conAnn);
		}
		
		conAnn.close();
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
