package frontend;
import backend.Facture;
import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class InterCreerFacture implements ActionListener {

    private static final String[] NOMS_OPERATIONS = {
        "Mastercard", // 0
        "Visa",
        "D�bit",
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
    
    public InterCreerFacture (DefaultTableModel model) {
    	
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
    	JLabel test = new JLabel("<html><center>FACTURE No XXXXX <div margin-left = 15px>Nom Commis</div><br>"+ladate+"</html>");
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
	
    
    public void actionPerformed ( ActionEvent e ) {
        
    }

    private void action(int x) {
    	try {
            JOptionPane.showMessageDialog(null,
                "Paiement compl�t�",
                "Succ�s",
                JOptionPane.INFORMATION_MESSAGE);
    	}
	catch (Exception oe) {
            
	}
    		
    }

}