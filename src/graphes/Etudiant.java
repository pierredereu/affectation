package graphes;

public abstract class Etudiant implements Comparable<Etudiant> {
    private String nom;
    private String prenom;
    private int anneeEtude;
    private double moyenne;
    private String id;
	private int nbAbsences;
	private boolean ejected;
	private Ressources ressource;
	private double coeffAbs;
	private double coeffAn;
    
    public Etudiant(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.anneeEtude = anneeEtude;
        this.moyenne = moyenne;
        this.nbAbsences = nbAbsences;
        this.id = id;
        this.ejected = false;
        this.ressource = ressource;
        this.coeffAbs=1;
        this.coeffAn=1;
    }
    
    public Etudiant(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences) {
        this(nom, prenom, anneeEtude, moyenne,ressource, nbAbsences, null);
    }
    
    public Etudiant(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource) {
        this(nom, prenom, anneeEtude, moyenne,ressource, 0, null);
    }
    
    public Etudiant(Etudiant e) {
        this(e.getNom(), e.getPrenom(), e.getAnneeEtude(), e.getMoyenne(),e.getRessource(), e.getNbAbsences(), e.getID());
    }
    
    public String getNom() {return this.nom;}
    public String getPrenom() {return this.prenom;}
    public int getAnneeEtude() {return this.anneeEtude;}
    public double getMoyenne() {return this.moyenne;}
    public String getID() {return this.id;}
	public int getNbAbsences() {return nbAbsences;}
	public double getCoeffAbs() {return coeffAbs;}
	public double getCoeffAn() {return coeffAn;}
	public boolean estEjecte() {return this.ejected;}
	public Ressources getRessource() {return this.ressource;}
    
	public void setNbAbsences(int nbAbsences) {this.nbAbsences = nbAbsences;}
	public void setID(String id) {this.id = id;}
	public void setCoeffAbs(double coeffAbs) {this.coeffAbs = coeffAbs;}
	public void setCoeffAn(double coeffAn) {this.coeffAn = coeffAn;}
	public void ejecter() {this.ejected = true;}

	@Override
	public int compareTo(Etudiant e) {
		return (int) Math.round(e.calculCout() - this.calculCout());
	}
	
	@Override
	public String toString() {
		return this.getID();
	}
	
    public abstract double calculCout();
}