package dessin;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.TreeSet;
/**
 * Interface des objets en 3D
 * @author Francis Gosselin
 *
 */
public interface Objet3D extends Comparable<Objet3D>{
	/**
	 * D�termine si un point est dans le prisme ou non
	 * @param pos la position du point
	 * @return vrai si le point est dans le prisme
	 */
	public boolean contient(double [] pos);
	
	/**
	 * dessine les faces du prisme en 3D
	 * @param g2d le contexte graphique
	 */
	public void dessiner(Graphics2D g2d);
	
	/**
	 * change la position en 3 dimension de la cam�ra
	 * @param v la position en 3 dimension de la cam�ra
	 */
	public void setPosition(double[] v);
	
	/**
	 * change le vecteur du regard 
	 * @param v le vecteur Regard
	 */
	public void setVectRegard(double[] v);
	
	/**
	 * change l'angle par rapport � l'axe des x
	 * @param angleX angle par rapport � l'axe des x
	 */
	public void setAngleX(double x);
	
	/**
	 * change l'angle par rapport � l'axe des y
	 * @param angleY angle par rapport � l'axe des y
	 */
	public void setAngleY(double y);	
	
	/**
	 * retourne le centre du prisme
	 * @return le centre du prisme
	 */
	public double[] getCentre();

	/**
	 * Retourne les Faces que l'on peut voir du prisme
	 * @return les Faces que l'on peut voir du prisme
	 */
	public TreeSet<Objet3D> getFaces();
	
	/**
	 * retourne l'aire des faces
	 * @return l'aire des 6 faces
	 */
	public Area getArea();
	
	/**
	 * d�termine si l'objet est comprable � un autre objet
	 * @return vrai s'il l'objet est comparable
	 */
	public boolean estComparable(Objet3D o);
}
