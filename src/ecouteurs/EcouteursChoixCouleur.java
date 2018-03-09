package ecouteurs;

import java.awt.Color;
import java.util.EventListener;
	/**
	 * Interface qui d�termine les �couteus du choix de couleur de la queue
	 * @author Francis Gosselin
	 */
public interface EcouteursChoixCouleur extends EventListener{
	/**
	 * d�termine la couleur choisie par l'utilisateur
	 * @param couleurChoisie la couleur choisie
	 */
	public void choixCouleur(Color couleurChoisie);
	
}
