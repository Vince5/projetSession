/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vincent
 */
public class Facture {
    
    private int noFacture;
    private Date date;
    private int codeEmploye;
    private double totalAvTaxes;
    private double totalApTaxes;
    //String pour l'instant
    private String [] modesPaiement;
    private ArrayList<ProduitsFacture> produits;
    private int cptProduit = 0;
    
    public void Facture(){
        
    }
    
    public void ajouterProduitFacture(String noProduit, int qte, TypeQuantite type, double prixUnitaire){
        
        Produit produit = Produit.getProduitAvNo(noProduit);
        ProduitsFacture pF = new ProduitsFacture(produit, type, qte, prixUnitaire);
        produits.add(pF);
        totalAvTaxes = calculMontantAvTaxes();
    }
    
    public void retirerProduitFacture(String noProduit) {
        for(int i=0; i<produits.size(); i++){
            if(produits.get(i).getProduit().getNoProduit().equals(noProduit)){
                produits.remove(i);
                break;
            }
        }
    }
    
    private double calculMontantAvTaxes(){
        
        double montant = 0;
        
        for( int i=0; i<produits.size();i++ ){
            
            montant+=produits.get(i).getPrixQte();
            
        }
        return montant;
    }
    
    private void confirmationFacture(){
        
        if(produits.isEmpty()){
            //MessageErreur
            
        }else{
        
            totalApTaxes = FonctionsSysteme.CalculerTaxesQC(totalAvTaxes);
            //Interface mode de paiement
        }
    }
}
