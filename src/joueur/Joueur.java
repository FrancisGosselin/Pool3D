package joueur;

import java.awt.Color;
/**
 * Créer un joueur
 * 
 * @author Francis Gosselin
 *
 */
public class Joueur {
	private Color c;
	private String nom;
	private Color typeBoule;
	/**
	 * Constructeur.
	 * @param q couleur de la queue du joueur
	 * @param nom nom du joueur
	 */
	public Joueur(Color q, String nom) {
		super();
		this.c = q;
		this.nom = nom;
	}
	
	/**
	 * Constructeur.
	 * @param q couleur de la queue du joueur
	 */
	public Joueur(Color q) {
		super();
		this.c = q;
	}
	
	/**
	 * retourne la couleur de la queue
	 * @return la couleur de la queue
	 */
	public Color getCouleur() {
		return c;
	}
	
	/**
	 * retourne le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * retourne la couleur de balle que le joueur doit rentrer
	 * @return la couleur des boules du joueur
	 */
	public Color getTypeBoule() {
		return typeBoule;
	}

	/**
	 * Change la couleur de boule que le joueur doit rentrer
	 * @param typeBoule la couleur des boules
	 */
	public void setTypeBoule(Color typeBoule) {
		this.typeBoule = typeBoule;
	}
	
	/**
	 * Détermine si un joueur est équivalent à un autre
	 * @return vrai si les joueurs sont pareils
	 */
	public boolean equals(Joueur j2){
		return nom.equals(j2.getNom());
	}
	
	
}
