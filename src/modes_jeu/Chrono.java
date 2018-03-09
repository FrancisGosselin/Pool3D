package modes_jeu;

import javax.swing.JLabel;
import javax.swing.event.EventListenerList;

import ecouteurs.EcouteursChrono;
/**
 * Un compte � rebours qui d�bute avec 4 minutes.
 * @author Ruibin Wang
 *
 *
 */
public class Chrono extends JLabel implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean enMarche = false;
	private long tempsDebut;
	private final long DEFAULT_DUREE = 240000;
	private long duree = DEFAULT_DUREE;
	private long tempsRestant = duree;
	
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	
	/**
	 * Constructeur.
	 * @param string : Texte initial.
	 */
	public Chrono(String string) {
		super(string);
	}
	/**
	 * D�marre le compte � rebours.
	 */
	public void commence() {
		if (!enMarche) {
			enMarche = true;
			tempsDebut = System.currentTimeMillis();
			(new Thread(this)).start();
		}
	}
	/**
	 * Met en pause le compte � rebours.
	 */
	public void arreter() {
		enMarche = false;
		duree = tempsRestant;
	}
	/**
	 * M�thode d'animation.
	 */
	@Override
	public void run() {
		while (enMarche) {
			tempsRestant = duree - (System.currentTimeMillis()-tempsDebut);
			this.setText(getMinRestantes() + ":" + ((getSecRestantes() < 10)?"0":"") + getSecRestantes());
				
			if (getMinRestantes() == 0) {
				if (getSecRestantes() <= 0) {
					arreter();
					leverPartieTerminee();
					duree = DEFAULT_DUREE;
				}
			}
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Retourne le nombre de secondes restantes.
	 * @return un entier indiquant le nombre de secondes restantes.
	 */
	public int getSecRestantes() {
		return ((int)tempsRestant/1000)%60;
	}
	/**
	 * Retourne le nombre de minutes restantes.
	 * @return un entier indiquant le nombre de minutes restantes.
	 */
	public int getMinRestantes() {
		return ((int)tempsRestant/1000)/60;
	}
	/**
	 * Modifie le temps restant.
	 * @param millis : Le nouveau temps en millisecondes.
	 */
	public void setTempsMillis(long millis) {
		duree = duree -(tempsRestant-millis);
		tempsRestant = millis;
		
		if (tempsRestant <= 0) {
			leverPartieTerminee();
			arreter();
		}
		this.setText(getMinRestantes() + ":" + ((getSecRestantes() < 10)?"0":"") + getSecRestantes());
		System.out.println(getMinRestantes() + ":" + ((getSecRestantes() < 10)?"0":"") + getSecRestantes());
	}
	/**
	 * Retourne le temps restant.
	 * @return le temps restant en millisecondes.
	 */
	public long getTempsMillis() {
		return tempsRestant;
	}
	/**
	 * R�initialise le chronom�tre.
	 */
	public void reset() {
		arreter();
		duree = DEFAULT_DUREE;
		tempsRestant = duree;
		this.setText(getMinRestantes() + ":" + ((getSecRestantes() < 10)?"0":"") + getSecRestantes());
	}
	/**
	 * Ajoute les �couteurs personnalis�s.
	 * @param ecouteursChrono : Les �couteurs personnalis�s de type EcouteursChrono.
	 */
	public void addEcouteursPerso(EcouteursChrono ecouteursChrono) {
	    OBJETS_ENREGISTRES.add(EcouteursChrono.class, ecouteursChrono);
	}
	/**
	 * L�ve un �v�nement lorsque la partie est termin�e.
	 */
	private void leverPartieTerminee() {
		for (EcouteursChrono ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursChrono.class)) {
			ecoute.partieTermineeListener();
		}
	}
}
