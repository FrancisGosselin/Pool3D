package ecouteurs;

import java.awt.Color;
import java.util.EventListener;
/**
 * Interface qui ajoute des ecouteurs personnalises pour la gestion de l'animation et de l'interface utilisateur.
 * @author Ruibin Wang
 *
 */
public interface EcouteursPerso extends EventListener{
	/**
	 * Indique que la scène s'est immobilisé.
	 * @param distanceParcourue : La distance parcourue par la boule blanche en mètres.
	 */
	public void immobilisationListener();
	
	/**
	 * Indique qu'un changement de certaines valeurs a eu lieu dans la simulation.
	 * @param tempsEcoule : Le temps de simulation écoulé depuis le début.
	 * @param vitesse : La vitesse de la balle blanche en m/s.
	 * @param distanceParcourue : La distance parcourue par la boule blanche en mètres.
	 */
	public void valeursChangeListener(double tempsEcoule, double vitesse, double distanceParcourue);
	
	/**
	 * Indique qu'une boule a été rentrée.
	 * @param isBlanche : true si la balle rentrée est blanche.
	 */
	public void uneBouleEstRentree(boolean isBlanche);
	
	/**
	 * Indique que la balle blanche a été frappée.
	 * @param force : Le module de la force de frappe.
	 * @param accel : Le module de l'accélération initiale.
	 */
	public void frappeListener(double force, double accel);
	/**
	 * Indique que la première boule dans le jeu est rentrée
	 */
	public void bouleEstRentree(Color c);
	
	
}
