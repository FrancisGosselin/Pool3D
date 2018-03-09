package ecouteurs;

import java.util.EventListener;
/**
 * Interface qui ajoute des �couteurs personnalis�s pour la gestion des fen�tres.
 * @author Ruibin Wang
 *
 */
public interface EcouteursPersoFenetres extends EventListener{
	/**
	 * Indique la volont� de retourner au menu principal.
	 */
	public void retourAuMenuListener();

}
