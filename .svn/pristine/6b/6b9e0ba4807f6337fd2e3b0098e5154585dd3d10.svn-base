package classement;
/**
 * Une entrée dans le classement des scores.
 * @author Ruibin Wang
 *
 */
public class ElementLeaderboard implements Comparable<ElementLeaderboard>{
	private int score;
	private String nom;
	private String date;
	/**
	 * Constructeur par defaut.
	 */
	public ElementLeaderboard() {}
	/**
	 * Constructeur complet.
	 */
	public ElementLeaderboard(int score, String nom, String date) {
		this.score = score;
		this.nom = nom;
		this.date = date;
	}
	
	/**
	 * Méthode de comparaison.
	 */
	@Override
	public int compareTo(ElementLeaderboard elem) {
		if (this.score > elem.getScore()) {
			return 1;
		}
		if (this.score == elem.getScore()) {
			return 0;
		}
		return -1;
	}

	/**
	 * Retourne le score
	 * @return le score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Modifie le score
	 * @param score : Le nouveau score.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Retourne le nom.
	 * @return le nom.
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Modifie le nom.
	 * @param nom : Le nouveau nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Retourne la date
	 * @return la date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Modifie la date.
	 * @param date : La nouvelle date.
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
