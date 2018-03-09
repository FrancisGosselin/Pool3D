package dessin;

import java.awt.Color;

import calculs.Physique;
/**
 * Crée la boule blanche.
 * @author Ruibin Wang
 *
 */
public class BouleBlanche3D extends Boule3D {
	private double distanceParcourue = 0;
	
	/**
	 * Constructeur
	 * @param position position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param origine position du milieu de la boule
	 * @param rayon rayon de la boule
	 * @param angleX angle en X
	 * @param angleY angle en Y
	 */
	public BouleBlanche3D(double[] position, double[] vecRegard, double[] origine, double rayon, double angleX, double angleY) {
		super(position, vecRegard, origine, rayon, angleX, angleY);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur
	 * @param position position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param origine position du milieu de la boule
	 * @param rayon rayon de la boule
	 * @param angleX angle en X
	 * @param angleY angle en Y
	 * @param c Couleur de la boule
	 */
	public BouleBlanche3D(double[] position, double[] vecRegard, double[] origine, double rayon, double angleX, double angleY, Color c) {
		super(position, vecRegard, origine, rayon, angleX, angleY, c);
	}
	
	/**
	 * Permet d'effectuer un pas Euler.
	 * @param deltaTemps : La durée d'un pas en double.
	 */
	public void unPasEuler(double deltaTemps) {
		initialiseForces();
		Physique.vitessePeripherique(this);
		Physique.forceFriction(this);
		
		setVitesseLineaire(Outil3D.additionVec(getVitesseLineaire(), Physique.deltaVitesseLineaire(this, deltaTemps)));
		
		setPositionCentreDeMasse(Outil3D.additionVec(getPositionCentreDeMasse(), Outil3D.scal(getVitesseLineaire(), deltaTemps)));
		
		if(getPositionCentreDeMasse()[1]>220+Boule3D.getDefaultRayon()*100){
			setVitesseLineaire(Outil3D.additionVec(getVitesseLineaire(), new double[] {0, -getAccelG()*20*deltaTemps, 0}));
		}else{
			setVitesseLineaire(new double[] {getVitesseLineaire()[0], 0, getVitesseLineaire()[2]});
			setPositionCentreDeMasse(new double[] {getPositionCentreDeMasse()[0], 220+Boule3D.getDefaultRayon()*100, getPositionCentreDeMasse()[2]});
		}
		distanceParcourue += Outil3D.norme(Outil3D.scal(getVitesseLineaire(), deltaTemps));
	}
	
	/**
	 * Retourne la distance parcourue
	 * @return la distance parcourue
	 */
	public double getDistanceParcourue() {
		return distanceParcourue;
	}
	
	/**
	 * Change la distance parcourue
	 * @param distanceParcourue la distance parcourue
	 */
	public void setDistanceParcourue(double distanceParcourue) {
		this.distanceParcourue = distanceParcourue;
	}
}
