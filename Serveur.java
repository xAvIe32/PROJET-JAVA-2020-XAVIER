import java.io.*;
import java.net.*;



public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Envoi_Reception envrec = new Envoi_Reception();
		FonctionsServ fonct = new FonctionsServ();
		
		//Création du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		
		
		
		String pathname = "D:\\Bureau\\Fichiers";
		Socket sock;
		String commande;
		Socket versSockCli = null;
		sock = SocketServeur.accept();
		//String valid = "OK";
		
		
		
		
		
		while(true)
		{
				
				String ligne = envrec.ReceiveMessage(sock);
				
				//envrec.envoieString("OK", sock);
				
				
				
				//On appelle la fonction de séparation pour avoir que la commande
				commande = fonct.LectureRecep(ligne);
				
				//On fait un switch sur commande pour différencier  les traitements
				switch (commande) {
				
				//Cas de téléchargement
				case "DOWNLOAD" :
					System.out.println("j'ai choisi le téléchargement");
				
				//Cas de listage des fichiers
				case "LIST":
					
					System.out.println("J'ai choisi la liste");
					
					String[] liste = fonct.ListFiles(pathname);
					
					envrec.envoieString("LIST " + liste.length, versSockCli);
					//valid = envrec.receptionString(sock);
					
					
					
					
					for (int i=0; i<liste.length; i++) {
						envrec.envoieString(liste[i], versSockCli);
						//valid = envrec.receptionString(sock);
						System.out.println(liste[i]);
					}
					
				System.out.println("Je suis sorti du for");
				//Cas ou aucune commande n'est reconnue
				default :
					System.out.println("La commande n'est pas reconnue");
				}
				
				
				//env.envoieFichier(sock, myFile);
				//valid = "azer";
				sock.close();
				SocketServeur.close();
				
				
				
				
				
			
		
		}
		
		
	}

}
