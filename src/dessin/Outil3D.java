package dessin;

import java.awt.Graphics2D;

import java.awt.geom.Path2D;
import java.util.ArrayList;




/**
 * Outils pour de calculs et de dessin et 3D
 * @author Francis Gosselin
 */
public class Outil3D {
	
	/**
	 * créer une face à partir de 4 point en 2D
	 * @param a point 1
	 * @param b point 2
	 * @param c point 3
	 * @param d point 4
	 * @return un segment de droite Path2D 
	 */
	public static Path2D.Double creerFace(double[] a,double[] b,double[] c,double[] d){
		Path2D.Double face=new Path2D.Double();
		face.moveTo(a[0], a[1]);
		face.lineTo(b[0], b[1]);
		face.lineTo(d[0], d[1]);
		face.lineTo(c[0], c[1]);
		face.lineTo(a[0], a[1]);
		return face;
	}
	
	/**
	 * Retourne 
	 * @param vec1 1er vecteur à additionner
	 * @param vec2 2e vecteur à additionner
	 * @return vecteur résultant de l'addition des vecteurs
	 */
	public static double grandeurVec(double[] vec,double[] origVec,double[] pos){
		double[] pos1=vecEntrePos(pos,additionVec(origVec,pos));
		double[] pos2=vecEntrePos(pos,additionVec(additionVec(origVec,vec),pos));
		double[] vec2=vecEntrePos(pos1,pos2);
		
		return norme(vec2)*1500/norme(pos1);
	}
	
	/**
	 * permet de trouver la position 2D d'un point 3D
	 * @param pos la position en 3D
	 * @param posCam la position de la caméra
	 * @param vecRegard le vecteur représentant la vue de la caméra
	 * @param angleX l'angle en X
	 * @param angleY l'angle en Y
	 * @return vecteur résultant de l'addition des vecteurs
	 */
	public static double[] trouverPos(double[] pos,double posCam[],double[] vecRegard,double angleX,double angleY){
		double k;
		double[] leVec=Outil3D.vecEntrePos(posCam, pos);
		/*
		 * POSSIBILITÉ D'ERREUR
		 */
		leVec=projeter(vecRegard, leVec);
		double norme=Outil3D.norme(leVec);
		
		
		k=(1500)/norme;
		
	
		
		double[] basA=new double[2];
		basA[0]=rotaterX(angleX,vecEntrePos(posCam,pos));
		basA[1]=rotaterY(angleX,angleY,vecEntrePos(posCam,pos));
		
		return scal(basA,k);
	}
	
	
	/**
	 * Retourne une face créée par 4 point de l'espace 3D
	 * @param angleX l'angle en X
	 * @param angleY l'angle en X
	 * @param vec1 le vecteur d'un coté
	 * @param vec2 le vecteur de l'autre coté
	 * @param posIni la position Initiale de la face
	 * @param ks dont un scalaire multipliant la position selon la profondeur
	 * @param kPosIni un scalaire multipliant la position Initiale selon la profondeur
	 * @param la position de la caméra
	 */
	public static Path2D.Double creerFace(double angleX,double angleY,double vec1[],double vec2[],double posIni[],double[] ks, double kPosIni,double[] posDistance){
		Path2D.Double face=new Path2D.Double();
		
		double[] A={posIni[0],posIni[1],posIni[2]};
		double[] B=Outil3D.additionVec(posIni, vec1);
		double[] C=Outil3D.additionVec(posIni, vec2);
		double[] D=Outil3D.additionVec(posIni, Outil3D.additionVec(vec1, vec2));
		
		
		double[] basA=new double[2];
		double[] basB=new double[2];
		double[] basC=new double[2];
		double[] basD=new double[2];
		
		basA[0]=rotaterX(angleX,vecEntrePos(posDistance,A));
		basA[1]=rotaterY(angleX,angleY,vecEntrePos(posDistance,A));
		
		basB[0]=rotaterX(angleX,vecEntrePos(posDistance,B));
		basB[1]=rotaterY(angleX,angleY,vecEntrePos(posDistance,B));
		
		basC[0]=rotaterX(angleX,vecEntrePos(posDistance,C));
		basC[1]=rotaterY(angleX,angleY,vecEntrePos(posDistance,C));
		
		basD[0]=rotaterX(angleX,vecEntrePos(posDistance,D));
		basD[1]=rotaterY(angleX,angleY,vecEntrePos(posDistance,D));

		basA=scal(basA,ks[0]);
		basB=scal(basB,ks[1]);
		basC=scal(basC,ks[2]);
		basD=scal(basD,ks[3]);

		
		
		face=creerFace(basA,basB,basC,basD);
		return face;
	}
	

	/**
	 * permet d'additionner deux vecteurs
	 * @param vec1 1er vecteur à additionner
	 * @param vec2 2e vecteur à additionner
	 * @return vecteur résultant de l'addition des vecteurs
	 */
	
	public static double[] additionVec(double[] vec1, double[] vec2){
		double[] fin=new double[vec1.length];
		for(int i=0;i<vec1.length;i++){
			fin[i]=vec1[i]+vec2[i];
		}

		return fin;
	}
	
	/**
	 * permet de soustraire le deuxième vecteur au deuxième
	 * @param vec1 un vecteur
	 * @param vec2 le vecteur que l'on soustrait
	 * @return vecteur résultant de la soustraction des vecteurs: vec1-vec2
	 */
	
	public static double[] soustractionVec(double[] vec1, double[] vec2){
		double[] fin=new double[vec1.length];
		for(int i=0;i<vec1.length;i++){
			fin[i]=vec1[i]-vec2[i];
		}

		return fin;
	}
	
	/**
	 * calcule et retourne 1 si la valeur entre les deux angles passé en 
	 * paramètre est de plus de 90 degrés, sinon, retourne -1
	 * @param vecteur1 un vecteur 
	 * @param vecteur2 un autre vecteur
	 * @return 1 si l'angle entre les deux vecteur est plus de 90 degrés, sinon retorune -1
	 */
	public static int angleEntre(double[] vecteur1,double[]vecteur2){
		double val=Math.acos(prodScal(vecteur1,vecteur2)/(norme(vecteur1)*norme(vecteur2)));
		
		
		if(val>(Math.PI/2)){
			return -1;
			
		}else{
			
			return 1;	
		}
		
	}
	
	/**
	 * Retourne la distance entre 2 positions en 3 dimensions
	 * @param vec1 une position
	 * @param vec2 un autre position
	 */
	public static double distanceEntrePos(double[] vec1, double[] vec2){
		double[] fin=new double[vec1.length];
		for(int i=0;i<vec1.length;i++){
			fin[i]=vec2[i]-vec1[i];
		}
		return norme(fin);
	}
	

	/**
	 * Retourne le vecteur entre 2 positions en 3 dimensions
	 * @param vec1 un vecteur
	 * @param vec2 un autre vecteur
	 */
	public static double[] vecEntrePos(double[] vec1, double[] vec2){
		double[] fin=new double[vec1.length];
		for(int i=0;i<vec1.length;i++){
			fin[i]=vec2[i]-vec1[i];
		}
		return fin;
	}
	

	/**
	 * calcule et retourne le produit Scalaire de deux vecteur
	 * @param vecteur1 un vecteur 
	 * @param vecteur2 un autre vecteur
	 * @return retourne le résultat réel du produit des deux vecteurs 
	 */
	public static double prodScal(double[] vecteur1,double[]vecteur2){
		double result=0;
		for(int i=0;i<vecteur1.length;i++){
			result=result+vecteur1[i]*vecteur2[i];
		}
		return result;
	}
	

	/**
	 * sert à connaitre la composante y d'un vecteur vue par raport à son angle de vue
	 * @param theta angle de vue
	 * @param vecteur vecteur dont on veut connaitre la composante y
	 * @return retourne la composante y d'un vecteur vue par raport à son angle de vue
	 */
	public static double rotaterY(double angleX,double theta, double[] vecteur){
		double[] copie=vecteur;
		double[] vecteurUni=new double[3];
		vecteurUni[0]=Math.sin(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(theta));
		vecteurUni[1]=Math.sin(Math.toRadians(theta));
		vecteurUni[2]=Math.cos(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(theta));
		
		
		int k=angleEntre(vecteurUni,copie);
		copie=projeter(vecteurUni,copie);
		
	
		return norme(copie)*k;
	}

	/**
	 * sert à connaitre la composante x d'un vecteur vue par raport à son angle de vue
	 * @param theta angle de vue
	 * @param vecteur vecteur dont on veut connaitre la composante x
	 * @return retourne la composante x d'un vecteur vue par raport à son angle de vue
	 */
	public static double rotaterX(double theta, double[] vecteur){
		double[] copie=vecteur;
		double[] vecteurUni=new double[3];
		vecteurUni[0]=Math.sin(Math.toRadians(theta));
		vecteurUni[1]=0;
		vecteurUni[2]=Math.cos(Math.toRadians(theta));
	
		int k=angleEntre(vecteurUni,copie);
	
		copie=projeter(vecteurUni,copie);
		
		
		return norme(copie)*k;

	}

	/**
	 * sert à obtenir la norme d'un vecteur
	 * @param vect vecteur dont on veut la norme
	 * @return norme du vecteur
	 */

	public static double norme(double[] vect){
		double somme=0;
		for(int i=0;i<vect.length;i++){
			somme=somme+vect[i]*vect[i];
		}
		return Math.sqrt(somme);
	}

	/**
	 * projete un vecteur sur un autre vecteur paralèle a l'angle désiré
	 * @param vectUni vecteur sur lequel on projète
	 * @param vect vecteur a projeter
	 * @return vecteur projeté sur le vecteurUni
	 */

	public static double[] projeter(double[] vectUni, double[] vect){
		double x=0,y=0;
		double[] copie=new double[3];
		for(int i=0;i<vect.length;i++){
			x=x+vectUni[i]*vect[i];
			y=y+vectUni[i]*vectUni[i];
		}

		for(int i=0;i<vect.length;i++){
			if(vectUni[i]!=0){
				copie[i]=vectUni[i]*(x/y);
			}
		}
		return copie;	
	}
	
	/**
	 * Retourne le produit vectoriel de 2 vecteur
	 * @param vec1 un vecteur
	 * @param vec2 un autre vecteur
	 */
	public static double[] produitVec(double[] vec1, double[] vec2){
		double[] result=new double[3];
		result[0]=(vec1[1]*vec2[2])-(vec2[1]*vec1[2]);
		result[1]=-((vec1[0]*vec2[2])-(vec2[0]*vec1[2]));
		result[2]=(vec1[0]*vec2[1])-(vec2[0]*vec1[1]);
		return result;
	}
	
	/**
	 * Retourne le même vecteur, dont tout les variables sont multiplié par un même scalaire
	 * @param vec le vecteur
	 * @param scal le scalaire 
	 */
	public static double[] scal(double[] vec,double scal){
		double[] vecFin=new double[vec.length];
		for(int k=0;k<vec.length;k++){
			vecFin[k]=vec[k];
		}
		for(int k=0;k<vec.length;k++){
			vecFin[k]=vecFin[k]*scal;
		}
		
		return vecFin;
	}
	
	
	/**
	 * Retourne le même Vecteur avec une norme de 1
	 * @param v le vecteur
	 */
	public static double[] normaliser(double[] vec){
		double k=1/(norme(vec));
		return scal(vec,k);
	}
	
	/**
	 * affiche dans la console un Vecteur et son nom
	 * @param v le vecteur
	 */
	public static void afficherVec(double[] v){
		System.out.println("vec: "+v[0]+", "+v[1]+", "+v[2]);
	}
	
	/**
	 * affiche dans la console un Vecteur et son nom
	 * @param v le vecteur
	 * @param n son nom
	 */
	public static void afficherNomVec(double[] v,String n){
		System.out.println(n+": "+v[0]+", "+v[1]+", "+v[2]);
	}
	
	/**
	 * Retourne un vecteur dans un tableau le vecteur avec lequel on regarde la scène 
	 * @param angleX l'angle en x
	 * @param angleY l'angle en y
	 */
	public static double[] calculVecRegard(double angleX,double angleY){
		double[] vecteurUni=new double[3];
		vecteurUni[0]=Math.sin(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(angleY));
		vecteurUni[1]=Math.sin(Math.toRadians(angleY));
		vecteurUni[2]=Math.cos(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(angleY));
		

		double[] vecteurUni2=new double[3];
		vecteurUni2[0]=Math.sin(Math.toRadians(angleX));
		vecteurUni2[1]=0;
		vecteurUni2[2]=Math.cos(Math.toRadians(angleX));
		
		double[]vect4=Outil3D.produitVec(vecteurUni2,vecteurUni);
		return vect4;
	}
	
	/**
	 * Retourne une copie d'un tableau
	 * @param le tableau à copier
	 */
	public static double[] copieVec(double[] v){
		double[] vec=new double[v.length];
		for(int i=0;i<v.length;i++){
			vec[i]=v[i];
		}
		return vec;
	}
	

	/**
	 * Retourne un vecteur 3D qui lorsqu'on le regarde en 2D affiche une ligne droite horizontale
	 * @param angleX l'angle en x
	 * @param angleY l'angle en y
	 */
	public static double[] vecteurX(double angleX){
		double[] vecteurUni2=new double[3];
		vecteurUni2[0]=100*Math.sin(Math.toRadians(angleX));
		vecteurUni2[1]=0;
		vecteurUni2[2]=100*Math.cos(Math.toRadians(angleX));
		return normaliser(vecteurUni2);
	}
	
	/**
	 * Retourne un vecteur 3D qui lorsqu'on le regarde en 2D affiche une ligne droite verticale
	 * @param angleX l'angle en x
	 * @param angleY l'angle en y
	 */
	public static double[] vecteurY(double angleX,double angleY){
		double[] vecteurUni=new double[3];
		vecteurUni[0]=100*Math.sin(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(angleY));
		vecteurUni[1]=100*Math.sin(Math.toRadians(angleY));
		vecteurUni[2]=100*Math.cos(Math.toRadians(angleX+90))*Math.cos(Math.toRadians(angleY));
		
		return normaliser(vecteurUni);
	}
	
	/**
	 * Retourne l'intersection entre une droite et un plan
	 * @param plan le tableau {A,B,C,D} représentant l'équation d'un plan
	 * @param vecDroite le vecteur direction de la droite
	 * @param origDroite l'origine de la droite
	 * @return un tableau {x,y,z} représentant le point 3D d'intersection entre la droite et le plan
	 */
	public static double[] intersectionDroitePlan(double[] plan, double[] vecDroite,double[] origDroite){
		double a= -(plan[0]*origDroite[0])
				  -(plan[1]*origDroite[1])
				  -(plan[2]*origDroite[2])
				  -plan[3];
		
		double b=vecDroite[0]*plan[0]+vecDroite[1]*plan[1]+vecDroite[2]*plan[2];
		double t=a/b;
		double[] point=Outil3D.scal(vecDroite, t);
		point=Outil3D.additionVec(point, origDroite);
		return point;
	}
	
	/**
	 * Retourne une valeur boléene déterminante si deux position ou vecteur sont égale avec une tolérance
	 * @param p position 1
	 * @param p2 un autre position
	 * @param tolerance la tolérance
	 */
	public static boolean estEgal(double[] p, double[] p2,double tolerance){
		for(int i=0;i<p.length;i++){
			if(!((p[i]+tolerance)>=p2[i]&&(p[i]-tolerance<=p2[i]))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Retourne le même vecteur mais avec un module voulu 
	 * @param vec le Vecteur
	 * @param norme la grandeur que l'on veut que le vecteur soit
	 * @return le vecteur avec la bonne norme
	 */
	public static double[] vecteurNorme(double[] vec, double norme){
		double[] leVec=normaliser(vec);
		return scal(leVec,norme);
	}
	
	/**
	 * Dessine un vecteur 3D
	 * @param g2d le contexte graphic
	 * @param vec le vecteur
	 * @param origine l'origine du vecteur
	 * @param vecRegard le vecteur de regard
	 * @param posCam la position de la caméra
	 * @param angleX l'angle en X
	 * @param angleX l'angle en Y
	 */
	public static void dessinerVecteur(Graphics2D g2d, double[] vec, double grandeurPetitesFleches,double [] origine,double[] vecRegard,double[] posCam,double angleX, double angleY){
		double[] pos1=Outil3D.trouverPos(origine, posCam, vecRegard, angleX, angleY);
		
		double[] pos2=Outil3D.trouverPos(Outil3D.additionVec(origine, vec), posCam, vecRegard, angleX, angleY);

		double angleEntreVec=Math.toRadians(20);
		
		double[] point=Outil3D.additionVec(origine, Outil3D.vecteurNorme(vec, Outil3D.norme(vec)-grandeurPetitesFleches));
		double[] pos3=Outil3D.trouverPos(Outil3D.rotationPointY(point, Outil3D.additionVec(origine, vec), angleEntreVec), posCam, vecRegard, angleX, angleY);
		double[] pos4=Outil3D.trouverPos(Outil3D.rotationPointY(point, Outil3D.additionVec(origine, vec), -angleEntreVec), posCam, vecRegard, angleX, angleY);
		
	
		Path2D.Double ligne=new Path2D.Double();
		ligne.moveTo(pos1[0], pos1[1]);
		ligne.lineTo(pos2[0], pos2[1]);
		ligne.lineTo(pos3[0], pos3[1]);
		ligne.moveTo(pos2[0], pos2[1]);
		ligne.lineTo(pos4[0], pos4[1]);
		
		g2d.draw(ligne);
	}
	
	
	/**
	 * Dessine une ligne 3D
	 * @param g2d le contexte graphic
	 * @param posOrigine la position du début de la ligne
	 * @param posFin la position de la fin de la ligne
	 * @param vecRegard le vecteur de regard
	 * @param posCam la position de la caméra
	 * @param angleX l'angle en X
	 * @param angleX l'angle en Y
	 */
	public static void dessinerLigne(Graphics2D g2d, double[] posOrigine,double [] posFin,double[] vecRegard,double[] posCam,double angleX, double angleY){
		
		double[] pos1=Outil3D.trouverPos(posOrigine, posCam, vecRegard, angleX, angleY);
		
		double[] pos2=Outil3D.trouverPos(posFin, posCam, vecRegard, angleX, angleY);

	
		Path2D.Double ligne=new Path2D.Double();
		ligne.moveTo(pos1[0], pos1[1]);
		ligne.lineTo(pos2[0], pos2[1]);
		g2d.draw(ligne);
	}
	/**
	 * effectue la rotation d'un point selon l'axe des y
	 * @param point le point 
	 * @param axeRotation le point autour du quel on veut que le point tourne
	 * @return le point transformé
	 */
	public static double[] rotationPointY(double[] point,double[] axeRotation,double angle){
		double[][] matrice={{Math.cos(angle),0,Math.sin(angle)},
							{0,1,0},
							{-Math.sin(angle),0,Math.cos(angle)}};
		
		double[] temp=Outil3D.soustractionVec(point, axeRotation);
		temp=Outil3D.multiplieMatrice(temp, matrice);
		temp=Outil3D.additionVec(temp, axeRotation);
		
		return temp;
	}
	/**
	 * effectue l'opération d'un vecteur multiplié avec une matrice
	 * @param vec le Vecteur
	 * @param matrice la matrice
	 * @return le vecteur x la matrice
	 */
	public static double[] multiplieMatrice(double[] vec,double[][] matrice){
		int range = matrice.length;
	    int colone = matrice[0].length;

	    double[] resultat = new double[range];

	    for (int i = 0; i < range; i++) {
	        double k = 0;
	        for (int j = 0; j < colone; j++) {
	            k+= matrice[i][j]*vec[j];
	        }
	        resultat[i] = k;
	    }
	    return resultat;
	}
	
	/**
	 * retourne le premier point d'intersection en considérant seulement les variable X et Z entre une droite et un ensemble d'objet
	 * @param vec le vecteur directeur de la droite
	 * @param origine l'origine de la droite
	 * @param listeObjet l'ensemble d'objet
	 * @return l'intersection s'il y en a une, sinon retourne le vecteur aditionné avec son origine
	 */
	public static double[] intersectionXZ(double[] vec,double[]origine, ArrayList<Objet3D> listeObjet){
		double[] vecUni=Outil3D.normaliser(vec);
		double[] point; 
		for(double t=1;t<=Outil3D.norme(vec);t=t+0.2){
			point=Outil3D.additionVec(origine, Outil3D.scal(vecUni, t));
			
			for(Objet3D o:listeObjet){
				if(o instanceof Boule3D){
					Boule3D b=(Boule3D)o;
					if(!b.estRentre()){
						if(b.contient(point)){
							return point;
						}
					}
				}else{
					if(o.contient(point)){				
						return point;
					}
				}
				
			}
		}
		return Outil3D.additionVec(origine, vec);

	}
	/**
	 * Copie une liste d'objet3D
	 * @param ar la liste d'objet
	 * @return la copie de la liste
	 */
	public static ArrayList<Objet3D> copie(ArrayList<Objet3D> ar){
		ArrayList<Objet3D> a= new ArrayList<Objet3D>();
		for(Objet3D s:ar){
			a.add(s);
		}
		return a;
	}
	
}
