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
    
    public Produit(String noProduit, String nom, String description, int quantite, Double prixU, String codePFournisseur){
        this.noProduit = noProduit;
        this.nom = nom;
        this.description = description;
        this.qte = quantite;
        this.prixU = prixU;
        this.codePFournisseur = codePFournisseur;
    }
    
    public Produit(){
        
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
    public void setNoProduit(String noProduit){
        this.noProduit = noProduit;
    }
    
    public void setFournisseur(String fournisseur){
        this.fournisseur = fournisseur;
    }
    
      public void setCodePFournisseur(String codePFournisseur){
        this.codePFournisseur = codePFournisseur;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public void setPrixU(double prixU){
        this.prixU = prixU;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getCodePFournisseur(){
        return codePFournisseur;
    }
    
    public String getNom(){
        return nom;
    }
    
    public double getPrixU(){
        return prixU;
    }
    
    public double getQte(){
        return qte;
    }
    
    
    public String getfournisseur(){
        return fournisseur;
    }
    
    public void ajouteProduit() {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                    
                
                rs = stmt.executeQuery("INSERT INTO Produits VALUES ('" + noProduit + 
                        "'," + prixU + ",'" + codePFournisseur + "','" + description + "','" + nom + "'," + qte + ", 'true');");
                
        }catch(SQLException e){
             //e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }
    
    public static void modifierProduit(String noProduit, String nom, String desc, String quantite, String prix, String codePF) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                rs = stmt.executeQuery("UPDATE Produits "
                        + "SET prix = " + prix + ", codeProduitFournisseur = '" + codePF + "', description = '" + desc + "', nom = '" + nom + "', quantiteEnStock = " + quantite + " WHERE noProduit = '" + noProduit + "';");
                
        }catch(SQLException e){
             //e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }
        
    public static void supprimerProduit(String noProduit, String nom, String desc, String quantite, String prix, String codePF) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                rs = stmt.executeQuery("UPDATE Produits SET actif = 'false'  WHERE noProduit = '" + noProduit+ "';");
                
        }catch(SQLException e){
             //e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }     

    //Va chercher dans la base de données le produit grâce à un numéro de Produit.
    public static Produit getProduitAvNo(String noProduit){
        
        Produit produit = new Produit();
        boolean vide = true;
        //Recherche le produit avec le noProduit dans la bd
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;

                rs = stmt.executeQuery("SELECT * FROM Produits WHERE noProduit='" + noProduit + "' AND actif='true';");
                while ( rs.next() ) {
                    
                    produit.setNoProduit(rs.getString("noProduit"));
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
        
        
        if(produit.getQte() < quantite || produit == null){
            
            retour = false;
        }
        
        return retour;
    }

    
}
