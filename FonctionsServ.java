
public class FonctionsServ {

	public void ListFiles() {
		
	}
	
	public String LectureRecep(String recep) {
		//Variables
		String com = null;
		
		//Séparation de la commande
		String[] ligne = recep.split(" ");
		
		//Affectation de la commande dans la variable
		com = ligne[0];
		
		//retour de la commande
		return com;
	}
	
	
	
	

}
