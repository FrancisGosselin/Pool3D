package dessin;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import calculs.Physique;
import ecouteurs.EcouteursPerso;
import joueur.Joueur;
import scene.Tour;

/**
 * zone de dessin
 * 
 * @author Ruibin Wang
 * @author Francis Gosselin
 */
public class ZoneDessin extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private double debutX,debutY,debutXA,debutYA;
	private double angleX=270.1,angleY=75;
	private final double ANGLE_X_INI=0,ANGLE_Y_INI=30;
	private int nbBoulesRentre=0;
	private boolean afficherTir=true;
	private boolean placerBlanche=false;
	private boolean unJoueur=false;
	
	private Prisme3D dessin2;
	private double distance=0;
	private ArrayList<Objet3D> arbre2=new ArrayList<Objet3D>();

	private ArrayList<Objet3D> pattes2=new ArrayList<Objet3D>();
	private ArrayList<Objet3D> table=new ArrayList<Objet3D>();
	private ArrayList<Objet3D> cote=new ArrayList<Objet3D>();
	private ArrayList<Objet3D> coins=new ArrayList<Objet3D>();
	private ArrayList<Prisme3D> coteDessin=new ArrayList<Prisme3D>();
	private ArrayList<Disque> trous=new ArrayList<Disque>();
	private ArrayList<Plan3D> tapis=new ArrayList<Plan3D>();
	private Tour tour;
	private boolean premierFoisApresCoup=false;
	
	private double sensibiliteSouris=100;
	
	private double deplacementHoriz=3,deplacementVert=3;
	private double dep=10;
	private double[] posCam={1000,1000,1000};
	private double[] vecRegard=Outil3D.calculVecRegard(angleX, angleY);
	private double[] vecteurUni2;
	private boolean lock=true;
	private double distancePosRegard=450;
	private double[] posRegard={125,225,250};
	private final double[] POS_REGARD_FRAPPE={125,225,250};
	private double zoom=800;
	
	private boolean bouleEstRentree=false;
	private BouleBlanche3D bouleBlanche;
	private Boule3D boule1, boule2, boule3, boule4, boule5, boule6, boule7, boule8, boule9, boule10, bouleNoir, boule12, boule13, boule14, boule15;
	private ArrayList<Boule3D> listeBoules;
	private ArrayList<Boule3D> listeBoulesRouges;
	private ArrayList<Boule3D> listeBoulesBleues;
	private int nbRouges=7,nbBleu=7,nbNoir=1;
	private boolean bleuRentre=false,rougeRentre=false,noirRentre=false;
	
	private double distanceBouleQueue=20;
	private boolean deplaceQueue=false;
	private double posEtirementY=0;
	private double kDeplacement=0.5;
	private Joueur joueurEnCours;
	private boolean afficherEchelle=true;

	
	private boolean anim=false;
	private boolean antiAlliasing=true;
	private Queue q;
	private static final double DEFAULT_DELTA_TEMPS = 0.01;
	private double deltaTemps = DEFAULT_DELTA_TEMPS;
	private static final long DEFAULT_SLEEP = 5;
	private long sleep = DEFAULT_SLEEP;
	private boolean aFrapper=false;
	private double tempsEcoule = 0;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

	
	/**
	 * Constructeur.
	 */
	//Francis Gosselin
	public ZoneDessin() {
		Boule3D.setCoeffFrottementGlisse(Boule3D.getDefaultCoefffrottementGlisse());
		creerTableEtPattes();
		creerBoules();
		creerCotes();
		creerCoins();
		creerTrous();
		
		
		arbre2.addAll(cote);
		arbre2.addAll(coins);
		
	
		/* 
		* Change la direction de la boule blanche 
		* selon les touches w et s
		* 
		*/
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				double k=1;
				
				if(arg0.isShiftDown()){
					
					k=50;

					if(c=='D'||c=='A'){
						
						if(c=='D'){
							deplacementHoriz=dep*k;
						}else{
							deplacementHoriz=-dep*k;
						}
						
						double[] s=Outil3D.copieVec(vecteurUni2);
						s=Outil3D.normaliser(s);
						s=Outil3D.scal(s, deplacementHoriz);
						posCam=Outil3D.additionVec(s, posCam);
					}
					if(c=='S'||c=='W'){
						if(c=='W'){
							distance=-dep*k;
						}else{
							distance=dep*k;
						}
						double[] p={0,0,0};
						p[0]=vecRegard[0]*distance;
						p[2]=vecRegard[2]*distance;
						posCam=Outil3D.additionVec(posCam, p);
						
							
					}
					
					
					if(c=='R'||c=='F'){	
							if(c=='R'){
								deplacementVert=dep*k;
							}else{
								
								deplacementVert=-dep*k;
							}
							
						double[] v={0,deplacementVert,0};	
						posCam=Outil3D.additionVec(posCam, v)	;
							
						}
					
					
				}
				
				
				
				if(c=='u'){
					lock=!lock;
				}
				
				if(c=='d'||c=='a'){
					
					if(c=='d'){
						deplacementHoriz=dep*k;
					}else{
						deplacementHoriz=-dep*k;
					}
					
					double[] s=Outil3D.copieVec(vecteurUni2);
					s=Outil3D.normaliser(s);
					s=Outil3D.scal(s, deplacementHoriz);
					posCam=Outil3D.additionVec(s, posCam);
					
				}
				if(c=='s'||c=='w'){
					
					if(c=='w'){
						distance=-dep*k;
					}else{
						distance=dep*k;
					}
					if(lock){
						if(distancePosRegard>=200||distance>=0){
							
							distancePosRegard+=distance;
						}
						
					}
					double[] p={0,0,0};
					p[0]=vecRegard[0]*distance;
					p[2]=vecRegard[2]*distance;
					posCam=Outil3D.additionVec(posCam, p);
					
						
				}
				
				if(c=='r'||c=='f'){	
						if(c=='r'){
							deplacementVert=dep*k;
						}else{
							
							deplacementVert=-dep*k;
						}
						
					double[] v={0,deplacementVert,0};	
					posCam=Outil3D.additionVec(posCam, v)	;
						
				
					}
					repaint();
				}
					
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
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
				
				double x1=(arg0.getX()-getWidth()/2.0);
				double y1=(-arg0.getY()+getHeight()/2.0);
				if(q.getArea().contains(x1,y1)){
					deplaceQueue=true;
					posEtirementY=y1;
				}else{
					double x=arg0.getX();
					double y=arg0.getY();
	
					 x=arg0.getX();
					 y=arg0.getY();
						debutX=x;
						debutY=y;
						debutXA=angleX;
						debutYA=angleY;
				}
				
					
				
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(q.getEtirement()>0){	
					demarrer();
				}
				
				deplaceQueue=false;
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
				if(!aFrapper&&isEnabled()){
					if(deplaceQueue){
						double y1=(-arg0.getY()+getHeight()/2.0);
						q.setEtirement((posEtirementY-y1)*kDeplacement);
						
					}else{
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
							
					}
				repaint();
				}
				
				}
			
		});
		setBackground(Color.white);
		repaint();
	}
	/**
	 * Créer la liste de pattes, le tapis et la partie basse de la table 
	 */
	//Francis
	private void creerTableEtPattes(){
		Color couleurPattes=new Color(86, 47, 14);
		double hauteur=190;
		dessin2=new Prisme3D(0,20,0,20,hauteur,20,angleX,angleY,posCam,couleurPattes);
		pattes2.add(dessin2);
		
		dessin2=new Prisme3D(0,20,480,20,hauteur,20,angleX,angleY,posCam,couleurPattes);
		pattes2.add(dessin2);
		
		dessin2=new Prisme3D(230,20,0,20,hauteur,20,angleX,angleY,posCam,couleurPattes);
		pattes2.add(dessin2);
		
		dessin2=new Prisme3D(230,20,480,20,hauteur,20,angleX,angleY,posCam,couleurPattes);
		pattes2.add(dessin2);
		
		Color couleurTable=new Color(10, 108, 3);
		dessin2=new Prisme3D(0,199.9,0,125,20,250,angleX,angleY,posCam,couleurPattes);
		table.add(dessin2);
		dessin2=new Prisme3D(125,199.9,0,125,20,250,angleX,angleY,posCam,couleurPattes);
		table.add(dessin2);
		dessin2=new Prisme3D(0,199.9,250,125,20,250,angleX,angleY,posCam,couleurPattes);
		table.add(dessin2);
		dessin2=new Prisme3D(125,199.9,250,125,20,250,angleX,angleY,posCam,couleurPattes);
		table.add(dessin2);
		
		double[] vecX={125,0,0};
		double[] vecZ={0,0,250};
		Plan3D p;
		p =new Plan3D(vecZ,vecX,new double[]{0,220,0},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ,vecX,new double[]{124,220,0},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ,vecX,new double[]{0,220,248},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
		p =new Plan3D(vecZ,vecX,new double[]{124,220,248},angleX,angleY,1500,posCam,vecRegard,couleurTable);
		p.dessinerContour(false);
		tapis.add(p);
	}
	
	/**
	 * Créer la liste de boules
	 */
	//RuiBin
	private void creerBoules(){
		listeBoules = new ArrayList<Boule3D>();
		listeBoulesBleues = new ArrayList<Boule3D>();
		listeBoulesRouges = new ArrayList<Boule3D>();
		double[] vec={125,220+Boule3D.getDefaultRayon()*100,100};
		bouleBlanche= new BouleBlanche3D(posCam, vecRegard, vec,Boule3D.getDefaultRayon()*100,angleX,angleY,Color.white);

		//Création de la boule blanche

		
		//5eme ligne
		boule1 = new Boule3D(posCam, vecRegard, new double[] {125+4*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		boule2 = new Boule3D(posCam, vecRegard, new double[] {125+2*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.blue);
		boule3 = new Boule3D(posCam, vecRegard, new double[] {125, 220+Boule3D.getDefaultRayon()*100, 500*0.75}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		boule4 = new Boule3D(posCam, vecRegard, new double[] {125-2*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		boule5 = new Boule3D(posCam, vecRegard, new double[] {125-4*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.blue);
		//4eme ligne
		boule6 = new Boule3D(posCam, vecRegard, new double[] {125+3*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-2*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY,Color.blue);
		boule7 = new Boule3D(posCam, vecRegard, new double[] {125+Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-2*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		boule8 = new Boule3D(posCam, vecRegard, new double[] {125-Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-2*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY,Color.blue);
		boule9 = new Boule3D(posCam, vecRegard, new double[] {125-3*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-2*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		//3eme ligne
		boule10 = new Boule3D(posCam, vecRegard, new double[] {125+2*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-4*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY,Color.red);
		boule12 = new Boule3D(posCam, vecRegard, new double[] {125-2*Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-4*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.blue);
		//2eme ligne
		boule13 = new Boule3D(posCam, vecRegard, new double[] {125+Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-6*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY,Color.blue);
		boule14 = new Boule3D(posCam, vecRegard, new double[] {125-Boule3D.getDefaultRayon()*100, 220+Boule3D.getDefaultRayon()*100, 500*0.75-6*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.red);
		//premiere boule
		boule15 = new Boule3D(posCam, vecRegard, new double[] {125, 220+Boule3D.getDefaultRayon()*100, 500*0.75-8*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.blue);
	
		
		listeBoulesRouges.add(boule1);
		listeBoulesRouges.add(boule3);
		listeBoulesRouges.add(boule4);
		listeBoulesRouges.add(boule7);
		
		listeBoulesRouges.add(boule9);
		listeBoulesRouges.add(boule10);
		listeBoulesRouges.add(boule14);
		
		listeBoulesBleues.add(boule2);
		listeBoulesBleues.add(boule5);
		listeBoulesBleues.add(boule6);
		listeBoulesBleues.add(boule8);
		
		listeBoulesBleues.add(boule12);
		listeBoulesBleues.add(boule13);
		listeBoulesBleues.add(boule15);
	
		bouleNoir = new Boule3D(posCam, vecRegard, new double[] {125, 220+Boule3D.getDefaultRayon()*100, 500*0.75-4*Boule3D.getDefaultRayon()*100}, Boule3D.getDefaultRayon()*100, angleX, angleY, Color.black);
		listeBoules.add(bouleNoir);
		listeBoules.add(bouleBlanche);

		listeBoules.addAll(this.listeBoulesRouges);
		listeBoules.addAll(this.listeBoulesBleues);
		
	}
	
	/**
	 * Créer les Cotés de la table
	 **/
	 //Francis
	private void creerCotes(){
		Color c=new Color(50,50,50);
		
		//haut
		dessin2=new Prisme3D(25.1,220.1,0,99.9,10,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		dessin2=new Prisme3D(125.1,220.1,0,99.8,10,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		//bas
		dessin2=new Prisme3D(25.1,220.1,485,99.9,10,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		dessin2=new Prisme3D(125.1,220.1,485,99.8,10,15,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
	
		//gauche
		dessin2=new Prisme3D(0,220.1,25.1,15,10,209.8,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		dessin2=new Prisme3D(0,220.1,265.1,15,10,209.8,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		//droite
		
		dessin2=new Prisme3D(235,220.1,25.1,15,10,209.8,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		
		dessin2=new Prisme3D(235,220.1,265.1,15,10,209.8,angleX,angleY,posCam,c);
		coteDessin.add(dessin2);
		
		cote.addAll(coteDessin);
		
	}
	
	/**
	 * Créer les coins de la table
	 */
	//Francis
	private void creerCoins(){
		//Coin du haut
				Color c=new Color(211,211,211);
				double[] p1={15,220.1,15};
				Coin co=new Coin(25,220.1,25,-25,10,-25,10,angleX,angleY,posCam,p1,c);
				
				 coins.add(co);
				
				double[] p2={235,220.1,15};
				co=new Coin(225,220.1,25,25,10,-25,10,angleX,angleY,posCam,p2,c);
				coins.add(co);
			
				
				//Coins de milieu 
				co=new Coin(235,220.1,250,15,10,-14.9,10,angleX,angleY,posCam,c);
				coins.add(co);
				
				co=new Coin(235,220.1,250,15,10,14.9,10,angleX,angleY,posCam,c);
				coins.add(co);
				
				co=new Coin(15,220.1,250,-15,10,-14.9,10,angleX,angleY,posCam,c);
				coins.add(co);
				
				co=new Coin(15,220.1,250,-15,10,14.9,10,angleX,angleY,posCam,c);
				coins.add(co);
				
				//Coins du bas
				double[] p3={15,220.1,485};
				co=new Coin(25,220.1,475,-25,10,25,10,angleX,angleY,posCam,p3,c);
				coins.add(co);
				
				double[] p4={235,220.1,485};
				co=new Coin(225,220.1,475,25,10,25,10,angleX,angleY,posCam,p4,c);
				coins.add(co);
				
	}
	
	/**
	 * Créer la liste de trous
	 */
	//Francis
	private void creerTrous(){
		Disque d=new Disque(14,219.95,14,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		d=new Disque(250-14,219.95,14,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		
		d=new Disque(250-10,219.95,250,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		d=new Disque(10,219.95,250,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		d=new Disque(14,219.95,486,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
		
		d=new Disque(250-14,219.95,486,10,angleX,angleY,posCam,vecRegard,Color.black);
		trous.add(d);
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
					if(aFrapper){
					posRegard=this.POS_REGARD_FRAPPE;
					angleX=this.ANGLE_X_INI;
					angleY=this.ANGLE_Y_INI;
					
				}else{
					posRegard=bouleBlanche.getCentre();
				}
				double[] vecteurUni=Outil3D.vecteurY(angleX, angleY);
				vecteurUni2=Outil3D.vecteurX(angleX);
				
				vecRegard=Outil3D.produitVec(vecteurUni2,vecteurUni);
				
				vecRegard=Outil3D.normaliser(vecRegard);
	
					if(aFrapper){
						posCam=Outil3D.scal(vecRegard, zoom);
					}else{
						posCam=Outil3D.scal(vecRegard, distancePosRegard);
					}
					
					posCam=Outil3D.additionVec(posCam, posRegard);
					
					
					
					
			}
			
				double[] temp=Outil3D.copieVec(vecRegard);
					temp[1]=0;
					temp=Outil3D.vecteurNorme(temp, distanceBouleQueue);
					temp=Outil3D.additionVec(bouleBlanche.getCentre(), temp);
				q.setVectRegard(vecRegard);
				q.setPosition(posCam);
				q.setAngleX(angleX);
				q.setAngleY(angleY);
				q.setOrigine(temp);
				
				vecRegard=Outil3D.normaliser(vecRegard);
			for(Objet3D c: arbre2){
				c.setVectRegard(vecRegard);
				c.setPosition(posCam);
				c.setAngleX(angleX);
				c.setAngleY(angleY);
				
			}
			
			for(Objet3D c: pattes2){
				c.setVectRegard(vecRegard);
				c.setPosition(posCam);
				c.setAngleX(angleX);
				c.setAngleY(angleY);
				
				
			}
			
			for(Objet3D c: table){
				c.setVectRegard(vecRegard);
				c.setPosition(posCam);
				c.setAngleX(angleX);
				c.setAngleY(angleY);
				
			}
			
			vecRegard=Outil3D.normaliser(vecRegard);
			
			ArrayList<Objet3D> ar=new ArrayList<Objet3D>();
			for(Objet3D o:pattes2){
				
				ar.addAll(o.getFaces());
			}
			Collections.sort(ar);
			for(Objet3D h:ar){
				
				h.dessiner(g2d);
			}
	
		
		ar=new ArrayList<Objet3D>();
			
	
		for(Objet3D o:table){
			
			ar.add(o);
		}
		
		Collections.sort(ar);
		for(Objet3D h:ar){
			
			h.dessiner(g2d);
		}
		
		for(Plan3D p:tapis){
			p.setVectRegard(vecRegard);
			p.setPosition(posCam);
			p.setAngleX(angleX);
			p.setAngleY(angleY);
			p.dessiner(g2d);
		}
		
	
		
		

		
		
		ArrayList<Objet3D> a=new ArrayList<Objet3D>();
		for(Objet3D b:listeBoules){
			a.add(b);
		}
		for(Objet3D b:a){
			b.setVectRegard(vecRegard);
			b.setPosition(posCam);
			b.setAngleX(angleX);
			b.setAngleY(angleY);
		}
		
		ar=new ArrayList<Objet3D>();
		if(!aFrapper&&afficherTir){
			dessinerVec(g2d);
		}
		
		
		Collections.sort(a);
		
		Objet3D p=new Prisme3D(16,220.1,25.1,218,10,449,angleX,angleY,posCam,Color.green);
		p.setVectRegard(vecRegard);
		p.setPosition(posCam);
		p.setAngleX(angleX);
		p.setAngleY(angleY);
		
		
		
		if(aFrapper){
			if(this.premierFoisApresCoup){
				tour=new Tour(arbre2);
				tour.addWithGhost(p, a);
				this.premierFoisApresCoup=false;
			}
			tour.updateGhost(a);
		}else{
			tour=new Tour(arbre2);
			tour.addWithGhost(p, a);
			
		}
		
		for(Objet3D u:trous){
				u.setVectRegard(vecRegard);
				u.setPosition(posCam);
				u.setAngleX(angleX);
				u.setAngleY(angleY);
				u.dessiner(g2d);
		}
		
		tour.dessiner(g2d);
	
		
		if(afficherEchelle){
			dessinerEchelle(g2d);
		}
		if(!aFrapper){
			q.dessiner(g2d);
		}
		
		
		
	}
	
	
	/**
	 * Méthode permettant de dessiner un vecteur 3D
	 * @param le contexte graphique
	 */
	//Francis Gosselin
	private void dessinerVec(Graphics2D g2d){
		
		double[] temp=Outil3D.copieVec(vecRegard);
		temp[1]=0;
		temp=Outil3D.vecteurNorme(temp, -300);
		
		double[] ori=Outil3D.additionVec(bouleBlanche.getCentre(), Outil3D.vecteurNorme(temp, 10));
		
		ArrayList<Objet3D> temp2=Outil3D.copie(arbre2);
		temp2.addAll(listeBoules);
		temp2.addAll(trous);
		double[] point=Outil3D.intersectionXZ(temp,ori, temp2);
		temp=Outil3D.vecEntrePos(bouleBlanche.getCentre(), point);
		
		boolean v=true;
		if(this.bouleEstRentree&&!unJoueur){
			
			if(bouleNoir.contient(point)){
				boolean check=true;
				if(joueurEnCours.getTypeBoule().equals(Color.red)){
					for(Boule3D b:listeBoulesRouges){
						if(!b.estRentre())check=false;
					}
					v=check;
				}else{
					for(Boule3D b:listeBoulesBleues){
						if(!b.estRentre())check=false;
					}
					v=check;
				}
			}else{
				if(joueurEnCours.getTypeBoule().equals(Color.red)){
					for(Boule3D b:listeBoulesBleues){
						if(b.contient(point)){
							v=false;
						}
					}		
				}else{
					for(Boule3D b:listeBoulesRouges){
						if(b.contient(point)){
							v=false;
						}
					}
				}
			}
		}	
		if(v){
			g2d.setColor(Color.green);
		}else{
			g2d.setColor(Color.red);
		}
		Stroke s=g2d.getStroke();
		
		g2d.setStroke(new BasicStroke(2));
		Outil3D.dessinerVecteur(g2d, temp,Outil3D.norme(q.getForceFrappe())/30+30,Outil3D.additionVec(bouleBlanche.getCentre(), new double[] {0,-100*Boule3D.getDefaultRayon(),0}) , vecRegard, posCam, angleX, angleY);
		boolean trouve=false;
		for(Objet3D o:listeBoules){
			
			if(!trouve&&o.contient(point)&&o.getClass().equals(Boule3D.class)){
				trouve=true;
				Boule3D b2=(Boule3D)o;
				double e = 8./9.;
				double[] vA0 = Outil3D.scal(vecRegard, 10);
				vA0[1]=0;
				double[] vB0 = b2.getVitesseLineaire();
				
				
				double[] n = Outil3D.normaliser(Outil3D.soustractionVec(b2.getPositionCentreDeMasse(), point));
				
				double jerkN = -(1+e)*(Outil3D.prodScal(Outil3D.soustractionVec(vA0, vB0), n))/(2/Boule3D.getMasse());
				
				
				double[] vB=Outil3D.soustractionVec(vB0, Outil3D.scal(n, jerkN/Boule3D.getMasse()));
				
				
				
				temp=Outil3D.copieVec(vB);
				temp[1]=0;
				temp=Outil3D.vecteurNorme(temp, -100);
				
				
				ori=Outil3D.additionVec(o.getCentre(), Outil3D.vecteurNorme(temp, 10));
				
				point=Outil3D.intersectionXZ(temp,ori, temp2);
				
				temp=Outil3D.vecEntrePos(o.getCentre(), point);
				Outil3D.dessinerVecteur(g2d, temp,30,Outil3D.additionVec(o.getCentre(), new double[] {0,-100*Boule3D.getDefaultRayon(),0}) , vecRegard, posCam, angleX, angleY);
	
				
			}
		}
		g2d.setStroke(s);
	}
	
	/**
	 * Permet de dessiner l'échelle
	 * @param g2d le contexte graphique
	 */
	//Francis
	private void dessinerEchelle(Graphics2D g2d){
		AffineTransform a=g2d.getTransform();
		
		double[] p1={270,230,0},p2={270,230,500};
		double[] v={10,0,0};
		Color c=g2d.getColor();
		
		g2d.setColor(Color.black);
		
		
		Outil3D.dessinerLigne(g2d, Outil3D.additionVec(p1, v), Outil3D.additionVec(p1, Outil3D.scal(v, -1)), vecRegard, posCam, angleX, angleY);
		Outil3D.dessinerLigne(g2d, Outil3D.additionVec(p2, v), Outil3D.additionVec(p2, Outil3D.scal(v, -1)), vecRegard, posCam, angleX, angleY);
		Outil3D.dessinerLigne(g2d, p1,p2 , vecRegard, posCam, angleX, angleY);
		
		
		
		
		double[] pos=Outil3D.additionVec(p1, Outil3D.scal(Outil3D.vecEntrePos(p1, p2), 0.5));
		double[] p=Outil3D.trouverPos(pos, posCam, vecRegard, angleX, angleY);
		g2d.scale(1, -1);
		
		g2d.drawString("5.00 m", ((int)p[0]-50), -(int)p[1]);
		g2d.setTransform(a);
		g2d.setColor(c);
	}
	
	
	/**
	 * Boucle d'animation.
	 */
	//Ruibin Wang
	@Override
	public void run() {
		while(anim){
			if(!aFrapper){
				
				if(q.getEtirementSelonBoule()>0){
					q.prochaineImage();
				}else{
					aFrapper=true;
					bouleBlanche.setForceQueue(q.getForceFrappe());
					
					this.premierFoisApresCoup=true;
					
					
					for (Boule3D boule : listeBoules) {
						boule.initialiseForces();
					}
					Physique.vitesseApresFrappe(bouleBlanche);
					
					Physique.vitesseAngulaire(bouleBlanche);
					leverFrappe(Math.abs(Outil3D.norme(q.getForceFrappe())), Math.abs(Outil3D.norme(bouleBlanche.getAccelLineaire())));
					prochaineImage(1);
				}
			}else{			
				prochaineImage(1);
			}

			try{
				Thread.sleep(sleep);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if (aFrapper&&sceneImmobile()) {
				if(!unJoueur){
					Color c=bouleEstRentreeDurantTour();
					if(!bouleEstRentree&&!c.equals(Color.gray)&&!c.equals(Color.white)&&!c.equals(Color.black)){
						bouleEstRentree=true;
						
						leverBouleRentree(bouleEstRentreeDurantTour());
					}
					int kRouge=getNbBoules(Color.red),kBleu=getNbBoules(Color.blue);
					if(kBleu<nbBleu){
						nbBleu=kBleu;
						bleuRentre=true;
					}else{
						nbBoulesRentre=0;
						bleuRentre=false;
					}
					if(kRouge<nbRouges){
						nbRouges=kRouge;
						rougeRentre=true;
					}else{
						nbBoulesRentre=0;
						rougeRentre=false;
					}
					if(bouleEstRentree){
						if(joueurEnCours.getTypeBoule().equals(Color.red)){
							nbBoulesRentre=nbRouges-kRouge;
						}else{
							nbBoulesRentre=nbBleu-kBleu;
						}
					}	
				} else {
					Color c=bouleEstRentreeDurantTour();
					if(!bouleEstRentree&&!c.equals(Color.gray)&&!c.equals(Color.white)){
						bouleEstRentree=true;
						
						leverBouleRentree(bouleEstRentreeDurantTour());
					}
					int kRouge=getNbBoules(Color.red),kBleu=getNbBoules(Color.blue),kNoir = getNbBoules(Color.black);
					
					nbBoulesRentre = (nbBleu + nbRouges + nbNoir)-(kBleu + kRouge + kNoir);
					nbBleu = kBleu;
					nbRouges = kRouge;
					nbNoir = kNoir;
				}
				
				arreter();
				leverImmobilisation();
				aFrapper=false;
				if (angleX == 0) {
					angleX=270;
					angleY=60;
				}
			
				if(placerBlanche){
					placerBlanche=false;
					setBlancheIni();
				}
				repaint();
			}
		}
	}

	/**
	 * Affiche la prochaine image après un pas.
	 */
	//Ruibin Wang
	public void prochaineImage(int nbPas) {
		
		collisionBouleBoule();
		bouleEntreDansTrou();
		collisionBoulePrisme();
		for (int n = 0; n < nbPas; n++) {
			for (Boule3D boule : listeBoules) {
				if (!Outil3D.estEgal(boule.getVitesseLineaire(), new double[]{0, 0, 0}, 0.01)) {
					boule.unPasEuler(deltaTemps);
				}
			}
			tempsEcoule+=deltaTemps;
		}
		leverValeursChange();
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
	 * Lève un évènement lorsque la scène devient immobile.
	 */
	//Ruibin Wang
	private void leverImmobilisation() {
		for (EcouteursPerso ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPerso.class)) {
			ecoute.immobilisationListener();
		}
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
	 * Lève un évènement lorsque des valeurs physiques changent.
	 */
	//Ruibin Wang
	private void leverValeursChange() {
		for (EcouteursPerso ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPerso.class)) {
			ecoute.valeursChangeListener(Math.round(100*tempsEcoule)/100., Math.round(Math.abs(Outil3D.norme(bouleBlanche.getVitesseLineaire())))/100.,(Math.round(bouleBlanche.getDistanceParcourue())/100.));
		}
	}
	
	/**
	 * Lève un évènement lorsque la balle blanche a été frappée.
	 */
	//Ruibin Wang
	private void leverFrappe(double forceFrappe, double accel) {
		for (EcouteursPerso ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPerso.class)) {
			ecoute.frappeListener(Math.round(100*forceFrappe)/100., Math.round(accel*10)/1000.);
		}
	}
	
	/**
	 * Lève un évènement lorsque la première boule sur le jeu est rentrée.
	 * @param c le type de boule
	 */
	//Ruibin Wang
	private void leverBouleRentree(Color c) {
		for (EcouteursPerso ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPerso.class)) {
			ecoute.bouleEstRentree(c);
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
				if (Outil3D.norme(Outil3D.vecEntrePos(b1.getPositionCentreDeMasse(),b2.getPositionCentreDeMasse())) < b1.getRayon()*2) {
					
						double e = 8./9.;
						double[] vA0 = b1.getVitesseLineaire();
						double[] vB0 = b2.getVitesseLineaire();
						double[] p2;
						double[] p1;
						if(Outil3D.norme(vB0)>0.1){
							p2=Outil3D.additionVec(b2.getPositionCentreDeMasse(), Outil3D.vecteurNorme(vB0, b1.getRayon()/4));
								
						}else{
							 p2=b1.getPositionCentreDeMasse();
						}
						if(Outil3D.norme(vA0)>0.1){
							p1=Outil3D.additionVec(b1.getPositionCentreDeMasse(), Outil3D.vecteurNorme(vA0, b1.getRayon()/4));
								
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
							b1.setPositionCentreDeMasse(Outil3D.additionVec(pointMilieu, Outil3D.vecteurNorme(vec,- 0.5*Boule3D.getDefaultRayon()*100)));
							b2.setPositionCentreDeMasse(Outil3D.additionVec(pointMilieu, Outil3D.vecteurNorme(vec, 0.5*Boule3D.getDefaultRayon()*100)));
						
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
						enleverBlanche();
					}
					estRentree = true;
				}
			}
			if (estRentree) leverUneBouleEstRentree(boule.isWhite());
		}
	}
	
	/**
	 * Détecter si une boule est rentrée dans un trou, retourne la couleur de la boule si 
	 * une boule est rentrée, sinon retourne la couleur grise
	 */
	//Francis Gosselin
	private Color bouleEstRentreeDurantTour() {
		for (Boule3D boule : listeBoulesRouges) {		
				if(boule.estRentre()){
					return Color.red;	
			}
		}
		for (Boule3D boule : listeBoulesBleues) {
			if(boule.estRentre()){
				return Color.blue;
			}	
		}
		if(bouleBlanche.estRentre()){	
			return Color.white;
		}
			
		if(bouleNoir.estRentre()){
			return Color.black;
		}
		return Color.gray;
	}
	
	
	
	/**
	 * Détecter si la scène est immobile 
	 */
	//Ruibin Wang
	private boolean sceneImmobile(){
		for(Boule3D b:this.listeBoules){
			if(!b.estRentre()&&Outil3D.norme(b.getVitesseLineaire())>=0.7){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * change le joueur qui est entrain de frapper
	 * @param j le joueur
	 */
	//Francis Gosselin
	public void setJoueurEnCours(Joueur j){
		joueurEnCours=j;
		double[] temp=Outil3D.copieVec(vecRegard);
		temp[1]=0;
		temp=Outil3D.vecteurNorme(temp, distanceBouleQueue);
		temp=Outil3D.additionVec(bouleBlanche.getCentre(), temp);
		
		q=new Queue(temp,angleX,angleY,posCam,vecRegard,this.distanceBouleQueue-this.bouleBlanche.getRayon(),j.getCouleur());
		q.setVectRegard(vecRegard);
		q.setPosition(posCam);
		q.setAngleX(angleX);
		q.setAngleY(angleY);
		q.setOrigine(temp);
		
	}
	
	/**
	 * Détermine si la boule noire est rentrée
	 */
	//Francis Gosselin
	public boolean bouleNoirRentrer(){
		return bouleNoir.estRentre();
	}
	
	/**
	 * Détermine si la boule blanche est rentrée
	 */
	//Francis Gosselin
	public boolean bouleBlancheRentrer(){
		return bouleBlanche.estRentre();
	}
	
	/**
	 * Détermine quelle couleur de boule la boule blanche a touché en premier pendant le tour
	 * @return la type de boule
	 */
	//Francis Gosselin
	public Color premiereBouleTouche(){
		return Color.red;
	}
	
	/**
	 * Détermine si une boule d'une couleur c, est rentrée durant le tour
	 * @param c la couleur
	 * @return vrai si une boule de la couleur demandé, est rentrée durant le tour
	 */
	//Francis Gosselin
	public boolean typeBouleRentrer(Color c){
		if(c.equals(Color.blue)){
			return bleuRentre;
		}
		if(c.equals(Color.red)){
			return rougeRentre;
		}
		if(c.equals(Color.black)) {
			return noirRentre;
		}
		return false;
	}
	
	
	/**
	 * retourne le nombre de boules d'une certaine Couleur qui sont sur la table(rouge ou bleu)
	 * @param c la couleur de boules
	 * @return le nombre de boules sur la table
	 */
	//Francis Gosselin
	public int getNbBoules(Color c){
		int k=0;
		if(c.equals(Color.red)){
			for (Boule3D boule : listeBoulesRouges) {		
				if(!boule.estRentre()){
					k++;
				}
			}
		}
		if(c.equals(Color.blue)){
			for (Boule3D boule : listeBoulesBleues) {		
				if(!boule.estRentre()){
					k++;	
				}
			}
		}
		if(c.equals(Color.black)) k = (bouleNoir.estRentre())?0:1;
		return k;
	}
	/**
	 * Retourne le nombre de boules restantes en tout.
	 * @return le nombre de boules restantes en tout.
	 */
	//Francis Gosselin
	public int getNbBoules() {
		int k=0;
		for (Boule3D boule : listeBoules) {		
			if(!boule.estRentre()){
				k++;
			}
		}
		return k;
	}
	
	/**
	 * Retourne la valeur du sleep par defaut.
	 * @return
	 */
	public static long getDefaultSleep() {
		return DEFAULT_SLEEP;
	}
	/**
	 * Retourne la valeur du sleep.
	 * @return La valeur du sleep en millisecondes.
	 */
	public long getSleep() {
		return sleep;
	}
	/**
	 * Modifie la valeur du sleep.
	 * @param sleep : La nouvelle valeur du sleep en millisecondes.
	 */
	public void setSleep(long sleep) {
		this.sleep = 10-sleep;
	}

	/**
	 * Remet la boule blanche à sa position initiale
	 */
	//Francis Gosselin
	public void setBlancheIni(){
		double[] vec={125,220+Boule3D.getDefaultRayon()*100,80};
		
		bouleBlanche.setPositionCentreDeMasse(vec);
		bouleBlanche.setVitesseLineaire(new double[]{0,0,0});
		
	}
	
	/**
	 * Remet la boule blanche à sa position initiale
	 */
	//Francis Gosselin
	private void enleverBlanche(){
		double[] vec={125,220+Boule3D.getDefaultRayon()*100,-200};
		
		bouleBlanche.setPositionCentreDeMasse(vec);
		bouleBlanche.setVitesseLineaire(new double[]{0,0,0});
		placerBlanche=true;
		
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
	 *///Francis Gosselin
	public void setAntiAlliasing(boolean antiAlliasing) {
		this.antiAlliasing = antiAlliasing;
		repaint();
	}
	
	/**
	 *  Retourne le nombre de boules rentrées durant le tour
	 *  @return le nombre de boules rentrées durant le tour
	 *///Francis Gosselin
	public int getNbBouleRentreDurantTour(){
		return nbBoulesRentre;
	}
	
	/**
	 * Permet d'aficher l'aide au tir
	 * @param c vrai s'il l'on veut afficher l'aide au tir
	 */
	//Francis Gosselin
	public void afficherTir(boolean c){
		afficherTir=c;
		repaint();
	}
	
	/**
	 * Permet de rendre la table transparente
	 * @param c vrai s'il l'on veut que la table soit transparente
	 */
	//Francis Gosselin
	public void transparent(boolean c){
		Plan3D.transparent(c);
		repaint();
	}
	
	/**
	 *  Permet de changer le mode de partie
	 */
	//Francis Gosselin
	public void setUnJoueur(){
		unJoueur=true;
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
	 * Permet d'ajuster la sensibilité de la souris
	 * @param x la sensibilité en pourcentage
	**/
	//Francis Gosselin
	public void ajusterSensibilite(double x){
		sensibiliteSouris=x;
	}
	/**
	 * Permet d'afficher l'échelle
	 * @param b vrai si l'utilisateur veut voir l'échelle
	 */
	//Francis Gosselin
	public void afficherEchelle(boolean b){
		afficherEchelle=b;
		repaint();
	}
	
	public boolean isAnimation(){
		return aFrapper;
	}
	
}
