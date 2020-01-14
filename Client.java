import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		
		
		Envoi_Reception envrec = new Envoi_Reception();
		fonctionsCli fonct = new fonctionsCli();
		
		try {
			Socket sock = new Socket("127.0.0.1", 1234);
			
			//Saisie de l'action à envoyer au serveur
			String saisie = fonct.saisieClavier();
			
			//Appel de la  fonction d'envoie de chaines
			envrec.envoieString(saisie, sock);
			
			//recep.receptionFichier(sock);
			System.out.println("Fichier reçu");
			
			
			//Saisie de l'action à envoyer au serveur
			String saisie2 = fonct.saisieClavier();
			
			//Appel de la  fonction d'envoie de chaines
			envrec.envoieString(saisie2, sock);
			
			
			
			
			sock.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}