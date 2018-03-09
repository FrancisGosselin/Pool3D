package calculs;

import dessin.Boule3D;
import dessin.Outil3D;
/**
 * Classe pour effectuer les calculs physiques.
 * @author Ruibin Wang
 *
 */
public class Physique {
	private static double tempsDeContactQueueBoule = 0.1;
	/**
	 * Calcule l'accélération linéaire immédiatement après l'impact de la queue sur une boule, la retourne dans
	 * un vecteur 3d et met à jour l'accélération linéaire de la boule.
	 * @param boule : La boule sur laquelle la force de la queue est appliquée.
	 * @return un vecteur 3d de l'accélération linéaire de la boule après l'impact de la queue en mètres/s.
	 */
	public static double[] accelApresFrappe(Boule3D boule) {
		double[] direction = Outil3D.normaliser(boule.getForceTotale());
		double module = Outil3D.norme(boule.getForceTotale())/Boule3D.getMasse();
		
		boule.setAccelLineaire(Outil3D.scal(direction, module));
		return boule.getAccelLineaire();
	}
	/**
	 * Calcule la force totale agissant sur le centre de masse de la boule.
	 * @param boule : L'objet boule.
	 * @return Le vecteur de la somme des forces en Newtons.
	 */
	public static double[] forceTotale(Boule3D boule) {
		double[] forceTotale = Outil3D.additionVec(Outil3D.additionVec(boule.getForceQueue(), boule.getForceNormale()), Boule3D.getForceGravitationnelle());
		boule.setForceTotale(forceTotale);
		return forceTotale;
	}
	/**
	 * Calcule la vitesse linéaire immédiatement après l'impact de la queue sur une boule, la retourne dans
	 * un vecteur 3d et met à jour la vitesse linéaire de la boule.
	 * @param boule : La boule sur laquelle la force de la queue est appliquée.
	 * @return un vecteur 3d de la vitesse linéaire de la boule après l'impact de la queue en mètres/s.
	 */
	public static double[] vitesseApresFrappe(Boule3D boule) {
		double[] direction = Outil3D.normaliser(boule.getForceTotale());
		double module = Outil3D.norme(boule.getForceQueue())*tempsDeContactQueueBoule/Boule3D.getMasse();
		
		boule.setVitesseLineaire(Outil3D.scal(direction, module));
		return boule.getVitesseLineaire();
	}
	/**
	 * Calcule la force gravitationnelle agissant sur les boules, le retourne dans un vecteur 3d.
	 * @return un vecteur 3d de la force gravitationnelle agissant sur les boules en Newtons.
	 */
	public static double[] forceGravite() {
		double[] forceGravite = Outil3D.scal(new double[] {0, -1, 0}, Boule3D.getMasse()*Boule3D.getAccelG());
		return forceGravite;
	}
	/**
	 * Calcule la force normale agissant sur une boule, le retourne dans un vecteur 3d et met à jour
	 * la force normale de la boule.
	 * @param boule : L'objet boule.
	 * @return un vecteur 3d de la force normale agissant sur les boules en Newtons.
	 */
	public static double[] forceNormale(Boule3D boule) {
		double[] direction = {0, 1, 0};
		double module = -Outil3D.additionVec(boule.getForceQueue(), Boule3D.getForceGravitationnelle())[1];
		
		boule.setForceNormale(Outil3D.scal(direction, module));
		return boule.getForceNormale();
	}
	/**
	 * Calcule le moment de force appliqué à une boule après l'impact de la queue, le retourne dans un vecteur 3d
	 * et met à jour le moment de force de la boule.
	 * @param forceQueue : Un vecteur 3d de la force appliquée par la queue sur la boule en Newtons.
	 * @param boule : L'objet boule.
	 * @return le moment de force dans un vecteur 3d en Newtons-mètres.
	 */
	public static double[] momentDeForce(Boule3D boule) {
		double[] directionPosImpact = Outil3D.normaliser(boule.getForceTotale());
		double modulePosImpact = boule.getRayon();
		double[] positionImpact = Outil3D.scal(directionPosImpact, -modulePosImpact);
		
		boule.setMomentDeForce(Outil3D.produitVec(positionImpact, boule.getForceTotale()));
		return boule.getMomentDeForce();
	}
	/**
	 * Calcule l'accélération angulaire appliquée à une boule après l'impact de la queue, la retourne dans un vecteur 3d
	 * et met à jour l'accélération angulaire de la boule.
	 * @param boule : L'objet boule.
	 */
	public static double[] accelAngulaire(Boule3D boule) {
		return Outil3D.scal(momentDeForce(boule), (5./2.)/(Boule3D.getMasse()*boule.getRayon()*boule.getRayon()));
	}
	/**
	 * Calcule la vitesse angulaire appliquée à une boule après l'impact de la queue, la retourne dans un vecteur 3d
	 * et met à jour la vitesse angulaire de la boule.
	 * @param boule : L'objet boule.
	 */
	public static double[] vitesseAngulaire(Boule3D boule) {
		return Outil3D.scal(accelAngulaire(boule), tempsDeContactQueueBoule);
	}
	/**
	 * Calcule la force de friction au périmètre de la boule, le retourne dans un vecteur 3d et met à jour
	 * la force de friction de la boule.
	 * @param boule : L'objet boule.
	 * @return un vecteur 3d de la force de friction au périmètre de la boule en Newtons.
	 */
	public static double[] forceFriction(Boule3D boule) {
		double coeffFriction = boule.isRoule()?Boule3D.getCoeffFrottementRoule():Boule3D.getCoeffFrottementGlisse();
		double module = coeffFriction*Outil3D.norme(boule.getForceNormale());
		double[] direction = Outil3D.scal(Outil3D.normaliser(boule.getVitessePeripherique()), -1);
		boule.setForceFriction(Outil3D.scal(direction, module));

		return boule.getForceFriction();
	}
	/**
	 * Calcule la vitesse linéaire à la périphérie de la boule, le retourne dans un vecteur 3d et met à jour
	 * la vitesse périphérique de la boule.
	 * @param boule : L'objet boule.
	 * @return un vecteur 3d de la vitesse périphérique de la boule en mètres/s.
	 */
	public static double[] vitessePeripherique(Boule3D boule) {
		double[] vp = Outil3D.additionVec(Outil3D.produitVec(boule.getVitesseAngulaire(), new double[] {0, -boule.getRayon(), 0}), boule.getVitesseLineaire());
		boule.setVitessePeripherique(vp);
		return vp;
	}
	
	/**
	 * Calcule le changement de la vitesse linéaire d'une boule après un intervalle de temps.
	 * @param boule : L'objet boule.
	 * @param deltaTemps : L'intervalle de temps en secondes.
	 * @return un vecteur 3d du changement de la vitesse linéaire après l'intervalle de temps donné en mètres/s.
	 */
	public static double[] deltaVitesseLineaire(Boule3D boule, double deltaTemps) {
		double deltaVitesseModule = Outil3D.norme(boule.getForceFriction())*deltaTemps/Boule3D.getMasse();
		double[] deltaVitesseDirection = Outil3D.normaliser(boule.getForceFriction());
		double[] deltaVitesse = Outil3D.scal(deltaVitesseDirection, deltaVitesseModule);
		
		//boule.setVitesseLineaire(Outil3D.additionVec(boule.getVitesseLineaire(), deltaVitesse));
		
		return deltaVitesse;
	}
	/**
	 * Calcule le changement de la vitesse angulaire d'une boule après un intervalle de temps.
	 * @param boule : L'objet boule.
	 * @param deltaTemps : L'intervalle de temps en secondes.
	 * @return un vecteur 3d du changement de la vitesse angulaire après l'intervalle de temps donné en radians/s.
	 */
	public static double[] deltaVitesseAngulaire(Boule3D boule, double deltaTemps) {
		double[] r = {0, -boule.getRayon(), 0};
		double[] deltaVitesseAngulaire = Outil3D.scal(Outil3D.produitVec(r, boule.getForceFriction()), (5./2.)*deltaTemps/(Boule3D.getMasse()*boule.getRayon()));
		
		boule.setVitesseAngulaire(Outil3D.additionVec(boule.getVitesseAngulaire(), deltaVitesseAngulaire));
		return deltaVitesseAngulaire;
	}
	
}
