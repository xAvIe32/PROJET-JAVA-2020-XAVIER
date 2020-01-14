import java.io.*;
import java.net.Socket;

public class Envoi_Reception {
		
	
		public void envoieString(String toSend, Socket sock) {
			OutputStream os = null;
			
			try {
			
				os = sock.getOutputStream();
				PrintStream pn = new PrintStream(os);
				pn.println(toSend);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public String receptionString(Socket sock) {
			//On la met dans la variable ligne
			String ligne = null;
			
			try {
				//Reception de la chaine contenant la commande
				InputStream is = sock.getInputStream();
				
				ligne = new String(is.readAllBytes());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ligne;
		}
		public void envoieFichier(Socket sock, File myFile) {
			
			EncodeDecode enc = new EncodeDecode();
			
			//Variables
			String ligne;
			//POUR LES IMAGES
	        String encodstring = null;
			try {
				
				encodstring = enc.encodeFileToBase64Binary(myFile);
				System.out.println(encodstring);
				
				Reader InputString = new StringReader(encodstring);
				BufferedReader reader = new BufferedReader(InputString);
				System.out.println(reader);
				while ((ligne = reader.readLine()) != null) {
					
					//Création du flux de données
					OutputStream os = sock.getOutputStream();			
					PrintStream pStream = new PrintStream(os);
					pStream.println(ligne);
				}
				
				reader.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		}
		
		
		
		
		public void receptionFichier(Socket sock) {
			try {
				
					EncodeDecode dec = new EncodeDecode();
					
					//Récupération du flux de donnés
					InputStream is = sock.getInputStream();
					
					//Création du fichier qui accueilliras les données
					OutputStream fos = new FileOutputStream("C:\\Users\\Xavier\\Desktop\\Essai.txt");
					
					//Ecriture des données dans le fichier
					int c;
					while ((c = is.read()) != -1) {
						fos.write(c);
						
					}
					
					BufferedReader lecteurAvecBuffer = null;
					StringBuilder stringBuild = new StringBuilder();
					String line = null;
					
					lecteurAvecBuffer = new BufferedReader(new FileReader("C:\\Users\\Xavier\\Desktop\\Essai.txt"));
					while ((line = lecteurAvecBuffer.readLine()) != null) {
						stringBuild.append(line);
					}
					//String imageString = lecteurAvecBuffer.toString();
					String imageString = stringBuild.toString();
					dec.decodeBase64BinaryToFile(imageString);
				    
				    
				    
				    
					
					lecteurAvecBuffer.close();
					//Fermeture du fichier
					fos.close();			

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}


