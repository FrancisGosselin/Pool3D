package modes_jeu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

import classement.Leaderboard;
import dessin.Boule3D;
import dessin.ZoneDessin;
import ecouteurs.EcouteursChrono;
import ecouteurs.EcouteursPerso;
import ecouteurs.EcouteursPersoFenetres;
import joueur.Joueur;
import menus_aide.APropos;
import menus_aide.ConceptsPhysiques;
import menus_aide.GuideUtilisation;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.Dimension;

/**Zone de jeu pour le mode à un joueur.
 * 
 * @author Ruibin Wang
 *
 */
public class Partie1Joueur extends JFrame{
	private static final long serialVersionUID = 1L;
	private static ZoneDessin zoneDessin;
	private static ConceptsPhysiques conceptsPhysiques;
	private static GuideUtilisation guideUtilisation;
	private static APropos aPropos;
	private Chrono lblTempsRestantInfo;
	private Color couleur;
	private int nbPas = 1;
	
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private JTextField txtFriction;
	private JTextField txtMasse;
	private JTextField txtAccelG;
	private JLabel lblTempsSimInfo;
	private JLabel lblForceDeFrappeInfo;
	private JLabel lblSuitesInfo;
	private JLabel lblAccelBlancheInfo;
	private JLabel lblScoreInfo;
	private JLabel lblMeilleureSerieInfo;
	private JButton btnPause;
	private JButton btnUnPas;
	private JLabel lblDistanceParcourueInfo;
	private JLabel lblVitesseInfo;
	private int score;
	private JButton btnOptions;
	private JButton btnClassement;
	private JCheckBox chckBoxTableFantome;
	private JCheckBox checkBox;
	private JCheckBox chkBoxAA;
	private JCheckBox chckBoxAideAuTir;
	private JLabel lblEnPause;
	private JButton btnRecommencer;
	private JButton btnRetourAuMenu;
	private JPanel panel;
	private JSlider sliderNbPas;
	private JLabel lblPas;
	private JButton btnQuitter;
	
	private Leaderboard leaderboard;
	
	/**
	 * Constructeur.
	 * @param couleur : La couleur de la queue choisie par le joueur.
	 */
	public Partie1Joueur(Color couleur) {
		setResizable(false);
		setTitle("Simulateur : Jeu de billard 2017 par Francis Gosselin & Ruibin Wang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 800);
		getContentPane().setLayout(null);
		this.couleur = couleur;
		creerZoneDessin();
		
		JLabel lbl1Joueur = new JLabel("1 JOUEUR");
		lbl1Joueur.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Joueur.setFont(new Font("Castellar", Font.BOLD, 48));
		lbl1Joueur.setBounds(326, 32, 326, 58);
		getContentPane().add(lbl1Joueur);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1264, 21);
		getContentPane().add(menuBar);
		
		JMenu mnAide = new JMenu("Aide");
		mnAide.setFocusable(false);
		menuBar.add(mnAide);
		
		JMenuItem mnItemGuideDutilisation = new JMenuItem("Guide d'utilisation");
		mnItemGuideDutilisation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guideUtilisation = new GuideUtilisation();
				guideUtilisation.setVisible(true);
			}
		});
		
		mnAide.add(mnItemGuideDutilisation);
		
		JMenuItem mnItemConceptsPhysiques = new JMenuItem("Concepts Physiques");
		mnItemConceptsPhysiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conceptsPhysiques = new ConceptsPhysiques();
				conceptsPhysiques.setVisible(true);
			}
		});
		mnAide.add(mnItemConceptsPhysiques);
		
		JMenuItem mnItemAPropos = new JMenuItem("\u00C0 propos");
		mnItemAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aPropos = new APropos();
				aPropos.setVisible(true);
			}
		});
		mnAide.add(mnItemAPropos);
		
		JLabel lblTempsRestant = new JLabel("Temps restant :");
		lblTempsRestant.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTempsRestant.setBounds(656, 169, 139, 25);
		getContentPane().add(lblTempsRestant);
		
		lblTempsRestantInfo = new Chrono("4:00");
		lblTempsRestantInfo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTempsRestantInfo.setBounds(805, 158, 86, 33);
		lblTempsRestantInfo.addEcouteursPerso(new EcouteursChrono() {
			@Override
			public void partieTermineeListener() {
				zoneDessin.setEnabled(false);
				zoneDessin.arreter();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				leaderboard.addToLeaderboard(JOptionPane.showInputDialog("Le temps est écoulé, votre score est de " + score + " points. \nEntrez votre nom pour être ajouté au classement : "), score, dateFormat.format(new Date()));
				
				ouvreLeaderboard();
				
				recommencer();
			}			
		});
		getContentPane().add(lblTempsRestantInfo);
		
		
		lblEnPause = new JLabel("EN PAUSE");
		lblEnPause.setOpaque(true);
		lblEnPause.setBackground(new Color(0, 0, 0, 200));
		lblEnPause.setVisible(false);
		lblEnPause.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblEnPause.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnPause.setBounds(503, 453, 259, 70);
		getContentPane().add(lblEnPause);
		
		JLabel lblScore = new JLabel("Score :");
		lblScore.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblScore.setBounds(717, 99, 61, 21);
		getContentPane().add(lblScore);
		
		lblScoreInfo = new JLabel("0");
		lblScoreInfo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblScoreInfo.setBounds(805, 87, 115, 33);
		getContentPane().add(lblScoreInfo);
		
		JLabel lblForceDeFrappeTxt = new JLabel("Force de frappe (N) :");
		lblForceDeFrappeTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblForceDeFrappeTxt.setBounds(20, 117, 115, 25);
		getContentPane().add(lblForceDeFrappeTxt);
		
		JLabel lblAccelBlancheTxt = new JLabel("Acc\u00E9l\u00E9ration de la balle blanche (m/s^2) :");
		lblAccelBlancheTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccelBlancheTxt.setBounds(20, 143, 209, 25);
		getContentPane().add(lblAccelBlancheTxt);
		
		lblForceDeFrappeInfo = new JLabel("0");
		lblForceDeFrappeInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblForceDeFrappeInfo.setBounds(264, 111, 115, 33);
		getContentPane().add(lblForceDeFrappeInfo);
		
		lblAccelBlancheInfo = new JLabel("0");
		lblAccelBlancheInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAccelBlancheInfo.setBounds(264, 135, 115, 33);
		getContentPane().add(lblAccelBlancheInfo);
		
		JLabel lblSuitesTxt = new JLabel("Coups r\u00E9ussis de suite :");
		lblSuitesTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSuitesTxt.setBounds(389, 117, 122, 25);
		getContentPane().add(lblSuitesTxt);
		
		lblSuitesInfo = new JLabel("0");
		lblSuitesInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSuitesInfo.setBounds(548, 111, 90, 33);
		getContentPane().add(lblSuitesInfo);
		
		panel = new JPanel();
		panel.setFocusable(false);
		panel.setVisible(false);
		panel.setEnabled(false);
		panel.setBounds(940, 215, 314, 538);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setFocusable(false);
		btnRetourAuMenu.setBounds(159, 486, 138, 23);
		panel.add(btnRetourAuMenu);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setFocusable(false);
		btnQuitter.setBounds(10, 515, 287, 23);
		panel.add(btnQuitter);
		
		JPanel panelPhys = new JPanel();
		panelPhys.setFocusable(false);
		panelPhys.setBounds(10, 0, 287, 250);
		panel.add(panelPhys);
		panelPhys.setLayout(null);
		panelPhys.setBackground(Color.LIGHT_GRAY);
		
		JLabel label_16 = new JLabel("Physique");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_16.setBounds(80, 11, 127, 20);
		panelPhys.add(label_16);
		
		JSlider sliderFrottement = new JSlider();
		sliderFrottement.setFocusable(false);
		sliderFrottement.setMinimum(500);
		sliderFrottement.setMaximum(5000);
		sliderFrottement.setValue((int)(Boule3D.getDefaultCoefffrottementGlisse()*1000));
		sliderFrottement.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				Boule3D.setCoeffFrottementGlisse((double)sliderFrottement.getValue()/1000.);
				txtFriction.setText(Math.round(100*Boule3D.getCoeffFrottementGlisse())/100.+"");
			}	
		});
		sliderFrottement.setBackground(Color.LIGHT_GRAY);
		sliderFrottement.setBounds(20, 62, 200, 23);
		panelPhys.add(sliderFrottement);
		
		JSlider sliderMasse = new JSlider();
		sliderMasse.setMinimum(100);
		sliderMasse.setMaximum(1000);
		sliderMasse.setValue((int)(Boule3D.getDefaultMasse()*1000));
		sliderMasse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boule3D.setMasse((double)sliderMasse.getValue()/1000.);
				txtMasse.setText(Math.round(Boule3D.getMasse()*100000)/1000.+"");
			}
		});
		sliderMasse.setBackground(Color.LIGHT_GRAY);
		sliderMasse.setBounds(20, 131, 200, 23);
		panelPhys.add(sliderMasse);
		
		JSlider sliderGravite = new JSlider();
		sliderGravite.setMinimum(4000);
		sliderGravite.setMaximum(20000);
		sliderGravite.setValue((int)(Boule3D.getDefaultAccelG()*1000));
		sliderGravite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boule3D.setAccelG((double)sliderGravite.getValue()/1000.);
				txtAccelG.setText(Math.round(Boule3D.getAccelG()*100)/100.+"");
			}
		});
		sliderGravite.setBackground(Color.LIGHT_GRAY);
		sliderGravite.setBounds(20, 200, 200, 23);
		panelPhys.add(sliderGravite);
		
		JLabel lblCoeffFrottement = new JLabel("Coefficient de frottement");
		lblCoeffFrottement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoeffFrottement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeffFrottement.setBounds(49, 31, 188, 26);
		panelPhys.add(lblCoeffFrottement);
		
		JLabel lblMasse = new JLabel("Masse des balles (g)");
		lblMasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasse.setBounds(49, 101, 188, 26);
		panelPhys.add(lblMasse);
		
		lblTempsSimInfo = new JLabel("0");
		
		btnPause = new JButton("PAUSE");
		btnPause.setFocusable(false);
		
		JLabel lblConstanteGravitationnelle = new JLabel("Acc\u00E9l\u00E9ration gravitationnelle (m/s^2)");
		lblConstanteGravitationnelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblConstanteGravitationnelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConstanteGravitationnelle.setBounds(29, 171, 229, 26);
		panelPhys.add(lblConstanteGravitationnelle);
		
		txtFriction = new JTextField(Math.round(Boule3D.getDefaultCoefffrottementGlisse()*1000)/1000.+"");
		txtFriction.setFocusable(false);
		txtFriction.setBounds(222, 62, 55, 20);
		panelPhys.add(txtFriction);
		txtFriction.setColumns(10);
		
		txtMasse = new JTextField(Math.round(Boule3D.getDefaultMasse()*100000)/1000.+"");
		txtMasse.setFocusable(false);
		txtMasse.setColumns(10);
		txtMasse.setBounds(222, 131, 55, 20);
		panelPhys.add(txtMasse);
		
		txtAccelG = new JTextField(Math.round(Boule3D.getDefaultAccelG()*100)/100.+"");
		txtAccelG.setFocusable(false);
		txtAccelG.setColumns(10);
		txtAccelG.setBounds(222, 200, 55, 20);
		panelPhys.add(txtAccelG);

		btnRecommencer = new JButton("Recommencer");
		btnRecommencer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Rectangle rekt = zoneDessin.getBounds();
				zoneDessin.arreter();
				getContentPane().remove(zoneDessin);
				creerZoneDessin();
				focus();
				zoneDessin.setBounds(rekt);
				getContentPane().revalidate();
				getContentPane().repaint();
				
				lblTempsRestantInfo.reset();
				btnPause.setText("PAUSE");
				btnPause.setEnabled(false);
				btnUnPas.setEnabled(false);
				
				lblForceDeFrappeInfo.setText(0+"");
				lblTempsSimInfo.setText(0+"");
				lblSuitesInfo.setText(0+"");
				lblAccelBlancheInfo.setText(0+"");
				lblScoreInfo.setText(0+"");
				lblDistanceParcourueInfo.setText(0+"");
				lblVitesseInfo.setText(0+"");

				lblMeilleureSerieInfo.setText("");
				lblEnPause.setVisible(false);
			}
			
		});
		btnRecommencer.setFocusable(false);
		btnRecommencer.setBounds(10, 486, 136, 23);
		panel.add(btnRecommencer);
		
		chkBoxAA = new JCheckBox("");
		chkBoxAA.setMargin(new Insets(0, 0, 0, 0));
		chkBoxAA.setBounds(10, 284, 15, 14);
	
		chkBoxAA.setFocusable(false);
		chkBoxAA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		chkBoxAA.setSelected(true);
		chkBoxAA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chkBoxAA.isEnabled()) zoneDessin.setAntiAlliasing(chkBoxAA.isSelected());
			}
		});
		panel.add(chkBoxAA);
		
		chckBoxTableFantome = new JCheckBox("");
		chckBoxTableFantome.setMargin(new Insets(0, 0, 0, 0));
		chckBoxTableFantome.setFocusable(false);
		chckBoxTableFantome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		chckBoxTableFantome.setBounds(10, 263, 15, 14);
		chckBoxTableFantome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckBoxTableFantome.isEnabled()) zoneDessin.transparent(chckBoxTableFantome.isSelected());
			}
		});
		checkBox = new JCheckBox("");
		checkBox.setMargin(new Insets(0, 0, 0, 0));
		checkBox.setFocusable(false);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkBox.isEnabled()) zoneDessin.afficherEchelle(checkBox.isSelected());
			}
		});
		checkBox.setSelected(true);
		checkBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		checkBox.setBounds(148, 263, 15, 14);
		panel.add(checkBox);
		
		panel.add(chckBoxTableFantome);
		
		chckBoxAideAuTir = new JCheckBox("");
		chckBoxAideAuTir.setMargin(new Insets(0, 0, 0, 0));
		chckBoxAideAuTir.setFocusable(false);
		chckBoxAideAuTir.setBounds(148, 284, 15, 14);
		panel.add(chckBoxAideAuTir);
		chckBoxAideAuTir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		chckBoxAideAuTir.setSelected(true);
		
		lblPas = new JLabel("It\u00E9rations \u00E0 parcourir en mode pas-\u00E0-pas");
		lblPas.setFocusable(false);
		lblPas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPas.setBounds(32, 367, 249, 14);
		panel.add(lblPas);
		
		JLabel lblSensibilite = new JLabel("Sensibilit\u00E9 de la souris");
		lblSensibilite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSensibilite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSensibilite.setBounds(63, 313, 188, 26);
		panel.add(lblSensibilite);
		
		JSlider sliderSensibilite = new JSlider();
		sliderSensibilite.setFocusable(false);
		sliderSensibilite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoneDessin.ajusterSensibilite(sliderSensibilite.getValue());
			}
		});
		sliderSensibilite.setValue(100);
		sliderSensibilite.setMinimum(30);
		sliderSensibilite.setMaximum(180);
		sliderSensibilite.setBounds(57, 333, 200, 23);
		panel.add(sliderSensibilite);
		
		JButton btnReduireTemps = new JButton("R\u00E9duire le temps restant");
		btnReduireTemps.setFocusable(false);
		btnReduireTemps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lblTempsRestantInfo.getTempsMillis()> 6000) lblTempsRestantInfo.setTempsMillis(lblTempsRestantInfo.getTempsMillis() -5000);
			}
		});
		btnReduireTemps.setBounds(10, 454, 287, 25);
		panel.add(btnReduireTemps);
		
		sliderNbPas = new JSlider();
		sliderNbPas.setFocusable(false);
		sliderNbPas.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				nbPas = sliderNbPas.getValue();
			}
		});
		sliderNbPas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		sliderNbPas.setValue(1);
		sliderNbPas.setMinorTickSpacing(1);
		sliderNbPas.setMajorTickSpacing(2);
		sliderNbPas.setPaintTicks(true);
		sliderNbPas.setPaintLabels(true);
		sliderNbPas.setMinimum(1);
		sliderNbPas.setMaximum(10);
		sliderNbPas.setBounds(51, 392, 200, 40);
		panel.add(sliderNbPas);
		
		JLabel lblAnticrnelage = new JLabel("Anticr\u00E9nelage");
		lblAnticrnelage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAnticrnelage.setBounds(33, 284, 90, 14);
		panel.add(lblAnticrnelage);
		
		JLabel label = new JLabel("Table fant\u00F4me");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label.setBounds(33, 263, 90, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Afficher l'\u00E9chelle");
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_1.setBounds(171, 263, 126, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Afficher l'aide au tir");
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_2.setBounds(171, 284, 126, 14);
		panel.add(label_2);
		chckBoxAideAuTir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if (chckBoxAideAuTir.isEnabled()) zoneDessin.afficherTir(chckBoxAideAuTir.isSelected());
			}
		});
		
		btnUnPas = new JButton("1 PAS");
		btnUnPas.setFocusable(false);
		
		btnOptions = new JButton("MENU & OPTIONS");
		btnOptions.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnOptions.setFocusable(false);
		btnOptions.setBounds(951, 178, 287, 35);
		getContentPane().add(btnOptions);
				
		btnPause.setBounds(951, 58, 184, 52);
		getContentPane().add(btnPause);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnPause.getText().equals("COMMENCER")) {
					lblTempsRestantInfo.commence();
					btnPause.setText("PAUSE");
					zoneDessin.setEnabled(true);
					
					if (zoneDessin.isAnimation()) {
						zoneDessin.demarrer();
					}
					
					btnUnPas.setEnabled(false);
					lblEnPause.setVisible(false);
					
					Component[] comp = panel.getComponents();
					for (Component c : comp) {
						c.setEnabled(true);
					}
				} else {
					lblTempsRestantInfo.arreter();
					btnPause.setText("COMMENCER");
					zoneDessin.setEnabled(false);
					zoneDessin.arreter();
					btnUnPas.setEnabled(true);
					lblEnPause.setBounds(zoneDessin.getBounds());
					getContentPane().setComponentZOrder(lblEnPause, 0);;
					lblEnPause.setVisible(true);
					
					Component[] comp = panel.getComponents();
					for (Component c : comp) {
						c.setEnabled(false);
					}
					sliderNbPas.setEnabled(true);
					lblPas.setEnabled(true);
					btnRecommencer.setEnabled(true);
					btnRetourAuMenu.setEnabled(true);
					btnQuitter.setEnabled(true);
				}
			}
		});
		btnPause.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnPause.setEnabled(false);
		
		JLabel lblTempsSim = new JLabel("Temps de simulation (s) :");
		lblTempsSim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTempsSim.setBounds(389, 143, 128, 25);
		getContentPane().add(lblTempsSim);
		
		lblTempsSimInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTempsSimInfo.setBounds(548, 135, 90, 33);
		getContentPane().add(lblTempsSimInfo);
		
		btnUnPas.setEnabled(false);
		btnUnPas.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnUnPas.setBounds(1140, 58, 98, 52);
		getContentPane().add(btnUnPas);
		
		JLabel lblVitesse = new JLabel("Vitesse de la balle blanche (m/s) :");
		lblVitesse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVitesse.setBounds(20, 169, 175, 25);
		getContentPane().add(lblVitesse);
		
		lblVitesseInfo = new JLabel("0");
		lblVitesseInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVitesseInfo.setBounds(264, 161, 115, 33);
		getContentPane().add(lblVitesseInfo);
		
		JLabel lblDistanceParcourue = new JLabel("Distance parcourue (m) :");
		lblDistanceParcourue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDistanceParcourue.setBounds(389, 169, 128, 25);
		getContentPane().add(lblDistanceParcourue);
		
		lblDistanceParcourueInfo = new JLabel("0");
		lblDistanceParcourueInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDistanceParcourueInfo.setBounds(548, 161, 90, 33);
		getContentPane().add(lblDistanceParcourueInfo);
		
		btnClassement = new JButton("CLASSEMENT");
		btnClassement.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnClassement.setFocusable(false);
		btnClassement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ouvreLeaderboard();
			}
		});
		btnClassement.setBounds(952, 138, 142, 35);
		getContentPane().add(btnClassement);
		
		JButton btnEffacer = new JButton("EFFACER LES SCORES");
		btnEffacer.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		btnEffacer.setFocusable(false);
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				leaderboard.effacer();
			}
		});
		btnEffacer.setBounds(1096, 138, 142, 35);
		getContentPane().add(btnEffacer);
		
		lblMeilleureSerieInfo = new JLabel("0");
		lblMeilleureSerieInfo.setBounds(805, 122, 115, 33);
		getContentPane().add(lblMeilleureSerieInfo);
		lblMeilleureSerieInfo.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblAnimation = new JLabel("ANIMATION");
		lblAnimation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimation.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblAnimation.setBounds(1030, 30, 139, 25);
		getContentPane().add(lblAnimation);
		
		JLabel lblScores = new JLabel("SCORES");
		lblScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblScores.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblScores.setBounds(1026, 112, 139, 25);
		getContentPane().add(lblScores);
		
		JLabel lblMeilleureSrie = new JLabel("Meilleure s\u00E9rie :");
		lblMeilleureSrie.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblMeilleureSrie.setBounds(650, 134, 128, 21);
		getContentPane().add(lblMeilleureSrie);
		
		JLabel lblDonnes = new JLabel("DONN\u00C9ES");
		lblDonnes.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonnes.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblDonnes.setBounds(255, 83, 139, 25);
		getContentPane().add(lblDonnes);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setSize(new Dimension(0, 10));
		separator.setPreferredSize(new Dimension(0, 10));
		separator.setBounds(10, 96, 250, 10);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 10));
		separator_1.setPreferredSize(new Dimension(0, 10));
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(389, 96, 250, 10);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setSize(new Dimension(0, 10));
		separator_2.setPreferredSize(new Dimension(0, 10));
		separator_2.setForeground(Color.DARK_GRAY);
		separator_2.setBounds(10, 200, 631, 10);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(10, 96, 7, 103);
		getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(641, 96, 7, 103);
		getContentPane().add(separator_4);
		
		btnUnPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoneDessin.prochaineImage(nbPas);
				lblTempsRestantInfo.setTempsMillis(lblTempsRestantInfo.getTempsMillis()-(long)(zoneDessin.getDeltaTemps()*1000*nbPas));
			}
		});
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setEnabled(!panel.isEnabled());
				panel.setVisible(!panel.isVisible());
				zoneDessin.setBounds(zoneDessin.getX(), zoneDessin.getY(), panel.isVisible()?928:1229, zoneDessin.getHeight());
				btnOptions.setText(panel.isEnabled()?"MASQUER LE MENU":"MENU & OPTIONS");
				lblEnPause.setBounds(zoneDessin.getBounds());
			}
		});
		btnQuitter.addActionListener(new ActionListener() {
			//Ferme l'application au clique du bouton
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTempsRestantInfo.arreter();
				leverRetourAuMenu();
			}
		});
	}
	
	/**
	 * Crée la zone de dessin.
	 */
	private void creerZoneDessin() {
		score = 0;
		zoneDessin = new ZoneDessin();
		zoneDessin.setJoueurEnCours(new Joueur(couleur));
		zoneDessin.setUnJoueur();
		zoneDessin.setBounds(10, 215, 1228, 536);
		
		zoneDessin.setFocusable(true);
		zoneDessin.requestFocusInWindow();
		zoneDessin.setRequestFocusEnabled(true);
		zoneDessin.requestFocus();
		focus();
		
		leaderboard = new Leaderboard();
		leaderboard.addEcouteurs(new EcouteursPersoFenetres() {

			@Override
			public void retourAuMenuListener() {
				zoneDessin.setEnabled(false);
				btnOptions.setEnabled(true);
				
				btnClassement.setEnabled(true);
				btnPause.setText("COMMENCER");
				btnPause.setEnabled(true);
				btnUnPas.setEnabled(true);
				
				sliderNbPas.setEnabled(true);
				lblPas.setEnabled(true);
				btnRecommencer.setEnabled(true);
				btnRetourAuMenu.setEnabled(true);
				btnQuitter.setEnabled(true);
			}
		});
		leaderboard.setVisible(false);
		leaderboard.setFocusable(false);
		leaderboard.setRequestFocusEnabled(false);
		leaderboard.setBounds(210, 278, 558, 420);
		getContentPane().add(leaderboard);
		getContentPane().add(zoneDessin);
		
		zoneDessin.addEcouteursPerso((new EcouteursPerso(){
			public void immobilisationListener(){
				Joueur j1=new Joueur(couleur);
				j1.setTypeBoule(Color.red);
				zoneDessin.setJoueurEnCours(j1);
				if (zoneDessin.getNbBouleRentreDurantTour() > 0) {
					lblSuitesInfo.setText((Integer.parseInt(lblSuitesInfo.getText())+zoneDessin.getNbBouleRentreDurantTour()) + "");
				} else {
					lblMeilleureSerieInfo.setText(lblSuitesInfo.getText());
					lblSuitesInfo.setText(0 + "");
				}
			}
			public void bouleEstRentree(Color c){
			}
			public void valeursChangeListener(double tempsInterne, double vitesse, double distanceParcourue){
				lblTempsSimInfo.setText(Math.round(100*tempsInterne)/100. + "");
				lblVitesseInfo.setText(vitesse + "");
				lblDistanceParcourueInfo.setText(distanceParcourue+"");
			}
			@Override
			public void frappeListener(double force, double accel) {
				lblForceDeFrappeInfo.setText(force+"");
				lblAccelBlancheInfo.setText(accel+"");
				lblTempsRestantInfo.commence();
				btnPause.setEnabled(true);
			}
			@Override
			public void uneBouleEstRentree(boolean isBlanche) {
				score+=isBlanche?-200:100;
				
				lblScoreInfo.setText(score +"");
				
				if (zoneDessin.getNbBoules() <= 1) {
					Rectangle rekt = zoneDessin.getBounds();
					zoneDessin.arreter();
					getContentPane().remove(zoneDessin);
					creerZoneDessin();
					zoneDessin.setBounds(rekt);
					getContentPane().revalidate();
					getContentPane().repaint();
					
					score = Integer.parseInt(lblScoreInfo.getText());
				}
				lblTempsRestantInfo.setTempsMillis(lblTempsRestantInfo.getTempsMillis() + (isBlanche?-20000:5000));
			}
		}));
	}
	
	/**
	 * Ajoute les écouteurs personnalisés.
	 * @param ecouteursPersoFenetres : Les écouteurs personnalisés de type EcouteursPersoFenetres.
	 */
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
	
	/**
	 * Donne le focus à la zone de dessin.
	 */
	public void focus(){
		zoneDessin.setVisible(true);
		zoneDessin.setFocusable(true);
		zoneDessin.requestFocusInWindow();
	}
	
	/**
	 * Recommence la partie.
	 */
	private void recommencer() {
		Rectangle rekt = zoneDessin.getBounds();
		zoneDessin.arreter();
		getContentPane().remove(zoneDessin);
		creerZoneDessin();
		zoneDessin.setBounds(rekt);
		getContentPane().revalidate();
		getContentPane().repaint();
		
		lblTempsRestantInfo.reset();
		btnPause.setText("PAUSE");
		btnPause.setEnabled(false);
		btnUnPas.setEnabled(false);
		
		lblForceDeFrappeInfo.setText(0+"");
		lblTempsSimInfo.setText(0+"");
		lblSuitesInfo.setText(0+"");
		lblAccelBlancheInfo.setText(0+"");
		lblScoreInfo.setText(0+"");
		lblDistanceParcourueInfo.setText(0+"");
		lblVitesseInfo.setText(0+"");

		lblMeilleureSerieInfo.setText("");
	}
	
	/**
	 * Ouvre le tableau de classement.
	 */
	private void ouvreLeaderboard() {
		getContentPane().setComponentZOrder(lblEnPause, 0);
		lblEnPause.setBounds(zoneDessin.getBounds());
		lblEnPause.setVisible(true);
		lblTempsRestantInfo.arreter();
		leaderboard.setBounds((zoneDessin.getX() + zoneDessin.getWidth()/2 - leaderboard.getWidth()/2), leaderboard.getY(), leaderboard.getWidth(), leaderboard.getHeight());
		leaderboard.open();
		getContentPane().setComponentZOrder(leaderboard, 0);
		zoneDessin.setEnabled(false);
		zoneDessin.arreter();
		chckBoxTableFantome.setEnabled(false);
		checkBox.setEnabled(false);
		chkBoxAA.setEnabled(false);
		chckBoxAideAuTir.setEnabled(false);
		
		btnOptions.setEnabled(false);
		btnUnPas.setEnabled(false);
		btnPause.setEnabled(false);
		btnClassement.setEnabled(false);
		
		chkBoxAA.setEnabled(false);
		checkBox.setEnabled(false);
		chckBoxAideAuTir.setEnabled(false);
		chckBoxTableFantome.setEnabled(false);
		
		Component[] comp = panel.getComponents();
		for (Component c : comp) {
			c.setEnabled(false);
		}
	}
}
