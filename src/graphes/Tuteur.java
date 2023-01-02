package graphes;

public class Tuteur extends Etudiant {
	private int maxTutores;
	
    public Tuteur(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences, String id) {
        super(nom, prenom, anneeEtude, moyenne,ressource, nbAbsences, id);
        maxTutores = 0;
    }
    
    public Tuteur(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences) {
        this(nom, prenom, anneeEtude, moyenne,ressource, nbAbsences, null);
    }
    
    public Tuteur(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource) {
        this(nom, prenom, anneeEtude, moyenne,ressource, 0, null);
    }
    
    public Tuteur(Tuteur e) {
        this(e.getNom(), e.getPrenom(), e.getAnneeEtude(), e.getMoyenne(),e.getRessource(), e.getNbAbsences(), e.getID());
    }

    @Override
    public double calculCout() {
    	double res = Math.abs(this.getMoyenne() - 20);
    	if (this.getAnneeEtude() == 3) res -= (3*getCoeffAn());
    	res += this.getNbAbsences()*(0.5*getCoeffAbs());
    	if (res < 0) res = 0;
        return res;
    }
    
    public int getMaxTutores() {
    	return this.maxTutores;
    }

	public void setMaxTutores(int max) {
		this.maxTutores = max;
	}
}