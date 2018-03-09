package menus_aide;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
/**
 * La classe ConceptPhysique est une fenêtre secondaire qui affiche les informations concernant la physique dans le jeu.
 * @author Ruibin Wang
 *
 */
public class ConceptsPhysiques extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedImage> imgList;
	private JLabel lblImage1, lblImage2, lblImage3, lblImage4;
	/**
	 * Constructeur.
	 */
	public ConceptsPhysiques() {
		imgList = new ArrayList<BufferedImage>();
		loadImages();
		setTitle("Concepts physiques");
		setBounds(0, 0, 800, 600);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 21));
		tabbedPane.setBounds(12, 11, 762, 488);
		getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLocation(15, 37);
		panel_1.setLayout(null);
		tabbedPane.addTab("Mouvement des balles", null, panel_1, null);
		
		JLabel lblLaPhysiqueDans_1 = new JLabel("La physique dans le mouvement des balles");
		lblLaPhysiqueDans_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaPhysiqueDans_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblLaPhysiqueDans_1.setBounds(10, 11, 739, 67);
		panel_1.add(lblLaPhysiqueDans_1);
		
		JTextPane txtpnLorsDeLa = new JTextPane();
		txtpnLorsDeLa.setEditable(false);
		txtpnLorsDeLa.setText("Lors de la frappe de la boule blanche \u00E0 l'aide de la queue, la boule ne subit qu'une seule force effective, celle de la queue, \u00E9tant donn\u00E9 que la force gravitationnelle est compl\u00E8tement neutralis\u00E9e par la force normale appliqu\u00E9e par la surface de la table.\r\n\r\nBien que le vecteur de la force ait une composante dans l'axe verticale, celle-ci n'est pas tenue compte puisque le mouvement de la boule est limit\u00E9 dans le plan de la table (nous ne permettons pas aux boules de prendre de l'altitude dans les modes de jeu r\u00E9guliers).\r\n\r\nAvec la composante en x, z de la force appliqu\u00E9e \u00E0 la boule, nous sommes en mesure de d\u00E9terminer l'acc\u00E9l\u00E9ration initiale transmise. Pour obtenir la vitesse initiale, nous avons besoin du temps de contact entre les deux objets, une valeur tr\u00E8s petite que nous approximons en utilisant la valeur statique de 0.1 seconde.");
		txtpnLorsDeLa.setBounds(37, 80, 300, 347);
		panel_1.add(txtpnLorsDeLa);
		
		JTextPane txtpnLorsqueLaBoule = new JTextPane();
		txtpnLorsqueLaBoule.setEditable(false);
		txtpnLorsqueLaBoule.setText("Lorsque la boule est en mouvement, la seule force qui influence sa vitesse est la force de frottement caus\u00E9e par son contact avec le tapis de la table. Pour des raisons de complexit\u00E9, les boules ne sont pas en rotation. \r\n\r\nPour obtenir le changement de vitesse, il suffit donc d'utiliser la m\u00E9thode d'Euler apr\u00E8s avoir isol\u00E9 l'acc\u00E9l\u00E9ration de la boule en utilisant la seconde loi de Newton.");
		txtpnLorsqueLaBoule.setBounds(413, 80, 300, 166);
		panel_1.add(txtpnLorsqueLaBoule);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Collisions avec la table", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblLaPhysiqueDans_2 = new JLabel("La physique dans les collisions entre");
		lblLaPhysiqueDans_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaPhysiqueDans_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLaPhysiqueDans_2.setBounds(32, 21, 694, 45);
		panel_2.add(lblLaPhysiqueDans_2);
		
		JLabel lblUneBouleEt = new JLabel("une boule et une bordure de table");
		lblUneBouleEt.setHorizontalAlignment(SwingConstants.CENTER);
		lblUneBouleEt.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblUneBouleEt.setBounds(32, 49, 694, 45);
		panel_2.add(lblUneBouleEt);
		
		JTextPane txtpnLorsquuneBouleEntre = new JTextPane();
		txtpnLorsquuneBouleEntre.setEditable(false);
		txtpnLorsquuneBouleEntre.setText("Lorsqu'une boule entre en collision avec un plan, il se produit un ph\u00E9nom\u00E8ne de r\u00E9flexion dans la trajectoire de la boule. Le nouveau vecteur vitesse de la boule est d\u00E9termin\u00E9 par la loi de la r\u00E9flexion en utilisant l'\u00E9quation suivante :");
		txtpnLorsquuneBouleEntre.setBounds(37, 100, 300, 90);
		panel_2.add(txtpnLorsquuneBouleEntre);
		
		JTextPane txtpnSourceVzina = new JTextPane();
		txtpnSourceVzina.setEditable(false);
		txtpnSourceVzina.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtpnSourceVzina.setText("Source : V\u00C9ZINA, Simon. \u00ABChapitre 2.2 - La r\u00E9flexion et les miroirs plans\u00BB, Coll\u00E8ge de Maisonneuve, [en ligne][http://profs.cmaisonneuve.qc.ca/svezina/nyc/note_nyc/NYC_XXI_Chap%202.2.pdf] (derni\u00E8re modification le 2017/02/23) (consult\u00E9 le 2017/04/12).");
		txtpnSourceVzina.setBounds(37, 410, 700, 30);
		panel_2.add(txtpnSourceVzina);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Collisions entre boules", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblLaPhysiqueDans = new JLabel("La physique dans les collisions entre boules");
		lblLaPhysiqueDans.setBounds(37, 11, 684, 67);
		lblLaPhysiqueDans.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaPhysiqueDans.setFont(new Font("Tahoma", Font.BOLD, 28));
		panel.add(lblLaPhysiqueDans);
		
		JTextPane txtpnLorsqueDeuxBoules = new JTextPane();
		
		txtpnLorsqueDeuxBoules.setText("Lorsque deux boules entrent en collision ensemble, il se produit une collision presque parfaitement \u00E9lastique, puisqu'il y a perte d'\u00E9nergie cin\u00E9tique du syst\u00E8me sous forme d'\u00E9nergie sonore et thermique entre autres.\r\n\r\nLe pourcentage d'\u00E9nergie qui est conserv\u00E9e apr\u00E8s une collision est donn\u00E9 par le coefficient de restitution. Dans le cas de boules de billard, cette valeur est similaire \u00E0 celle de boules d'ivoires, qui est de 8/9.\r\n\r\nPour calculer les nouveaux vecteurs de vitesse imm\u00E9diatement apr\u00E8s une collision, il nous faut d'abord obtenir la composante de l'impulsion appliqu\u00E9e parall\u00E8lement \u00E0 la normale \u00E0 la surface des boules o\u00F9 le contact a eu lieu. Pour ceci, nous utilisons la formule suivante obtenue gr\u00E2ce au principe de la conservation de la quantit\u00E9 de mouvement :\r\n\r\n");
		txtpnLorsqueDeuxBoules.setEditable(false);
		txtpnLorsqueDeuxBoules.setCaretPosition(0);
		
		
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(txtpnLorsqueDeuxBoules);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(37, 80, 300, 280);
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMinimum());
		panel.add(jsp);
		
		JTextPane txtpnParLaSuite = new JTextPane();
		txtpnParLaSuite.setEditable(false);
		txtpnParLaSuite.setText("Par la suite, il suffit de diviser cette impulsion par la masse de chacune des boule pour obtenir le module des vecteurs du changement de leur vitesse. En multipliant le vecteur de la normale \u00E0 la surface par ce module, nous obtenons ainsi le vecteur delta vitesse. \r\n\r\nFinalement, il reste \u00E0 additionner ce vecteur au vecteur vitesse initiale d'une des boules pour obtenir le nouveau vecteur vitesse. Pour la seconde boule, il faut soustraire le vecteur delta vitesse puisque l'impulsion est appliqu\u00E9e dans la direction inverse.");
		txtpnParLaSuite.setBounds(37, 80, 300, 230);
		panel.add(txtpnParLaSuite);
		
		JTextPane txtpnSourceVzina_1 = new JTextPane();
		txtpnSourceVzina_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtpnSourceVzina_1.setText("Source : V\u00C9ZINA, Simon. \u00ABChapitre 3.11c - Les collisions in\u00E9lastiques\u00BB, Coll\u00E8ge de Maisonneuve, [en ligne] [http://profs.cmaisonneuve.qc.ca/svezina/nya/note_nya/NYA_XXI_Chap%203.11c.pdf] (derni\u00E8re modification le 2015/10/05) (consult\u00E9 le 2017/04/12).");
		txtpnSourceVzina_1.setBounds(37, 410, 700, 30);
		panel.add(txtpnSourceVzina_1);
		
		lblImage4 = new JLabel("", new ImageIcon(imgList.get(3).getScaledInstance(400, imgList.get(3).getHeight()*400/imgList.get(3).getWidth(), Image.SCALE_SMOOTH)), JLabel.CENTER);
		lblImage4.setBounds(350, 80, 400, 280);
		lblImage4.setVerticalAlignment(JLabel.TOP);
		panel.add(lblImage4);
		
		lblImage3 = new JLabel("", new ImageIcon(imgList.get(2).getScaledInstance(400, imgList.get(2).getHeight()*400/imgList.get(2).getWidth(), Image.SCALE_SMOOTH)), JLabel.CENTER);
		lblImage3.setBounds(350, 80, 400, 280);
		lblImage3.setVerticalAlignment(JLabel.TOP);
		lblImage3.setVisible(false);
		panel.add(lblImage3);
		
		lblImage2 = new JLabel("", new ImageIcon(imgList.get(1).getScaledInstance(400, imgList.get(1).getHeight()*400/imgList.get(1).getWidth(), Image.SCALE_SMOOTH)), JLabel.CENTER);
		lblImage2.setBounds(350, 100, 400, 280);
		lblImage2.setVerticalAlignment(JLabel.TOP);
		panel_2.add(lblImage2);
		
		lblImage1 = new JLabel("", new ImageIcon(imgList.get(0).getScaledInstance(300, imgList.get(0).getHeight()*300/imgList.get(0).getWidth(), Image.SCALE_SMOOTH)), JLabel.CENTER);
		lblImage1.setBounds(413, 259, 300, 168);
		lblImage1.setVerticalAlignment(JLabel.TOP);
		panel_1.add(lblImage1);
		
		
		JButton btnNextPage = new JButton("Page suivante");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = btnNextPage.getText().equals("Page suivante");
				lblImage3.setVisible(b);
				lblImage4.setVisible(!b);
				txtpnParLaSuite.setVisible(b);
				jsp.setVisible(!b);
				
				btnNextPage.setText(b?"Page précédente":"Page suivante");
			}
		});
		btnNextPage.setBounds(169, 373, 168, 25);
		panel.add(btnNextPage);
		
		JButton button = new JButton("Retourner au jeu");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(466, 506, 308, 34);
		getContentPane().add(button);
	}
	/**
	 * Ajoute les images dans la liste d'images.
	 */
	private void loadImages() {
		for (int i = 1 ; i < 5 ; i++) {
			URL url = getClass().getClassLoader().getResource("concept_phys_img" + i + ".jpg");
			
			BufferedImage img = null;
			try {
				img = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imgList.add(img);
		}
	}
}
