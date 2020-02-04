import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		
		
		Envoi_Reception envrec = new Envoi_Reception();
		fonctionsCli fonct = new fonctionsCli();
		String recepFile = new String();
		
		
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
					
					String[] ligne = recep.split("£");
					
					String com = ligne[0];
					System.out.println("La commande est : " + com);
					
					String filename = ligne[1];
					
					
					switch(com) {
					
					case "FILE" :
						
					
						String Guilhem = envrec.receptionString(sock);
						
							
						//System.out.println("recepfile === " + Guilhem);
							
						System.out.println("Reception : " + Guilhem);
							
						envrec.StringToFile(sock, filename, Guilhem);
							
						File fichierRecu = new File("D:\\Bureau\\Réception\\" + filename);
							
						if (fichierRecu.exists()) {
							System.out.println("");
							System.out.println("Fichier reçu avec succès !!");
							System.out.println("");
							
						}
							
						
						
						
						break;
						
						
						
						
						
						
						
					case "FILETHREAD" :
						
						int longTablo = Integer.parseInt(envrec.receptionString(sock));
						System.out.println("long = " + longTablo);
						
						//String[] result = new String[longTablo];
						
						ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
						String[] result = null;
						try {
							result = (String[]) in.readObject();
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						System.out.println("longueur = " +result.length);
						for (int j=0; j < result.length; j++) {
							System.out.println(result[j]);
						}
						
						
						
						
						System.out.println("AU SECOOOURS");
						
						
						break;
						
						
						
						
						
						
						
						
					case "LIST" :
						
						
						
						String listFichiers = ligne[1];
						
						fonct.AfficherListe(listFichiers);
						
						break;
						
						
						
					case "CLOSE" :
						
						System.out.println("fermeture de la connexion");
						String raison = ligne[1];
						System.out.println(raison);
						sock.close();
						i = 30000;
						
						break;
						
					case "FALSEACT":
						System.out.println("");
						System.out.println("La commande n'est pas reconnue");
						System.out.println("");
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