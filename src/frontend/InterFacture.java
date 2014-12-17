package frontend;
import backend.Facture;
import backend.Produit;
import backend.Utilisateur;
import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

//Formulaire pour créer une facture
public class InterFacture implements ActionListener {

    private static final String[] NOMS_OPERATIONS = {
        "Numero Article", // 0
    };

    /*********Composants de la fenetre**************/
    private static JFrame fenetrePrincipale;
    private static JPanel menuGauche;
    private static JPanel menuBas;
    private static JPanel menuCentre;
    private static int x=0;
    private static JLabel entete;
    private static JTextField TArticle;
    private static JLabel LArticle;
    private static JTextField TQteArticle;
    private static JLabel LQteArticle;
    private static JTable inventaire;
    private static String titre[] = {"#", "Nom Article", "Prix Unitaire", "Qte", "Total"};
    private static JButton Executer;
    private static DefaultTableModel model;
    private static JTextArea affichage;
    private static JScrollPane scrollpane;
    private Facture facture;
    private Utilisateur user = new Utilisateur();
    
    Object[][] data = new Object[1][];
   
    /***********************************************/
    
    public InterFacture() {}
    
    public InterFacture (Utilisateur luser) {
    	
        
    	user = luser;
        facture = new Facture();
    	/**********Creation Fenetre********************/
    	fenetrePrincipale = new JFrame("Facturation");
    	fenetrePrincipale.setSize(1000, 600);
    	fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	fenetrePrincipale.setLocationRelativeTo(null);
    	/**********************************************/
    	/*************JPanel gauche********************/
    	menuGauche = new JPanel();
    	menuGauche.setBorder(BorderFactory.createLineBorder(Color.white));
    	menuGauche.setBackground(Color.WHITE);
    	/**********************************************/
        
    	
        Object data2[] =  {"2", "BZHHydde", "28 ans", "1.80 m", "300"};
        data[x] = data2;
        x++;
        
        affichage = new JTextArea(33,65);
    	affichage.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    	affichage.setEditable(false);
        
        
        model = new DefaultTableModel(); 
        inventaire = new JTable(model); 

        // Create a couple of columns 
        //{"#", "Nom Article", "Prix Unitaire", "Qte", "Total"};
        
        model.addColumn("#"); 
        model.addColumn("Nom article");
        model.addColumn("Prix Unitaire");
        model.addColumn("Qte");
        model.addColumn("Total");

        
        //inventaire = new JTable(data, titre);
    	scrollpane = new JScrollPane(inventaire,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
    	/************JPanel du bas*********************/
    	menuBas = new JPanel();
        LArticle = new JLabel("Numéro Article");
        TArticle = new JTextField(10);
        TArticle.addActionListener(this);
        LQteArticle = new JLabel("Quantité");
        TQteArticle = new JTextField(2);
        TQteArticle.setText("1");
        TQteArticle.addActionListener(this);
    	/**********************************************/
    	
    	/************JPanel du centre******************/
    	menuCentre = new JPanel();
    	menuCentre.setBackground(Color.WHITE);
    	menuCentre.setLayout(new FlowLayout());
    	menuCentre.setLayout(new BorderLayout ());

        
        Border border = BorderFactory.createTitledBorder("<html><i>GestionPlus</i></html>");
        menuCentre.setBorder(border);
    	/**********************************************/
    	
    	/************Elements du Panel du Bas**********/
        entete = new JLabel("<html>NOM ENTREPRISE<br>" +
        					"Adresse Entreprise <br>" +
        					"Autres infos pertinentes</html>");
    	Date ladate = new Date();
    	JLabel test = new JLabel("<html><center>FACTURE No " + facture.getNoFacture() + " <div margin-left = 15px>" + user.getPrenom() + " " + user.getNom() + "</div><br>"+ladate+"</html>");
    	test.setForeground(Color.black);
    	test.setOpaque(true);
    	test.setBackground(Color.WHITE);
    	test.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	test.setFont(new Font("Arial",Font.BOLD,16));
    	Executer = new JButton ( "Payer" );
    	Executer.addActionListener(this);
    	
        /*****Ajout de tous les composants dans la fenetre**/
    	menuBas.add(entete);
    	menuCentre.add("North",test);
        menuCentre.add(scrollpane);
    	menuGauche.add(LArticle);
        menuGauche.add(TArticle);
        menuGauche.add(LQteArticle);
        menuGauche.add(TQteArticle);
        menuGauche.add(Executer);
        
    	fenetrePrincipale.setLayout(new BorderLayout());   	
        fenetrePrincipale.getContentPane().add(menuCentre, BorderLayout.CENTER);
        fenetrePrincipale.getContentPane().add(menuBas, BorderLayout.NORTH);
        fenetrePrincipale.getContentPane().add(menuGauche, BorderLayout.AFTER_LAST_LINE);
        
    	fenetrePrincipale.setVisible(true);
        
        /*****************************************************/
    }
	
    
    public void actionPerformed ( ActionEvent e ) {
        String noArticle = TArticle.getText();
        int quantite = Integer.parseInt(TQteArticle.getText());
        
       //Lorsque nous cliquons sur Payer
    	if(e.getSource() == Executer) {  
            facture.confirmationFacture(model, user);
            facture = new Facture();
            
                    model = new DefaultTableModel();
                    
                    model.addColumn("#"); 
                    model.addColumn("Nom article");
                    model.addColumn("Prix Unitaire");
                    model.addColumn("Qte");
                    model.addColumn("Total");

                    
                    inventaire.setModel(model);
              
            
        }else if(noArticle.equals("")) {
            JOptionPane.showMessageDialog(null,
                "Entrez un code valide",
                "Erreur",
                JOptionPane.INFORMATION_MESSAGE);
        }else if(quantite<1) {
            JOptionPane.showMessageDialog(null,
                "Entrez une quantite valide",
                "Erreur",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            Produit leProduit = new Produit();
            leProduit.setNoProduit(noArticle) ;
            leProduit = Produit.getProduitAvNo(noArticle);
            if(leProduit==null) {       
                JOptionPane.showMessageDialog(null,
                    "Code invalide",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
            }else{
                
                //Ajoute dans la table le produit
                
                double montant = leProduit.getPrixU()*quantite; 
                model.addRow(new Object[]{x, leProduit.getNom(), leProduit.getPrixU(), quantite , montant});
               
                facture.ajouterProduitFacture(noArticle, quantite, leProduit.getPrixU());
                
                x++;
            }
        }
    }

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
