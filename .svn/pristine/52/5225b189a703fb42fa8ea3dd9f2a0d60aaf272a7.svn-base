package dessin;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.TreeSet;
/**
 * Créer une face en 3D
 * 
 * @author Francis Gosselin
 */
public class Plan3D implements Comparable<Objet3D>,Objet3D{
	private double angleX;
	private double angleY;
	private double[] origine;
	private double vec1[]={0,0,0},vec2[]={0,0,0};
	private double distanceProjection;
	private double[] position;
	private boolean trou=false;
	private double rayon;
	private double[] posTrou;
	private boolean cont=true;

	private double[] vecRegard;
	private Color c=Color.green;
	private Path2D.Double face;
	private static boolean transparent=false;
	
	/**
	 * Constructeur.
	 */
	public Plan3D(){
	}
	
	/**
	 * Constructeur
	 * @param vec1 vecteur d'un coté
	 * @param vec2 vecteur d'un autre coté 
	 * @param posIni position du point du plan
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param distance du plan de projection
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 */
	public  Plan3D(double[] vec1,double[] vec2,double[] posIni, double angX,double angY,double distanceProjection,
			double[] posDistance, double[] vecRegard){
		angleX=angX;
		angleY=angY;
		
		origine=Outil3D.copieVec(posIni);
		this.vec1=vec1;
		this.vec2=vec2;
		this.distanceProjection=distanceProjection;
		this.position=posDistance;

		this.vecRegard=vecRegard;
	}
	
	/**
	 * Constructeur
	 * @param vec1 vecteur d'un coté
	 * @param vec2 vecteur d'un autre coté 
	 * @param posIni position du point du plan
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param distance du plan de projection
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param c Couleur du plan
	 * @param contour vrai si les contours doivent être dessiner
	 */
	public  Plan3D(double[] vec1,double[] vec2,double[] posIni, double angX,double angY,double distanceProjection,
			double[] posDistance, double[] vecRegard,Color c,boolean contour){
		angleX=angX;
		angleY=angY;
		
		origine=Outil3D.copieVec(posIni);
		this.vec1=vec1;
		this.vec2=vec2;
		this.distanceProjection=distanceProjection;
		this.position=posDistance;
		cont=contour;
		this.vecRegard=vecRegard;
		this.c=c;

	}
	
	/**
	 * Constructeur
	 * @param vec1 vecteur d'un coté
	 * @param vec2 vecteur d'un autre coté 
	 * @param posIni position du point du plan
	 * @param angX angle en X
	 * @param angY angle en Y
	 * @param distance du plan de projection
	 * @param posDistance position de la caméra
	 * @param vecRegard vecteur du regard
	 * @param c Couleur du plan
	 */
	public  Plan3D(double[] vec1,double[] vec2,double[] posIni, double angX,double angY,double distanceProjection,
			double[] posDistance, double[] vecRegard,Color c){
		angleX=angX;
		angleY=angY;
		
		origine=Outil3D.copieVec(posIni);
		this.vec1=vec1;
		this.vec2=vec2;
		this.distanceProjection=distanceProjection;
		this.position=posDistance;
		this.vecRegard=vecRegard;
		this.c=c;

	}

	
	/**
	 * trouve une position 2D d'un point 3D
	 * @param le point 3D
	 */
	private double trouverPos(double[] pos){
		double k;
		double[] leVec=Outil3D.vecEntrePos(position, pos);
		/*
		 * POSSIBILITÉ D'ERREUR
		 */
		
		//int k2=Outil3D.angleEntre(posDistance,leVec);
		leVec=Outil3D.projeter(vecRegard, leVec);
		
		k=(1500)/Outil3D.norme(leVec);
		
		Outil3D.trouverPos(pos, position, vecRegard, angleX, angleY);
		
		return k;
	}
	
	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	public void dessiner(Graphics2D g2d){
		
		
		//System.out.println("posIni: "+posDistance[0]+", "+posDistance[1]+", "+posDistance[2]+", ");
		
		double posIni[]=Outil3D.copieVec(origine);
		double[] basA=Outil3D.copieVec(origine);
		double[] basB=Outil3D.additionVec(posIni, vec1);
		double[] basC=Outil3D.additionVec(posIni, vec2);
		double[] basD=Outil3D.additionVec(posIni, Outil3D.additionVec(vec1, vec2));
		
		double[] check1=Outil3D.vecEntrePos(position, basA),check2=Outil3D.vecEntrePos(position, basB),
				check3=Outil3D.vecEntrePos(position, basC),check4=Outil3D.vecEntrePos(position, basD);
		int k=Outil3D.angleEntre(check1, vecRegard)+Outil3D.angleEntre(check2, vecRegard)+Outil3D.angleEntre(check3, vecRegard)+Outil3D.angleEntre(check4, vecRegard);
		double[] ks={trouverPos(basA),trouverPos(basB),trouverPos(basC),trouverPos(basD)};
		double kPosIni=trouverPos(posIni);
		face =Outil3D.creerFace(angleX,angleY,vec1,vec2,posIni,ks,kPosIni,position);
		
		if(k!=4){
		

			if(trou){
				Disque d=new Disque(posTrou,rayon,angleX,angleY,position,vecRegard);
				double[] l=Outil3D.produitVec(Outil3D.vecEntrePos(posTrou, origine), Outil3D.produitVec(vec1, vec2));
				l=Outil3D.vecteurNorme(l, rayon);
				double[] orig=Outil3D.additionVec(origine, l);
				l=Outil3D.scal(l, -2);
				Plan3D p=new Plan3D(l,Outil3D.vecEntrePos(origine, posTrou),orig,angleX,angleY,distanceProjection,position,vecRegard);
					
				Area a2=d.getArea();
				a2.add(p.getArea());
				
				Area a1=new Area(face);
				
				
				a1.subtract(a2);
				g2d.setColor(c);
				if(!transparent){
					g2d.fill(a1);	
				}
				
				
				if(cont){
					g2d.setColor(Color.black);
					g2d.draw(a1);
				}
					
			}else{
				
				g2d.setColor(c);
				if(!transparent){
					g2d.fill(face);
				}
				if(cont){
					g2d.setColor(Color.black);
					g2d.draw(face);
				}
				
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
		Plan3D pan;
		try{
		if(obj.getClass().equals(Plan3D.class)){
			pan=(Plan3D) obj;
		}else{
			pan=(Plan3D)obj.getFaces().first();	
			
			for(Objet3D l:obj.getFaces()){
				Plan3D q=(Plan3D)l;
				if(this.intersecte(q)){
					pan=q;
					
					break;
				}
			}
		}
		
		
		Area a1=getArea();
		Area a2=pan.getArea();
		a1.intersect(a2);
		
		
		if(a1.isEmpty()){
			double[] pos1,pos2;
			pos1=this.getCentre();
			pos2=pan.getCentre();
			if(Outil3D.distanceEntrePos(position, pos1)>Outil3D.distanceEntrePos(position, pos2)){
				return -1;
			}else{
				return 1;
			}
		}else{
			
			double x=a1.getBounds2D().getX()+a1.getBounds2D().getWidth()/2.0;
			double y=a1.getBounds2D().getY()+a1.getBounds2D().getHeight()/2.0;
			
			double[] temp=Outil3D.normaliser(vecRegard);
			temp=Outil3D.scal(temp, -1500);
			double[] pos3=Outil3D.additionVec(temp, position);
			
			double[] vecX=Outil3D.vecteurX(angleX);
			double[] vecY=Outil3D.vecteurY(angleX,angleY);
			vecX=Outil3D.normaliser(vecX);
			
			vecX=Outil3D.scal(vecX, x);
			vecY=Outil3D.normaliser(vecY);
			vecY=Outil3D.scal(vecY, y);
			pos3=Outil3D.additionVec(pos3, Outil3D.additionVec(vecX, vecY));
			
	
			
			double[] leVec=Outil3D.vecEntrePos(position, pos3);	
			
			double[] inter1=Outil3D.intersectionDroitePlan(getEquationPlan(), leVec, position);
			double[] inter2=Outil3D.intersectionDroitePlan(pan.getEquationPlan(), leVec, position);
			
			
			if(Outil3D.norme(Outil3D.vecEntrePos(position, inter1))
					<
			   Outil3D.norme(Outil3D.vecEntrePos(position, inter2))){
				return 1;
			}else{
				return -1;
			}
		}
		}catch(ClassCastException e){
			double[] pos1,pos2;
			pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vec1,0.5), Outil3D.scal(vec2,0.5)));
			pos2=obj.getCentre();
			if(Outil3D.distanceEntrePos(position, pos1)>Outil3D.distanceEntrePos(position, pos2)){
				//System.out.println(pan.nom+" bat "+nom);
				return -1;
			}else{
				
				return 1;
			}
			
		}
		
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
		if(face==null){
			
			//System.out.println("posIni: "+posDistance[0]+", "+posDistance[1]+", "+posDistance[2]+", ");
			
			double posIni[]=Outil3D.copieVec(origine);
			double[] basA=Outil3D.copieVec(origine);
			double[] basB=Outil3D.additionVec(posIni, vec1);
			double[] basC=Outil3D.additionVec(posIni, vec2);
			double[] basD=Outil3D.additionVec(posIni, Outil3D.additionVec(vec1, vec2));
			double[] ks={trouverPos(basA),trouverPos(basB),trouverPos(basC),trouverPos(basD)};
			double kPosIni=trouverPos(posIni);
			face=Outil3D.creerFace(angleX,angleY,vec1,vec2,posIni,ks,kPosIni,position);
		}
		return new Area(face);
		
	}

	
	/**
	 * Retourne faux
	 * @return faux
	 */
	@Override
	public boolean contient(double[] pos) {
		return false;
	}
	
	/**
	 * change la position en 3 dimension de la caméra
	 * @param v la position en 3 dimension de la caméra
	 */
	@Override
	public void setPosition(double[] v) {
		this.position=v;
		
	}
	
	
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	@Override
	public void setVectRegard(double[] v) {
		this.vecRegard=v;
		
	}

	/**
	 * retourne le centre du cercle
	 * @return le centre du cercle
	 */
	@Override
	public double[] getCentre() {
		double[] pos1=Outil3D.additionVec(origine,Outil3D.additionVec(Outil3D.scal(vec1,0.5), Outil3D.scal(vec2,0.5)));
		return pos1;
	}
	
	/**
	 * retourne un valeur bolléen vrai si lorsqu'on regarde en 2D les deux plan, ils se touchent 
	 * @return la valeur booléene
	 */
	public boolean intersecte(Plan3D p){
		Area a1=getArea();
		Area a2=p.getArea();
		a1.intersect(a2);
		
		double scal=Outil3D.prodScal(Outil3D.vecEntrePos(position, origine), getNormal());
		
		if(scal<0.01&&scal>-0.01){
			
			return false;
		}
		
		return !a1.isEmpty();
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
		
		double scal=Outil3D.prodScal(Outil3D.vecEntrePos(position, origine), getNormal());
		
		if(scal<0.01&&scal>-0.01){
			
			return false;
		}
		
		return !a1.isEmpty();
	}
	
	/**
	 * retourne vrai si les plans sont parralèles
	 * @return la valeur booléene
	 */
	public boolean estEgal(Plan3D pan){
		return Outil3D.estEgal(getEquationPlan(), pan.getEquationPlan(), 0);
	}
	
	/**
	 * ajoute un trou au trou à l'origine
	 * @param le rayon du trou
	 */
	public void ajouterTrou(double rayon){
		trou=true;
		this.rayon=rayon;
		posTrou=origine;
	}
	
	/**
	 * ajoute un trou au trou à l'origine
	 * @param le rayon du trou
	 */
	public void ajouterTrou(double rayon, double[] pos){
		trou=true;
		this.rayon=rayon;
		posTrou=pos;
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
	 * Retourne la grandeur de l'allongement du trou projeter sur un coté, fonction que si le plan est carré et que
	 * la position du trou est sur la diagonale du carré
	 * @return la grandeur de l'allongement du trou projeter sur un coté
	 */
	public double getDistanceTrouCote(){
		if(!trou){
			return 0;
		}
		double[] l=Outil3D.produitVec(Outil3D.vecEntrePos(posTrou, origine), Outil3D.produitVec(vec1, vec2));
		l=Outil3D.vecteurNorme(l, rayon);
		
		l=Outil3D.projeter(vec1, l);
		return Outil3D.norme(l)*2;	
	}
	
	/**
	 * Permet de rendre le prisme transparent
	 * @param c vrai si l'on veut que la table soit transparente
	 */
	public static void transparent(boolean c){
		transparent=c;
	}
	
	/**
	 * Permet de dessiner le contour du prisme ou non
	 * @param c vrai si l'on veut voir le contour
	 */
	public void dessinerContour(boolean c){
		cont=c;
	}



}
