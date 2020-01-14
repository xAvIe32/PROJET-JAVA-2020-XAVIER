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
			
				
				
				
				//Saisie de l'action à envoyer au serveur
				String saisie = fonct.saisieClavier();
				
				//Appel de la  fonction d'envoie de chaines
				envrec.envoieString(saisie, sock);
				
				//valid = envrec.receptionString(sock);
				
				
			
				
				//recep.receptionFichier(sock);
				System.out.println("Fichier reçu");
			
				String recep = envrec.ReceiveMessage(sock);
				//envrec.envoieString("OK", sock);
				
				String[] ligne = recep.split(" ");
				
				String com = ligne[0];
				
				
				
				switch(com) {
				case "FILE" :
					
				case "LIST" :
					
					int tailleList = Integer.parseInt(ligne[1]);
					String[] listFichiers = new String[tailleList];
					
					System.out.println("La liste des fichiers disponibles est : ");
					for (int i = 0; i<tailleList; i++) {
						listFichiers[i] = envrec.ReceiveMessage(sock);
						//envrec.envoieString("OK", sock);
						System.out.println(listFichiers[i]);
					}
				}
				
				//valid = "AEZR";
				sock.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
		
		
		
	}

}