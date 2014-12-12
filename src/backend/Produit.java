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
    
    Produit(String text, String text0, String text1, String text2, String text3, String text4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Produit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void Produit(){
        
    }
    
    public void Produit(String noProduit, String nom, String description, String quantite, String prixU, String codePFournisseur){
        this.description = description;
        this.nom = nom;
        this.noProduit = noProduit;
        this.codePFournisseur = codePFournisseur;
        this.prixU = Double.parseDouble(prixU);
        this.qte = Integer.parseInt(quantite);
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
    
    public void ajouteProduit(Produit prod) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                    
                
                rs = stmt.executeQuery("INSERT INTO Produits VALUES ('" + prod.getNoProduit() + 
                        "'," + prod.getPrixU() + ",'" + prod.getCodePFournisseur() + "','" + prod.getDescription() + "','" + prod.getNom() + "," + prod.getQte());
                
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }
    
        public void modifierProduit(Produit prod) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                    
                
                rs = stmt.executeQuery("UPDATE Produits SET prix = " + prod.getPrixU() + "', codeProduitFournisseur = '" + prod.getCodePFournisseur() + "', description = '" + prod.getDescription() + "', nom = '" + prod.getNom() + ", quantiteEnStock = '" + prod.getQte() + ";'");
                
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
    }
        
          public void supprimerProduit(Produit prod) {
        
        Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;
                
                java.util.Date date = new java.util.Date();
               
                    
                
                rs = stmt.executeQuery("UPDATE Produits SET actif = false  WHERE noProduit = '" + prod.getNoProduit()+ "';");
                
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
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
