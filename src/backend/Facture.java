/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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
    private ProduitsFacture [] produits;
    
}
