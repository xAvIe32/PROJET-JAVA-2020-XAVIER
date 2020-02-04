import java.io.*;
import java.net.Socket;

public class Envoi_Reception {
		
		Socket sock;
		
	
		public void envoieString(String toSend, Socket sock) {
			OutputStream os;
			
			try {
			
				os = sock.getOutputStream();
				PrintStream pn = new PrintStream(os);
				//os.flush();
				pn.println(toSend);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
		
		
		public String receptionString(Socket sock) {
			
			String ligne = new String();
			try {
				
				
				
				BufferedReader reader = new BufferedReader (new InputStreamReader(sock.getInputStream()));
				ligne = reader.readLine();

				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ligne;
		}
		
		
		
		
		
		
		
		
		public BufferedReader FileToString (Socket sock, File myFile, String filename) {
			
			EncodeDecode enc = new EncodeDecode();
			
			//POUR LES IMAGES
	        String encodstring = null;
	        BufferedReader reader = null;
			try {
				
				encodstring = enc.encodeFileToBase64Binary(myFile);
				System.out.println(encodstring);
				
				Reader InputString = new StringReader(encodstring);
				 reader = new BufferedReader(InputString);
				System.out.println("FiletoString " + reader);
				
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("LE FICHIER N'EXISTE PAS LA COOIN DE TA RACE");
				this.envoieString("CLOSE", sock);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return reader;	
		}
		
		
		
		
		
		
		
		
		public void StringToFile(Socket sock, String filename, String string) {
			try {
				
					EncodeDecode dec = new EncodeDecode();
					
					
					
					
					Reader inputString = new StringReader(string);	
					BufferedReader reader = new BufferedReader(inputString);
					
					
					StringBuilder stringBuild = new StringBuilder();
					String line = new String();
					
					
					while ((line = reader.readLine()) != null) {
						stringBuild.append(line);
					}
					
					
					
					
					//String imageString = lecteurAvecBuffer.toString();
					String fileString = stringBuild.toString();
					
					
					byte[] fileByte = dec.decodeBase64BinaryToFile(fileString);
				    
					OutputStream outputfile = new FileOutputStream("D:\\Bureau\\Réception\\" + filename);
					//Ecriture du fichier
					outputfile.write(fileByte);
				    
				    
					
					//Fermeture du fichier
					outputfile.close();			

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}


