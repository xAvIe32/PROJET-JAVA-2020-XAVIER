import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		
		
		Envoi_Reception envrec = new Envoi_Reception();
		fonctionsCli fonct = new fonctionsCli();
		//String valid = "OK";
		
		
		//while (valid == "OK") {
			try {
				Socket sock = new Socket("127.0.0.1", 1234);
			
				
				int i = 1;
				while(i == 1) {
					
					//Saisie de l'action à envoyer au serveur
					String saisie = fonct.saisieClavier();
					
					//Appel de la  fonction d'envoie de chaines
					envrec.envoieString(saisie, sock);
					
					//valid = envrec.receptionString(sock);
					
					
				
					
					//recep.receptionFichier(sock);
				
					String recep = envrec.receptionString(sock);
					//envrec.envoieString("OK", sock);
					
					String[] ligne = recep.split(" ");
					
					String com = ligne[0];
					System.out.println("La commande est : " + com);
					
					
					
					switch(com) {
					case "FILE" :
						
					case "LIST" :
						
						
						String listFichiers = envrec.receptionString(sock);
						
						System.out.println("La liste des fichiers disponibles est : ");
						
						System.out.println("je suis la liste : " + listFichiers);
						
						break;
						
						
						
					case "CLOSE" :
						
						System.out.println("fermeture de la connexion");
						sock.close();
						i = 30000;
						
						break;
						
					default :
						System.out.println("bonjour, je ne sers à rien");
						break;
					}
					
					
					
					
					
				}
				
			sock.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
		
		
		
	}

}