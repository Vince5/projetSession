/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Vincent
 */
public class Produit {
    
    private String nom;
    private String description;
    private String noProduit;
    private String codePFournisseur;
    private String fournisseur;
    private TypeQuantite [] typesDispo;
    private int prixU;
    private boolean actif;
    private static int qte;
    
    
    public void Produit(){
        
    }
    
    public static Produit getProduitAvNo(String noProduit){
        
        Produit produit = new Produit();
        
        //Recherche le produit avec le noProduit dans la bd
        
        //Sinon, produit = null;
        
        return produit;
    }
    
    public static boolean validerProduit(String noProduit, int quantite){
        
        boolean retour = true;
        
        Produit produit = getProduitAvNo(noProduit);
        
        
        if(qte < quantite || produit == null){
            
            retour = false;
        }
        
        return retour;
    }
    
}
