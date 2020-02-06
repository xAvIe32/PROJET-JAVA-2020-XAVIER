import java.io.*;
import java.net.*;

public class mainServeur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Cr�ation d'un serveur
		SRV Serv = new SRV("D:\\Bureau\\Fichiers\\", 1234);
		//Cr�ation du socket serveur
		Serv.creaServSock();
		//Acceptation de la connexion
		Serv.acceptDem();
		//R�cup�ration du socket de service
		Socket sserv = Serv.getService();
		String commande = new String();
		
		
		
		int i = 1;
		while(i == 1)
		{
				//R�ception de la demande du client
				String ligne = Serv.receptionString(sserv);
				System.out.println(ligne);
				
				//On appelle la fonction de s�paration pour avoir que la commande
				String[] result = ligne.split(" ");
				commande = result[0];
				System.out.println(commande);
				
				switch (commande) {
				
				//Cas de t�l�chargement
				case "DOWNLOAD" :
					String stringFile =  new String();
					//Nom du fichier
					String filename = result[1];
					System.out.println("j'ai choisi le t�l�chargement");
					//Cr�ation du fichier
					File myFile = new File(Serv.getPath() + filename);
					
					//Envoi du fichier s'il existe
					if (myFile.exists()==true) {
						//Conversion en String
						stringFile = Serv.FileToString(myFile, filename);
						//Envoi
						Serv.envoieString("FILE�" + filename + "�" + stringFile, sserv);
					}
					else {
						//Fermeture de la connexion si le fichier n'existe pas
						Serv.envoieString("CLOSE�Le fichier n'existe pas", sserv);
						i = 3000000;
					}
					
					break;

				//Cas de listage des fichiers
				case "LIST":
					
					System.out.println("J'ai choisi la liste");
					//Envoie de la liste
					Serv.envoieString(Serv.ListFiles(), sserv);
					
					break;					
					
				//Cas de demande de fermeture par le client
				case "CLOSE":
					System.out.println("Fermeture de la connexion");
					//Envoie de la fermeture au client
					Serv.envoieString("CLOSE�D�connexion demand�e par le Client", sserv);
					i = 3000000;
					break;

				//Cas ou aucune commande n'est reconnue
				default: 
					System.out.println("La commande n'est pas reconnue");
					//Envoie de l'erreur de commande
					Serv.envoieString("FALSEACT�Action non reconnue", sserv);
					break;
				}
		}
		Serv.fermer();
	}
}
