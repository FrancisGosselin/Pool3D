package dessin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * Créer une queue de billard en 3D
 * 
 * @author Francis Gosselin
 *
 */
public class Queue implements Comparable<Objet3D>, Objet3D{
	private double angleX;
	private double angleY;
	private double[] origine=new double[3];
	private double distanceProjection=1500;
	private double etirement=0;
	private double etirementInitial=0;

	private int nbImageAnim=40;
	private double distanceQueueBoule;
	

	private TreeSet<Objet3D> arbre=new TreeSet<Objet3D>();
	
	private double[] vecLong={0,0,0},vecLarg={0,0,0},vecRegard=new double[3];

	private double[] posCam;
	private double longeurQueue=100;
	private double rayon=1.5;

	private Color c=Color.green;
	private Color cHaut=null;
	/**
	 * Constructeur
	 * @param origX origine en X
	 * @param origY origine en Y
	 * @param origZ origine en Z
	 * @param longeur grandeur en X
	 * @param hauteur grandeur en Y
	 * @param largeur grandeur en Z
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posIni position de la caméra
	 * @param c Couleur du prisme
	 */
	public Queue(double[] origine, double angX,double angY, double[] pos,double[] vecRegard,double distanceQueueBoule,Color c){
		angleX=angX;
		angleY=angY;
		this.origine=origine;
		this.c=c;
		this.posCam=pos;
		this.vecRegard=vecRegard;
		this.distanceQueueBoule=distanceQueueBoule;
		
		calculerFace();	
	}
	
	/**
	 * Constructeur
	 * @param origX origine en X
	 * @param origY origine en Y
	 * @param origZ origine en Z
	 * @param longeur grandeur en X
	 * @param hauteur grandeur en Y
	 * @param largeur grandeur en Z
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posIni position de la caméra
	 */
	public Queue(double origX,double origY,double origZ, double angX,double angY,double[] posIni,double[] vecRegard){
		angleX=angX;
		angleY=angY;
		origine[0]=origX;
		origine[1]=origY;
		origine[2]=origZ;
		posCam=posIni;
		this.vecRegard=vecRegard;
		
		calculerFace();
	}
	/**
	 * calcule les vecteurs longeur et largeur selon l'angleX
	 */
	private void calculVecteurs(){
		double [] temp={0,-1,0};
		vecLong=Outil3D.produitVec(Outil3D.vecteurX(angleX), temp);
	
		vecLong=Outil3D.vecteurNorme(vecLong, -longeurQueue);
		vecLong[1]=10;
		
		vecLarg=Outil3D.vecteurX(angleX);
		vecLarg=Outil3D.vecteurNorme(vecLarg, rayon*2);
	}

	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	public void dessiner(Graphics2D g2d){
		
		calculerFace();
		
		
		Area a1=new Area();
		Area a2=new Area();
		arbre=getFaces();
		
		Iterator<Objet3D> b=arbre.iterator();
		a1=b.next().getArea();
		a2.add(b.next().getArea());
		
		

		double[] temp=Outil3D.additionVec(origine, Outil3D.vecteurNorme(vecLong, etirement+rayon));
		Disque d2=new Disque(Outil3D.additionVec(temp, vecLong),rayon,angleX,angleY,this.posCam,vecRegard,c,true);
		

		g2d.setColor(c);
		g2d.fill(a2);
		g2d.setColor(Color.black);
		g2d.draw(a2);
		
		g2d.setColor(c);
		g2d.fill(a1);
		
		g2d.setColor(Color.black);
		double[] pos1=Outil3D.additionVec(temp, Outil3D.scal(vecLarg, 0.5));
		double[] pos2=Outil3D.additionVec(temp, Outil3D.scal(vecLarg, -0.5));
		
		Outil3D.dessinerLigne(g2d, pos1, Outil3D.additionVec(pos1, vecLong), vecRegard, posCam, angleX, angleY);
		Outil3D.dessinerLigne(g2d, pos2, Outil3D.additionVec(pos2, vecLong), vecRegard, posCam, angleX, angleY);
		
		d2.dessiner(g2d);
		
	}



	/**
	 * change l'angle par rapport à l'axe des x
	 * @param angleX angle par rapport à l'axe des x
	 */
	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}




	/**
	 * change l'angle par rapport à l'axe des y
	 * @param angleY angle par rapport à l'axe des y
	 */
	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}


	@Override
	/**
	 * compare le prisme avec un autre objet 3D
	 * @param v le deuxième objet 3D
	 * @return -1 si l'objet comparé est situé derrière, retourne 1 sinon
	 */
	public int compareTo(Objet3D obj) {
		return 1;
		
		
	}
	
	/**
	 * retourne le milieu du prisme
	 * @param v le milieu du prisme
	 */
	public double[] getPosition(){
		return posCam;
	}
	
	/**
	 * retourne le Vecteur Regard
	 * @param v le vecteur Regard
	 */
	public double[] getVecRegard(){
		return vecRegard;
	}
	
	
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	public void setVectRegard(double[] v){
		for(int i=0;i<3;i++){
			vecRegard[i]=v[i];
		}
	}
	
	/**
	 * change la position en 3 dimension de la caméra
	 * @param v la position en 3 dimension de la caméra
	 */
	public void setPosition(double[] v){
		for(int i=0;i<3;i++){
			posCam[i]=v[i];
		}
	}
	
	/**
	 * change l'origine de la queue
	 * @param v l'origine
	 */
	public void setOrigine(double[] v){
		for(int i=0;i<3;i++){
			origine[i]=v[i];
		}
	}

	/**
	 * retourne le centre du prisme
	 * @return le centre du prisme
	 */
	public double[] getCentre() {
		double [] pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vecLarg,0.5), Outil3D.scal(vecLong,0.5)));
		return pos1;
	}

	@Override
	/**
	 * retorune l'aire des faces
	 * @return l'aire des 6 faces
	 */
	public Area getArea() {
		Area a1=new Area();
		for(Objet3D l:arbre){
			a1.add(l.getArea());
		}
		
		return a1;
	}
	
	/**
	 * calcule les 2 faces du prisme
	 */
	private void calculerFace(){
		calculVecteurs();
		double[] temp=Outil3D.additionVec(origine, Outil3D.vecteurNorme(vecLong, etirement+rayon));
		
		double[] posface2=Outil3D.additionVec(temp,Outil3D.scal(vecLarg, -0.5));
		Plan3D face2;
		face2=new Plan3D(vecLong,vecLarg,posface2,angleX,angleY,distanceProjection,posCam,vecRegard,cHaut);
		
		Disque d=new Disque(temp,rayon,angleX,angleY,this.posCam,vecRegard,c,true);
		

		arbre=new TreeSet<Objet3D>();
		arbre.add(face2);
		arbre.add(d);
	
		
	}
	
	/**
	 * Retourne les Faces que l'on peut voir du prisme
	 * @return les Faces que l'on peut voir du prisme
	 */
	public TreeSet<Objet3D> getFaces(){
	
			calculerFace();
		
		TreeSet<Objet3D> p=new TreeSet<Objet3D>();
		Iterator<Objet3D> it=arbre.iterator();
		p.add(it.next());
		p.add(it.next());

		return p;
	}

	@Override
	public boolean contient(double[] pos) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * change la position par rapport a l'origine (le point de frappe)
	 * @param les Faces que l'on peut voir du prisme
	 */
	public void setEtirement(double etirement) {
		if(etirement<=0){
			this.etirement=0;
			this.etirementInitial = 0;
		}else{
			this.etirement = etirement;
			this.etirementInitial = etirement;
		}
	}
	/**
	 * retourne la position par rapport a l'origine (le point de frappe)
	 * @return la position par rapport a la boule blanche plus son rayon (le point de frappe)
	 */
	public double getEtirementSelonBoule() {
		return etirement+distanceQueueBoule+5;
	}
	
	/**
	 * retourne la position par rapport a l'origine (le point de frappe)
	 * @return la position par rapport a l'origine (le point de frappe)
	 */
	public double getEtirement() {
		return etirement;
	}
	
	/**
	 * Affiche la prochaine image dans le déplacement de la queue
	 */
	public void prochaineImage(){
		etirement=etirement-(etirementInitial/nbImageAnim);
		nbImageAnim--;
		if(etirement==-distanceQueueBoule){
			nbImageAnim=20;
		}
	}
	/**
	 * Retourne la force la frappe sur la boule blanche
	 * @return la force de frappe
	 */
	public double[] getForceFrappe(){
		double[] force=Outil3D.copieVec(vecLong);
		force[1]=0;
		
		return Outil3D.vecteurNorme(force, -10*etirementInitial);
	}
	
	/**
	 * détermine si l'objet est comprable à un autre objet
	 * @return vrai s'il l'objet est comparable
	 */
	@Override
	public boolean estComparable(Objet3D o) {
		return false;
	}
	
}
