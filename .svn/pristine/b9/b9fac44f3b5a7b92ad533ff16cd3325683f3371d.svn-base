package modes_jeu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import dessin.Plan3D;
import dessin.ZoneDessinArcade;
import ecouteurs.EcouteursModeArcade;
import ecouteurs.EcouteursPersoFenetres;

public class PartieArcade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ZoneDessinArcade zoneDessinArcade;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private JSlider slider;
	private JProgressBar progressBar;
	

	/**
	 * Create the frame.
	 */
	public PartieArcade() {
		Plan3D.transparent(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1296, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnMenu = new JButton("Retour au menu");
		btnMenu.setRequestFocusEnabled(false);
		btnMenu.setFocusable(false);
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leverRetourAuMenu();
			}
		});
		btnMenu.setBounds(1064, 78, 187, 48);
		contentPane.add(btnMenu);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 89, 391, 37);
		contentPane.add(progressBar);
		
		JLabel lblAppuyezSurShift = new JLabel("Lorsque la barre est au maximum\r\n");
		lblAppuyezSurShift.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAppuyezSurShift.setBounds(74, 11, 275, 47);
		contentPane.add(lblAppuyezSurShift);
		
		JLabel lblAppuyezSurShift_1 = new JLabel("Appuyez sur Shift pour produire un boost de vitesse");
		lblAppuyezSurShift_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAppuyezSurShift_1.setBounds(24, 52, 377, 23);
		contentPane.add(lblAppuyezSurShift_1);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setRequestFocusEnabled(false);
		slider.setFocusable(false);
		slider.setValue(15);
		slider.setSnapToTicks(true);
		slider.setMinimum(5);
		slider.setMaximum(25);
		slider.setBounds(793, 29, 229, 48);
		contentPane.add(slider);
		
		JButton btnRecommencer = new JButton("Recommencer");
		btnRecommencer.setFocusPainted(false);
		btnRecommencer.setFocusTraversalKeysEnabled(false);
		btnRecommencer.setFocusable(false);
		btnRecommencer.setRequestFocusEnabled(false);
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneDessinArcade.recommencer(slider.getValue());
			}
		});
		btnRecommencer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRecommencer.setBounds(1064, 11, 187, 48);
		contentPane.add(btnRecommencer);
		
		JLabel lblNombreDeBoules = new JLabel("Nombre de boules au d\u00E9part\r\n");
		lblNombreDeBoules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreDeBoules.setBounds(819, 4, 214, 37);
		contentPane.add(lblNombreDeBoules);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Commandes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(441, 29, 326, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAvancerw = new JLabel("Avancer: 'w'");
		lblAvancerw.setBounds(29, 21, 150, 23);
		panel.add(lblAvancerw);
		lblAvancerw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblReculerw = new JLabel("Reculer: 's'");
		lblReculerw.setBounds(29, 55, 150, 23);
		panel.add(lblReculerw);
		lblReculerw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblSauterEspace = new JLabel("Sauter: 'espace'");
		lblSauterEspace.setBounds(144, 21, 150, 23);
		panel.add(lblSauterEspace);
		lblSauterEspace.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblBoostDeVitesse = new JLabel("boost de vitesse: 'shift'");
		lblBoostDeVitesse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBoostDeVitesse.setBounds(144, 55, 150, 23);
		panel.add(lblBoostDeVitesse);
		creerZoneDessin();
		
		
	}
	
	private void creerZoneDessin(){
		zoneDessinArcade = new ZoneDessinArcade(slider.getValue());
		zoneDessinArcade.setFocusTraversalKeysEnabled(false);
		zoneDessinArcade.setBounds(0, 137, 1251, 597);
		zoneDessinArcade .setFocusable(false);
		zoneDessinArcade .requestFocusInWindow();
		zoneDessinArcade .setRequestFocusEnabled(false);
		zoneDessinArcade .requestFocus();
		contentPane.add(zoneDessinArcade);
		
		JLabel lblSensibilitSouris = new JLabel("Sensibilit\u00E9 souris");
		lblSensibilitSouris.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSensibilitSouris.setBounds(857, 78, 119, 37);
		contentPane.add(lblSensibilitSouris);
		
		JSlider slider_1 = new JSlider();
		slider_1.setFocusable(false);
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoneDessinArcade.ajusterSensibilite(slider_1.getValue());
			}
		});
		slider_1.setValue(140);
		slider_1.setMinimum(30);
		slider_1.setMaximum(180);
		slider_1.setBounds(793, 108, 229, 23);
		contentPane.add(slider_1);
		
		zoneDessinArcade.addEcouteursModeArcade(new EcouteursModeArcade(){
			public void boost(double d){
				progressBar.setValue((int)d);
			}
		});
	}
	
	public void focus(){
		
		zoneDessinArcade.setVisible(true);
		zoneDessinArcade.setFocusable(true);
		zoneDessinArcade.requestFocusInWindow();
		
	}
	
	public void addEcouteursPersoFenetres(EcouteursPersoFenetres ecouteursPersoFenetres) {
	    OBJETS_ENREGISTRES.add(EcouteursPersoFenetres.class, ecouteursPersoFenetres);
	}
	
	/**
	 * Lève un évènement lorsque l'utilisateur désire retourner au menu principal.
	 */
	public void leverRetourAuMenu() {
		for (EcouteursPersoFenetres ecoute : OBJETS_ENREGISTRES.getListeners(EcouteursPersoFenetres.class)) {
			ecoute.retourAuMenuListener();
		}
	}
}
