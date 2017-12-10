package cs.Lab2.Tree;

public class Tree {
	public Float geopoint_x=(float) -1;
	public Float geopoint_y=(float) -1;
	public String arrondissment;
	public String genre;
	public String espece;
	public String famille;
	public int annee_plantation= -1;
	public Float hauteur=(float) -1;
	public Float circonference=(float) -1;
	public String adresse;
	public String nom_commun;
	public String variete;
	public int id;
	public String nom_ev;

	public Tree(String line) {
		String[] info = line.split(";");
		// geopoint
		String geopoint = info[0].replace("(", "").replace(")", "");
		this.geopoint_x = Float.parseFloat(geopoint.split(", ")[0]);
		this.geopoint_y = Float.parseFloat(geopoint.split(", ")[1]);
		// arrondissement
		this.arrondissment = info[1];
		// genre
		this.genre = info[2];
		// espece
		this.genre = info[3];
		// famille
		this.famille = info[4];
		// annee plantation
		if (info[5].length() > 0){
			this.annee_plantation = Integer.parseInt(info[5]);
		}
		// hauteur
		if (info[6].length() > 0){
			this.hauteur = Float.parseFloat(info[6]);
		}
		// circonference
		if (info[7].length() > 0){
			this.circonference = Float.parseFloat(info[7]);
		}
		//adresse
		this.adresse = info[8];
		// nom commun
		this.nom_commun = info[9];
		// variete
		this.variete = info[10];
		// object id
		this.id = Integer.parseInt(info[11]);
		// nom EV
		this.nom_ev = info[12];	
	}

	@Override
	public String toString() {
		return "(" + geopoint_x + ", " + geopoint_y + ")"
				+ ";" + arrondissment + ";" + genre
				+ ";" + espece + ";" + famille
				+ ";" + annee_plantation + ";"
				+ hauteur + ";" + circonference + ";"
				+ adresse + ";" + nom_commun + ";"
				+ variete + ";" + id + ";" + nom_ev;
	}

}
