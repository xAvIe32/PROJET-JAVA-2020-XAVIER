import java.io.*;
import java.net.*;



public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Envoi_Reception envrec = new Envoi_Reception();
		FonctionsServ fonct = new FonctionsServ();
		
		//Cr�ation du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		
		//String pathname = new String("C:\\Users\\Xavier\\Pictures\\logos\\mayonnaiz.mp3");
		//File myFile = new File(pathname);
		Socket sock;
		String commande;
		
		
		
		while(true)
		{
				sock = SocketServeur.accept();
				
				String ligne = envrec.receptionString(sock);
				
				//On appelle la fonction de s�paration pour avoir que la commande
				commande = fonct.LectureRecep(ligne);
				
				//On fait un switch sur commande pour diff�rencier  les traitements
				switch (commande) {
				
				//Cas de t�l�chargement
				case "DOWNLOAD" :
					System.out.println("j'ai choisi le t�l�chargement");
					break;
				
				//Cas de listage des fichiers
				case "LIST" :
					System.out.println("J'ai choisi la liste");
					break;
				
				//Cas ou aucune commande n'est reconnue
				default :
					System.out.println("La commande n'est pas reconnue");
				}
				
				
				//env.envoieFichier(sock, myFile);
				sock.close();
				SocketServeur.close();
				
				
				
				
				
			
		
		}
		
		
	}

}
