package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import ecouteurs.EcouteursModeArcade;
import ecouteurs.EcouteursPerso;


/**
 * Zone de dessin du mode arcade
 * @author Francis Gosselin
 * @author Rui Bin Wang
 */
public class ZoneDessinArcade extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private double debutX,debutY,debutXA,debutYA;
	private double angleX=270,angleY=75;
	private final double[] POS_BLANCHE_INI={125,220+Boule3D.getDefaultRayon()*100,80};
	private double vMax=130;
	private double boostMax=240;
	private double boost=240;
	
	private Prisme3D dessin2;
	private ArrayList<Objet3D> arbre2=new ArrayList<Objet3D>();
	private ArrayList<Objet3D> jeu=new ArrayList<Objet3D>();
	
	private ArrayList<Objet3D> cote=new ArrayList<Objet3D>();
	private ArrayList<Prisme3D> coteDessin=new ArrayList<Prisme3D>();
	private ArrayList<Objet3D> coteTrier=new ArrayList<Objet3D>();
	private ArrayList<Disque> trous=new ArrayList<Disque>();
	private ArrayList<Plan3D> tapis=new ArrayList<Plan3D>();
	
	
	private double sensibiliteSouris=180;

	private double[] posCam={1000,1000,1000};
	private double[] vecRegard=Outil3D.calculVecRegard(angleX, angleY);
	private double[] vecteurUni2;
	private boolean lock=true;
	private double distancePosRegard=450;
	private double[] posRegard={125,225,250};
	
	private Boule3D bouleBlanche,boule1;
	private ArrayList<Boule3D> listeBoules;
	private boolean avance=false,reculer=false;
	
	private int nbBoules=20;

	
	private boolean anim=false;
	private boolean antiAlliasing=true;
	private static final double DEFAULT_DELTA_TEMPS = 0.01;
	private double deltaTemps = DEFAULT_DELTA_TEMPS;
	private long sleep = 5;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

	
	/**
	 * Constructeur.
	 * @param b le nombre de boules initial
	 */
	public ZoneDessinArcade(int b) {
		nbBoules=b;
		Boule3D.setCoeffFrottementGlisse(0.05);
	
		Color c=new Color(50,50,50);
		
		
		//haut
		dessin2=new Prisme3D(0,220.1,-15,500,100,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		//bas
		dessin2=new Prisme3D(0,220.1,500,500,100,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);

		//gauche
		dessin2=new Prisme3D(-15,220.1,0,15,100,500,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);

		
		//droite
		dessin2=new Prisme3D(500,220.1,0,15,100,500,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);

		cote.addAll(coteDessin);
		
		Collections.sort(cote);
		for(Objet3D p:cote){
			coteTrier.add(p);
		}
		
		
		
		Disque d=new Disque(14,219.95,14,12,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		d=new Disque(500-14,219.95,14,12,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		
		d=new Disque(250,219.95,250,12,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		
		d=new Disque(14,219.95,486,12,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		d=new Disque(500-14,219.95,486,12,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		

		jeu.addAll(trous);
		creerListeBoules();
		//table
		Color couleurTable=new Color(10, 108, 3);
		
		double[] vecX2={250,0,0};
		double[] vecZ2={0,0,250};
		Plan3D p;
		p =new Plan3D(vecZ2,vecX2,new double[]{0,220,0},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ2,vecX2,new double[]{250,220,0},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ2,vecX2,new double[]{0,220,249},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ2,vecX2,new double[]{250,220,249},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				
				
				if(arg0.isShiftDown()){
					
					if(boost==boostMax&&Outil3D.norme(bouleBlanche.getVitesseLineaire())<vMax+boostMax){
						double[] t=Outil3D.copieVec(Outil3D.vecteurNorme(vecRegard, -1));
						t[1]=0;
						
						t=Outil3D.vecteurNorme(t, boost);
						bouleBlanche.setVitesseLineaire(Outil3D.additionVec(bouleBlanche.getVitesseLineaire(), t));
						boost=0;
					}
					
				}	
				
				
				if(c=='s'||c=='w'){
					
	
					
					if(c=='w'){
						avance=true;
						
					}else{
						reculer=true;
					}	
					
				}
				if(c==' '&&bouleBlanche.getPositionCentreDeMasse()[1]<=220+Boule3D.getDefaultRayon()*100){
					
					double[] t2={0,150,0};
					bouleBlanche.setVitesseLineaire(Outil3D.additionVec(bouleBlanche.getVitesseLineaire(), t2));
				}
				
					
				repaint();
				}
					
			@Override
			public void keyReleased(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if(c=='w'){
					avance=false;	
				}
				if(c=='s'){
					reculer=false;	
				}
				
			}
		});
		/*
		 * intialise la position du bloc ou l'angle de vue
		 * si voir en 3D
		 *
		 */
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
					double x=arg0.getX();
					double y=arg0.getY();
	
					 x=arg0.getX();
					 y=arg0.getY();
						debutX=x;
						debutY=y;
						debutXA=angleX;
						debutYA=angleY;
				
				
					
				
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		
		/*
		 * permet de déplacer soit la position du bloc 
		 * ou l'angle de vue si en 3D
		 *
		 */
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(isEnabled()){
					
						angleX=debutXA+((debutX-arg0.getX())/(15)*sensibiliteSouris/100);
						angleY=debutYA+((debutY-arg0.getY())/(15)*sensibiliteSouris/100);
							if(angleX<0){
								angleX=360;
								debutX=arg0.getX();
								debutXA=angleX;
							}
							if(angleY>=80){
								angleY=80;
								debutY=arg0.getY();
								debutYA=angleY;
							}
							if(angleX>360){
								angleX=0;
								debutX=arg0.getX();
								debutXA=angleX;
							}
							
							if(angleY<0){
								angleY=0;
								debutY=arg0.getY();
								debutYA=angleY;
							}
							
					
				repaint();
				}
				
				}
			
		});
		setBackground(Color.white);
		
		demarrer();
	}
	
	/**
	 * Créer les boules sur le jeu
	 */
	//Francis Gosselin
	private void creerListeBoules(){
		
		
		listeBoules = new ArrayList<Boule3D>();
		double[] vec={125,220+Boule3D.getDefaultRayon()*100,80};
		if(bouleBlanche==null){
			bouleBlanche= new Boule3D(posCam, vecRegard, vec,Boule3D.getDefaultRayon()*100,angleX,angleY,Color.white);
			
		}
		
		for(int k=0;k<nbBoules;k++){
			boule1 = new Boule3D(posCam, vecRegard, new double[] {Math.random()*450+20, 220+Boule3D.getDefaultRayon()*100, Math.random()*450+20}, Boule3D.getDefaultRayon()*100, angleX, angleY, new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			boule1.setVitesseLineaire(new double[]{Math.random()*40-20,0,Math.random()*120-20});
			listeBoules.add(boule1);
		}
		
		
		listeBoules.add(bouleBlanche);
			
		arbre2=new ArrayList<Objet3D>();
		arbre2.addAll(jeu);
		arbre2.addAll(listeBoules);
		
	}
	
	/**
	 * Méthode permettant de dessiner la scène 3D
	 * @param g le contexte graphique
	 */
	//Francis Gosselin
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	
		g2d.translate(getWidth()/2.0, getHeight()/2.0);
		g2d.scale(1, -1);
		
		if(antiAlliasing){
			g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		}
			
			
					
			
			if(lock){
				
				posRegard=bouleBlanche.getCentre();
				
				double[] vecteurUni=Outil3D.vecteurY(angleX, angleY);
				vecteurUni2=Outil3D.vecteurX(angleX);
				
				vecRegard=Outil3D.produitVec(vecteurUni2,vecteurUni);
				
				vecRegard=Outil3D.normaliser(vecRegard);
	
					
					posCam=Outil3D.scal(vecRegard, distancePosRegard);
					
					
					posCam=Outil3D.additionVec(posCam, posRegard);
					
					
					
					
			}
			
				vecRegard=Outil3D.normaliser(vecRegard);
				
			for(Objet3D c: arbre2){
				c.setVectRegard(vecRegard);
				c.setPosition(posCam);
				c.setAngleX(angleX);
				c.setAngleY(angleY);
				
			}
			
			
			
			vecRegard=Outil3D.normaliser(vecRegard);
			

	
		
		ArrayList<Objet3D> ar;
			
		ar=new ArrayList<Objet3D>();
			
	
		
		for(Plan3D p:tapis){
			p.setVectRegard(vecRegard);
			p.setPosition(posCam);
			p.setAngleX(angleX);
			p.setAngleY(angleY);
			p.dessiner(g2d);
		}
		
		ar=new ArrayList<Objet3D>();
		
		
		
		
		Collections.sort(cote);
		
		arbre2.removeAll(coteTrier);
		coteTrier=new ArrayList<Objet3D>();
		
		 
	
		coteTrier.addAll(cote);
		
	
		
		for(Objet3D o:arbre2){
			if(o.getClass().equals(Boule3D.class)){
				Boule3D b=(Boule3D)o;
			
				if(b.isVisible(-getWidth()/2.0,-getHeight()/2.0,getWidth()/2.0,getHeight()/2.0)){
					ar.add(o);
				}
			}else{
				ar.add(o);
			}
	
		}
		Collections.sort(ar);
		for(Objet3D h:ar){
			
			h.dessiner(g2d);
			
		}	
	}
	
	/**
	 * Boucle d'animation.
	 */
	//Ruibin Wang
	@Override
	public void run() {
		while(anim){
			if(boost<boostMax){
				boost=boost+boostMax/500;
			}else{
				boost=boostMax;
			}
			
			if((avance||reculer)&&Outil3D.norme(bouleBlanche.getVitesseLineaire())<=100){
				int k=0;
				if(avance){
					k=-1;
				}else{
					k=1;
				}
				double[] temp=Outil3D.copieVec(vecRegard);
				temp[1]=0;
				double[] temp2=Outil3D.scal(temp, k*vMax);
				temp2[1]=bouleBlanche.getVitesseLineaire()[1];
				bouleBlanche.setVitesseLineaire(temp2);
			}
						
			prochaineImage(1);
			

			try{
				Thread.sleep(sleep);
			}catch(Exception e){
				e.printStackTrace();
			}
			int k=0;
			for(Boule3D b:listeBoules){
				if(!b.estRentre()){
					k++;
				}
			}
			if(k==1){
				creerListeBoules();
			}
		}
	}
	
	
	/**
	 * Affiche la prochaine image après un pas.
	 */
	//Ruibin Wang
	public void prochaineImage(int nbPas) {
		leverBoost();
		collisionBouleBoule();
		bouleEntreDansTrou();
		collisionBoulePrisme();
		for (int n = 0; n < nbPas; n++) {
			for (Boule3D boule : listeBoules) {
				if (!Outil3D.estEgal(boule.getVitesseLineaire(), new double[]{0, 0, 0}, 0.01)) {
					boule.unPasEuler(deltaTemps);
				}
			}
		}
		if(Outil3D.norme(bouleBlanche.getVitesseLineaire())>1){
			bouleBlanche.setVitesseLineaire(Outil3D.additionVec(bouleBlanche.getVitesseLineaire(), Outil3D.vecteurNorme(bouleBlanche.getVitesseLineaire(), -1)));
		}
		
		repaint();
	}
	
	/**
	 * Démarre l'animation.
	 */
	//Ruibin Wang
	public void demarrer(){
		if(!anim){
			anim=true;
			Thread proc=new Thread(this);
			proc.start();
		}
	}
	
	/**
	 * Met fin à l'animation.
	 */
	//Ruibin Wang
	public void arreter() {
		anim = false;
	}
	
	/**
	 * Ajoute les écouteurs personnalisés.
	 * @param ecouteursPerso : Les écouteurs personnalisés de type EcouteursPerso.
	 */
	//Ruibin Wang
	public void addEcouteursPerso(EcouteursPerso ecouteursPerso) {
	    OBJETS_ENREGISTRES.add(EcouteursPerso.class, ecouteursPerso);
	}
	
	/**
	 * Ajoute les écouteurs pour le mode arcade.
	 * @param ecouteursPerso : Les écouteurs personnalisés de type EcouteursmodeArcade.
	 */
	//Ruibin Wang
	public void addEcouteursModeArcade(EcouteursModeArcade ecouteursPerso) {
	    OBJETS_ENREGISTRES.add(EcouteursModeArcade.class, ecouteursPerso);
	}
	
	/**
	 * Lève un évènement lorsqu'une boule est rentrée.
	 */
	//Ruibin Wang
	private void leverUneBouleEstRentree(boolean isBlanche) {
		for (EcouteursPerso ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPerso.class)) {
			ecoute.uneBouleEstRentree(isBlanche);
		}
	}
	
	/**
	 * Écouteur qui définit le pourcentage de boost accumulé.
	 */
	private void leverBoost() {
		for (EcouteursModeArcade ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursModeArcade.class)) {
			ecoute.boost(getBoost());
		}
	}
	
	
	/**
	 * Détecter collision entre boules.
	 */
	//Ruibin Wang
	private void collisionBouleBoule() {
		for (int a = 0 ; a < listeBoules.size() ; a++) {
			for (int b = a+1 ; b < listeBoules.size() ; b++) {
				Boule3D b1=listeBoules.get(a);
				Boule3D b2=listeBoules.get(b);
				if (!b1.estRentre()&&!b2.estRentre()&&Outil3D.norme(Outil3D.vecEntrePos(b1.getPositionCentreDeMasse(),b2.getPositionCentreDeMasse())) <= b1.getRayon()*2) {
	
					
						double e = 8./9.;
						double[] vA0 = b1.getVitesseLineaire();
						double[] vB0 = b2.getVitesseLineaire();
						double[] p2;
						double[] p1;
						if(Outil3D.norme(vB0)>0.1){
							p2=Outil3D.additionVec(b2.getPositionCentreDeMasse(), Outil3D.vecteurNorme(vB0, b1.getRayon()/16));
								
						}else{
							 p2=b1.getPositionCentreDeMasse();
						}
						if(Outil3D.norme(vA0)>0.1){
							p1=Outil3D.additionVec(b1.getPositionCentreDeMasse(), Outil3D.vecteurNorme(vA0, b1.getRayon()/16));
								
						}else{
							 p1=b1.getPositionCentreDeMasse();
						}
						
						double distance=Outil3D.distanceEntrePos(p1, p2);
						if(Outil3D.distanceEntrePos(b1.getPositionCentreDeMasse(), b2.getPositionCentreDeMasse())>distance){
							double[] n = Outil3D.normaliser(Outil3D.soustractionVec(b2.getPositionCentreDeMasse(), b1.getPositionCentreDeMasse()));
							
							double jerkN = -(1+e)*(Outil3D.prodScal(Outil3D.soustractionVec(vA0, vB0), n))/(2/Boule3D.getMasse());
							
							b1.setVitesseLineaire(Outil3D.additionVec(vA0, Outil3D.scal(n, jerkN/Boule3D.getMasse())));
							b2.setVitesseLineaire(Outil3D.soustractionVec(vB0, Outil3D.scal(n, jerkN/Boule3D.getMasse())));
							
						
						}
						
						if(Outil3D.distanceEntrePos(b1.getPositionCentreDeMasse(), b2.getPositionCentreDeMasse())>Boule3D.getDefaultRayon()*100*2){
							
							double [] vec=Outil3D.vecEntrePos(b1.getPositionCentreDeMasse(), b2.getPositionCentreDeMasse());
							double [] pointMilieu=Outil3D.additionVec(b1.getPositionCentreDeMasse(), Outil3D.scal(vec, 0.5));
							b1.setPositionCentreDeMasse(Outil3D.additionVec(pointMilieu, Outil3D.vecteurNorme(vec, 0.5*Boule3D.getDefaultRayon()*100)));
							b2.setPositionCentreDeMasse(Outil3D.additionVec(pointMilieu, Outil3D.vecteurNorme(vec, -0.5*Boule3D.getDefaultRayon()*100)));
						
						}
						
					}
			}
		}
	}
	/**
	 * Détecter collision entre boules et cotes
	 */
	//Francis Gosselin
	private void collisionBoulePrisme() {
		for (Boule3D boule : listeBoules) {
			for(Prisme3D m: coteDessin){
				boule.traiterCollisionMur(m);
			}
		}
	}
	
	/**
	 * Détecter si une boule est dans un trou
	 */
	//Francis Gosselin
	private void bouleEntreDansTrou() {
		for (Boule3D boule : listeBoules) {
			boolean estRentree = false;
			for(Disque d: trous){
				if(boule.estDansTrou(d)){
					if(boule.isWhite()){
						bouleBlanche.setPositionCentreDeMasse(POS_BLANCHE_INI);;
					}
					estRentree = true;
				}
			}
			if (estRentree) leverUneBouleEstRentree(boule.isWhite());
		}
	}
	
	/**
	 * Retourne si le dessin utilise l'anticrénelage.
	 * @return true si le dessin utilise l'anticrénelage, false sinon.
	 */
	//Francis Gosselin
	public boolean isAntiAlliasing() {
		return antiAlliasing;
	}
	/**
	 * Modifie la volonté d'utiliser l'anticrénelage.
	 * @param antiAlliasing : boolean indiquant la volonté d'utiliser l'anticrénelage.
	 */
	//Francis Gosselin
	public void setAntiAlliasing(boolean antiAlliasing) {
		this.antiAlliasing = antiAlliasing;
		repaint();
	}
	
	/**
	 * Retourne la durée d'un pas en secondes.
	 * @return la durée d'un pas en secondes.
	 */
	//Francis Gosselin
	public double getDeltaTemps() {
		return deltaTemps;
	}
	/**
	 * Modifie la durée d'un pas.
	 * @param deltaTemps : La nouvelle durée d'un pas en secondes.
	 */
	//Francis Gosselin
	public void setDeltaTemps(double deltaTemps) {
		this.deltaTemps = deltaTemps;
	}
	
	/**
	 * Ajuste la sensibilité de la souris
	 * @param x la sensibilité, en pourcentage
	 */
	public void ajusterSensibilite(double x){
		sensibiliteSouris=x;
	}
	
	/**
	 * Retourne le pourcentage du boost acquis
	 * @return le boost
	 */
	public double getBoost(){
		return boost/boostMax*100;
	}
	
	/**
	 * Recommence le jeu
	 * @param b le nombre de boules voulu au commencement
	 */
	public void recommencer(int b){
		nbBoules=b;
		creerListeBoules();
	}
}
