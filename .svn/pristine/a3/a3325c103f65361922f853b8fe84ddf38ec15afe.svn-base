package dessin;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.TreeSet;

import calculs.Physique;
/**
 * Créer une boule en 3D
 * 
 * @author Ruibin Wang
 * @author Francis Gosselin
 */
public class Boule3D implements Comparable<Objet3D>, Objet3D{
	private double[] positionCamera;
	private double[] vecRegard;
	private double[] positionCentreDeMasse;
	private Ellipse2D.Double cercle;
	private double angleX,angleY;
	private Color c = Color.green;
	private boolean estRentrer=false;
	
	//Valeurs par défaut
	private static final double DEFAULT_COEFFFROTTEMENT_BALLEBALLE = 0.055;
	private static final double DEFAULT_COEFFFROTTEMENT_GLISSE = 1.7;
	private static final double DEFAULT_COEFFFROTTEMENT_ROULE = 50;
	private static final double DEFAULT_ACCELG = 9.807;			//En mètres/s^2
	private static final double DEFAULT_RAYON = 0.05;//0.02625;			//En mètres
	private static final double DEFAULT_MASSE = 0.30;//0.15;				//En kilogrammes
	private static final double DEFAULT_TOLERANCE_COLLISION = 5;
	//Propriétés globales (pareilles pour toutes les balles)
	private static double accelG = DEFAULT_ACCELG;
	private static double coeffFrottementBalleBalle = DEFAULT_COEFFFROTTEMENT_BALLEBALLE;
	private static double coeffFrottementGlisse = DEFAULT_COEFFFROTTEMENT_GLISSE;
	private static double coeffFrottementRoule = DEFAULT_COEFFFROTTEMENT_ROULE;
	private static double toleranceCollision = DEFAULT_TOLERANCE_COLLISION;
	private double rayon = DEFAULT_RAYON;
	private static double masse = DEFAULT_MASSE;
	//Vitesses & accélérations
	private double[] vitesseLineaire = {0, 0, 0};			//En mètres/s
	private double[] vitesseAngulaire = {0, 0, 0};			//En rad/s
	private double[] vitessePeripherique = {0, 0, 0};		//En mètres/s
	private double[] accelLineaire = {0, 0, 0};				//En mètres/s^2
	private double[] accelAngulaire = {0, 0, 0};			//En rad/s^2
	private double[] accelPeripherique = {0, 0, 0};			//En mètres/s^2
	//Forces en Newtons
	private double[] forceTotale = {0, 0, 0};
	private double[] forceFriction = {0, 0, 0};
	private double[] momentDeForce = {0, 0, 0};
	private double[] forceQueue = {0, 0, 0};
	private double[] forceNormale = {0, 0, 0};
	private static double[] forceGravitationnelle = {0, 0, 0};
	private double toleranceRoule = 0.0001;

	/**
	 * Constructeur
	 * @param position position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param origine position du milieu de la boule
	 * @param rayon rayon de la boule
	 * @param angleX angle en X
	 * @param angleY angle en Y
	 */
	//Francis Gosselin
	public Boule3D(double[] position, double[] vecRegard, double[] origine, double rayon, double angleX, double angleY) {
		this.positionCamera = position;
		this.vecRegard = vecRegard;
		this.positionCentreDeMasse = origine;
		this.rayon = DEFAULT_RAYON;
		this.angleX = angleX;
		this.angleY = angleY;
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
	//Francis Gosselin
	public Boule3D(double[] position, double[] vecRegard, double[] origine, double rayon, double angleX, double angleY, Color c) {
		this.positionCamera = position;
		this.vecRegard = vecRegard;
		this.positionCentreDeMasse = origine;
		this.rayon = DEFAULT_RAYON*100;
		this.angleX = angleX;
		this.angleY = angleY;
		this.c=c;
	}
	/**
	 * Initialise les forces.
	 */
	//Ruibin Wang
	public void initialiseForces() {
		forceGravitationnelle = Physique.forceGravite();
		Physique.forceNormale(this);
		Physique.forceTotale(this);
		Physique.accelApresFrappe(this);
	}
	/**
	 * Permet d'effectuer un pas Euler.
	 * @param deltaTemps : La durée d'un pas en double.
	 */
	//Ruibin Wang
	public void unPasEuler(double deltaTemps) {
		initialiseForces();
		Physique.vitessePeripherique(this);
		Physique.forceFriction(this);
		
		vitesseLineaire = Outil3D.additionVec(vitesseLineaire, Physique.deltaVitesseLineaire(this, deltaTemps));
		
		positionCentreDeMasse = Outil3D.additionVec(positionCentreDeMasse, Outil3D.scal(vitesseLineaire, deltaTemps));
		
		if(positionCentreDeMasse[1]>220+Boule3D.getDefaultRayon()*100){
			vitesseLineaire[1]+=-accelG*20*deltaTemps;
			
		}else{
			vitesseLineaire[1]=0;
			positionCentreDeMasse[1]=220+Boule3D.getDefaultRayon()*100;
		}
	}

	/**
	 * Retourne un valeur booleen qui détermine si un point en 3D est dans la boule
	 * @param pos la position du point
	 * @return vrai si le point est dans la boule
	 */
	//Francis Gosselin
	@Override
	public boolean contient(double [] pos) {
		double distance=Outil3D.distanceEntrePos(pos, positionCentreDeMasse);
		
		return (distance-toleranceCollision)<=rayon;
	}
	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	//Francis Gosselin
	@Override
	public void dessiner(Graphics2D g2d) {
		if(!estRentrer){
			double[] check1=Outil3D.additionVec(positionCentreDeMasse, Outil3D.scal(vecRegard, -rayon));
			
			double[] check=Outil3D.vecEntrePos(positionCamera, check1);
			double[] ori=Outil3D.trouverPos(positionCentreDeMasse, positionCamera, vecRegard, angleX, angleY);
			
			
			if(Outil3D.angleEntre(check, vecRegard)==-1||(ori[0]<0.001&&ori[0]>-0.001&&ori[1]>-0.001&&ori[1]<0.001)){ 
				double rayon2=calculRayon();
				double distance=Math.sqrt(Math.pow(rayon2, 2)+Math.pow(rayon, 2));
				distance=(Outil3D.norme(Outil3D.vecEntrePos(positionCamera, positionCentreDeMasse)))-distance;
				rayon2=rayon2*1500/distance;
			
				cercle= new Ellipse2D.Double(ori[0]-rayon2, ori[1]-rayon2, rayon2*2, rayon2*2);
				
				Color temp=g2d.getColor();
				
				
				g2d.setColor(c);
				g2d.fill(cercle);
				g2d.setColor(Color.black);
				g2d.draw(cercle);
				g2d.setColor(temp);
				
				//getHitBox().dessiner(g2d);
			}
		}
	}
	/**
	 * calcul le rayon de la boule en pixels
	 * @return le rayon de la boule en pixels
	 */
	//Francis Gosselin
	private double calculRayon(){
		double ray=rayon*Math.sin(Math.acos(rayon/(Outil3D.norme(Outil3D.vecEntrePos(positionCamera, positionCentreDeMasse)))));
		return ray;
	}
	/**
	 * change la position en 3 dimension de la caméra
	 * @param v la position en 3 dimension de la caméra
	 */
	//Francis Gosselin
	@Override
	public void setPosition(double[] v) {
		for(int i=0;i<3;i++){
			positionCamera[i]=v[i];
		}
	}
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	//Francis Gosselin
	@Override
	public void setVectRegard(double[] v) {
		for(int i=0;i<3;i++){
			vecRegard[i]=v[i];
		}
	}
	/**
	 * compare la boule avec un autre objet 3D
	 * @param v le deuxième objet 3D
	 * @return -1 si l'objet comparé est situé derrière, retourne 1 sinon
	 */
	//Francis Gosselin
	@Override
	public int compareTo(Objet3D arg0) {
		if(arg0 instanceof Boule3D){
			Boule3D b=(Boule3D)arg0;	
			if(Outil3D.distanceEntrePos(positionCamera, positionCentreDeMasse)>Outil3D.distanceEntrePos(positionCamera, b.getCentre())){
				return -1;
			}else{
				return 1;
			}
		}else{
			return this.getHitBox().compareTo(arg0);		
		}
		
		
	}

	/**
	 * change l'angle par rapport à l'axe des x
	 * @param angleX angle par rapport à l'axe des x
	 */
	//Francis Gosselin
	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}

	/**
	 * change l'angle par rapport à l'axe des y
	 * @param angleY angle par rapport à l'axe des y
	 */
	//Francis Gosselin
	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}
	
	/**
	 * retourne le milieu du prisme
	 * @return le milieu du prisme
	 */
	//Francis Gosselin
	public double[] getOrigine(){
		return positionCentreDeMasse;
	}
	/**
	 * retourne le centre du cercle
	 * @return le centre du cercle
	 */
	//Francis Gosselin
	@Override
	public double[] getCentre() {
		return positionCentreDeMasse;
	}
	/**
	 * retourne l'aire de face
	 * @return l'aire de la face
	 */
	//Francis Gosselin
	public Area getArea(){
		/*
		if(cercle==null){
			double[] ori=Outil3D.trouverPos(positionCentreDeMasse, positionCamera, vecRegard, angleX, angleY);	
			double rayon2=calculRayon();
			double distance=Math.sqrt(Math.pow(rayon2, 2)+Math.pow(rayon, 2));
			distance=(Outil3D.norme(Outil3D.vecEntrePos(positionCamera, positionCentreDeMasse)))-distance;
			rayon2=rayon2*1500/distance;
		
			cercle= new Ellipse2D.Double(ori[0]-rayon2, ori[1]-rayon2, rayon2*2, rayon2*2);	
		}
		*/
		return getHitBox().getArea();
	}
	/**
	 * Retourne les Faces que l'on peut voir de l'objet
	 * @return les Faces que l'on peut voir de l'objet
	 */
	//Francis Gosselin
	public TreeSet<Objet3D> getFaces(){
		double [] vecX=Outil3D.scal(Outil3D.normaliser(Outil3D.vecteurX(angleX)), 2*rayon);
		double [] vecY=Outil3D.scal(Outil3D.normaliser(Outil3D.vecteurY(angleX, angleY)), 2*rayon);
		double [] oriTemp=Outil3D.additionVec(positionCentreDeMasse, Outil3D.scal(vecX, -0.5));
		oriTemp=Outil3D.additionVec(oriTemp, Outil3D.scal(vecY, -0.5));
		Plan3D pan=new Plan3D(vecX,vecY,oriTemp,angleX,angleY,500,positionCamera,vecRegard,c);
		TreeSet<Objet3D> p=new TreeSet<Objet3D>();
		p.add(pan);
		return p;
	}
	/**
	 * Retourne la valeur de l'accélération gravitationnelle.
	 * @return la valeur de l'accélération gravitationnelle en mètres/s^2.
	 */
	//Ruibin Wang
	public static double getAccelG() {
		return accelG;
	}
	/**
	 * Modifie la valeur de l'accélération gravitationnelle.
	 * @param accelG : La nouvelle valeur de l'accélération gravitationnelle en mètres/s^2.
	 */
	//Ruibin Wang
	public static void setAccelG(double accelG) {
		Boule3D.accelG = accelG;
	}
	/**
	 * Retourne le coefficient de frottement entre deux balles.
	 * @return le coefficient de frottement entre deux balles.
	 */
	//Ruibin Wang
	public static double getCoeffFrottementBalleBalle() {
		return coeffFrottementBalleBalle;
	}
	/**
	 * Modifie le coefficient de frottement entre deux balles.
	 * @param coeffFrottementBalleBalle : Le nouveau coefficient de frottement entre deux balles.
	 */
	//Ruibin Wang
	public static void setCoeffFrottementBalleBalle(double coeffFrottementBalleBalle) {
		Boule3D.coeffFrottementBalleBalle = coeffFrottementBalleBalle;
	}
	/**
	 * Retourne le coefficient de frottement entre une balle glissante et la surface de la table.
	 * @return le coefficient de frottement entre une balle glissante et la surface de la table.
	 */
	//Ruibin Wang
	public static double getCoeffFrottementGlisse() {
		return coeffFrottementGlisse;
	}
	/**
	 * Modifie le coefficient de frottement entre une balle glissante et la surface de la table.
	 * @param coeffFrottementGlisse : Le nouveau coefficient de frottement entre une balle glissante et la surface de la table.
	 */
	//Ruibin Wang
	public static void setCoeffFrottementGlisse(double coeffFrottementGlisse) {
		Boule3D.coeffFrottementGlisse = coeffFrottementGlisse;
	}
	/**
	 * Retourne le coefficient de frottement entre une balle roulante et la surface de la table.
	 * @return le coefficient de frottement entre une balle roulante et la surface de la table.
	 */
	//Ruibin Wang
	public static double getCoeffFrottementRoule() {
		return coeffFrottementRoule;
	}
	/**
	 * Modifie le coefficient de frottement entre une balle roulante et la surface de la table.
	 * @param coeffFrottementRoule : Le nouveau coefficient de frottement entre une balle roulante et la surface de la table.
	 */
	//Ruibin Wang
	public static void setCoeffFrottementRoule(double coeffFrottementRoule) {
		Boule3D.coeffFrottementRoule = coeffFrottementRoule;
	}
	/**
	 * Retourne la masse d'une balle.
	 * @return la masse d'une balle en kilogrammes.
	 */
	//Ruibin Wang
	public static double getMasse() {
		return masse;
	}
	/**
	 * Modifie la masse de chaque balle.
	 * @param masse : La nouvelle masse de chaque balle en kilogrammes.
	 */
	//Ruibin Wang
	public static void setMasse(double masse) {
		Boule3D.masse = masse;
	}
	/**
	 * Retourne la vitesse linéaire du centre de masse de la balle.
	 * @return Un vecteur 3d de la vitesse linéaire du centre de masse de la balle en mètres/s.
	 */
	//Ruibin Wang
	public double[] getVitesseLineaire() {
		return vitesseLineaire;
	}
	/**
	 * Modifie la vitesse linéaire du centre de masse de la balle.
	 * @param vitesseLineaire : Un vecteur 3d de la nouvelle vitesse linéaire du centre de masse de la balle en mètres/s.
	 */
	//Ruibin Wang
	public void setVitesseLineaire(double[] vitesseLineaire) {
		this.vitesseLineaire = vitesseLineaire;
	}
	/**
	 * Retourne la vitesse angulaire de la balle.
	 * @return un vecteur 3d de la vitesse angulaire de la balle en radians/s.
	 */
	//Ruibin Wang
	public double[] getVitesseAngulaire() {
		return vitesseAngulaire;
	}
	/**
	 * Modifie la vitesse angulaire de la balle.
	 * @param vitesseAngulaire : Un vecteur 3d de la nouvelle vitesse angulaire de la balle en radians/s.
	 */
	//Ruibin Wang
	public void setVitesseAngulaire(double[] vitesseAngulaire) {
		this.vitesseAngulaire = vitesseAngulaire;
	}
	/**
	 * Retourne la vitesse linéaire à la périphérie de la balle.
	 * @return un vecteur 3d de la vitesse linéaire à la périphérie de la balle en mètres/s.
	 */
	//Ruibin Wang
	public double[] getVitessePeripherique() {
		return vitessePeripherique;
	}
	/**
	 * Modifie la vitesse linéaire à la périphérie de la balle.
	 * @param vitessePeripherique : Un vecteur 3d de la nouvelle vitesse linéaire à la périphérie de la balle en mètres/s.
	 */
	//Ruibin Wang
	public void setVitessePeripherique(double[] vitessePeripherique) {
		this.vitessePeripherique = vitessePeripherique;
	}
	/**
	 * Retourne l'accélération linéaire du centre de masse de la balle.
	 * @return un vecteur 3d de l'accélération linéaire du centre de masse de la balle en mètres/s^2.
	 */
	//Ruibin Wang
	public double[] getAccelLineaire() {
		return accelLineaire;
	}
	/**
	 * Modifie l'accélération linéaire du centre de masse de la balle.
	 * @param accelLineaire : Un vecteur 3d de la nouvelle accélération linéaire du centre de masse de la balle en mètres/s^2.
	 */
	//Ruibin Wang
	public void setAccelLineaire(double[] accelLineaire) {
		this.accelLineaire = accelLineaire;
	}
	/**
	 * Retourne l'accélération angulaire de la balle.
	 * @return un vecteur 3d de l'accélération angulaire de la balle en radians/s.
	 */
	//Ruibin Wang
	public double[] getAccelAngulaire() {
		return accelAngulaire;
	}
	/**
	 * Modifie l'accélération angulaire de la balle.
	 * @param accelAngulaire : Un vecteur 3d de la nouvelle accélération angulaire de la balle en radians/s.
	 */
	//Ruibin Wang
	public void setAccelAngulaire(double[] accelAngulaire) {
		this.accelAngulaire = accelAngulaire;
	}
	/**
	 * Retourne l'accélération linéaire de la balle à la périphérie.
	 * @return un vecteur 3d de l'accélération linéaire de la balle à la périphérie en mètres/s^2.
	 */
	//Ruibin Wang
	public double[] getAccelPeripherique() {
		return accelPeripherique;
	}
	/**
	 * Modifie l'accélération linéaire de la balle à la périphérie.
	 * @param accelPeripherique : Un vecteur 3d de la nouvelle accélération linéaire de la balle à la périphérie en mètres/s^2.
	 */
	//Ruibin Wang
	public void setAccelPeripherique(double[] accelPeripherique) {
		this.accelPeripherique = accelPeripherique;
	}
	/**
	 * Retourne la somme des forces linéaires appliquées au centre de masse de la balle.
	 * @return un vecteur 3d de la somme des forces linéaires appliquées au centre de masse de la balle en Newtons.
	 */
	//Ruibin Wang
	public double[] getForceTotale() {
		return forceTotale;
	}
	/**
	 * Modifie la somme des forces linéaires appliquées au centre de masse de la balle.
	 * @param forceTotale : Un vecteur 3d de la nouvelle somme des forces linéaires appliquées au centre de masse de la balle en Newtons.
	 */
	//Ruibin Wang
	public void setForceTotale(double[] forceTotale) {
		this.forceTotale = forceTotale;
	}
	/**
	 * Retourne la force de friction appliquée à la périphérie de la balle.
	 * @return un vecteur 3d de la force de friction appliquée à la périphérie de la balle en Newtons.
	 */
	//Ruibin Wang
	public double[] getForceFriction() {
		return forceFriction;
	}
	/**
	 * Modifie la force de friction appliquée à la périphérie de la balle.
	 * @param forceFriction : Un vecteur 3d de la nouvelle force de friction appliquée à la périphérie de la balle en Newtons.
	 */
	//Ruibin Wang
	public void setForceFriction(double[] forceFriction) {
		this.forceFriction = forceFriction;
	}
	/**
	 * Retourne le moment de force subit par la balle.
	 * @return un vecteur 3d du moment de force en Newtons*mètres.
	 */
	//Ruibin Wang
	public double[] getMomentDeForce() {
		return momentDeForce;
	}
	/**
	 * Modifie le moment de force subit par la balle.
	 * @param momentDeForce : Un vecteur 3d du nouveau moment de force en Newtons*mètres.
	 */
	//Ruibin Wang
	public void setMomentDeForce(double[] momentDeForce) {
		this.momentDeForce = momentDeForce;
	}
	/**
	 * Retourne la force appliquée par la queue.
	 * @return la force appliquée par la queue en Newtons.
	 */
	//Ruibin Wang
	public double[] getForceQueue() {
		return forceQueue;
	}
	/**
	 * Modifie la force appliquée par la queue.
	 * @param forceQueue : Un vecteur 3d de la force appliquée par la queue en Newtons.
	 */
	//Ruibin Wang
	public void setForceQueue(double[] forceQueue) {
		this.forceQueue = forceQueue;
	}
	/**
	 * Retourne la force gravitationnelle.
	 * @return la force gravitationnelle dans un vecteur 3d en Newtons.
	 */
	//Ruibin Wang
	public static double[] getForceGravitationnelle() {
		return forceGravitationnelle;
	}
	/**
	 * Modifie la force gravitationnelle.
	 * @param forceGravitationnelle : Un nouveau vecteur 3d de la force gravitationnelle en Newtons.
	 */
	//Ruibin Wang
	public static void setForceGravitationnelle(double[] forceGravitationnelle) {
		Boule3D.forceGravitationnelle = forceGravitationnelle;
	}
	/**
	 * Retourne la force normale de la balle appliquée par la surface de la table.
	 * @return un vecteur 3d de la force normale de la balle appliquée par la surface de la table en Newtons.
	 */
	//Ruibin Wang
	public double[] getForceNormale() {
		return forceNormale;
	}
	/**
	 * Modifie la force normale de la balle appliquée par la surface de la table.
	 * @param forceNormale : La nouvelle force normale de la balle appliquée par la surface de la table en Newtons.
	 */
	//Ruibin Wang
	public void setForceNormale(double[] forceNormale) {
		this.forceNormale = forceNormale;
	}
	/**
	 * Retourne le vecteur position du centre de masse de la boule.
	 * @return un vecteur 3d partant de l'origine du monde vers le centre de masse de la boule en mètres.
	 */
	//Ruibin Wang
	public double[] getPositionCentreDeMasse() {
		return positionCentreDeMasse;
	}
	/**
	 * Modifie le vecteur position du centre de masse de la boule.
	 * @param positionCentreDeMasse : Un vecteur 3d partant de l'origine du monde vers la nouvelle position
	 * du centre de masse de la boule en mètres.
	 */
	//Ruibin Wang
	public void setPositionCentreDeMasse(double[] positionCentreDeMasse) {
		this.positionCentreDeMasse = positionCentreDeMasse;
	}
	/**
	 * Retourne le rayon des boules.
	 * @return le rayon des boules en mètres.
	 */
	//Ruibin Wang
	public double getRayon() {
		return rayon;
	}
	/**
	 * Modifie le rayon des boules.
	 * @param rayon : Le nouveau rayon des boules en mètres.
	 */
	//Ruibin Wang
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	/**
	 * Retourne le coefficient de frottement par défaut entre deux balles.
	 * @return le coefficient de frottement par défaut entre deux balles.
	 */
	//Ruibin Wang
	public static double getDefaultCoefffrottementBalleballe() {
		return DEFAULT_COEFFFROTTEMENT_BALLEBALLE;
	}
	/**
	 * Retourne le coefficient de frottement par défaut entre une balle glissante et la surface de la table.
	 * @return le coefficient de frottement par défaut entre une balle glissante et la surface de la table.
	 */
	//Ruibin Wang
	public static double getDefaultCoefffrottementGlisse() {
		return DEFAULT_COEFFFROTTEMENT_GLISSE;
	}
	/**
	 * Retourne le coefficient de frottement par défaut entre une balle roulante et la surface de la table.
	 * @return le coefficient de frottement par défaut entre une balle roulante et la surface de la table.
	 */
	//Ruibin Wang
	public static double getDefaultCoefffrottementRoule() {
		return DEFAULT_COEFFFROTTEMENT_ROULE;
	}
	/**
	 * Retourne l'accélération gravitationnelle par défaut.
	 * @return l'accélération gravitationnelle par défaut en mètres/s^2.
	 */
	//Ruibin Wang
	public static double getDefaultAccelG() {
		return DEFAULT_ACCELG;
	}
	/**
	 * Retourne le rayon par défaut d'une balle.
	 * @return le rayon par défaut d'une balle en mètres.
	 */
	//Ruibin Wang
	public static double getDefaultRayon() {
		return DEFAULT_RAYON;
	}
	/**
	 * Retourne la masse par défaut d'une balle.
	 * @return la masse par défaut d'une balle en kilogrammes.
	 */
	//Ruibin Wang
	public static double getDefaultMasse() {
		return DEFAULT_MASSE;
	}
	/**
	 * Retourne la tolérance par défaut lors de la gestion d'une collision.
	 * @return la tolérance par défaut lors de la gestion d'une collision en mètres.
	 */
	//Ruibin Wang
	public static double getDefaultToleranceCollision() {
		return DEFAULT_TOLERANCE_COLLISION;
	}
	/**
	 * Détermine si la balle en mouvement roule.
	 * @return true si la boule roule et false si elle glisse.
	 */
	//Ruibin Wang
	public boolean isRoule() {
		return (Outil3D.norme(vitessePeripherique)+toleranceRoule >= Outil3D.norme(vitesseAngulaire)*rayon&&
				Outil3D.norme(vitessePeripherique)-toleranceRoule <= Outil3D.norme(vitesseAngulaire)*rayon);
	}
	/**
	 * change la vitesse de la boule si elle rentre en contact avec un mur
	 * @param m le mur
	 */
	//Francis Gosselin
	public void traiterCollisionMur(Prisme3D m){
		double[] point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {-this.rayon,0,0}); 
		if(m.contient(point)){
			this.vitesseLineaire[0]=-vitesseLineaire[0];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{rayon/5.0,0,0});
		}
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {this.rayon,0,0}); 
		
		if(m.contient(point)){
			this.vitesseLineaire[0]=-vitesseLineaire[0];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{-rayon/5.0,0,0});
		}
		
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {0,0,-this.rayon}); 
		
		
		if(m.contient(point)){
			this.vitesseLineaire[2]=-vitesseLineaire[2];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{0,0,rayon/5.0});
		}
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {0,0,this.rayon}); 
		
		if(m.contient(point)){
			this.vitesseLineaire[2]=-vitesseLineaire[2];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{0,0,-rayon/5.0});
		}
	}
	
	/**
	 * change la vitesse de la boule si elle rentre en contact avec un coin
	 * @param m le coin
	 */
	//Francis Gosselin
	public void traiterCollisionMur(Coin m){
		double[] point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {-this.rayon,0,0}); 
		if(m.contient(point)){
			this.vitesseLineaire[0]=-vitesseLineaire[0];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{rayon/5.0,0,0});
		}
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {this.rayon,0,0}); 
		
		if(m.contient(point)){
			this.vitesseLineaire[0]=-vitesseLineaire[0];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{-rayon/5.0,0,0});
		}
		
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {0,0,-this.rayon}); 
		
		
		if(m.contient(point)){
			this.vitesseLineaire[2]=-vitesseLineaire[2];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{0,0,rayon/5.0});
		}
		point=Outil3D.additionVec(this.positionCentreDeMasse, new double[] {0,0,this.rayon}); 
		
		if(m.contient(point)){
			this.vitesseLineaire[2]=-vitesseLineaire[2];
			positionCentreDeMasse=Outil3D.additionVec(positionCentreDeMasse, new double[]{0,0,-rayon/5.0});
		}
	}
	
	/**
	 * détermine si la boule est rentré dans un trou spécifique
	 * @param le trou 
	 * @return vrai si la boule est rentré
	 */
	//Francis Gosselin
	public boolean estDansTrou(Disque m){
		if(this.positionCentreDeMasse[1]<=220+Boule3D.getDefaultRayon()*100&&m.contient(this.positionCentreDeMasse)&&!this.estRentrer){
			if(!c.equals(Color.white)){
				estRentrer=true;
			}
			return true;	
		}
		return false;
	}
	
	/**
	 * détermine si la boule est rentré dans un trou 
	 * @return vrai si la boule est rentrée.
	 */
	//Francis Gosselin
	public boolean estRentre(){
		return estRentrer;
	}
	
	/**
	 * Retourne un prisme qui entoure la boule
	 * @return le prisme qui entoure la boule 
	 */
	//Francis Gosselin
	public Prisme3D getHitBox(){
		double[] temp=Outil3D.copieVec(this.positionCentreDeMasse);
		temp=Outil3D.additionVec(temp, new double[]{-rayon*0.9,0,0});
		temp=Outil3D.additionVec(temp, new double[]{0,-rayon*0.9,0});	
		temp=Outil3D.additionVec(temp, new double[]{0,0,-rayon*0.9});
		Prisme3D p=new Prisme3D(temp[0],temp[1],temp[2],2*rayon*0.9,2*rayon*0.9,2*rayon*0.9,angleX,angleY,this.positionCamera,c);
		p.setVectRegard(vecRegard);
		return p;
		
	}
	/**
	 * Détermine si une boule est égale à une autre.
	 * @param b : L'autre boule.
	 * @return true si les boules sont égales, false sinon.
	 */
	//Francis Gosselin
	public boolean equals(Boule3D b){
		double [] b1=b.getPositionCentreDeMasse();
		for(int k=0;k<3;k++){
			if(this.positionCentreDeMasse[k]!=b1[k]){
				return false;
			}
		}
		return true;

	}
	
	/**
	 * Détermine si la boule doit être visible ou non
	 * @param c vrai si elle doit être visible
	 */
	//Francis
	public void setVisible(boolean c){
		this.estRentrer=c;
	}
	
	/**
	 * Détermine si la boule est visible dans l'écran
	 * @param xMin minimum x sur l'écran
	 * @param yMin minimum y sur l'écran
	 * @param xMax Maximun x sur l'écran
	 * @param yMax Maximum y sur l'écran
	 * @return retourne vrai si la boule est visible sur l'écran
	 */
	//Francis Gosselin
	public boolean isVisible(double xMin,double yMin,double xMax,double yMax){
		
		
		double[] ori=Outil3D.trouverPos(positionCentreDeMasse, positionCamera, vecRegard, angleX, angleY);
		if(ori[0]<xMin||ori[0]>xMax||ori[1]<yMin||ori[1]>yMax){
			return false;
		}
		return true;
	}
	
	/**
	 * Détermine si la boule est blanche
	 * @return vrai si la boule est blanche
	 */
	//Francis Gosselin
	public boolean isWhite(){
		if(c.equals(Color.white)){
			return true;
		}
		return false;
	}
	
	/**
	 * Retourne la couleur de la boule
	 * @return la couleur de la boule
	 */
	//Francis Gosselin
	public Color getCouleur(){
		return c;
	}
	
	/**
	 * détermine si l'objet est comprable à un autre objet
	 * @return vrai s'il l'objet est comparable
	 */
	//Francis
	@Override
	public boolean estComparable(Objet3D o) {
		Area a1=getArea();
		Area a2=o.getArea();
		a1.intersect(a2);
		
		return !a1.isEmpty();
	}

}
