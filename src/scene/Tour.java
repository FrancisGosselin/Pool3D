package scene;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;

import dessin.Objet3D;

/**
 * Structure de données créée spécialement pour une scène d'objet 3D.
 * La classe Tour permet de dessiner une scène d'objets 3D en se triant elle même
 * pour permettre au objets de s,afficher correctement.
 * 
 * Tous les objets qui sont ajoutés doivent implémenter l'interface Objet3D
 * 
 * @author Francis Gosselin
 *
 */
public class Tour {
	private ArrayList<Section> tour;
	private Objet3D fantome;
	private ArrayList<Objet3D> remplaceFantome;
	
	/**
	 * Constructeur
	 */
	public Tour(){
		tour=new ArrayList<Section>();
	}
	
	/**
	 * Contructeur qui ajoute tous les éléments d'une liste non trier 
	 * @param o la liste d'objets
	 */
	public Tour(ArrayList<Objet3D> o){
		tour=new ArrayList<Section>();
		for(int k=0;k<o.size();k++){
			add(o.get(k));
		}
		
	}
	
	/**
	 * Dessine la Scène 3D avec tous les éléments ajoutés
	 * @param g2d le Contexte graphique 2D
	 */
	public void dessiner(Graphics2D g2d){
		Section s;	
		ListIterator<Section> q=tour.listIterator(tour.size());
		while(q.hasPrevious()){
			s=q.previous();
			s.dessiner(g2d);
			
		}
		
	}
	
	/**
	 * Permet de mettre à jour la liste d'éléments représenter par l'objet fantôme 
	 * ne dessine pas les éléments si la méthode addWithGhost n'a pas été appelée.
	 * @param o la liste d'éléments
	 */
	public void updateGhost(ArrayList<Objet3D> o){
		this.remplaceFantome=o;
		
	}
	
	/**
	 * Copie la liste de Sections dans la tour
	 * @param ar la liste de Section a copier
	 * @return une copie de la liste
	 */
	private static ArrayList<Section> copie(ArrayList<Section> ar){
		ArrayList<Section> a= new ArrayList<Section>();
		for(Section s:ar){
			a.add(s);
		}
		return a;
	}
	
	/**
	 * Permet de représenter une liste d'élément en un Objet3D fantôme qui
	 * contient tous les éléments de la liste. La liste doit être déjà trier et 
	 * l'objet fantôme ne doit pas toucher aucun autre élément ajouté à la tour. 
	 * @param o l'objet qui représente la liste
	 * @param e la liste
	 */
	public void addWithGhost(Objet3D o,ArrayList<Objet3D> e){
		add(o);
		fantome=o;
		remplaceFantome=e;
		
		
	}
	
	/**
	 * Ajoute un objet 3D à la tour et tri la tour
	 * @param o l'objet à ajouter
	 */
	public void add(Objet3D o){
		if(tour.isEmpty()){
			tour.add(new Section(o));
		}else{
			
			boolean trouver=false;
			int posDessout=-1;
			Section p;
			
			for(int pos=0;pos<tour.size()&&!trouver;pos++){
				p=tour.get(pos);
				
				int f=p.add(o);

				if(f==1||f==2){
					trouver=true;
					ArrayList<Section> a=copie(tour);
					boolean fini=true;
					
					for(int k=a.size()-1;k>pos&&fini;k--){
						if(a.get(k).scoop(pos, o,a.get(pos))!=null)fini=false;		
					}
				}else{
					if(f==-1){
						posDessout=pos;
					}
				}
			}
			
			if(!trouver){
				
				if(posDessout!=-1){
					tour.get(posDessout).addDebut(o);
				}else{									
					tour.add(new Section(o));
				}
			}	
		}
	}
	
	/**
	 * Classe composant la liste de la tour
	 * tous les éléments à l'intérieur d'une section se touchent indirectement. 
	 * 
	 * @author Francis Gosselin
	 *
	 */
	private class Section{
		private ArrayList<Objet3D> liste;
		/**
		 * Remplace un Objet dans la section par une liste d'objet
		 * @param o l'objet à remplacer
		 * @param a la liste d'objet
		 */
		public void remplacer(Objet3D o,ArrayList<Objet3D> a){
			int index=liste.indexOf(o);
			if(index!=-1){
				liste.remove(index);
				liste.addAll(index, a);
			}
		}
		
		
		/**
		 * Créé une Section à partir d'un objet
		 * @param o : l'objet
		 */
		public Section(Objet3D o){
			liste=new ArrayList<Objet3D>();
			liste.add(o);
		}
		
		/**
		 * Créé une Section à partir d'une liste triée
		 * @param o : la liste d'objets
		 */
		public Section(ArrayList<Objet3D> o){
			liste=new ArrayList<Objet3D>();
			liste.addAll(o);
		}
		
		/**
		 * Ajoute un objet au début de la section
		 * @param o : l'objet
		 */
		public void addDebut(Objet3D o){
			liste.add(0, o);
		}
		
		/**
		 * Dessine la Section 
		 * @param g2d le contexte graphique
		 */
		public void dessiner(Graphics2D g2d){
			Objet3D o;
			if(liste.contains(fantome));
			remplacer(fantome,remplaceFantome);
			for(int k=0;k<liste.size();k++){
				o=liste.get(k);
				o.dessiner(g2d);						
			}
		}
		
		/**
		 * Permet d'ajouter un objet à la section
		 * @param o : l'objet
		 * @return 1 si l'objet à été placer dans la Section
		 */
		public int add(Objet3D o){
			boolean unDessut=false;
			boolean unDessous=false;
			Objet3D l;
			for(int k=0;k<liste.size();k++){
				l=liste.get(k);
				if(o.estComparable(l)){
					if(!unDessut){
						if(o.compareTo(l)==1){
							if(k==liste.size()-1){
								liste.add(o);
								
								return 1;
							}
							unDessut=true;
						}else{
							unDessous=true;
						}
					}else{
						
						if(o.compareTo(l)==-1){
								ArrayList<Objet3D> ar=sousSection(k),ar2=new ArrayList<Objet3D>(),ar3=new ArrayList<Objet3D>();
								ar.remove(o);
								
								if(unDessous){
									
									Objet3D t;
									for(int q=liste.size()-1;q>=0;q--){
										t=liste.get(q);
										if(o.estComparable(t)){
											if(o.compareTo(t)==-1){
												ar.add(0, t);liste.remove(t);
											}
										}
									}
								}
								if(ar.size()>1){
									for(int x=1;x<ar.size();x++){
										if(ar.get(x).estComparable(o)){
											if(ar.get(x).compareTo(o)==-1)ar2.add(ar.get(x));
										}else{
											ar3.add(ar.get(x));
										}
									}
									ar.removeAll(ar2);
									ar.removeAll(ar3);
									liste.addAll(ar2);
								}
								ar.add(0,o);
								
								int index=tour.indexOf(this);

								tour.add(index, new Section(ar));
								
								
								if(!ar3.isEmpty()){
									tour.add(index, new Section(ar3));
								}
								if(liste==null)tour.remove(this);
								
								
							return 2;
						}else{
							if(k==liste.size()-1){
								liste.add(o);
								
								return 1;	
							}
						}
					}
				}	
			}
			if(unDessut){
				tour.add(tour.indexOf(this),new Section(o));
				
				return 1;
			}
			if(unDessous){
				return -1;
			}
			return 0;
		}
		
		public ArrayList<Objet3D> sousSection(int pos){
			ArrayList<Objet3D> a1=new ArrayList<Objet3D>();
			int k=0;
			while(k<pos){
				a1.add(liste.get(k));
				k++;
			}
			
			ArrayList<Objet3D> a2=new ArrayList<Objet3D>();
			for(k=pos;k<liste.size();k++){
				a2.add(liste.get(k));
			}
			liste=a1;
			return a2;
		}
		
		/**
		 * Permet de chercher un élément dans la section, de le comparer avec un objet et de le remettre
		 * dans une section plus haute si l'objet est en dessous de l'objet comparé.
		 * 
		 * @param pos la position de la section de départ
		 * @param o l'objet à comparer
		 * @param w la section plus haute
		 * @return la liste d'objet rammenée plus haut, retourne null s'il y en a pas
		 */
		public ArrayList<Objet3D> scoop(int pos,Objet3D o,Section w){
			
			for(int k=0;k<liste.size();k++){
				if(o.estComparable(liste.get(k))){
					if(o.compareTo(liste.get(k))==-1){
						ArrayList<Objet3D> ar=new ArrayList<Objet3D>();
						ar.add(o);
						if(liste.size()==1){
							ar.add(liste.get(0));
							liste.remove(0);
						}else{
							ar.addAll(sousSection(k));
						
						}
						
						Section j;
						
						for(int q=tour.indexOf(this)-1;q>pos;q--){
							
							j=tour.get(q);
							boolean trouver=false;
							int p=0;
							while(p<j.liste.size()&&!trouver){
								
								for(Objet3D b:ar){
									if(j.liste.get(p).estComparable(b)){
										if(j.liste.get(p).compareTo(b)==1){
											trouver=true;
										}
									}
								}
								if(!trouver){
									p++;
								}	
							}
							if(trouver){
								ar.addAll(j.sousSection(p));
								if(j.liste==null){
									tour.remove(j);
								}
							}
						}	
						ar.remove(o);
						if(w.liste.indexOf(o)==w.liste.size()-1){
							w.liste.addAll(ar);
						}else{
							w.liste.addAll(w.liste.indexOf(o)+1,ar);
						}
						
						if(liste==null){
							tour.remove(this);
						}
						return ar;
					}
				}
			}
			return null;
		}
	}
}
