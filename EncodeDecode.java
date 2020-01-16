
import java.io.*;
import java.util.Base64;



public class EncodeDecode {

	
	//FONCTION D'ENCODAGE DES FICHIERS EN BASE64
	public String encodeFileToBase64Binary(File file) throws Exception{
		
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
	public byte[] decodeBase64BinaryToFile(String string) {
		System.out.println(string);
		
		
		//Decodage de la chaine
		 byte[] imageByte = Base64.getDecoder().decode(string);
		
		 return imageByte;
		
	}

}
