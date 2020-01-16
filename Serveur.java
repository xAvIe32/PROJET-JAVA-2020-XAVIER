import java.io.*;
import java.net.*;



public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Envoi_Reception envrec = new Envoi_Reception();
		FonctionsServ fonct = new FonctionsServ();
		
		//Création du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		
		
		
		String pathname = "C:\\Users\\Xavier\\Desktop\\FICHIERS";
		Socket sock;
		String commande = new String();
		sock = SocketServeur.accept();
		//String valid = "OK";
		
		
		
		
		int i = 1;
		while(i == 1)
		{
				
				String ligne = envrec.receptionString(sock);
				//On appelle la fonction de séparation pour avoir que la commande
				commande = fonct.LectureRecep(ligne);
			
				System.out.println(commande);
				//On fait un switch sur commande pour différencier  les traitements
				
				
				
				
				
				
				switch (commande) {
				
				
				
				//Cas de téléchargement
				case "DOWNLOAD" :
					System.out.println("j'ai choisi le téléchargement");
					break;
				

					
				//Cas de listage des fichiers
				case "LIST":
					
					System.out.println("J'ai choisi la liste");
					
					String liste = fonct.ListFiles(pathname);
					
					System.out.println(liste);
					envrec.envoieString(liste, sock);
					
					break;

					
					
					
				//Cas ou aucune commande n'est reconnue
				default :
					System.out.println("La commande n'est pas reconnue");
					envrec.envoieString("CLOSE", sock);
					i = 3000000;
					break;
				}
				
				
				
				
		}
		sock.close();
		SocketServeur.close();
	}

}
