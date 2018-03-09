package ecouteurs;

import java.util.EventListener;
/**
 * Interface qui ajoute des écouteurs personnalisés pour le mode arcade
 * @author Francis Gosselin
 *
 */
public interface EcouteursModeArcade extends EventListener{
	/**
	 * Indique le stimulant restant.
	 */
	public void boost(double b);
	
}
