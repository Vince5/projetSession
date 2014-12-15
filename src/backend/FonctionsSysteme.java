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
public class FonctionsSysteme {
    
    private static double tps = 0.05;
    private static double tvq = 0.09975;
            
    
    public static double CalculerTaxesQC(double montantAvTaxes){
        
        double montantFinal = 0;
        
        montantFinal = montantAvTaxes * tps;
        montantFinal += montantAvTaxes * tvq;
        montantFinal += montantAvTaxes;
        
        return montantFinal;
    }
    
    
}
