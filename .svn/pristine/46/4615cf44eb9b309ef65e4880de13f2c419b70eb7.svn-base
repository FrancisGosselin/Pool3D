package aaplication;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import choixCouleur.ChoixCouleur;
import ecouteurs.EcouteursChoixCouleur;
import ecouteurs.EcouteursPersoFenetres;
import modes_jeu.Partie1Joueur;
import modes_jeu.Partie2Joueurs;
import modes_jeu.PartieArcade;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
/**
 * Application menu principal.
 * 
 * @author Ruibin Wang
 *
 */
public class App24Billard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMainMenuTitle;
	private JTextField txtFieldNom1;
	private JTextField txtFieldNom2;
	private static App24Billard frame;
	private static Partie2Joueurs frame2;
	private static Partie1Joueur frame3;
	private static PartieArcade frameA;
	private static ChoixCouleur choixC1,choixC2,choixCSeul;
	private JLabel labelAutreC2, labelAutreC1; 
	private Color autreC1=Color.green,autreC2=Color.red,c1=Color.green,c2=Color.red,cSeul=Color.red,autreCSeul=Color.green;
	private static Image img;
	private JButton btn2Joueurs;
	private JPanel panel2Joueurs;

	private JLabel labelAutreCSeul;
	private JButton btn1Joueur;
	private JLabel j;
	private JButton btnRetour;
	private JPanel panel1Joueur;
	private JPanel panelPrincipal;

	/**
	 * Lance l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new App24Billard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crée la fenêtre.
	 */
	public App24Billard() throws IOException {
		setResizable(false);
		
		setTitle("Simulateur : Jeu de billard 2017 par Francis Gosselin & Ruibin Wang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1268, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRetour = new JButton("RETOUR");
		btnRetour.setBounds(485, 560, 257, 34);
		contentPane.add(btnRetour);
		
		btnRetour.setBackground(new Color(255, 165, 0));
		btnRetour.setVisible(false);
		btnRetour.setFocusable(false);
		btnRetour.setFont(new Font("Century Gothic", Font.ITALIC, 15));
		btnRetour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel1Joueur.setVisible(false);
				panel2Joueurs.setVisible(false);
				btnRetour.setVisible(false);
				panelPrincipal.setVisible(true);
			}
		});
		
		JPanel panelMainMenu = new JPanel();
		panelMainMenu.setBounds(-26, 0, 1280, 762);
		contentPane.add(panelMainMenu);
		panelMainMenu.setLayout(null);
		
		panel1Joueur = new JPanel();
		panel1Joueur.setBounds(258, 148, 764, 465);
		panelMainMenu.add(panel1Joueur);
		panel1Joueur.setBackground(new Color(140, 230, 140, 100));
		panel1Joueur.setVisible(false);
		
		panel1Joueur.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		panel1Joueur.setLayout(null);
		
		JLabel lblCouleurSolo_1 = new JLabel("COULEUR DE LA QUEUE");
		lblCouleurSolo_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCouleurSolo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleurSolo_1.setBounds(95, 135, 574, 54);
		panel1Joueur.add(lblCouleurSolo_1);
		lblCouleurSolo_1.setForeground(new Color(255, 255, 255));
		lblCouleurSolo_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		
		JButton btnAutre = new JButton("MODIFIER");
		btnAutre.setBackground(new Color(0, 250, 154));
		btnAutre.setFocusable(false);
		btnAutre.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnAutre.setBounds(290, 205, 141, 28);
		panel1Joueur.add(btnAutre);
		
		labelAutreCSeul = new JLabel("");
		labelAutreCSeul.setBounds(443, 205, 34, 28);
		panel1Joueur.add(labelAutreCSeul);
		labelAutreCSeul.setOpaque(true);
		labelAutreCSeul.setBackground(Color.GREEN);
		
		btn1Joueur = new JButton("COMMENCER");
		btn1Joueur.setFocusable(false);
		btn1Joueur.setBackground(new Color(0, 250, 154));
		btn1Joueur.setBounds(166, 335, 431, 70);
		panel1Joueur.add(btn1Joueur);
		btn1Joueur.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		
		JLabel lblPartieUn = new JLabel("PARTIE \u00C0 UN JOUEUR");
		lblPartieUn.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPartieUn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartieUn.setForeground(new Color(255, 255, 255));
		lblPartieUn.setFont(new Font("Castellar", Font.BOLD, 45));
		lblPartieUn.setBounds(82, 45, 600, 54);
		panel1Joueur.add(lblPartieUn);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setForeground(new Color(0, 204, 0));
		separator_11.setBackground(Color.WHITE);
		separator_11.setBounds(166, 163, 95, 11);
		panel1Joueur.add(separator_11);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setForeground(new Color(0, 204, 0));
		separator_12.setBackground(Color.WHITE);
		separator_12.setBounds(502, 163, 95, 11);
		panel1Joueur.add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setForeground(new Color(0, 204, 0));
		separator_13.setBackground(Color.WHITE);
		separator_13.setBounds(165, 163, 2, 115);
		panel1Joueur.add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setForeground(new Color(0, 204, 0));
		separator_14.setBackground(Color.WHITE);
		separator_14.setBounds(596, 163, 2, 115);
		panel1Joueur.add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setForeground(new Color(0, 204, 0));
		separator_15.setBackground(Color.WHITE);
		separator_15.setBounds(166, 278, 431, 28);
		panel1Joueur.add(separator_15);
		btn1Joueur.addActionListener(new ActionListener() {
			//Crée une partie à un joueur.
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							cSeul=autreCSeul;
							
							frame3 = new Partie1Joueur(cSeul);
							frame3.setBounds(frame.getBounds());
							frame.setEnabled(false);
							frame.setVisible(false);
							frame3.setEnabled(true);
							frame3.setVisible(true);
							frame3.focus();
							frame3.addEcouteursPersoFenetres(new EcouteursPersoFenetres() {
								@Override
								public void retourAuMenuListener() {
									frame.setVisible(true);
									frame.setEnabled(true);
									frame3.dispose();
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnAutre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixCSeul=new ChoixCouleur(Color.green);
				choixCSeul.setVisible(true);
				choixCSeul.addEcouteursChoixCouleur(new EcouteursChoixCouleur() {
					@Override
					public void choixCouleur(Color c) {
						autreCSeul=c;
						labelAutreCSeul.setBackground(c);
						repaint();
					}
				});
			}
		});
		
		panelPrincipal = new JPanel();
		panelPrincipal.setForeground(Color.BLACK);
		panelPrincipal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		panelPrincipal.setBounds(258, 148, 764, 465);
		panelMainMenu.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(140, 230, 140, 100));
		
		JButton btn1JoueurPrincipal = new JButton("1 JOUEUR");
		btn1JoueurPrincipal.setFocusable(false);
		btn1JoueurPrincipal.setBackground(new Color(0, 250, 154));
		btn1JoueurPrincipal.setBounds(166, 70, 431, 70);
		panelPrincipal.add(btn1JoueurPrincipal);
		btn1JoueurPrincipal.setFont(new Font("Century Gothic", Font.BOLD, 35));
		
		JButton btn2JoueursPrincipal = new JButton("2 JOUEURS");
		btn2JoueursPrincipal.setBackground(new Color(0, 250, 154));
		btn2JoueursPrincipal.setFocusable(false);
		btn2JoueursPrincipal.setBounds(166, 197, 431, 70);
		panelPrincipal.add(btn2JoueursPrincipal);
		btn2JoueursPrincipal.setFont(new Font("Century Gothic", Font.BOLD, 35));
		
		JButton btnCommencerUnePartie = new JButton("MODE ARCADE");
		btnCommencerUnePartie.setBackground(new Color(0, 250, 154));
		btnCommencerUnePartie.setFocusable(false);
		btnCommencerUnePartie.setBounds(166, 316, 431, 70);
		panelPrincipal.add(btnCommencerUnePartie);
		btnCommencerUnePartie.addActionListener(new ActionListener() {
			//Crée une partie à un joueur.
			@Override
			public void actionPerformed(ActionEvent e) {
				frameA = new PartieArcade();
				frameA.setBounds(frame.getBounds());
				frame.setEnabled(false);
				frame.setVisible(false);
				frameA.setEnabled(true);
				frameA.setVisible(true);
				frameA.focus();
							
				frameA.addEcouteursPersoFenetres(new EcouteursPersoFenetres() {
					@Override
					public void retourAuMenuListener() {
						frame.setVisible(true);
						frame.setEnabled(true);
						frameA.dispose();
				}
			});}
		});
		btnCommencerUnePartie.setFont(new Font("Century Gothic", Font.BOLD, 35));
		
		btn1JoueurPrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(false);
				panel1Joueur.setVisible(true);
				btnRetour.setVisible(true);
			}
			
		});
		
		btn2JoueursPrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(false);
				panel2Joueurs.setVisible(true);
				btnRetour.setVisible(true);
			}
		});
		
		panel2Joueurs = new JPanel();
		panel2Joueurs.setBackground(new Color(140, 230, 140, 100));
		panel2Joueurs.setVisible(false);
		panel2Joueurs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		panel2Joueurs.setBounds(258, 148, 764, 465);
		panelMainMenu.add(panel2Joueurs);
		panel2Joueurs.setLayout(null);
		
		JLabel lblJoueur1 = new JLabel("JOUEUR 1");
		lblJoueur1.setBounds(123, 93, 184, 46);
		panel2Joueurs.add(lblJoueur1);
		lblJoueur1.setForeground(Color.WHITE);
		lblJoueur1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtFieldNom1 = new JTextField();
		txtFieldNom1.setBounds(123, 148, 184, 20);
		panel2Joueurs.add(txtFieldNom1);
		txtFieldNom1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!txtFieldNom1.getText().isEmpty() && !txtFieldNom2.getText().isEmpty()) {
					btn2Joueurs.setEnabled(true);
				} else {
					btn2Joueurs.setEnabled(false);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == ' ') {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btn2Joueurs.isEnabled()) {
					for (ActionListener act : btn2Joueurs.getActionListeners()) {
						act.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
					}
				}
			}
		});
		txtFieldNom1.setTransferHandler(null);
		txtFieldNom1.setColumns(10);
		
		JLabel lblJoueur2 = new JLabel("JOUEUR 2");
		lblJoueur2.setBounds(457, 93, 185, 43);
		panel2Joueurs.add(lblJoueur2);
		lblJoueur2.setForeground(Color.WHITE);
		lblJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur2.setFont(new Font("Century Gothic", Font.BOLD, 25));
		
		txtFieldNom2 = new JTextField();
		txtFieldNom2.setBounds(457, 148, 185, 20);
		panel2Joueurs.add(txtFieldNom2);
		txtFieldNom2.setColumns(10);
		
		JLabel lblCouleur1 = new JLabel("COULEURS DES QUEUES");
		lblCouleur1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleur1.setBounds(178, 220, 407, 25);
		panel2Joueurs.add(lblCouleur1);
		lblCouleur1.setForeground(Color.WHITE);
		lblCouleur1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("MODIFIER");
		btnNewButton.setBackground(new Color(0, 250, 154));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnNewButton.setBounds(122, 256, 141, 28);
		panel2Joueurs.add(btnNewButton);
		
		labelAutreC1 = new JLabel("");
		labelAutreC1.setBounds(273, 256, 34, 28);
		panel2Joueurs.add(labelAutreC1);
		labelAutreC1.setBackground(autreC1);
		labelAutreC1.setOpaque(true);
		
		JButton btnAutre_1 = new JButton("MODIFIER");
		btnAutre_1.setBackground(new Color(0, 250, 154));
		btnAutre_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnAutre_1.setBounds(501, 256, 141, 28);
		panel2Joueurs.add(btnAutre_1);
		
		btn2Joueurs = new JButton("COMMENCER");
		btn2Joueurs.setBackground(new Color(0, 250, 154));
		btn2Joueurs.setBounds(166, 335, 431, 70);
		panel2Joueurs.add(btn2Joueurs);
		
		btn2Joueurs.setForeground(Color.BLACK);
		btn2Joueurs.setEnabled(false);
		btn2Joueurs.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		
		labelAutreC2 = new JLabel("");
		labelAutreC2.setBounds(457, 256, 34, 28);
		panel2Joueurs.add(labelAutreC2);
		labelAutreC2.setOpaque(true);
		labelAutreC2.setBackground(autreC2);
		
		JLabel lblNoms = new JLabel("NOMS");
		lblNoms.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoms.setForeground(Color.WHITE);
		lblNoms.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNoms.setBounds(178, 137, 407, 34);
		panel2Joueurs.add(lblNoms);
		
		JLabel lblPartieDeux = new JLabel("PARTIE \u00C0 DEUX JOUEURS");
		lblPartieDeux.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPartieDeux.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartieDeux.setForeground(Color.WHITE);
		lblPartieDeux.setFont(new Font("Castellar", Font.BOLD, 45));
		lblPartieDeux.setBounds(10, 45, 744, 54);
		panel2Joueurs.add(lblPartieDeux);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(255, 255, 255));
		separator.setForeground(new Color(0, 204, 0));
		separator.setBounds(57, 117, 2, 70);
		panel2Joueurs.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(0, 204, 0));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(706, 117, 2, 70);
		panel2Joueurs.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(0, 204, 0));
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(57, 234, 2, 70);
		panel2Joueurs.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(new Color(0, 204, 0));
		separator_3.setBackground(Color.WHITE);
		separator_3.setBounds(706, 234, 2, 70);
		panel2Joueurs.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(0, 204, 0));
		separator_4.setBackground(Color.WHITE);
		separator_4.setBounds(57, 234, 206, 11);
		panel2Joueurs.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(new Color(0, 204, 0));
		separator_5.setBackground(Color.WHITE);
		separator_5.setBounds(502, 234, 206, 11);
		panel2Joueurs.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(new Color(0, 204, 0));
		separator_6.setBackground(Color.WHITE);
		separator_6.setBounds(57, 304, 651, 20);
		panel2Joueurs.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(new Color(0, 204, 0));
		separator_7.setBackground(Color.WHITE);
		separator_7.setBounds(57, 187, 651, 11);
		panel2Joueurs.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(new Color(0, 204, 0));
		separator_8.setBackground(Color.WHITE);
		separator_8.setBounds(279, 117, 206, 11);
		panel2Joueurs.add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(new Color(0, 204, 0));
		separator_9.setBackground(Color.WHITE);
		separator_9.setBounds(613, 117, 95, 11);
		panel2Joueurs.add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setForeground(new Color(0, 204, 0));
		separator_10.setBackground(Color.WHITE);
		separator_10.setBounds(57, 117, 95, 11);
		panel2Joueurs.add(separator_10);
		btn2Joueurs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(txtFieldNom1.getText().equals(txtFieldNom2.getText()))) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							c1=autreC1;
							c2=autreC2;
							
							frame2 = new Partie2Joueurs(txtFieldNom1.getText(), txtFieldNom2.getText(),c1 , c2);
							frame2.setBounds(frame.getBounds());
							frame.setEnabled(false);
							frame.setVisible(false);
							frame2.setEnabled(true);
							frame2.setVisible(true);
							frame2.addEcouteursPersoFenetres(new EcouteursPersoFenetres() {
								@Override
								public void retourAuMenuListener() {
									frame.setVisible(true);
									frame.setEnabled(true);
									frame2.dispose();
									txtFieldNom1.setText("");
									txtFieldNom2.setText("");
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Vous ne pouvez pas entrer deux noms identiques.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAutre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixC2=new ChoixCouleur(Color.red);
				choixC2.setVisible(true);
				choixC2.addEcouteursChoixCouleur(new EcouteursChoixCouleur() {
					@Override
					public void choixCouleur(Color c) {
						autreC2=c;
						labelAutreC2.setBackground(c);
						repaint();
					}
				});
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixC1=new ChoixCouleur(autreC1);
				choixC1.setVisible(true);
				choixC1.addEcouteursChoixCouleur(new EcouteursChoixCouleur() {
					@Override
					public void choixCouleur(Color c) {				
						autreC1=c;
						labelAutreC1.setBackground(c);
					}
				});
			}
		});
		txtFieldNom2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!txtFieldNom1.getText().isEmpty() && !txtFieldNom2.getText().isEmpty()) {
					btn2Joueurs.setEnabled(true);
				} else {
					btn2Joueurs.setEnabled(false);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == ' ') {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btn2Joueurs.isEnabled()) {
					for (ActionListener act : btn2Joueurs.getActionListeners()) {
						act.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
					}
				}
			}
		});
		txtFieldNom2.setTransferHandler(null);
		
		lblMainMenuTitle = new JLabel("JEU DE BILLARD 3D");
		lblMainMenuTitle.setForeground(Color.WHITE);
		lblMainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenuTitle.setBounds(194, 33, 892, 104);
		lblMainMenuTitle.setFont(new Font("Gisha", Font.BOLD, 70));
		panelMainMenu.add(lblMainMenuTitle);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.setBackground(new Color(255, 165, 0));
		btnQuitter.setFocusable(false);
		btnQuitter.setFont(new Font("Century Gothic", Font.ITALIC, 15));
		btnQuitter.addActionListener(new ActionListener() {
			//Ferme l'application.
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(511, 624, 257, 34);
		panelMainMenu.add(btnQuitter);
		
		lireImage();
		
		ImageIcon image=new ImageIcon(img);
		j=new JLabel();
		j.setVisible(true);
		j.setBounds(-25, 1, 1305, 761);
		j.setIcon(image);
		panelMainMenu.add(j);
	}
	
	/**
	 * Lis l'image pour le fond d'écran
	 */
	private void lireImage(){
		img=null;
		URL url=getClass().getClassLoader().getResource("poolTable.jpg");
		if(url==null){
			JOptionPane.showMessageDialog(null, "Fichier de fond d'écran introuvable");
		}
		
		try{
			img=ImageIO.read(url);
		}catch(IOException e){
		}
	}
}
