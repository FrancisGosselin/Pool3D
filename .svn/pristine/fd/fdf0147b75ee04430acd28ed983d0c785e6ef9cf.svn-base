package joueur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Créer un panneau affichant les boules restantes
 * @author Francis Gosselin
 */
public class DessinBoulesRestantes extends JPanel {
	private static final long serialVersionUID = 1L;
	private double rayon = 200./7.;
	private int nbBoules=7;
	private Color c;
	
	/**
	 * Constructeur.
	 * @param c la couleur des boules à dessiner
	 */
	public DessinBoulesRestantes(Color c) {
		this.c=c;	
	}
	
	/**
	 * Dessine le contexte graphique
	 * @param g le contexte graphique
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	
		
		Ellipse2D.Double boule; 
		for(int k=0;k<nbBoules;k++){
			boule=new Ellipse2D.Double(rayon*k,0,rayon,rayon);
			
			g2d.setRenderingHint(
	                RenderingHints.KEY_ANTIALIASING, 
	                RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(c);
			g2d.fill(boule);
			g2d.setColor(Color.black);
			g2d.draw(boule);
		}
		
	}
	
	/**
	 * Change le nombre de boules a dessiner
	 * @param nb le nombre de boules
	 */
	public void setNbBoules(int nb){
		nbBoules=nb;
		repaint();
	}
	/**
	 * Change la couleur des boules
	 * @param c la couleur des boules
	 */
	public void setCouleur(Color c){
		this.c=c;
		repaint();
	}
}
