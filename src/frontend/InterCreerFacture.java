package frontend;
import backend.Facture;
import backend.action;
import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

//Interface de la finalisation et du paiement de facture.
public class InterCreerFacture implements ActionListener {

    private static final String[] NOMS_OPERATIONS = {
        "Mastercard", // 0
        "Visa",
        "Débit",
        "Comptant",
    };

    /*********Composants de la fenetre**************/
    private static JFrame fenetrePrincipale;
    private static JPanel menuGauche;
    private static JPanel menuBas;
    private static JPanel menuCentre;
    private static int x=0;
    private static JLabel entete;
    private static ButtonGroup ensBouttons;
    private static JTable inventaire;
    private static JButton Executer;
    private static DefaultTableModel model;
    private static JRadioButton[] tableauBoutons;
    private static Box groupeBoutons;
    private static JScrollPane scrollpane;
    Facture laFacture = new Facture();
    Object[][] data = new Object[1][];
   
    /***********************************************/
    
    public InterCreerFacture (DefaultTableModel model, Facture facture) {
    	
        laFacture = facture;
    	/**********Creation Fenetre********************/
    	fenetrePrincipale = new JFrame("Facturation");
    	fenetrePrincipale.setSize(1000, 600);
    	fenetrePrincipale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	fenetrePrincipale.setLocationRelativeTo(null);
    	/**********************************************/
    	/*************JPanel gauche********************/
    	menuGauche = new JPanel();
    	menuGauche.setBorder(BorderFactory.createLineBorder(Color.white));
    	menuGauche.setBackground(Color.WHITE);
    	/**********************************************/
        /*
        model = new DefaultTableModel();
    	model.addColumn("#");
        model.addColumn("Nom Article");
        model.addColumn("Prix Unitaire");
        model.addColumn("Qte");
        model.addColumn("Total");
    	*/
        
        inventaire = new JTable(model);
    	scrollpane = new JScrollPane(inventaire,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
    	/************JPanel du bas*********************/
    	menuBas = new JPanel();
    	/**********************************************/
    	
    	/************JPanel du centre******************/
    	menuCentre = new JPanel();
    	menuCentre.setBackground(Color.WHITE);
    	menuCentre.setLayout(new FlowLayout());
        ensBouttons = new ButtonGroup();
    	menuCentre.setLayout(new BorderLayout ());

        
        Border border = BorderFactory.createTitledBorder("<html><i>GestionPlus</i></html>");
        menuCentre.setBorder(border);
    	/**********************************************/
    	
    	/************Elements du Panel du Bas**********/
        entete = new JLabel("<html>NOM ENTREPRISE<br>" +
        					"Adresse Entreprise <br>" +
        					"Autres infos pertinentes</html>");
    	Date ladate = new Date();
    	JLabel test = new JLabel("<html><center>FACTURE No " + laFacture.getNoFacture() + " <div margin-left = 15px>Nom Commis</div><br>"+ladate+"</html>");
    	test.setForeground(Color.black);
    	test.setOpaque(true);
    	test.setBackground(Color.WHITE);
    	test.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	test.setFont(new Font("Arial",Font.BOLD,16));
    	Executer = new JButton ( "Payer" );
    	Executer.addActionListener(this);
    	
        tableauBoutons = new JRadioButton[NOMS_OPERATIONS.length];
        groupeBoutons = Box.createHorizontalBox();
        
        for ( int i = 0; i < NOMS_OPERATIONS.length; i++ ) {
        	tableauBoutons[i] = new JRadioButton ( NOMS_OPERATIONS[i] ); // creation du bouton i
                groupeBoutons.add ( tableauBoutons[i] );                    // ajouter le bouton au groupe de boutons
            ensBouttons.add(tableauBoutons[i]);
        } 
        tableauBoutons[0].setSelected(true);
        
        /*****Ajout de tous les composants dans la fenetre**/
    	menuBas.add(entete);
    	menuCentre.add("North",test);
        menuCentre.add(scrollpane);
        menuGauche.add(groupeBoutons);
        menuGauche.add(Executer);
        
    	fenetrePrincipale.setLayout(new BorderLayout());   	
        fenetrePrincipale.getContentPane().add(menuCentre, BorderLayout.CENTER);
        fenetrePrincipale.getContentPane().add(menuBas, BorderLayout.NORTH);
        fenetrePrincipale.getContentPane().add(menuGauche, BorderLayout.AFTER_LAST_LINE);
        
    	fenetrePrincipale.setVisible(true);
        /*****************************************************/
    }
	
    //Création du fichier de la facture. Fermeture de cette facture.
    public void actionPerformed ( ActionEvent e ) {
        int ligne = inventaire.getRowCount();
        String nomProd;
        String Quant;
        String PrixUn;
        String prixTot;
        
            try {
                double totApTax = laFacture.getTotalApTaxes();
                String totalString = String.format("%.2f", totApTax);
                
                File f = new File("facture"+laFacture.getNoFacture()+".txt");
                FileWriter fw;
                fw = new FileWriter(f);
                String str = "Facture: \n"+
                        "Nom Produit \t\t Quantite \t\t Prix Unitaire \t\t Prix Total\n\n";
                for(int i=0; i<ligne; i++) {
                    nomProd = inventaire.getModel().getValueAt(i, 1).toString();
                    Quant = inventaire.getModel().getValueAt(i, 2).toString();
                    PrixUn = inventaire.getModel().getValueAt(i, 3).toString();
                    prixTot = inventaire.getModel().getValueAt(i, 4).toString();
                    str+= ""+nomProd+" \t\t\t\t "+Quant+" \t\t\t\t "+PrixUn+" \t\t\t\t "+prixTot+"\n";
                }
                str+= "\nTotal:\t\t \t\t \t \t\t \t \t\t\t\t\t\t " + totalString;
                
                String modePaiement = "";
                
                for ( int i = 0; i < NOMS_OPERATIONS.length; i++ ) {
                    if(tableauBoutons[i].isSelected()){
                        modePaiement = NOMS_OPERATIONS[i];
                    }
                }
                str+= "\n Mode de paiement: " + modePaiement;
                fw.write(str);
                fw.close();
                
                JOptionPane.showMessageDialog(null,
                "Paiement complété",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
                
                fenetrePrincipale.dispose();
                
                
            }catch (IOException ex) {
                Logger.getLogger(action.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    //Non implémenté
    private void action(int x) {
    	try {
            JOptionPane.showMessageDialog(null,
                "Paiement complété",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
    	}
	catch (Exception oe) {
            
	}
    		
    }

}