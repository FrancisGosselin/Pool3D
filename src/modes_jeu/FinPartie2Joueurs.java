package modes_jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import ecouteurs.EcouteursPersoFenetres;
	/**
	 * Fenêtre qui s'ouvre lors de la fin de la partie 2 joueurs
	 * @author Francis Gosselin
	 */
public class FinPartie2Joueurs extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String jSerie,joueur;
	private int coup,mSerie;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

	/**
	 * Constructeur
	 * @param n le nom du joueur gagnant
	 * @param jCoup le joueur ayant fait le meilleur coup
	 * @param jSerie le joueur ayant fait la meilleur serie
	 * @param coup le nombre de coups
	 * @param mCoup le meilleur coup
	 * @param mSerie la meilleure Série
	 * @throws HeadlessException Exception
	 */
	public FinPartie2Joueurs(String n, String jCoup, String jSerie, int coup, int mCoup, int mSerie)
			throws HeadlessException {
		super();
		
		this.joueur=n;
		this.jSerie = jSerie;
		this.coup = coup;
		this.mSerie = mSerie;
		peindre();
	}

	/**
	 * Créer le panel
	 */
	private void peindre(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 395);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250,250,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		
		JLabel lblLeJoueur = new JLabel(joueur+" a gagn\u00E9!");
		lblLeJoueur.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblLeJoueur.setBounds(27, 4, 360, 77);
		contentPane.add(lblLeJoueur);
		
		
		JLabel lblNombreDeCoups = new JLabel("Nombre de coups jou\u00E9s : ");
		lblNombreDeCoups.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNombreDeCoups.setBounds(44, 109, 206, 50);
		contentPane.add(lblNombreDeCoups);
		
		JLabel label = new JLabel(coup+"");
		label.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label.setBounds(273, 123, 70, 26);
		contentPane.add(label);
		
		JLabel lblMeilleureSrieDe = new JLabel("Meilleure S\u00E9rie : ");
		lblMeilleureSrieDe.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblMeilleureSrieDe.setBounds(44, 179, 193, 26);
		contentPane.add(lblMeilleureSrieDe);
		
		JLabel lblNewLabel = new JLabel(mSerie+"");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(273, 179, 78, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblPar = new JLabel("Par : ");
		lblPar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblPar.setBounds(121, 216, 54, 26);
		contentPane.add(lblPar);
		
		JLabel label_1 = new JLabel(jSerie);
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		label_1.setBounds(273, 216, 83, 26);
		
		contentPane.add(label_1);
		
		Gif gif = new Gif();
		gif.setBounds(397, 23, 310, 295);
		contentPane.add(gif);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Retourner au menu");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leverRetourAuMenu();
			}
		});
		btnNewButton.setBounds(78, 268, 244, 51);
		contentPane.add(btnNewButton);
	}
	
	
	/**
	 * Écouteur pour revenir au menu
	 * @param ecouteursPerso l'écouteur
	 */
	public void addEcouteursPersoFenetres(EcouteursPersoFenetres ecouteursPerso) {
	    OBJETS_ENREGISTRES.add(EcouteursPersoFenetres.class, ecouteursPerso);
	}
	
	/**
	 * Lève l'événement de retour au menu
	 */
	private void leverRetourAuMenu() {
		for (EcouteursPersoFenetres ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPersoFenetres.class)) {
			ecoute.retourAuMenuListener();
		}
	} 
}
