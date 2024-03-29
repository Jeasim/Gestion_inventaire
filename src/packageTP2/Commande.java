package packageTP2;
import java.util.*;
public class Commande 
{
private int numero;
private String noCarteBoniClient;
private Vector<Item> items;
private static int compteur;

  public Commande(String noCarte)
  {
  this.numero=compteur++;
  this.noCarteBoniClient = noCarte;
  items = new Vector<Item>();
  }

  public int getNumero()
  {
  return numero;
  }
  public String getNoCarteBoniClient()
  {
  return noCarteBoniClient;
  }
  public Vector<Item> getItems()
  {
  return items;
  }
  public void ajouterItem ( Item i)
  {
  items.add(i);
  }
  
  public double calculerSousTotal ()
  {
  double total = 0;
  for ( int i = 0; i < items.size(); i++ )
    {
    Item item = items.get(i);
    int qte = item.getQte();
    Produit p = Inventaire.getProduit(item.getNomProduit());
    double prix = p.getPrix();
    total += qte * prix;
    }

  return total;
  }
  
  public double calculerTPS ()
  {
    double total = calculerSousTotal();
    return total*0.05;
  }
    //� partir du 1er janvier 2013, la taxe de vente du Qu�bec est harmonis�e avec le r�gime de la TPS. 
    //Ainsi, la TVQ est calcul�e sur le prix excluant la TPS. Pour contrer les pertes de revenu encourues 
    //par le gouvernement qu�b�cois suite � cette modification, le taux de la TVQ a �t� port� � 9,975 %. 
    //Pour les consommateurs, la modification n'a aucun impact en terme de montant � d�bourser, 
    //puisque le taux combin� est identique � celui de 2012, soit 14,975 %
  
  public double calculerTVQ()
  {
    double total = calculerSousTotal();
    return total * 0.09975;
  }
  
  public double calculerGrandTotal()
  {
    return calculerSousTotal() + calculerTPS() + calculerTVQ();
  }
  
  
  public int calculerPointsBonis()
  {
  int total = 0;
  for ( int i = 0; i < items.size(); i++ )
    {
    Item item = items.get(i);
    int qte = item.getQte();
    Produit p = Inventaire.getProduit(item.getNomProduit());
    int points = p.getPoints();
    total += qte * points;
    }

  return total;
  }
  
  public int nbArticlesTotalFacture()
	{
		int qte = 0;
		
		for(Item i : items)
		{
			qte += i.getQte();
		}
		
		return qte;
	}
 
 
}