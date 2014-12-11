/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.*;

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
    private double prixU;
    private boolean actif;
    private int qte;
    
    
    public void Produit(){
        
    }
    /*
    public static void main(String[] args){
        System.out.println("allo");
        Produit.getProduitAvNo("IPOD30513956");
    }
    */
    public String getNoProduit(){
        return noProduit;
    }
    
    public static Produit getProduitAvNo(String noProduit){
        
        Produit produit = new Produit();
        boolean vide = true;
        //Recherche le produit avec le noProduit dans la bd
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;

                rs = stmt.executeQuery("SELECT * FROM Produits WHERE noProduit='" + noProduit + "'");
                while ( rs.next() ) {
                    
                    produit.noProduit = rs.getString("noProduit");
                    produit.nom = rs.getString("nom");
                    produit.description = rs.getString("description");
                    produit.prixU = rs.getDouble("prix");
                    produit.qte = rs.getInt("quantiteEnStock");
                    produit.codePFournisseur = rs.getString("codeProduitFournisseur");
                    
                    vide = false;
                    
                }
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
        //Sinon, produit = null;
        if(vide) produit = null;
        
        return produit;
    }
    
    public static boolean validerProduit(String noProduit, int quantite){
        
        boolean retour = true;
        
        Produit produit = getProduitAvNo(noProduit);
        
        
        if(produit.qte < quantite || produit == null){
            
            retour = false;
        }
        
        return retour;
    }
    
}
