package menus_aide;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

import ecouteurs.EcouteursPersoFenetres;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
/**
 * La classe GuideUtilisation est une fenêtre secondaire qui affiche les informations concernant le jeu.
 * @author Francis Gosselin
 *
 */
public class GuideUtilisation extends JFrame {
	private static final long serialVersionUID = 1L;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private ArrayList<Image> joueur2, jouerTour,joueur1;
	private JPanel tabRegles;
	private JPanel tabJouerSonTour;
	private JPanel tabPartieUnJoueur;
	
	/**
	 * Constructeur.
	 */
	public GuideUtilisation() {
		setResizable(false);
		setTitle("Guide d'utilisation");
		setBounds(0, 0, 941, 674);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 24, 905, 537);
		getContentPane().add(tabbedPane);
		
		tabRegles = new JPanel();
		tabbedPane.addTab("Partie à deux joueurs", null, tabRegles, null);
		tabRegles.setLayout(null);
		
		JLabel lblLesReglesDune = new JLabel("Les r\u00E8gles d'une partie \u00E0 deux joueurs");
		lblLesReglesDune.setHorizontalAlignment(SwingConstants.CENTER);
		lblLesReglesDune.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblLesReglesDune.setBounds(37, 11, 684, 67);
		tabRegles.add(lblLesReglesDune);
		
		JTextPane txtpnPourDbuterLa = new JTextPane();
		txtpnPourDbuterLa.setBackground(SystemColor.menu);
		txtpnPourDbuterLa.setText("Pour d\u00E9buter la partie, le joueur 1 doit commencer en frappant la boule blanche pour casser le jeu. Si aucune boule n'est rentr\u00E9e, c'est au tour de l'autre joueur\r\n");
		txtpnPourDbuterLa.setBounds(47, 77, 343, 55);
		tabRegles.add(txtpnPourDbuterLa);
		
		JTextPane txtpnChaqueJoueurJoue = new JTextPane();
		txtpnChaqueJoueurJoue.setBackground(SystemColor.menu);
		txtpnChaqueJoueurJoue.setText("Chaque joueur joue alors coup par coup jusqu'a ce qu'une premi\u00E8re boule soit rentr\u00E9. \r\nD\u00E8s la premi\u00E8re boule rentr\u00E9e, le joueur qui a rentr\u00E9 cette boule s'approprie ce type de boule. Ainsi, pour le restant de la partie il essayera de rentrer les boules de cette couleur. ");
		txtpnChaqueJoueurJoue.setBounds(46, 282, 377, 93);
		tabRegles.add(txtpnChaqueJoueurJoue);
		
		JTextPane txtpnEnsuiteLeJeu = new JTextPane();
		txtpnEnsuiteLeJeu.setBackground(SystemColor.menu);
		txtpnEnsuiteLeJeu.setText("Ensuite le jeu continu, les joueurs ont cependant un objectif: rentrer toutes les boules de leur couleur. De plus, d\u00E8s qu'un joueur a ,durant son coup, rentr\u00E9 au moins une de ses boules, il peut tirer a nouveau et ainsi de suite. ");
		txtpnEnsuiteLeJeu.setBounds(462, 77, 357, 67);
		tabRegles.add(txtpnEnsuiteLeJeu);
		
		JTextPane txtpnLorsqueUnJoueur = new JTextPane();
		txtpnLorsqueUnJoueur.setBackground(SystemColor.menu);
		txtpnLorsqueUnJoueur.setText("Lorsque un joueur a rentr\u00E9 toutes ses boules, il doit rentrer la boule noir. Du moment qu'il la rentre, il gagne la partie. Cependant, il faut faire attention, si un joueur rentre la noire sans avoir rentr\u00E9 toutes ses boules, il perd directement!\r\n ");
		txtpnLorsqueUnJoueur.setBounds(466, 282, 335, 76);
		tabRegles.add(txtpnLorsqueUnJoueur);
		lireImage();
		JLabel label1 = new JLabel("");
		label1.setBounds(10, 143, 392, 143);
		Image img=joueur2.get(0).getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
		label1.setIcon(new ImageIcon(img));
		tabRegles.add(label1);
		
		JLabel label2 = new JLabel("");
		label2.setBounds(10, 416, 413, 60);
		img=joueur2.get(1).getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_SMOOTH);
		label2.setIcon(new ImageIcon(img));
		tabRegles.add(label2);
		
		JLabel label3 = new JLabel("");
		label3.setBounds(433, 155, 335, 116);
		img=joueur2.get(2).getScaledInstance(label3.getWidth(), label3.getHeight(), Image.SCALE_SMOOTH);
		label3.setIcon(new ImageIcon(img));
		tabRegles.add(label3);
		
		JLabel label4 = new JLabel("");
		label4.setBounds(433, 376, 335, 122);
		img=joueur2.get(3).getScaledInstance(label4.getWidth(), label4.getHeight(), Image.SCALE_SMOOTH);
		label4.setIcon(new ImageIcon(img));
		tabRegles.add(label4);
		
		JLabel label = new JLabel("1");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(13, 77, 24, 43);
		tabRegles.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(10, 297, 24, 43);
		tabRegles.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(428, 89, 24, 43);
		tabRegles.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setBounds(432, 297, 24, 43);
		tabRegles.add(label_3);
		
		tabJouerSonTour = new JPanel();
		tabbedPane.addTab("Jouer son tour", null, tabJouerSonTour, null);
		tabJouerSonTour.setLayout(null);
		
		JLabel lblJouerSonTour = new JLabel("Jouer son tour");
		lblJouerSonTour.setHorizontalAlignment(SwingConstants.CENTER);
		lblJouerSonTour.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblJouerSonTour.setBounds(37, 11, 684, 67);
		tabJouerSonTour.add(lblJouerSonTour);
		tabJouerSonTour.setAutoscrolls(true);
		
		JTextPane txtpnPourFaireUn = new JTextPane();
		txtpnPourFaireUn.setBackground(SystemColor.menu);
		txtpnPourFaireUn.setText("Pour faire un coup, il faut d'abord d\u00E9cider o\u00F9 est-ce que l'on veut tirer. Pour ce faire le joueur doit cliquer sur la zone de dessin et glisser la souris pour changer l'angle de frappe");
		txtpnPourFaireUn.setBounds(37, 89, 318, 54);
		tabJouerSonTour.add(txtpnPourFaireUn);
		
		JTextPane txtpnEnsuiteIlFaut = new JTextPane();
		txtpnEnsuiteIlFaut.setBackground(SystemColor.menu);
		txtpnEnsuiteIlFaut.setText("Ensuite, il faut d\u00E9cider de la force de frappe: cliquez sur la queue et glissez la vers le bas de l'\u00E9cran. Plus elle est recul\u00E9, plus la frappe sera forte!");
		txtpnEnsuiteIlFaut.setBounds(431, 118, 331, 54);
		tabJouerSonTour.add(txtpnEnsuiteIlFaut);
		
		JTextPane txtpnPourMieuxVoir = new JTextPane();
		txtpnPourMieuxVoir.setBackground(SystemColor.menu);
		txtpnPourMieuxVoir.setText("Pour mieux voir la table et les boules, vous pouvez la voir de plus loin ou de plus pr\u00E8s en utilisant la touche 'w' pour agrandir et 's' pour r\u00E9tr\u00E9cir. De plus, vous pouvez changer la sensibilit\u00E9 de la souris si vous trouvez que la queue est trop difficile \u00E0 controler");
		txtpnPourMieuxVoir.setBounds(26, 416, 318, 82);
		tabJouerSonTour.add(txtpnPourMieuxVoir);
		
		JLabel tour1 = new JLabel("");
		tour1.setBounds(37, 154, 372, 117);
		img=jouerTour.get(0).getScaledInstance(tour1.getWidth(), tour1.getHeight(), Image.SCALE_SMOOTH);
		tour1.setIcon(new ImageIcon(img));
		tabJouerSonTour.add(tour1);
		
		JLabel tour2 = new JLabel("");
		tour2.setBounds(37, 282, 372, 118);
		img=jouerTour.get(1).getScaledInstance(tour2.getWidth(), tour2.getHeight(), Image.SCALE_SMOOTH);
		tour2.setIcon(new ImageIcon(img));
		tabJouerSonTour.add(tour2);
		
		JLabel tour3 = new JLabel("");
		tour3.setBounds(431, 183, 400, 141);
		img=jouerTour.get(2).getScaledInstance(tour3.getWidth(), tour3.getHeight(), Image.SCALE_SMOOTH);
		tour3.setIcon(new ImageIcon(img));
		tabJouerSonTour.add(tour3);
		
		JLabel tour4 = new JLabel("");
		tour4.setBounds(431, 344, 410, 154);
		img=jouerTour.get(3).getScaledInstance(tour4.getWidth(), tour4.getHeight(), Image.SCALE_SMOOTH);
		tour4.setIcon(new ImageIcon(img));
		tabJouerSonTour.add(tour4);
		
		JLabel label_6 = new JLabel("1");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_6.setBounds(10, 89, 24, 43);
		tabJouerSonTour.add(label_6);
		
		JLabel label_7 = new JLabel("2");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_7.setBounds(404, 118, 24, 43);
		tabJouerSonTour.add(label_7);
		
		tabPartieUnJoueur = new JPanel();
		tabbedPane.addTab("Partie à un joueur", null, tabPartieUnJoueur, null);
		tabPartieUnJoueur.setLayout(null);
		
		JLabel lblLesRglesDune_1 = new JLabel("Les r\u00E8gles d'une partie \u00E0 un joueur");
		lblLesRglesDune_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLesRglesDune_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblLesRglesDune_1.setBounds(37, 11, 684, 67);
		tabPartieUnJoueur.add(lblLesRglesDune_1);
		
		JTextPane txtpnLaPartie = new JTextPane();
		txtpnLaPartie.setBackground(SystemColor.menu);
		txtpnLaPartie.setText("La partie \u00E0 un joueur commence lorsque le premier coup est frapp\u00E9. Vous avez alors 4 minutes pour rentrer le plus de boules possibles. Si vous r\u00E9ussissez \u00E0 rentrer toutes les boules, le jeu continu, toutes le boules r\u00E9apparraissent et la boule blanche revient \u00E0 sa position initiale ");
		txtpnLaPartie.setBounds(37, 89, 392, 84);
		tabPartieUnJoueur.add(txtpnLaPartie);
		
		JTextPane txtpnDsQuuneBoule = new JTextPane();
		txtpnDsQuuneBoule.setBackground(SystemColor.menu);
		txtpnDsQuuneBoule.setText("D\u00E8s qu'une boule rentre, votre score augmente de 100 points et vous gagnez 5 secondes. De plus, le score d'une boule rentr\u00E9e augmente de 20 point pour chaque tour cons\u00E9cutif que vous avez rentr\u00E9e une boule. Mais faites attention! Si la boule blanche rentre, vous perdez 20 secondes du temps restant en plus de 200 points. ");
		txtpnDsQuuneBoule.setBounds(467, 209, 320, 109);
		tabPartieUnJoueur.add(txtpnDsQuuneBoule);
		
		JLabel label_4 = new JLabel("1");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(10, 104, 24, 43);
		tabPartieUnJoueur.add(label_4);
		
		JLabel label_5 = new JLabel("2");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_5.setBounds(433, 215, 24, 43);
		tabPartieUnJoueur.add(label_5);
		
		JTextPane txtpnLaPartieSe = new JTextPane();
		txtpnLaPartieSe.setText("La partie se termine d\u00E8s que le temps s'est \u00E9coul\u00E9. Un message s'affichera donc et vous pourrez rentrer votre nom, vous pourrez ensuite voir dans le classement votre score votre nom et la date en comparaison avec les scores des autres joueurs. ");
		txtpnLaPartieSe.setBackground(SystemColor.menu);
		txtpnLaPartieSe.setBounds(37, 299, 320, 90);
		tabPartieUnJoueur.add(txtpnLaPartieSe);
		
		JLabel label_8 = new JLabel("3");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_8.setBounds(10, 310, 24, 43);
		tabPartieUnJoueur.add(label_8);
		
		JLabel joueur11 = new JLabel("");
		joueur11.setBounds(75, 201, 244, 57);
		img=joueur1.get(0).getScaledInstance(joueur11.getWidth(), joueur11.getHeight(), Image.SCALE_SMOOTH);
		joueur11.setIcon(new ImageIcon(img));
		tabPartieUnJoueur.add(joueur11);
		
		
		JLabel joueur12 = new JLabel("");
		joueur12.setBounds(467, 353, 256, 75);
		img=joueur1.get(1).getScaledInstance(joueur12.getWidth(), joueur12.getHeight(), Image.SCALE_SMOOTH);
		joueur12.setIcon(new ImageIcon(img));
		tabPartieUnJoueur.add(joueur12);
		
		JLabel joueur13 = new JLabel("");
		joueur13.setBounds(26, 389, 363, 109);
		img=joueur1.get(2).getScaledInstance(joueur13.getWidth(), joueur13.getHeight(), Image.SCALE_SMOOTH);
		joueur13.setIcon(new ImageIcon(img));
		tabPartieUnJoueur.add(joueur13);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Partie Arcade et autres\r\n", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblOptionsEtPartie = new JLabel("Options et Partie Arcade");
		lblOptionsEtPartie.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptionsEtPartie.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblOptionsEtPartie.setBounds(123, 11, 684, 67);
		panel.add(lblOptionsEtPartie);
		
		JTextPane txtpnPartieArcade = new JTextPane();
		txtpnPartieArcade.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnPartieArcade.setText("Partie Arcade");
		txtpnPartieArcade.setBackground(SystemColor.menu);
		txtpnPartieArcade.setBounds(403, 89, 118, 29);
		panel.add(txtpnPartieArcade);
		
		JTextPane txtpnDansLaPartie = new JTextPane();
		txtpnDansLaPartie.setText("Dans la partie arcade, vous controllez directement la boule blanche. La partie ne se termine jamais, les boules r\u00E9aparaissent si elles sont toutes rentr\u00E9es. Vous pouvez aussi choisir le nombre de boules lorsqu'elles r\u00E9apparaissent en utilisant le curseur. \r\nControlles :\r\n- Pour avancer : 'w'\r\n- Pour reculer : 's'\r\n- Cliquer et glisser la souris pour choisir la direction de la boule blanche\r\n- Pour produire un boost de vitesse : la touche majuscule");
		txtpnDansLaPartie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnDansLaPartie.setBackground(SystemColor.menu);
		txtpnDansLaPartie.setBounds(292, 115, 350, 134);
		panel.add(txtpnDansLaPartie);
		
		JTextPane txtpnChoisirLaCouleur = new JTextPane();
		txtpnChoisirLaCouleur.setText("Choisir la couleur de la queue");
		txtpnChoisirLaCouleur.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnChoisirLaCouleur.setBackground(SystemColor.menu);
		txtpnChoisirLaCouleur.setBounds(352, 263, 224, 29);
		panel.add(txtpnChoisirLaCouleur);
		
		JTextPane txtpnDansLeMenu = new JTextPane();
		txtpnDansLeMenu.setText("Dans le menu principal, avant de commencer un partie \u00E0 deux ou \u00E0 un joueur, vous pouvez choisir la couleur de votre queue de billard. Vous pouvez prendre une couleur classique en choisissant une couleur dans la liste d\u00E9roulante, ou bien prendre une couleur personnalis\u00E9e en cliquant sur le bouton \"autre couleur\", confirmez ensuite votre choix en choisissant le bouton radio devant la couleur choisie. ");
		txtpnDansLeMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnDansLeMenu.setBackground(SystemColor.menu);
		txtpnDansLeMenu.setBounds(292, 303, 350, 105);
		panel.add(txtpnDansLeMenu);
		
		JTextPane txtpnChangerLaSensibilit = new JTextPane();
		txtpnChangerLaSensibilit.setText("Changer la sensibilit\u00E9 ");
		txtpnChangerLaSensibilit.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnChangerLaSensibilit.setBackground(SystemColor.menu);
		txtpnChangerLaSensibilit.setBounds(372, 413, 175, 29);
		panel.add(txtpnChangerLaSensibilit);
		
		JTextPane txtpnSiVousJugez = new JTextPane();
		txtpnSiVousJugez.setText("Si vous jugez que la souris bouge trop ou pas assez facilement, il est toujours possible de changer la sensibilit\u00E9 dans les options. ");
		txtpnSiVousJugez.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnSiVousJugez.setBackground(SystemColor.menu);
		txtpnSiVousJugez.setBounds(292, 442, 350, 56);
		panel.add(txtpnSiVousJugez);
		
		
		JButton btnNewButton = new JButton("Retourner au jeu");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(607, 572, 308, 53);
		getContentPane().add(btnNewButton);
		
		
	}
	
	/**
	 * lis les images.
	 */
	private void lireImage(){
		joueur2=new ArrayList<Image>(); 
		jouerTour=new ArrayList<Image>(); 
		joueur1=new ArrayList<Image>(); 
		Image img=null;
		for(int k=1;k<=4;k++){
			URL url=getClass().getClassLoader().getResource("guideUtilisation"+k+".png");
			if(url==null){
				JOptionPane.showMessageDialog(null, "Fichier de fond d'écran introuvable");
			}
			
			try{
				img=ImageIO.read(url);
				
				joueur2.add(img);
			}catch(IOException e){
				System.out.println("Erreur lors de la lecture de l'image");
			}
		}
		
		for(int k=1;k<=3;k++){
			URL url=getClass().getClassLoader().getResource("joueur"+k+".PNG");
			if(url==null){
				JOptionPane.showMessageDialog(null, "Fichier de fond d'écran introuvable");
			}
			
			try{
				img=ImageIO.read(url);
				
				joueur1.add(img);
			}catch(IOException e){
				System.out.println("Erreur lors de la lecture de l'image");
			}
		}
		
		for(int k=1;k<=4;k++){
			URL url=getClass().getClassLoader().getResource("tour"+k+".png");
			if(url==null){
				JOptionPane.showMessageDialog(null, "Fichier de fond d'écran introuvable");
			}
			
			try{
				img=ImageIO.read(url);
				
				jouerTour.add(img);
			}catch(IOException e){
				System.out.println("Erreur lors de la lecture de l'image");
			}
		}
		
			
		
	}
	
	/**
	 * Ajoute les écouteurs personnalisés.
	 * @param EcouteursPersoFenetres : Les écouteurs personnalisés de type EcouteursPersoFenetres.
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
}