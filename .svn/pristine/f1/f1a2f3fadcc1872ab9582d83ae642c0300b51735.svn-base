package classement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ecouteurs.EcouteursPersoFenetres;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
/**
 * JPanel qui affiche le tableau des scores.
 * 
 * @author : Ruibin Wang
 */
public class Leaderboard extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableLabels;
	private JTable tableLeaderboard;
	
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	/**
	 * Constructeur
	 */
	public Leaderboard() {
		setFocusable(false);
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(128, 128, 128), null));
		setLayout(null);
		
		JLabel lblTitre = new JLabel("Classement des scores");
		lblTitre.setFocusable(false);
		lblTitre.setFont(new Font("Castellar", Font.BOLD, 25));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(61, 11, 438, 49);
		add(lblTitre);
		
		tableLabels = new JTable();
		tableLabels.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		tableLabels.setEnabled(false);
		tableLabels.setFocusable(false);
		tableLabels.setCellSelectionEnabled(false);
		
		tableLabels.setModel(new DefaultTableModel(
			new Object[][] {
				{"Position", "Nom", "Score", "Temps"},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		tableLabels.setBounds(22, 73, 516, 16);
		add(tableLabels);
		add(new JScrollPane());
		
		JButton btnRetourAuJeu = new JButton("RETOUR");
		btnRetourAuJeu.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnRetourAuJeu.setFocusable(false);
		btnRetourAuJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				leverRetourAuMenu();
			}
		});
		btnRetourAuJeu.setBounds(411, 348, 128, 23);
		add(btnRetourAuJeu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBounds(22, 89, 516, 248);
		add(scrollPane);
		
		tableLeaderboard = new JTable();
		tableLeaderboard.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		tableLeaderboard.setEnabled(false);
		tableLeaderboard.setRowSelectionAllowed(false);
		tableLeaderboard.setFocusable(false);
		scrollPane.setViewportView(tableLeaderboard);
		tableLeaderboard.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"", "", "", ""
			}
		));
	}
	/**
	 * Reoturne une liste des entrées pour le tableau des scores.
	 * @return une liste des entrées pour le tableau des scores.
	 */
	public ArrayList<ElementLeaderboard> loadLeaderboard() {
		ArrayList<ElementLeaderboard> elements = new ArrayList<ElementLeaderboard>();
		
		File file = new File(getClass().getClassLoader().getResource("leaderboard.txt").getFile());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (Scanner scanner = new Scanner(file)) {
			int stage = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (stage == 0) {//nom
					elements.add(new ElementLeaderboard());
					elements.get(elements.size()-1).setNom(line);
				}
				if (stage == 1) {//score
					elements.get(elements.size()-1).setScore(Integer.parseInt(line));
				}
				if (stage == 2) {//date
					elements.get(elements.size()-1).setDate(line);
				}
				stage = (stage+1)%3;
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		elements.sort((e1, e2) -> -e1.compareTo(e2));
		return elements;
	}
	/**
	 * Ouvre le tableau de classement.
	 */
	public void open() {
		ArrayList<ElementLeaderboard> list = loadLeaderboard();
		
		Object[][] entryList = new Object[list.size()][4];
		for (int i = 0 ; i < list.size() ; i++) {
			ElementLeaderboard elem = list.get(i);
			entryList[i] = new Object[] {i+1, elem.getNom(), elem.getScore(), elem.getDate()};
			System.out.println(elem.getNom());
			System.out.println(elem.getScore());
			System.out.println(elem.getDate());
		}
		
		
		tableLeaderboard.setModel(new DefaultTableModel(
				entryList,
				new String[] {
					"", "", "", ""
				}
			));
		setVisible(true);
	}
	/**
	 * Ajoute une entrée dans le classement.
	 * @param nom : Le nom du joueur.
	 * @param score : Le score à la fin de la partie.
	 * @param date : La date de fin de partie.
	 */
	public void addToLeaderboard(String nom, int score, String date) {
		File file = new File(getClass().getClassLoader().getResource("leaderboard.txt").getFile());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			
			bw.write(nom+"\r\n");
			bw.write(score+"\r\n");
			bw.write(date+"\r\n");
			
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Efface les scores.
	 */
	public void effacer() {
		File file = new File(getClass().getClassLoader().getResource("leaderboard.txt").getFile());
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	/**
	 * Ajoute les écouteurs personnalisés.
	 * @param EcouteursPersoFenetres : Les écouteurs personnalisés de type EcouteursPersoFenetres.
	 */
	public void addEcouteurs(EcouteursPersoFenetres ecouteursPersoFenetres) {
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
