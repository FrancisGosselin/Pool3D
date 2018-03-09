package choixCouleur;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import ecouteurs.EcouteursChoixCouleur;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoixCouleur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DessinChoixCouleur dessinChoixCouleur;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private JButton btnNewButton;
	private Color c;

	/**
	 * Constructeur.
	 * @param couleurDefault la couleur par défault de la queue
	 */
	public ChoixCouleur( Color couleurDefault) {
		
		setTitle("Choix de la couleur de la queue");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 890, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dessinChoixCouleur = new DessinChoixCouleur();
		dessinChoixCouleur.setBounds(614, 21, 151, 312);
		dessinChoixCouleur.setCouleur(couleurDefault);
		contentPane.add(dessinChoixCouleur);
		
		JColorChooser colorChooser = new JColorChooser();
		colorChooser.setBounds(10, 0, 594, 422);
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent arg0) {
	                Color color = colorChooser.getColor();
	                c=new Color(color.getRed(),color.getGreen(),color.getBlue(),255);
	                dessinChoixCouleur.setCouleur(c);
	                changementCouleur(c);
	            }
	        });
		contentPane.add(colorChooser);
		
		btnNewButton = new JButton("Confimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitter();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(775, 166, 89, 36);
		contentPane.add(btnNewButton);
	}
	
	/**
	 * Permet de fermer la fenêtre
	 */
	private void quitter(){
		this.setVisible(false);
	}
	
	/**
	 * Permet de changer la couleur 
	 * @param c la couleur de la queue
	 */
	private void changementCouleur(Color c) {
		for (EcouteursChoixCouleur ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursChoixCouleur.class)) {
			ecoute.choixCouleur(c);
		}
	}
	
	/**
	 * Ajoute l'écouteur personnalisé
	 * @param ecouteursPerso l'écouteur 
	 */
	public void addEcouteursChoixCouleur(EcouteursChoixCouleur ecouteursPerso) {
	    OBJETS_ENREGISTRES.add(EcouteursChoixCouleur.class, ecouteursPerso);
	}
	

	
}
