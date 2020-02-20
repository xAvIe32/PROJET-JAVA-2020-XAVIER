package Client;
import java.io.*;
import java.net.*;

public class mainClient {

	public static void main(String[] args) {
		
		//Création du client
		CLI Client = new CLI("D:\\Bureau\\Réception\\", 1234, "127.0.0.1");
		//Connection au serveur
		Client.connect();
		//Récupération du socket
		Socket sock = Client.getSock();
					
				
		int i = 1;
		while(i == 1) {
			
			//Saisie de l'action à envoyer au serveur
			String saisie = Client.saisieClavier();
			//Envoie de la chaine
			Client.envoieString(saisie, sock);
			//Réception du serveur suivant l'action demandée
			String recep = Client.receptionString(sock);
			//envrec.envoieString("OK", sock);
			//Split 
			String[] ligne = recep.split("£");
			String com = ligne[0];
			System.out.println("La commande est : " + com);
			
			switch(com) {
			
			case "FILE" :
				//Nom du fichier
				String filename = ligne[1];
				//Le Fichier en string
				String Guilhem = ligne[2];
				System.out.println("Reception : " + Guilhem);
				//Convertion en Fichier
				Client.StringToFile(filename, Guilhem);
				File fichierRecu = new File("D:\\Bureau\\Réception\\" + filename);
				//Test de la réception
				if (fichierRecu.exists()) {
					System.out.println("");
					System.out.println("Fichier reçu avec succès !!");
					System.out.println("");
					
				}
				
				break;
				
			case "LIST" :
				//Liste de fichiers
				String listFichiers = ligne[1];
				//Affichage propre
				Client.AfficherListe(listFichiers);
				
				break;
				
			case "CLOSE" :
				System.out.println("fermeture de la connexion");
				//Raison de la fermeture
				String raison = ligne[1];
				System.out.println(raison);
				//Fermeture du socket
				Client.fermer();
				i = 30000;
				
				break;
				
			case "FALSEACT":
				System.out.println("");
				System.out.println("La commande n'est pas reconnue");
				System.out.println("");
				break;
				
			default :
				//Pour le fun
				System.out.println("bonjour, je ne sers à rien");
				break;
			}
		}
		//Fermeture du socket client
		Client.fermer();
	}

}