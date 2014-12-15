/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Vincent
 *  Chaque porduit lorsqu'il ajouté dans une facture.
 */
public class ProduitsFacture {
    
    private Produit produit;
    private int qte;
    private double prix;
    
    public ProduitsFacture(Produit produit, int qte, double prix){
        this.produit = produit;
        this.qte = qte;
        this.prix = prix;
    }
    
    public Produit getProduit(){
        return produit;
    }
    
    public double getPrixQte(){
        return(qte*prix);
    }
}
