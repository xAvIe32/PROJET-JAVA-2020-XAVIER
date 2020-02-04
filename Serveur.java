import java.io.*;
import java.net.*;



public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		//Création du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		Socket sock;
		String commande = new String();
		sock = SocketServeur.accept();
		
		Envoi_Reception envrec = new Envoi_Reception();
		FonctionsServ fonct = new FonctionsServ(sock, "D:\\Bureau\\Fichiers", envrec);
		
		
		
		
		int i = 1;
		while(i == 1)
		{
				
				String ligne = envrec.receptionString(sock);
				
				System.out.println(ligne);
				String[] result = ligne.split(" ");
				
				commande = result[0];
				
				//On appelle la fonction de séparation pour avoir que la commande
			
				System.out.println(commande);
				//On fait un switch sur commande pour différencier  les traitements
				
				
				
				
				
				String filename = result[1];
				
				switch (commande) {
				
				
				
				//Cas de téléchargement
				case "DOWNLOAD" :
					
					
					
					System.out.println("j'ai choisi le téléchargement");
					
					
					
					fonct.EnvoieFichier(filename);
					
					break;
				

					
				//Cas de listage des fichiers
				case "LIST":
					
					System.out.println("J'ai choisi la liste");
					
					fonct.ListFiles();
					
					
					break;
					
					
					
					

				case "DOWNTHREAD":
					
					
					String[] fileStringTab = fonct.FileToTablo(filename);
					System.out.println(String.valueOf(fileStringTab.length));
					envrec.envoieString(String.valueOf(fileStringTab.length), sock);
					
					
					ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(fileStringTab);  
					
//					for (int j=0; j < fileStringTab.length; j++) {
//						System.out.println(fileStringTab[j]);
//						
//						envrec.envoieString(fileStringTab[j], sock);
//					}
					
					break;
					
					
					
					
					
					
					
					
					
				//Cas de demande de fermeture par le client
				case "CLOSE":
					System.out.println("Fermeture de la connexion");
					envrec.envoieString("CLOSE£Déconnexion demandée par le Client", sock);
					i = 3000000;
					break;
					 
					
				//Cas ou aucune commande n'est reconnue
				default: 
					System.out.println("La commande n'est pas reconnue");
					envrec.envoieString("FALSEACT£Action non reconnue", sock);
					break;
				}
				
				
				
				
		}
		sock.close();
		SocketServeur.close();
	}

}
