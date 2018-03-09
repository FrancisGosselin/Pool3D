package modes_jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import dessin.Boule3D;
import dessin.ZoneDessin;
import ecouteurs.EcouteursPerso;
import ecouteurs.EcouteursPersoFenetres;
import joueur.DessinBoulesRestantes;
import joueur.Joueur;
import menus_aide.ConceptsPhysiques;
import javax.swing.JTextField;

/**
 * Zone de jeu pour le mode à deux joueurs.
 * @author Ruibin Wang
 *
 */
public class Partie2Joueurs extends JFrame{
	private static ZoneDessin zoneDessin;
	private static final long serialVersionUID = 1L;
	private Joueur j1;
	private Joueur j2;
	private Joueur joueurEnCours;
	private static ConceptsPhysiques conceptsPhysiques;
	private static DessinBoulesRestantes boulesJ1;
	private static DessinBoulesRestantes boulesJ2;
	private static DessinBoulesRestantes typeBoulesJ1;
	private static DessinBoulesRestantes typeBoulesJ2;
	private boolean bouleRentre=false;
	private int meilleurSerie=0;
	private int meilleurCoup=0;
	private int serieActuelle=0;
	private static FinPartie2Joueurs fin;
	private int coups=0;
	
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private JTextField txtFriction;
	private JTextField txtMasse;
	private JTextField txtAccelG;
	private JLabel lblTempsSimInfo;
	private JLabel lblMeilleureSerieInfo;
	private JLabel lblMeilleureSerieParInfo;
	private JLabel lblForceDeFrappeInfo;
	private JLabel lblAccelBlancheInfo;
	private JLabel lblVitesseInfo;
	private JLabel lblSuitesInfo;
	private JLabel lblDistanceParcourueInfo;
	private JPanel panelCouleur;
	
	/**
	 * Constructeur.
	 * @param nom1 : le nom du joueur 1.
	 * @param nom2: le nom du joueur 2.
	 * @param c1 : la couleur de la queue choisie par le joueur 1.
	 * @param c2 : la couleur de la queue choisie par le joueur 2.
	 */
	public Partie2Joueurs(String nom1,String nom2,Color c1,Color c2) {
		setResizable(false);
		
		this.j1=new Joueur(c1,nom1);
		this.j2=new Joueur(c2,nom2);
		
		setTitle("Simulateur : Jeu de billard 2017 par Francis Gosselin & Ruibin Wang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 800);
		getContentPane().setLayout(null);
		
		
		
		boulesJ1=new DessinBoulesRestantes(j1.getTypeBoule());
		boulesJ1.setBounds(394, 92, 200, 30);
		boulesJ1.setNbBoules(0);
		getContentPane().add(boulesJ1);
		
		boulesJ2=new DessinBoulesRestantes(j2.getTypeBoule());
		boulesJ2.setBounds(1023, 92, 219, 30);
		boulesJ2.setNbBoules(0);
		getContentPane().add(boulesJ2);
		
		typeBoulesJ1=new DessinBoulesRestantes(j1.getTypeBoule());
		typeBoulesJ1.setNbBoules(0);
		typeBoulesJ1.setBounds(180, 92, 46, 30);
		getContentPane().add(typeBoulesJ1);
		
		typeBoulesJ2=new DessinBoulesRestantes(j2.getTypeBoule());
		typeBoulesJ2.setNbBoules(0);
		typeBoulesJ2.setBounds(809, 92, 40, 30);
		getContentPane().add(typeBoulesJ2);
		
		creerZoneDessin();
		JLabel lbl2Joueurs = new JLabel("2 Joueurs");
		lbl2Joueurs.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2Joueurs.setFont(new Font("Castellar", Font.BOLD, 48));
		lbl2Joueurs.setBounds(479, 23, 316, 58);
		getContentPane().add(lbl2Joueurs);
		
		JLabel lblTypeBalles1Txt = new JLabel("COULEUR DE BALLES :");
		lblTypeBalles1Txt.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTypeBalles1Txt.setBounds(10, 97, 173, 25);
		getContentPane().add(lblTypeBalles1Txt);
		
		JLabel lblJoueur1Txt = new JLabel("JOUEUR 1 :");
		lblJoueur1Txt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblJoueur1Txt.setBounds(116, 44, 138, 50);
		getContentPane().add(lblJoueur1Txt);
		
		JLabel lblNbBalles1Txt = new JLabel("BALLES RESTANTES :");
		lblNbBalles1Txt.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNbBalles1Txt.setBounds(249, 97, 142, 25);
		getContentPane().add(lblNbBalles1Txt);
		
		JLabel lblJoueur1Info = new JLabel(j1.getNom());
		lblJoueur1Info.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur1Info.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblJoueur1Info.setBounds(239, 44, 189, 50);
		getContentPane().add(lblJoueur1Info);
		
		JLabel lblNbBalles2Txt = new JLabel("BALLES RESTANTES :");
		lblNbBalles2Txt.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNbBalles2Txt.setBounds(859, 97, 154, 25);
		getContentPane().add(lblNbBalles2Txt);
		
		JLabel lblTypeBalles2Txt = new JLabel("COULEUR DE BALLES :");
		lblTypeBalles2Txt.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTypeBalles2Txt.setBounds(640, 97, 173, 25);
		getContentPane().add(lblTypeBalles2Txt);
		
		JLabel lblJoueur2Info = new JLabel(j2.getNom());
		lblJoueur2Info.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur2Info.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblJoueur2Info.setBounds(981, 44, 200, 50);
		getContentPane().add(lblJoueur2Info);
		
		JLabel lblJoueur2Txt = new JLabel("JOUEUR 2 :");
		lblJoueur2Txt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblJoueur2Txt.setBounds(856, 44, 115, 50);
		getContentPane().add(lblJoueur2Txt);
		
		JLabel lblDonnees = new JLabel("DONN\u00C9ES");
		lblDonnees.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonnees.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblDonnees.setBounds(432, 128, 101, 25);
		getContentPane().add(lblDonnees);
		
		JLabel lblForceDeFrappeTxt = new JLabel("Force de frappe (N) :");
		lblForceDeFrappeTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblForceDeFrappeTxt.setBounds(53, 168, 153, 25);
		getContentPane().add(lblForceDeFrappeTxt);
		
		JLabel lblAccelBlancheTxt = new JLabel("Acc\u00E9l\u00E9ration initiale de la balle blanche (m/s^2) :");
		lblAccelBlancheTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccelBlancheTxt.setBounds(53, 196, 264, 25);
		getContentPane().add(lblAccelBlancheTxt);
		
		JLabel lblSuitesTxt = new JLabel("Coups r\u00E9ussis de suite :");
		lblSuitesTxt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSuitesTxt.setBounds(575, 168, 189, 25);
		getContentPane().add(lblSuitesTxt);
		
		lblSuitesInfo = new JLabel("0");
		lblSuitesInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSuitesInfo.setBounds(807, 168, 73, 33);
		getContentPane().add(lblSuitesInfo);
		
		lblForceDeFrappeInfo = new JLabel("0");
		lblForceDeFrappeInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblForceDeFrappeInfo.setBounds(354, 168, 115, 33);
		getContentPane().add(lblForceDeFrappeInfo);
		
		lblAccelBlancheInfo = new JLabel("0");
		lblAccelBlancheInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAccelBlancheInfo.setBounds(354, 196, 115, 33);
		getContentPane().add(lblAccelBlancheInfo);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1264, 21);
		getContentPane().add(menuBar);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mnItemGuideDutilisation = new JMenuItem("Guide d'utilisation");
		mnAide.add(mnItemGuideDutilisation);
		
		JMenuItem mnItemConceptsPhysiques = new JMenuItem("Concepts physiques");
		mnItemConceptsPhysiques.addActionListener(new ActionListener() {
			//Ouvre la fenêtre affichant les informations concernant la physique du jeu. 
			public void actionPerformed(ActionEvent arg0) {
				conceptsPhysiques = new ConceptsPhysiques();
				conceptsPhysiques.setVisible(true);
			}
		});
		mnAide.add(mnItemConceptsPhysiques);
		
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBounds(950, 271, 302, 480);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		panel.setEnabled(false);
		
		JButton btnOptions = new JButton("MENU & OPTIONS");
		btnOptions.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnOptions.setFocusable(false);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setEnabled(!panel.isEnabled());
				panel.setVisible(!panel.isVisible());
				zoneDessin.setBounds(zoneDessin.getX(), zoneDessin.getY(), panel.isVisible()?941:1229, zoneDessin.getHeight());
				btnOptions.setText(panel.isEnabled()?"Masquer les options":"Options");
			}
		});
		btnOptions.setBounds(959, 250, 279, 20);
		getContentPane().add(btnOptions);
		
		JCheckBox chckBoxTableFantome = new JCheckBox("Table fant\u00F4me");
		chckBoxTableFantome.setFocusable(false);
		chckBoxTableFantome.setBounds(10, 255, 136, 25);
		panel.add(chckBoxTableFantome);
		chckBoxTableFantome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneDessin.transparent(chckBoxTableFantome.isSelected());
			}
		});
		chckBoxTableFantome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JPanel panelPhys = new JPanel();
		panelPhys.setBounds(10, 0, 279, 248);
		panel.add(panelPhys);
		panelPhys.setBackground(Color.LIGHT_GRAY);
		panelPhys.setLayout(null);
		
		JLabel lblPhysique = new JLabel("Physique");
		lblPhysique.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhysique.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhysique.setBounds(76, 11, 127, 20);
		panelPhys.add(lblPhysique);
		
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
		sliderMasse.setFocusable(false);
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
		sliderGravite.setFocusable(false);
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
		lblCoeffFrottement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeffFrottement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoeffFrottement.setBounds(45, 31, 188, 26);
		panelPhys.add(lblCoeffFrottement);
		
		JLabel lblMasse = new JLabel("Masse des balles (g)");
		lblMasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasse.setBounds(45, 101, 188, 26);
		panelPhys.add(lblMasse);
		
		JLabel lblConstanteGravitationnelle = new JLabel("Acc\u00E9l\u00E9ration gravitationnelle (m/s^2)");
		lblConstanteGravitationnelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblConstanteGravitationnelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConstanteGravitationnelle.setBounds(25, 171, 228, 26);
		panelPhys.add(lblConstanteGravitationnelle);
		
		txtFriction = new JTextField(Math.round(Boule3D.getDefaultCoefffrottementGlisse()*1000)/1000.+"");
		txtFriction.setFocusable(false);
		txtFriction.setColumns(10);
		txtFriction.setBounds(222, 65, 55, 20);
		panelPhys.add(txtFriction);
		
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
		
		JButton btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setFocusable(false);
		btnRetourAuMenu.setBounds(156, 420, 136, 23);
		panel.add(btnRetourAuMenu);
		
		JButton btnRecommencer = new JButton("Recommencer");
		btnRecommencer.setFocusable(false);
		btnRecommencer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Rectangle rekt = zoneDessin.getBounds();
				zoneDessin.arreter();
				getContentPane().remove(zoneDessin);
				creerZoneDessin();
				zoneDessin.setBounds(rekt);
				getContentPane().revalidate();
				getContentPane().repaint();
				
				lblForceDeFrappeInfo.setText(0+"");
				lblTempsSimInfo.setText(0+"");
				lblSuitesInfo.setText(0+"");
				lblAccelBlancheInfo.setText(0+"");
				
				lblMeilleureSerieInfo.setText("");
				lblVitesseInfo.setText(0+"");
				lblDistanceParcourueInfo.setText(0+"");
				
				zoneDessin.setJoueurEnCours(joueurEnCours);
				
				panelCouleur.setBounds(((joueurEnCours.equals(j1))?78:830), panelCouleur.getY(), panelCouleur.getWidth(), panelCouleur.getHeight());
				panelCouleur.setBackground(joueurEnCours.getCouleur());
				bouleRentre = false;
				
			}
		});
		btnRecommencer.setBounds(10, 420, 136, 23);
		panel.add(btnRecommencer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFocusable(false);
		btnQuitter.setBounds(10, 446, 279, 23);
		panel.add(btnQuitter);
		
		JCheckBox chckBoxAA = new JCheckBox("Anticr\u00E9nelage");
		chckBoxAA.setFocusable(false);
		chckBoxAA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneDessin.setAntiAlliasing(chckBoxAA.isSelected());
			}
		});
		chckBoxAA.setSelected(true);
		chckBoxAA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		chckBoxAA.setBounds(10, 275, 136, 25);
		panel.add(chckBoxAA);
		
		JCheckBox chckbxAideAuTir = new JCheckBox("Afficher l'aide au tir");
		chckbxAideAuTir.setFocusable(false);
		chckbxAideAuTir.setBounds(153, 276, 136, 23);
		panel.add(chckbxAideAuTir);
		chckbxAideAuTir.setSelected(true);
		chckbxAideAuTir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				zoneDessin.afficherTir(chckbxAideAuTir.isSelected());	
			}
		});
		chckbxAideAuTir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		JLabel lblVitesseAnim = new JLabel("Vitesse d'animation");
		lblVitesseAnim.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitesseAnim.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblVitesseAnim.setBounds(57, 335, 188, 26);
		panel.add(lblVitesseAnim);
		
		JSlider sliderVitesseAnim = new JSlider();
		sliderVitesseAnim.setValue((int)ZoneDessin.getDefaultSleep());
		sliderVitesseAnim.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoneDessin.setSleep((long)sliderVitesseAnim.getValue());
			}
		});
		sliderVitesseAnim.setMaximum(10);
		sliderVitesseAnim.setMinimum(1);
		sliderVitesseAnim.setFocusable(false);
		sliderVitesseAnim.setBounds(51, 355, 200, 23);
		panel.add(sliderVitesseAnim);
		
		JSlider sliderSensibilite = new JSlider();
		sliderSensibilite.setFocusable(false);
		sliderSensibilite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoneDessin.ajusterSensibilite(sliderSensibilite.getValue());
			}
		});
		sliderSensibilite.setValue(100);
		sliderSensibilite.setMaximum(180);
		sliderSensibilite.setMinimum(30);
		sliderSensibilite.setBounds(51, 320, 200, 23);
		panel.add(sliderSensibilite);
		
		JLabel lblSensibilite = new JLabel("Sensibilit\u00E9 de la souris");
		lblSensibilite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSensibilite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSensibilite.setBounds(57, 300, 188, 26);
		panel.add(lblSensibilite);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Afficher l'\u00E9chelle");
		chckbxNewCheckBox.setFocusable(false);
		chckbxNewCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoneDessin.afficherEchelle(chckbxNewCheckBox.isSelected());
			}
		});
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		chckbxNewCheckBox.setBounds(153, 256, 136, 23);
		panel.add(chckbxNewCheckBox);
		
		JPanel panelStats = new JPanel();
		panelStats.setBounds(959, 142, 279, 110);
		getContentPane().add(panelStats);
		panelStats.setBackground(Color.LIGHT_GRAY);
		panelStats.setLayout(null);
		
		
		JLabel lblStatistiques = new JLabel("Statistiques");
		lblStatistiques.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStatistiques.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistiques.setBounds(77, 6, 127, 20);
		panelStats.add(lblStatistiques);
		
		JLabel lblMeilleureSerieTxt = new JLabel("Meilleure S\u00E9rie :");
		lblMeilleureSerieTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMeilleureSerieTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMeilleureSerieTxt.setBounds(43, 37, 93, 20);
		panelStats.add(lblMeilleureSerieTxt);
		
		JLabel lblMeilleureSerieParTxt = new JLabel("par :");
		lblMeilleureSerieParTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMeilleureSerieParTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMeilleureSerieParTxt.setBounds(43, 68, 93, 19);
		panelStats.add(lblMeilleureSerieParTxt);
		
		lblMeilleureSerieInfo = new JLabel("");
		lblMeilleureSerieInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMeilleureSerieInfo.setBounds(146, 37, 93, 20);
		panelStats.add(lblMeilleureSerieInfo);
		
		lblMeilleureSerieParInfo = new JLabel("");
		lblMeilleureSerieParInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMeilleureSerieParInfo.setBounds(146, 68, 93, 20);
		panelStats.add(lblMeilleureSerieParInfo);
		
		JLabel lblTempsSim = new JLabel("Temps de simulation (s) :");
		lblTempsSim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTempsSim.setBounds(575, 196, 189, 25);
		getContentPane().add(lblTempsSim);
		
		lblTempsSimInfo = new JLabel("0");
		lblTempsSimInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTempsSimInfo.setBounds(807, 196, 73, 33);
		getContentPane().add(lblTempsSimInfo);
		
		JLabel lblVitesseDeLa = new JLabel("Vitesse de la balle blanche (m/s^2) :");
		lblVitesseDeLa.setBounds(53, 226, 219, 14);
		getContentPane().add(lblVitesseDeLa);
		
		lblVitesseInfo = new JLabel("0");
		lblVitesseInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVitesseInfo.setBounds(354, 222, 115, 33);
		getContentPane().add(lblVitesseInfo);
		
		JLabel lblDistanceParcourue = new JLabel("Distance parcourue (m) :");
		lblDistanceParcourue.setBounds(575, 226, 142, 14);
		getContentPane().add(lblDistanceParcourue);
		
		lblDistanceParcourueInfo = new JLabel("0");
		lblDistanceParcourueInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDistanceParcourueInfo.setBounds(807, 222, 90, 33);
		getContentPane().add(lblDistanceParcourueInfo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 142, 424, 5);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(540, 142, 414, 5);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 259, 944, 1);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(953, 142, 2, 116);
		getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(10, 144, 19, 116);
		getContentPane().add(separator_4);
		
		panelCouleur = new JPanel();
		panelCouleur.setBackground(joueurEnCours.getCouleur());
		panelCouleur.setBounds(78, 48, 358, 42);
		getContentPane().add(panelCouleur);
		
		btnQuitter.addActionListener(new ActionListener() {
			//Ferme la fenêtre au clic du bouton.
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leverRetourAuMenu();
			}
		});
		
	}
	/**
	 * Crée la zone de dessin.
	 */
	public void creerZoneDessin(){
		this.j1=new Joueur(j1.getCouleur(),j1.getNom());
		this.j2=new Joueur(j2.getCouleur(),j2.getNom());
		
		joueurEnCours = j1;
		
		zoneDessin = new ZoneDessin();
		zoneDessin.setJoueurEnCours(j1);
		zoneDessin.setBounds(10, 271, 1242, 480);

		zoneDessin.setFocusable(true);
		zoneDessin.requestFocusInWindow();
		zoneDessin.setRequestFocusEnabled(true);
		zoneDessin.requestFocus();
		focus();
		getContentPane().add(zoneDessin);
		
		zoneDessin.addEcouteursPerso((new EcouteursPerso(){
			public void immobilisationListener(){
				coups++;
				
				if(zoneDessin.bouleBlancheRentrer()){
					zoneDessin.setBlancheIni();
				}
				
				if(zoneDessin.bouleNoirRentrer()){
					Joueur jgagnant=joueurEnCours;
					if(bouleRentre){
						if(zoneDessin.getNbBoules(joueurEnCours.getTypeBoule())==0){
							jgagnant=joueurEnCours;
						}else{
							if(joueurEnCours.equals(j1)){
								jgagnant=j2;
							}else{
								jgagnant=j1;
							}
						}
					}else{
						if(joueurEnCours.equals(j1)){
							jgagnant=j2;
						}else{
							jgagnant=j1;
						}
					}
					fin=new FinPartie2Joueurs(jgagnant.getNom(),"",
												lblMeilleureSerieParInfo.getText(),coups,meilleurCoup,meilleurSerie);
					fin.addEcouteursPersoFenetres(new EcouteursPersoFenetres(){

						@Override
						public void retourAuMenuListener() {							
							leverRetourAuMenu();
						}
						
					});
					fin.setVisible(true);
				}
				
				if(bouleRentre){
					boulesJ1.setNbBoules(zoneDessin.getNbBoules(j1.getTypeBoule()));
					boulesJ2.setNbBoules(zoneDessin.getNbBoules(j2.getTypeBoule()));
					if(joueurEnCours.equals(j1)){
						if(!zoneDessin.typeBouleRentrer(j1.getTypeBoule())){
							joueurEnCours=j2;
							serieActuelle=0;
						}else{
							
							if(zoneDessin.getNbBouleRentreDurantTour()>meilleurCoup){
								meilleurCoup=zoneDessin.getNbBouleRentreDurantTour();
							}
							serieActuelle++;
							if(serieActuelle>meilleurSerie){
								lblMeilleureSerieInfo.setText(""+serieActuelle);
								lblMeilleureSerieParInfo.setText(j1.getNom());
								meilleurSerie=serieActuelle;
							}
							
						}
						lblSuitesInfo.setText(""+serieActuelle);
					}else{
						if(!zoneDessin.typeBouleRentrer(j2.getTypeBoule())){
							joueurEnCours=j1;
							serieActuelle=0;
						}else{
							
							if(zoneDessin.getNbBouleRentreDurantTour()>meilleurCoup){
								meilleurCoup=zoneDessin.getNbBouleRentreDurantTour();
							}
							serieActuelle++;
							if(serieActuelle>meilleurSerie){
								lblMeilleureSerieInfo.setText(""+serieActuelle);
								lblMeilleureSerieParInfo.setText(j2.getNom());
								meilleurSerie=serieActuelle;
							}
						}
						lblSuitesInfo.setText(""+serieActuelle);
					}
				}else{
					if(joueurEnCours.equals(j1)){
						joueurEnCours=j2;
					}else{
						joueurEnCours=j1;
					}
				}
				zoneDessin.setJoueurEnCours(joueurEnCours);
				
				panelCouleur.setBounds(((joueurEnCours.equals(j1))?78:830), panelCouleur.getY(), panelCouleur.getWidth(), panelCouleur.getHeight());
				panelCouleur.setBackground(joueurEnCours.getCouleur());
			}
			
			public void bouleEstRentree(Color c){
				
				bouleRentre=true;
				if(joueurEnCours.equals(j1)){
					j1.setTypeBoule(c);
					if(c.equals(Color.red)){
						j2.setTypeBoule(Color.blue);
					}else{
						j2.setTypeBoule(Color.red);
					}
				}else{
					j2.setTypeBoule(c);
					if(c.equals(Color.red)){
						j1.setTypeBoule(Color.blue);
					}else{
						j1.setTypeBoule(Color.red);
					}
				}
				boulesJ1.setNbBoules(7);
				boulesJ1.setCouleur(j1.getTypeBoule());
				boulesJ2.setNbBoules(7);
				boulesJ2.setCouleur(j2.getTypeBoule());
				
				typeBoulesJ1.setNbBoules(1);
				typeBoulesJ1.setCouleur(j1.getTypeBoule());
				typeBoulesJ2.setNbBoules(1);
				typeBoulesJ2.setCouleur(j2.getTypeBoule());
				
			}
			public void valeursChangeListener(double tempsInterne, double vitesse, double distanceParcourue){
				
				lblTempsSimInfo.setText(Math.round(tempsInterne*100)/100.+"");
				lblVitesseInfo.setText(vitesse+"");
				lblDistanceParcourueInfo.setText(distanceParcourue + "");
				
				
			}
			@Override
			public void frappeListener(double force, double accel) {
				lblForceDeFrappeInfo.setText(force+"");
				lblAccelBlancheInfo.setText(accel+"");
			}
			@Override
			public void uneBouleEstRentree(boolean isBlanche) {
			}
		}));

		boulesJ1.setNbBoules(0);
		boulesJ2.setNbBoules(0);
		typeBoulesJ1.setNbBoules(0);
		typeBoulesJ2.setNbBoules(0);
	
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
	 * Donne le focus à la zone d'animation
	 */
	public void focus(){
		zoneDessin.setVisible(true);
		zoneDessin.requestFocusInWindow();
	}
}
