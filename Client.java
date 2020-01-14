import java.io.*;
import java.net.*;


public class Client {

	public static void main(String[] args) {
		
		
		Fonctions fonctions = new Fonctions();
		try {
			Socket sock = new Socket("127.0.0.1", 1234);
			
			fonctions.receptionFichier(sock);
			System.out.println("Fichier reçu");
			sock.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}