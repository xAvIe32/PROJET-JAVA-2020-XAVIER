import java.io.*;
import java.net.*;

public class Serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Fonctions fonctions = new Fonctions();
		
		//Création du socket Serveur
		ServerSocket SocketServeur = new ServerSocket(1234);
		
		String pathname = new String("C:\\Users\\Xavier\\Pictures\\logos\\mayonnaiz.mp3");
		File myFile = new File(pathname);
		Socket sock;
		
		
		while(true)
		{
				sock = SocketServeur.accept();				
				fonctions.envoieFichier(sock, myFile);
				sock.close();
				SocketServeur.close();
				
				
				
				
				
			
		
		}
		
		
	}

}
