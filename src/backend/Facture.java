/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public void sauvegarderFacture(Facture fac) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                Date date = new Date();
                
                rs = stmt.executeQuery("INSERT INTO Factures VALUES ('" + fac.getNoFacture() + 
                        "'," + date.toString() + ",'" + fac.getCodeEmploye() + "'," + fac.getTotalApTaxes() + ");");
                
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }

    /**
     * @return the noFacture
     */
    public int getNoFacture() {
        return noFacture;
    }

    /**
     * @return the codeEmploye
     */
    public int getCodeEmploye() {
        return codeEmploye;
    }

    /**
     * @return the totalApTaxes
     */
    public double getTotalApTaxes() {
        return totalApTaxes;
    }
}
