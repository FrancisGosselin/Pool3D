package modes_jeu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
	/**
	 * Zone de dessin du gif
	 * @author Francis Gosselin
	 */
public class Gif extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private Image img;
	private int k=1;

	/**
	 * Create the panel.
	 */
	public Gif() {
		demarrer();

	}
	
	/**
	 * peint le composant
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		lireImage();
		g2d.drawImage(img,0,0,null);
		
	}
	
	/**
	 * Lis les images
	 */
	private void lireImage(){
		img=null;
		URL url=getClass().getClassLoader().getResource("frame"+k+".gif");
		if(url==null){
			JOptionPane.showMessageDialog(null, "Fichier de fond d'écran introuvable");
		}
		
		try{
			img=ImageIO.read(url).getSubimage(60, 160, 350, 300);
			
		}catch(Exception e){
			System.out.println("Erreur lors de la lecture de l'image");
		}
	
	}


	/**
	 * boucle d'animation
	 */
	@Override
	public void run() {
		while(true){
			
			k=(k%29)+1;
			
			repaint();
			
			
			
			try{
				Thread.sleep(30);
			}catch(Exception e){
				
			}
		}
		
	}
	
	/**
	 * Démarre l'animation
	 */
	public void demarrer(){
		Thread proc=new Thread(this);
		proc.start();
	}
}
