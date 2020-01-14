
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
	public void decodeBase64BinaryToFile(String string) {
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

}
