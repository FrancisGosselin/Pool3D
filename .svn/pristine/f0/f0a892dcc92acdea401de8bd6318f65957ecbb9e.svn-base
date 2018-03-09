package dessin;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;

import java.util.Iterator;
import java.util.TreeSet;
/**
 * Créer un coin de table de billard en 3D
 * 
 * @author Francis Gosselin
 *
 */
public class Coin implements Comparable<Objet3D>, Objet3D {
	private double angleX;
	private double angleY;
	private double[] origine=new double[3];
	private double distanceProjection=500;
	private double rayon=10;
	private double[] posTrou;
	

	private TreeSet<Objet3D> arbre=new TreeSet<Objet3D>();
	
	private double[] vecY={0,0,0},vecZ={0,0,0},vecX={0,0,0},vect4=new double[3];

	private double[] posCam;

	private Color c=Color.green;
	
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
	 * @param pos position de la caméra
	 * @param c Couleur du prisme
	 */
	public Coin(double origX,double origY,double origZ,double longeur,double hauteur,double largeur,double rayon, double angX,double angY, double[] pos,Color c){
		angleX=angX;
		angleY=angY;
		origine[0]=origX;
		origine[1]=origY;
		origine[2]=origZ;
		vecY[1]=hauteur;
		vecZ[2]=largeur;
		vecX[0]=longeur;
		this.c=c;
		this.posCam=pos;
		this.rayon=rayon;
		this.posTrou=Outil3D.copieVec(origine);
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
	 * @param c Couleur du prisme
	 * @param posTrou position du trou
	 * @aram posSortie la position de la sortie 
	 */
	public Coin(double origX,double origY,double origZ,double longeur,double hauteur,double largeur,double rayon, double angX,double angY, double[] pos,double[] posTrou,Color c){
		angleX=angX;
		angleY=angY;
		origine[0]=origX;
		origine[1]=origY;
		origine[2]=origZ;
		vecY[1]=hauteur;
		vecZ[2]=largeur;
		vecX[0]=longeur;
		this.c=c;
		this.posCam=pos;
		this.rayon=rayon;
		this.posTrou=posTrou;

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
	public Coin(double origX,double origY,double origZ,double longeur,double hauteur,double largeur, double angX,double angY,double[] posIni){
		angleX=angX;
		angleY=angY;
		origine[0]=origX;
		origine[1]=origY;
		origine[2]=origZ;
		vecY[1]=hauteur;
		vecZ[2]=largeur;
		vecX[0]=longeur;
		posCam=posIni;
		calculerFace();
	}

	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	public void dessiner(Graphics2D g2d){
		
		calculerFace();

		arbre=getFaces();
		for(Objet3D l:arbre){
			Plan3D p=(Plan3D)l;
			p.dessiner(g2d);
		}

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
		
			return getHitBox().compareTo(obj);

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
		return vect4;
	}
	
	
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	public void setVectRegard(double[] v){
		for(int i=0;i<3;i++){
			vect4[i]=v[i];
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
	 * retourne le centre du prisme
	 * @return le centre du prisme
	 */
	public double[] getCentre() {
		double [] pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vecY,0.5), Outil3D.scal(vecZ,0.5)));
		return pos1;
	}

	@Override
	/**
	 * retorune l'aire des faces
	 * @return l'aire des 6 faces
	 */
	public Area getArea() {
		
		return getHitBox().getArea();
	}
	
	/**
	 * calcule les 6 faces du prisme
	 */
	private void calculerFace(){
		double[] posface3=Outil3D.copieVec(origine);
		
		
		double[] posface2=Outil3D.copieVec(origine);
		Plan3D face5=new Plan3D(vecZ,vecX,posface2,angleX,angleY,distanceProjection,posCam,vect4,c);
		posface2=Outil3D.additionVec(posface2,vecY);
		Plan3D face2=new Plan3D(vecZ,vecX,posface2,angleX,angleY,distanceProjection,posCam,vect4,c);
		if(posTrou==null){
			face2.ajouterTrou(rayon);
			face5.ajouterTrou(rayon);
		}else{
			face2.ajouterTrou(rayon,Outil3D.additionVec(posTrou, vecY));
			face5.ajouterTrou(rayon,posTrou);
		}
		
		
		
		
	
		posface3=Outil3D.additionVec(posface3,vecZ);
		Plan3D face3=new Plan3D(vecX,vecY,posface3,angleX,angleY,distanceProjection,posCam,vect4,c);
		
		
		double[] posface=Outil3D.copieVec(origine);
		posface=Outil3D.additionVec(posface,vecX);
		
		Plan3D face=new Plan3D(vecZ,vecY,posface,angleX,angleY,distanceProjection,posCam,vect4,c);
		
		
		
		arbre=new TreeSet<Objet3D>();
		arbre.add(face);
		arbre.add(face2);
		arbre.add(face3);
		
		
		
		
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
		p.add(it.next());
		return p;
	}

	@Override
	/**
	 * Détermine si un point est dans le coin ou non
	 * @param vrai si le point est dans le coin
	 */
	public boolean contient(double[] pos) {
		if(pos[0]>=origine[0]&&pos[0]<=(origine[0]+vecX[0])&&(pos[1]>=origine[1]&&pos[1]<=(origine[1]+vecY[1]))&&(pos[2]>=origine[2]&&pos[2]<=(origine[2]+vecZ[2]))){
			
			double [] temp1=Outil3D.copieVec(posTrou);
			double[] temp2=Outil3D.copieVec(pos);
			temp1[1]=0;
			temp2[1]=0;
			if(Outil3D.norme(Outil3D.vecEntrePos(temp1, temp2))<=rayon){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * détermine si l'objet est comprable à un autre objet
	 * @return vrai s'il l'objet est comparable
	 */
	@Override
	public boolean estComparable(Objet3D o) {
		Area a1=getArea();
		Area a2=o.getArea();
		a1.intersect(a2);
		
		return !a1.isEmpty();
		
	}
	
	/**
	 * retourne le prisme qui entoure le coin
	 * @return le prisme qui entoure le coin
	 */
	public Prisme3D getHitBox(){
		Prisme3D p=new Prisme3D(origine[0],origine[1],origine[2],vecX[0],vecY[1],vecZ[2],angleX,angleY,posCam,Color.green);
		p.setVectRegard(vect4);
		return p;
	}

	
}
