package dessin;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;

import java.util.Iterator;
import java.util.TreeSet;
/**
 * Créer une prisme de 6 faces parralèle en 3D
 * 
 * @author Francis Gosselin
 *
 */
public class Prisme3D implements Comparable<Objet3D>, Objet3D{
	private double angleX;
	private double angleY;
	private double[] origine=new double[3];
	private double distanceProjection=1500;

	private TreeSet<Objet3D> arbre=new TreeSet<Objet3D>();
	
	private double[] vecY={0,0,0},vecZ={0,0,0},vecX={0,0,0},vecRegard=new double[3];

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
	 * @param posIni position de la caméra
	 * @param c Couleur du prisme
	 */
	public Prisme3D(double origX,double origY,double origZ,double longeur,double hauteur,double largeur, double angX,double angY, double[] pos,Color c){
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
	public Prisme3D(double origX,double origY,double origZ,double longeur,double hauteur,double largeur, double angX,double angY,double[] posIni){
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
	 * Retourne l'origine du prisme
	 * @return l'origine
	 */
	public double[] getOrigine() {
		return origine;
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

	
	/**
	 * compare le prisme avec un autre objet 3D
	 * @param v le deuxième objet 3D
	 * @return -1 si l'objet comparé est situé derrière, retourne 1 sinon
	 */
	private int comparePrisme(Objet3D obj) {

		TreeSet<Objet3D> a=getFaces();
		int k=0;
		for(Objet3D p:a){
			Plan3D s=(Plan3D)p;
			for(Objet3D l:obj.getFaces()){
				
				Plan3D q=(Plan3D)l;
				if(q.intersecte(s)){
						k=k+q.compareTo(s);
				}
			
			}
		}
		if(k==0){
			double[] pos1,pos2;
			pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vecY,0.5), Outil3D.scal(vecZ,0.5)));
			pos2=obj.getCentre();
			if(Outil3D.distanceEntrePos(posCam, pos1)>Outil3D.distanceEntrePos(posCam, pos2)){
				
				return -1;
			}else{
				
				return 1;
			}
		}
		return -k/Math.abs(k);
	}
	
	
	@Override
	/**
	 * compare le prisme avec un autre objet 3D
	 * @param v le deuxième objet 3D
	 * @return -1 si l'objet comparé est situé derrière, retourne 1 sinon
	 */
	public int compareTo(Objet3D obj) {
		
		if(obj.getClass().equals(Coin.class)||obj.getClass().equals(Prisme3D.class)){
				
			
			return comparePrisme(obj);
			
		}else{
			if(obj instanceof Boule3D){
				Boule3D b=(Boule3D)obj;
				return comparePrisme(b.getHitBox());
				
			}else{
				if(obj.getClass().equals(Disque.class)){
					return 1;
				}
				double[] pos1,pos2;
				pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vecY,0.5), Outil3D.scal(vecZ,0.5)));
				pos2=obj.getCentre();
				if(Outil3D.distanceEntrePos(posCam, pos1)>Outil3D.distanceEntrePos(posCam, pos2)){
					//System.out.println(pan.nom+" bat "+nom);
					return -1;
				}else{
					
					return 1;
				}
			}
			
			
		}
		
		
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
	 * retourne le centre du prisme
	 * @return le centre du prisme
	 */
	public double[] getCentre() {
		double [] pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.additionVec(Outil3D.scal(vecY,0.5),Outil3D.scal(vecX,0.5)), Outil3D.scal(vecZ,0.5)));
		return pos1;
	}

	@Override
	/**
	 * retorune l'aire des faces
	 * @return l'aire des 6 faces
	 */
	public Area getArea() {
		TreeSet<Objet3D> o=getFaces();
		Area a=new Area();
		for(Objet3D p:o){
			a.add(p.getArea());
		}
		
		
		return a;
	}
	
	/**
	 * calcule les 6 faces du prisme
	 */
	private void calculerFace(){
		double[] posface3=Outil3D.copieVec(origine);
		Plan3D face6=new Plan3D(vecX,vecY,posface3,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		posface3=Outil3D.additionVec(posface3,vecZ);
		Plan3D face3=new Plan3D(vecX,vecY,posface3,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		
		double[] posface=Outil3D.copieVec(origine);
		Plan3D face4=new Plan3D(vecZ,vecY,posface,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		posface=Outil3D.additionVec(posface,vecX);
		
		Plan3D face=new Plan3D(vecZ,vecY,posface,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		
		
		double[] posface2=Outil3D.copieVec(origine);
		Plan3D face5=new Plan3D(vecZ,vecX,posface2,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		posface2=Outil3D.additionVec(posface2,vecY);
		Plan3D face2;
		
		face2=new Plan3D(vecZ,vecX,posface2,angleX,angleY,distanceProjection,posCam,vecRegard,c);
		
		
		
		
		arbre=new TreeSet<Objet3D>();
		arbre.add(face);
		arbre.add(face2);
		arbre.add(face3);
		arbre.add(face4);
		arbre.add(face5);
		arbre.add(face6);
		
	}
	
	/**
	 * Retourne les Faces que l'on peut voir du prisme
	 * @return les Faces que l'on peut voir du prisme
	 */
	public TreeSet<Objet3D> getFaces(){
	
			calculerFace();
		
		TreeSet<Objet3D> p=new TreeSet<Objet3D>();
		Iterator<Objet3D> it=arbre.iterator();
		it.next();
		it.next();
		it.next();
		p.add(it.next());
		p.add(it.next());
		p.add(it.next());
		return p;
	}
	/**
	 * Retourne tous les faces du prisme
	 * @return les Faces du prisme
	 */
	public TreeSet<Objet3D> getAllFaces(){
	
			calculerFace();
		
		TreeSet<Objet3D> p=new TreeSet<Objet3D>();
		Iterator<Objet3D> it=arbre.iterator();
		p.add(it.next());
		p.add(it.next());
		p.add(it.next());
		p.add(it.next());
		p.add(it.next());
		p.add(it.next());
		return p;
	}
	
	/**
	 * Détermine si un point est dans le prisme ou non
	 * @param pos la position du point
	 * @return vrai si le point est dans le prisme
	 */
	@Override
	public boolean contient(double[] pos) {
		if(pos[0]>=origine[0]&&pos[0]<=(origine[0]+vecX[0])&&(pos[1]>=origine[1]&&pos[1]<=(origine[1]+vecY[1]))&&(pos[2]>=origine[2]&&pos[2]<=(origine[2]+vecZ[2]))){
			return true;
		}
		
		return false;
	}
	

	
	/**
	 * Détermine s'il l'on peut voir le prisme dans l'écran
	 * @return vrai si l'on peut voir le prisme dans l'écran
	 */
	public boolean isVisible(){
	
		double posIni[]=Outil3D.copieVec(origine);
		double[] basA=Outil3D.copieVec(origine);
		double[] basB=Outil3D.additionVec(posIni, vecX);
		double[] basC=Outil3D.additionVec(posIni, vecZ);
		double[] basD=Outil3D.additionVec(posIni, Outil3D.additionVec(vecX, vecZ));
		
		double[] check1=Outil3D.vecEntrePos(posCam, basA),check2=Outil3D.vecEntrePos(posCam, basB),
				check3=Outil3D.vecEntrePos(posCam, basC),check4=Outil3D.vecEntrePos(posCam, basD);
		int k=Outil3D.angleEntre(check1, vecRegard)+Outil3D.angleEntre(check2, vecRegard)+Outil3D.angleEntre(check3, vecRegard)+Outil3D.angleEntre(check4, vecRegard);
		
		return k!=-4;
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
	 * Retourne la couleur du prisme
	 * @return la couleur du prisme
	 */
	public Color getC() {
		return c;
	}

	
	
}
