import java.util.*;


public class Gradient {
	
	//Le noyau servant à filtrer l'image.
	private final static int[] noyau={-1,0,1};

	
	//Fonction qui réalise un filtrage suivant les x (horizontal) donc les effets sont verticaux.
	public static List<Integer> gradientx(List<Integer> l, int nbcolonnes, int nblignes)
	{
		List<Integer> list = new ArrayList<Integer>();
		initializeList(list,nbcolonnes,nblignes);
		//System.out.println(list.size());
		list.set(0, l.get(0));
		list.set(nblignes*nbcolonnes-1, l.get(nblignes*nbcolonnes-1));
		
		for (int i=0;i<=nblignes-1;i++)
		{
			for (int j=1;j<=nbcolonnes-2;j++)
			{
				list.set(i*nbcolonnes+j, Math.abs(l.get(i*nbcolonnes+j-1)*noyau[0]+l.get(i*nbcolonnes+j+1)*noyau[2]));
				
			}
		}
		
		for (int i=1;i<=nblignes-1;i++)
		{
			list.set(i*nbcolonnes, l.get(i*nbcolonnes));
			list.set(i*nbcolonnes-1, l.get(i*nbcolonnes-1));
		}
		return list;
	}
	
	//Fonction qui réalise un filtrage suivant les y (vertical), elle permet de détecter les contours horizontaux
	public static List<Integer> gradienty(List<Integer> l, int nbcolonnes, int nblignes)
	{
		
		List<Integer> list = new ArrayList<Integer>();
		initializeList(list,nbcolonnes,nblignes);
		
		for (int j=0;j<=nbcolonnes-1;j++)
		{
			for (int i=1;i<=nblignes-2;i++)
			{
				
				list.set(i*nbcolonnes+j,Math.abs(l.get((i-1)*nbcolonnes+j)*noyau[0]+l.get((i+1)*nbcolonnes+j)*noyau[2]));
				
				
			}
		}
		for (int j=0;j<=nbcolonnes-1;j++)
		{
			list.set(j, l.get(j));
			list.set((nblignes-1)*nbcolonnes+j,l.get((nblignes-1)*nbcolonnes+j));
	
		}
		
		return list;
		
	}
	
	//Fonction qui combine les deux types de filtrage.
	public static List<Integer> gradientxy(List l, int nbcolonnes, int nblignes)
	{
		
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> filteredx = new ArrayList<Integer>();
		List<Integer> filteredy = new ArrayList<Integer>();
		initializeList(list,nbcolonnes,nblignes);
		
		
		filteredx=gradientx(l,nbcolonnes,nblignes);
		filteredy=gradienty(l,nbcolonnes,nblignes);
		
		for(int i=0;i<=nblignes-1;i++)
		{
			for(int j=0;j<=nbcolonnes-1;j++)
			{
				list.set(i*nbcolonnes+j, arrondi(Math.sqrt(Math.pow(filteredx.get(i*nbcolonnes+j), 2)+Math.pow(filteredy.get(i*nbcolonnes+j), 2))));
				//On prend la norme des deux résultats (en x et en y) d'un pixel.
			}
			
			
		}
		
		
		return list;
		
	}
	
	private static int arrondi(double a)
	{
		return (int)(a+0.5);
	}
	private static void initializeList(List<Integer> l,int nbcolonnes, int nblignes)
	{
		for (int i=0;i<nblignes;i++)
		{
			for(int  j=0;j<nbcolonnes;j++)
				l.add(0);
		}
	}
	
}
