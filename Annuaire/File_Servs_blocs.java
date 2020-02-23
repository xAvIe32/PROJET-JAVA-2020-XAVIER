package Annuaire;

import java.util.ArrayList;

public class File_Servs_blocs {
	String nomF;
	ArrayList<String> tabStrings = new ArrayList<String>();
	ArrayList<Integer> blocs = new ArrayList<Integer>();
	
//	public File_Servs_blocs(String nomF, int[] servs, int[] blocs) {
//		super();
//		this.nomF = nomF;
//		this.servs = servs;
//		this.blocs = blocs;
//	}

	public String getNomF() {
		return nomF;
	}

	public void setNomF(String nomF) {
		this.nomF = nomF;
	}

	public ArrayList<String> getServs() {
		return tabStrings;
	}

	public void setServs(String servs) {
		for (String string : tabStrings) {
			if(string != servs) {
				this.tabStrings.add(servs);
			}
		}
	}

	public ArrayList<Integer> getBlocs() {
		return blocs;
	}

	public void setBlocs(Integer bl) {
		for (Integer integer : this.blocs) {
			if (integer != bl) {
				blocs.add(bl);
			}
		}
	}
	
//	public void ajoutServ(String s) {
//		for (int i=0; i<this.servs.length; i++) {
//			if ((servs[i]== null) || (servs[i] != s)) {
//				servs[i] = s;
//			}
//		}
//	}
	

}
