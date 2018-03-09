package ecouteurs;

import java.util.EventListener;
/**
 * Interface qui ajoute des écouteurs personnalisés pour la gestion des fenêtres.
 * @author Ruibin Wang
 *
 */
public interface EcouteursPersoFenetres extends EventListener{
	/**
	 * Indique la volonté de retourner au menu principal.
	 */
	public void retourAuMenuListener();

}
