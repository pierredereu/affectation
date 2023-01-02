package hud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import graphes.Affectation;
import graphes.Etudiant;
import graphes.Ressources;
import graphes.Tuteur;
import graphes.Tutore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MyController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Affectation a;
	private CalculAffectation<Etudiant> calcul;

	@FXML private ListView<Tuteur> tuteursListView = new ListView<>();
	@FXML private ListView<Tutore> tutoresListView = new ListView<>();
	@FXML private ListView<Tuteur> fixerTuteursListView = new ListView<>();
	@FXML private ListView<Tutore> fixerTutoresListView = new ListView<>();
	@FXML private ListView<Tuteur> eviterTuteursListView = new ListView<>();
	@FXML private ListView<Tutore> eviterTutoresListView = new ListView<>();
	@FXML private Label creerNomTuteur = new Label(" ");
	@FXML private Label creerPrenomTuteur = new Label(" ");
	@FXML private Label creerMoyenneTuteur = new Label(" ");
	@FXML private Label creerAbsTuteur = new Label(" ");
	@FXML private Label creerAnneeTuteur = new Label(" ");
	@FXML private Label creerNomTutore = new Label(" ");
	@FXML private Label creerPrenomTutore = new Label(" ");
	@FXML private Label creerMoyenneTutore = new Label(" ");
	@FXML private Label creerAbsTutore = new Label(" ");
	@FXML private Label creerAnneeTutore = new Label(" ");
	@FXML private Label fixerNomTuteur = new Label(" ");
	@FXML private Label fixerPrenomTuteur = new Label(" ");
	@FXML private Label fixerMoyenneTuteur = new Label(" ");
	@FXML private Label fixerAbsTuteur = new Label(" ");
	@FXML private Label fixerAnneeTuteur = new Label(" ");
	@FXML private Label fixerNomTutore = new Label(" ");
	@FXML private Label fixerPrenomTutore = new Label(" ");
	@FXML private Label fixerMoyenneTutore = new Label(" ");
	@FXML private Label fixerAbsTutore = new Label(" ");
	@FXML private Label fixerAnneeTutore = new Label(" ");
	@FXML private Label eviterNomEtu = new Label(" ");
	@FXML private Label eviterPrenomEtu= new Label(" ");
	@FXML private Label eviterMoyenneEtu= new Label(" ");
	@FXML private Label eviterAbsEtu = new Label(" ");
	@FXML private Label eviterAnneeEtu= new Label(" ");

	private Tuteur creerSelectedTuteur;
	private Tutore creerSelectedTutore;
	private Tuteur fixerSelectedTuteur;
	private Tutore fixerSelectedTutore;
	private Etudiant eviterSelectedEtu;
	private List<Tuteur> tuteursList;
	private List<Tutore> tutoresList;
	
	
	
	private void lireFichierEtudiants() {
		List<List<String>> records = new ArrayList<>();
		File f = new File(getClass().getResource("ListeEtudiants.csv").getFile());
		try {
			Scanner s = new Scanner(f);
		    String line;
			while (s.hasNextLine()) {
				line = s.nextLine();
			    String[] values = line.split(";");
			    records.add(Arrays.asList(values));
			}
		    s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Tuteur> tuteurs = new ArrayList<>();
		List<Tutore> tutores = new ArrayList<>();
		for (int i=1;i<records.size();i++) {
			if (Integer.parseInt(records.get(i).get(2)) == 1) {
				tutores.add(new Tutore(records.get(i).get(0), records.get(i).get(1), 1, Double.parseDouble(records.get(i).get(3)), Ressources.R101, Integer.parseInt(records.get(i).get(4))));
			} else {
				tuteurs.add(new Tuteur(records.get(i).get(0), records.get(i).get(1), Integer.parseInt(records.get(i).get(2)), Double.parseDouble(records.get(i).get(3)), Ressources.R101, Integer.parseInt(records.get(i).get(4))));
			}
		}
		Collections.sort(tuteurs);
		Collections.sort(tutores);
		a = new Affectation(tuteurs, tutores);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lireFichierEtudiants();
		tuteursList = new ArrayList<>();
		tutoresList = new ArrayList<>();
		for (Tuteur t : a.getTuteurs()) {
			if (!a.getAssociation().contientSommet(t)) {
				tuteursList.add(t);
			}
		}
		for (Tutore t : a.getTutores()) {
			if (!a.getAssociation().contientSommet(t)) {
				tutoresList.add(t);
			}
		}
		tuteursListView.getItems().setAll(tuteursList);
		tutoresListView.getItems().setAll(tutoresList);
		fixerTuteursListView.getItems().setAll(tuteursList);
		fixerTutoresListView.getItems().setAll(tutoresList);
		eviterTuteursListView.getItems().setAll(tuteursList);
		eviterTutoresListView.getItems().setAll(tutoresList);
		
		tuteursListView.setOnMouseClicked(e -> {
			creerSelectedTuteur = tuteursListView.getSelectionModel().getSelectedItem();
			creerNomTuteur.setText(creerSelectedTuteur.getNom());
			creerPrenomTuteur.setText(creerSelectedTuteur.getPrenom());
			creerMoyenneTuteur.setText(creerSelectedTuteur.getMoyenne() + "");
			creerAbsTuteur.setText(creerSelectedTuteur.getNbAbsences() + "");
			creerAnneeTuteur.setText(creerSelectedTuteur.getAnneeEtude() + "");
	    });
		tutoresListView.setOnMouseClicked(e -> {
			creerSelectedTutore = tutoresListView.getSelectionModel().getSelectedItem();
			creerNomTutore.setText(creerSelectedTutore.getNom());
			creerPrenomTutore.setText(creerSelectedTutore.getPrenom());
			creerMoyenneTutore.setText(creerSelectedTutore.getMoyenne() + "");
			creerAbsTutore.setText(creerSelectedTutore.getNbAbsences() + "");
			creerAnneeTutore.setText(creerSelectedTutore.getAnneeEtude() + "");
	    });
		fixerTuteursListView.setOnMouseClicked(e -> {
			fixerSelectedTuteur = fixerTuteursListView.getSelectionModel().getSelectedItem();
			fixerNomTuteur.setText(fixerSelectedTuteur.getNom());
			fixerPrenomTuteur.setText(fixerSelectedTuteur.getPrenom());
			fixerMoyenneTuteur.setText(fixerSelectedTuteur.getMoyenne() + "");
			fixerAbsTuteur.setText(fixerSelectedTuteur.getNbAbsences() + "");
			fixerAnneeTuteur.setText(fixerSelectedTuteur.getAnneeEtude() + "");
	    });
		fixerTutoresListView.setOnMouseClicked(e -> {
			fixerSelectedTutore = fixerTutoresListView.getSelectionModel().getSelectedItem();
			fixerNomTutore.setText(fixerSelectedTutore.getNom());
			fixerPrenomTutore.setText(fixerSelectedTutore.getPrenom());
			fixerMoyenneTutore.setText(fixerSelectedTutore.getMoyenne() + "");
			fixerAbsTutore.setText(fixerSelectedTutore.getNbAbsences() + "");
			fixerAnneeTutore.setText(fixerSelectedTutore.getAnneeEtude() + "");
	    });
		eviterTuteursListView.setOnMouseClicked(e -> {
			eviterSelectedEtu = eviterTuteursListView.getSelectionModel().getSelectedItem();
			eviterNomEtu.setText(eviterSelectedEtu.getNom());
			eviterPrenomEtu.setText(eviterSelectedEtu.getPrenom());
			eviterMoyenneEtu.setText(eviterSelectedEtu.getMoyenne() + "");
			eviterAbsEtu.setText(eviterSelectedEtu.getNbAbsences() + "");
			eviterAnneeEtu.setText(eviterSelectedEtu.getAnneeEtude() + "");
	    });
		eviterTutoresListView.setOnMouseClicked(e -> {
			eviterSelectedEtu = eviterTutoresListView.getSelectionModel().getSelectedItem();
			eviterNomEtu.setText(eviterSelectedEtu.getNom());
			eviterPrenomEtu.setText(eviterSelectedEtu.getPrenom());
			eviterMoyenneEtu.setText(eviterSelectedEtu.getMoyenne() + "");
			eviterAbsEtu.setText(eviterSelectedEtu.getNbAbsences() + "");
			eviterAnneeEtu.setText(eviterSelectedEtu.getAnneeEtude() + "");
	    });
	}

	public void switchToCreerAffectation(ActionEvent event) {
		System.out.println("Nouvelle Affectation pressed");
		try {
			root = FXMLLoader.load(getClass().getResource("CreerAffectation.fxml"));
		} catch (IOException e) {
			System.err.println(e);
		}
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		scene = new Scene(root);
		stage.setScene(scene);
        stage.setTitle("Créer une affectation");
		stage.show();
	}

	public void switchToAfficherAffectation(ActionEvent event) {
		System.out.println("Afficher Affectation pressed");
		try {
			root = FXMLLoader.load(getClass().getResource("AfficherAffectation.fxml"));
		} catch (IOException e) {
			System.err.println(e);
		}
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
        stage.setTitle("Afficher la dernière affectation");
		stage.show();
	}
	
	public void returnToMainMenu(ActionEvent event) {
		System.out.println("Annuler pressed");
		
		System.exit(0);
		
//		try {
//			root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//		} catch (IOException e) {
//			System.err.println(e);
//		}
//		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//        stage.setTitle("Afficher la dernière affectation");
//		stage.show();
	}

	public void switchToEviterAffectation(ActionEvent event) {
		System.out.println("Eviter d'affecter un étudiant pressed");
		try {
			root = FXMLLoader.load(getClass().getResource("EviterAffectation.fxml"));
		} catch (IOException e) {
			System.err.println(e);
		}
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
        stage.setTitle("Eviter d'affecter un étudiant");
		stage.show();
	}
	
	public void switchToFixerAffectation(ActionEvent event) {
		System.out.println("Fixer une affectation pressed");
		try {
			root = FXMLLoader.load(getClass().getResource("FixerAffectation.fxml"));
		} catch (IOException e) {
			System.err.println(e);
		}
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
        stage.setTitle("Fixer une affectation");
		stage.show();
	}

	
	private void ecrireAffectation(CalculAffectation<Etudiant> calcul) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("./res/hud/Resultat.csv"));
			StringBuilder sb = new StringBuilder();
			sb.append("nom_tuteur");
			sb.append(';');
			sb.append("prenom_tuteur");
			sb.append(';');
			sb.append("annee_etudes_tuteur");
			sb.append(';');
			sb.append("moyenne_tuteur");
			sb.append(';');
			sb.append("nb_absence_tuteur");
			sb.append(';');
			sb.append("nom_tutore");
			sb.append(';');
			sb.append("prenom_tutore");
			sb.append(';');
			sb.append("annee_etudes_tutore");
			sb.append(';');
			sb.append("moyenne_tutore");
			sb.append(';');
			sb.append("nb_absence_tutore");
			sb.append('\n');
			writer.write(sb.toString());
			for (Arete<Etudiant> a : calcul.getAffectation()) {
				Tuteur tuteur = (Tuteur) a.getExtremite1();
				Tutore tutore = (Tutore) a.getExtremite2();
				sb = new StringBuilder();
				sb.append(tuteur.getNom());
				sb.append(';');
				sb.append(tuteur.getPrenom());
				sb.append(';');
				sb.append(tuteur.getAnneeEtude());
				sb.append(';');
				sb.append(tuteur.getMoyenne());
				sb.append(';');
				sb.append(tuteur.getNbAbsences());
				sb.append(';');
				writer.write(sb.toString());
				sb = new StringBuilder();
				sb.append(tutore.getNom());
				sb.append(';');
				sb.append(tutore.getPrenom());
				sb.append(';');
				sb.append(tutore.getAnneeEtude());
				sb.append(';');
				sb.append(tutore.getMoyenne());
				sb.append(';');
				sb.append(tutore.getNbAbsences());
				sb.append('\n');
				writer.write(sb.toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void validerFixerCouple(ActionEvent event) {
//		if (fixerSelectedTuteur != null && fixerSelectedTutore != null) {
			a.forcerCouple(fixerSelectedTuteur, fixerSelectedTutore);
			switchToCreerAffectation(event);
			fixerSelectedTuteur = null;
			fixerSelectedTutore = null;
//		}
	}
	
	public void validerEjecterEtudiant(ActionEvent event) {
//		if (eviterSelectedEtu != null) {
			if (eviterSelectedEtu.getAnneeEtude() == 1) a.ejecterEtudiant((Tutore) eviterSelectedEtu);
			else a.ejecterEtudiant((Tuteur) eviterSelectedEtu);
			switchToCreerAffectation(event);
			eviterSelectedEtu = null;
//		}
	}
	
	public void validerAffectation(ActionEvent event) {
		calcul = a.genererGroupes();
		ecrireAffectation(calcul);
		switchToAfficherAffectation(event);
	}
}
