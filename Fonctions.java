import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

import javax.imageio.ImageIO;

public class Fonctions {

	
	//FONCTION D'ENCODAGE DES FICHIERS EN BASE64
	private static String encodeFileToBase64Binary(File file) throws Exception{
		
		//Ouverture du fichier
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        //Création d'un tableau de la taille du fichier
        byte[] bytes = new byte[(int)file.length()];
        //Lecture du fichier
        fileInputStreamReader.read(bytes);
        //Fermeture du flux
        fileInputStreamReader.close();
        
        String base64img;
        //Création de la chaine de caractères contenant le fichier en base64
        base64img = new String(Base64.getEncoder().encode(bytes), "UTF-8");
        System.out.println(base64img);
        //Retour de la chaine de caractères
        return base64img;
        
    }
	
	
	
	
	
	//FONCTION DE DECODAGE D'UNE CHAINE EN UN FICHIER
	private static void decodeBase64BinaryToFile(String string) {
		System.out.println(string);
		String imagestr = string;
		byte[] imageByte;
		
		//Decodage de la chaine
		imageByte = Base64.getDecoder().decode(imagestr);
		
		
		try {
			
			//Definition de l'emplacement du fichier
			OutputStream outputfile = new FileOutputStream("C:\\Users\\Xavier\\Desktop\\Essai.mp3");
			//Ecriture du fichier
			outputfile.write(imageByte);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}
	
	
	
	
	
	
	
	
	
	public void receptionFichier(Socket sock) {
		try {
			
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
			    try
			      {
					lecteurAvecBuffer = new BufferedReader(new FileReader("C:\\Users\\Xavier\\Desktop\\Essai.txt"));
					while ((line = lecteurAvecBuffer.readLine()) != null) {
						stringBuild.append(line);
					}
					//String imageString = lecteurAvecBuffer.toString();
					String imageString = stringBuild.toString();
					decodeBase64BinaryToFile(imageString);
			      }
			    catch(FileNotFoundException exc)
			      {
			    	System.out.println("Erreur d'ouverture");
			      }
			    
			    
			    
			    
				
				
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
	
	
	
	
	
	
	
	
	
public void envoieFichier(Socket sock, File myFile) {
		
		//Variables
		String ligne;
		//POUR LES IMAGES
         String encodstring = null;
		try {
			encodstring = encodeFileToBase64Binary(myFile);
			System.out.println(encodstring);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//POUR LES IMAGES
		
		
		
		
		try {
			
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
		}
		
			
	}
}
