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
public class ProduitsFacture {
    
    private Produit produit;
    private TypeQuantite typeQ;
    private int qte;
    private double prix;
    
    public ProduitsFacture(Produit produit, TypeQuantite typeQ, int qte, double prix){
        this.produit = produit;
        this.typeQ = typeQ;
        this.qte = qte;
        this.prix = prix;
    }
    
    public double getPrixQte(){
        return(qte*prix);
    }
}
