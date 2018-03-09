package dessin;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.TreeSet;
/**
 * Créer un objet 3D de disque
 * 
 * @author Francis Gosselin 
 *
 */
public class Disque extends Plan3D implements Comparable<Objet3D>,Objet3D{
	private double angleX;
	private double angleY;
	private double[] origine=new double[3];
	private double vec1[]={0,0,0},vec2[]={0,0,0};

	private double[] position;

	private double rayon;
	private boolean horizontal=false;

	private double[] vecRegard;
	private Color c=Color.green;
	private Ellipse2D.Double cercle;
	private Path2D.Double ligne;
	
	/**
	 * Constructeur
	 * @param oriX milieu du disque en x
	 * @param oriY milieu du disque en y
	 * @param oriZ milieu du disque en z
	 * @param rayon rayon du disque
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 */
	public  Disque(double oriX,double oriY,double oriZ,double rayon, double angX,double angY,
			double[] posDistance, double[] vecRegard){
		angleX=angX;
		angleY=angY;
		this.rayon=rayon;
		origine[0]=oriX;
		origine[1]=oriY;
		origine[2]=oriZ;
		this.position=posDistance;

		this.vecRegard=vecRegard;
	}
	
	/**
	 * Constructeur
	 * @param p vecteur du milieu du disque
	 * @param rayon rayon du disque
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 */
	public  Disque(double[] p,double rayon, double angX,double angY,
			double[] posDistance, double[] vecRegard){
		angleX=angX;
		angleY=angY;
		this.rayon=rayon;
		origine=p;
		this.position=posDistance;

		this.vecRegard=vecRegard;
	}
	
	/**
	 * Constructeur
	 * @param oriX milieu du disque en x
	 * @param oriY milieu du disque en y
	 * @param oriZ milieu du disque en z
	 * @param rayon rayon du disque
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param c couleur du disque
	 */
	public  Disque(double oriX,double oriY,double oriZ,double rayon, double angX,double angY,
			double[] posDistance, double[] vecRegard, Color c){
		angleX=angX;
		angleY=angY;
		this.rayon=rayon;
		origine[0]=oriX;
		origine[1]=oriY;
		origine[2]=oriZ;
		this.position=posDistance;
	
		this.vecRegard=vecRegard;
		this.c=c;
	}
	
	/**
	 * Constructeur
	 * @param oriX milieu du disque en x
	 * @param oriY milieu du disque en y
	 * @param oriZ milieu du disque en z
	 * @param rayon rayon du disque
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param c couleur du disque
	 */
	public  Disque(double[] origine,double rayon, double angX,double angY,
			double[] posDistance, double[] vecRegard, Color c, boolean horizontal){
		angleX=angX;
		angleY=angY;
		this.rayon=rayon;
		this.origine=origine;
		this.position=posDistance;
		this.vecRegard=vecRegard;
		this.c=c;
		this.horizontal=horizontal;
	}
	
	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	public void dessiner(Graphics2D g2d){
				creerDisque();
				if(c==Color.black){
					g2d.setColor(Color.black);
					g2d.fill(ligne);
					
				}else{
					if(cercle.getHeight()<200){
						g2d.setColor(c);
						g2d.fill(cercle);
						g2d.setColor(Color.black);
						g2d.draw(cercle);
					}
				
				}
				
	}
	
	
	/**
	 * créer la face du disque
	 */
	private void creerDisque(){
		if(c!=Color.black){
			double[] ori=Outil3D.trouverPos(origine, position, vecRegard, angleX, angleY);
				
				double[] vecXPlat=Outil3D.vecteurX(angleX);
				double[] vecYPlat= Outil3D.vecteurY(angleX,angleY);
				double[] EvecYPlat=Outil3D.vecteurY(angleX,angleY);
				EvecYPlat=Outil3D.scal(EvecYPlat,-1);
				if(!horizontal){
					vecXPlat[1]=0;
					vecYPlat[1]=0;
					EvecYPlat[1]=0;
				}else{
					vecXPlat[1]=0;
					double[] temp=Outil3D.copieVec(vecRegard);
					temp[1]=0;
					vecYPlat=Outil3D.soustractionVec(vecYPlat, Outil3D.projeter(temp, vecYPlat));
					EvecYPlat=Outil3D.soustractionVec(EvecYPlat, Outil3D.projeter(temp, EvecYPlat));
				}
				
				
				vecXPlat=Outil3D.normaliser(vecXPlat);
				vecYPlat=Outil3D.normaliser(vecYPlat);
				EvecYPlat=Outil3D.normaliser(EvecYPlat);
				
				double[] vecX=Outil3D.additionVec(origine,Outil3D.scal(vecXPlat,rayon) );
				double[] vecY=Outil3D.additionVec(origine,Outil3D.scal(vecYPlat,rayon));
				double[] EvecY=Outil3D.additionVec(origine, Outil3D.scal(EvecYPlat,rayon));
				
				
				double[] rayX=Outil3D.trouverPos(vecX, position, vecRegard, angleX, angleY);
				double[] rayY=Outil3D.trouverPos(vecY, position, vecRegard, angleX, angleY);
				double[] ErayY=Outil3D.trouverPos(EvecY, position, vecRegard, angleX, angleY);
				
				double rayonX=rayX[0]-ori[0];
				double rayonY=rayY[1]-ErayY[1];
		
				cercle= new Ellipse2D.Double(ori[0]-rayonX, ori[1]-0.5*rayonY, 2*rayonX, rayonY);
		}else{
			
			int segments=30;
			ligne=new Path2D.Double();
			double[] pos=Outil3D.additionVec(origine, Outil3D.vecteurNorme(new double[]{1,0,0}, rayon));
			double[] temp=Outil3D.trouverPos(pos, position, vecRegard, angleX, angleY);
			double angle=Math.PI*2/segments;
			ligne.moveTo(temp[0], temp[1]);
			for(int k=1;k<segments;k++){
				pos=Outil3D.rotationPointY(pos, origine, angle);
				temp=Outil3D.trouverPos(pos, position, vecRegard, angleX, angleY);
				ligne.lineTo(temp[0], temp[1]);
		}
			
			
			
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
	
	/**
	 * compare le plan avec un autre objet 3D
	 * @param v le deuxième objet 3D
	 * @return -1 si l'objet comparé est situé derrière, retourne 1 sinon
	 */
	@Override
	public int compareTo(Objet3D obj) {
		if(obj.getClass().equals(Disque.class)){
			if(Outil3D.distanceEntrePos(position, origine)>Outil3D.distanceEntrePos(position, obj.getCentre())){
				//System.out.println(pan.nom+" bat "+nom);
				return -1;
			}else{
				
				return 1;
			}
		}
		
		return -1;	
		
		
	}
	
	
	/**
	 * Retourne l'équation du plan en forme Ax+By+Cz+d=0
	 * dans le tableau {A,B,C,D}
	 * @return le tableau {A,B,C,D}
	 */
	public double[] getEquationPlan(){
		double[] vec=Outil3D.produitVec(vec1, vec2);
		double d=-(origine[0]*vec[0]+origine[1]*vec[1]+origine[2]*vec[2]);
		double[] rep={vec[0],vec[1],vec[2],d}; 
		return rep;
	}
	
	
	/**
	 * retourne l'aire de face
	 * @return l'aire de la face
	 */
	public Area getArea(){
		if(cercle==null){
			creerDisque();
		}
		Area a=new Area(cercle);
		return a;
	}

	
	/**
	 * Retourne faux
	 * @return faux
	 */
	@Override
	public boolean contient(double[] pos) {
		double[] temp=Outil3D.copieVec(pos);
		temp[1]=origine[1];
		temp=Outil3D.vecEntrePos(origine, temp);
		if(Outil3D.norme(temp)<rayon){
			return true;
		}
		
		return false;
	}
	
	/**
	 * change la position en 3 dimension de la caméra
	 * @param v la position en 3 dimension de la caméra
	 */
	@Override
	public void setPosition(double[] v) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	@Override
	public void setVectRegard(double[] v) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * retourne le centre du cercle
	 * @return le centre du cercle
	 */
	@Override
	public double[] getCentre() {
		return origine;
	}
	
	
	/**
	 * retourne vrai si les plans sont parralèles
	 * @return la valeur booléene
	 */
	public boolean estEgal(Plan3D pan){
		return Outil3D.estEgal(getEquationPlan(), pan.getEquationPlan(), 0);
	}
	
	/**
	 * retourne la normale du plan
	 * @return la normale du plan
	 */
	public double[] getNormal(){
		return Outil3D.produitVec(vec1, vec2);
	}
	
	/**
	 * Retourne les Faces que l'on peut voir de l'objet
	 * @return les Faces que l'on peut voir de l'objet
	 */
	public TreeSet<Objet3D> getFaces(){
		TreeSet<Objet3D> p=new TreeSet<Objet3D>();
		p.add(this);
		return p;
	}
	
	/**
	 * Détermine si le disque est comparable
	 */
	@Override
	public boolean estComparable(Objet3D o){
		return false;
	}
	
	
}
