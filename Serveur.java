import java.io.*;
import java.net.*;



public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Envoi_Reception envrec = new Envoi_Reception();
		FonctionsServ fonct = new FonctionsServ();
		
		//Cr�ation du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		
		
		
		String pathname = "D:\\Bureau\\Fichiers";
		Socket sock;
		String commande = new String();
		sock = SocketServeur.accept();
		//String valid = "OK";
		
		
		
		
		int i = 1;
		while(i == 1)
		{
				
				String ligne = envrec.receptionString(sock);
				
				System.out.println(ligne);
				String[] result = ligne.split(" ");
				
				commande = result[0];
				
				//On appelle la fonction de s�paration pour avoir que la commande
			
				System.out.println(commande);
				//On fait un switch sur commande pour diff�rencier  les traitements
				
				
				
				
				
				
				switch (commande) {
				
				
				
				//Cas de t�l�chargement
				case "DOWNLOAD" :
					
					String toSend = new String ();
					
					System.out.println("j'ai choisi le t�l�chargement");
					
					String filename = result[1];
					
					System.out.println(filename);
					
					
					
					
					File fichier = new File(pathname + "\\" + filename);
					
					
					
					if (fichier.exists() == true) {
						
						envrec.envoieString("FILE�"+filename, sock);
						BufferedReader reader = envrec.FileToString(sock, fichier, filename);
						
						while ((toSend = reader.readLine()) != null) {
							
							//Cr�ation du flux de donn�es
							envrec.envoieString(toSend, sock);
						}
					}
					else {
						System.out.println("Le fichier n'existe pas");
						envrec.envoieString("CLOSE�Le fichier n'existe pas", sock);
						i = 3000000;
					}
					
					
					break;
				

					
				//Cas de listage des fichiers
				case "LIST":
					
					System.out.println("J'ai choisi la liste");
					
					String liste = fonct.ListFiles(pathname);
					
					System.out.println(liste);
					envrec.envoieString(liste, sock);
					
					break;

					
					
					
				//Cas de demande de fermeture par le client
				case "CLOSE":
					System.out.println("Fermeture de la connexion");
					envrec.envoieString("CLOSE�D�connexion demand�e par le Client", sock);
					i = 3000000;
					break;
					 
					
				//Cas ou aucune commande n'est reconnue
				default: 
					System.out.println("La commande n'est pas reconnue");
					envrec.envoieString("FALSEACT�Action non reconnue", sock);
					break;
				}
				
				
				
				
		}
		sock.close();
		SocketServeur.close();
	}

}
