package graphes;

public class Tutore extends Etudiant {
    public Tutore(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences, String id) {
        super(nom, prenom, anneeEtude, moyenne,ressource, nbAbsences, id);
    }
    
    public Tutore(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource, int nbAbsences) {
        this(nom, prenom, anneeEtude, moyenne,ressource, nbAbsences, null);
    }
    
    public Tutore(String nom, String prenom, int anneeEtude, double moyenne,Ressources ressource) {
        this(nom, prenom, anneeEtude, moyenne,ressource, 0, null);
    }
    
    public Tutore(Tutore e) {
        this(e.getNom(), e.getPrenom(), e.getAnneeEtude(), e.getMoyenne(),e.getRessource(), e.getNbAbsences(), e.getID());
    }

    @Override
    public double calculCout() {
    	double res = this.getMoyenne();
    	res += this.getNbAbsences()*(0.5*getCoeffAbs());
    	if (res < 0) res = 0;
        return res;
    }
}
