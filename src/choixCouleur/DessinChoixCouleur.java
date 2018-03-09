package choixCouleur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import dessin.Outil3D;
import dessin.Queue;

public class DessinChoixCouleur extends JPanel {
	private Color c=Color.green;
	private Queue q;
	private final double[] origine={125,0,60};
	private final double[] posCam={125,130,-215.44533};
	private double[] vecRegard={0,0.173648177,-0.9848077};
	private final double angleX=270,angleY=60;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public DessinChoixCouleur() {
		setBackground(Color.white);
		
	}
	
	/**
	 * Dessine les composants
	 * @param g le contexte graphique
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.translate(getWidth()/2.0, getHeight()/2.0);
		g2d.scale(1, -1);
		
		double[] vecteurUni=Outil3D.vecteurY(angleX, angleY);
		double [] vecteurUni2=Outil3D.vecteurX(angleX);
		
		vecRegard=Outil3D.produitVec(vecteurUni2,vecteurUni);
		
		vecRegard=Outil3D.normaliser(vecRegard);
		
		q=new Queue(origine,angleX,angleY,posCam,vecRegard,15,c);
		q.dessiner(g2d);
		
		
	}
	
	/**
	 * Change la couleur de la queue
	 * @param c la couleur voulue
	 */
	public void setCouleur(Color c){
		this.c=c;
		repaint();
	}
	

}
