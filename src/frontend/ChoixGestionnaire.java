package frontend;
import java.awt.*;
import javax.swing.*;

import backend.Utilisateur;
import java.awt.event.*;

public class ChoixGestionnaire implements ActionListener {

    private static final String[] NOMS_OPERATIONS = {
        "Créer un nouveau produit", // 1
        "Modifier/Supprimer un produit", // 2
    };
    /*********Composants de la fenetre**************/
    private static JFrame fenetrePrincipale;
    private static JPanel menuGauche;
    private static JPanel menuBas;
    private static JPanel menuCentre;
    private static JLabel entete;
    private static JScrollPane scrollpane;
    private static JRadioButton[] tableauBoutons;
    private static Box groupeBoutons;
    private static ButtonGroup ensBouttons;
    private static JButton Executer;
    /**
     * @param luser *********************************************/

    public ChoixGestionnaire(){}
    public ChoixGestionnaire (Utilisateur luser) {
    	Utilisateur user = new Utilisateur();
    	user = luser;
    	/**********Creation Fenetre********************/
    	fenetrePrincipale = new JFrame("Choix Gestionnaire");
    	fenetrePrincipale.setSize(400, 300);
    	fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	fenetrePrincipale.setLocationRelativeTo(null);
    	/**********************************************/
    	
    	/*************JPanel gauche********************/
    	menuGauche = new JPanel();
    	menuGauche.setBorder(BorderFactory.createLineBorder(Color.white));
    	
    	scrollpane = new JScrollPane(menuGauche,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	/**********************************************/
    	
    	/************JPanel du bas*********************/
    	menuBas = new JPanel();
    	/**********************************************/
    	
    	/************JPanel du centre******************/
    	entete = new JLabel("<html>CHOIX GESTIONNAIRE <br>"+user.getPrenom()+" "+user.getNom()+"<br>");
    	menuCentre = new JPanel();
    	menuCentre.setBorder(BorderFactory.createLineBorder(Color.white));
    	menuCentre.add(entete);
    	/**********************************************/
    	
    	/************Elements du Panel du Bas**********/
    	
    	ensBouttons = new ButtonGroup();
    	Executer = new JButton ( "Choisir" );
    	Executer.addActionListener(this);
    	
    	/*********************************************/
    	
    	/**********Boutton radio du Panel Gauche******/
        tableauBoutons = new JRadioButton[NOMS_OPERATIONS.length];
        groupeBoutons = Box.createVerticalBox();
        
        for ( int i = 0; i < NOMS_OPERATIONS.length; i++ ) {
        	tableauBoutons[i] = new JRadioButton ( NOMS_OPERATIONS[i] ); // creation du bouton i
            groupeBoutons.add ( tableauBoutons[i] );                    // ajouter le bouton au groupe de boutons
            ensBouttons.add(tableauBoutons[i]);
        } 
        tableauBoutons[0].setSelected(true);
    	/*********************************************/
    	
        /*****Ajout de tous les composants dans la fenetre**/
    	menuBas.add(Executer);
        menuGauche.add(groupeBoutons);
    	fenetrePrincipale.setLayout(new BorderLayout());   	
        fenetrePrincipale.getContentPane().add(menuCentre, BorderLayout.NORTH);
        fenetrePrincipale.getContentPane().add(menuBas, BorderLayout.SOUTH);
        fenetrePrincipale.getContentPane().add(scrollpane, BorderLayout.CENTER);
        
    	fenetrePrincipale.setVisible(true);
        /*****************************************************/
    }
	
    public void actionPerformed ( ActionEvent e ) {
    	
    	if(tableauBoutons[0].isSelected()) {	// Ajouter produit
    		fenetrePrincipale.dispose();
    		Ajouters ajout = new Ajouters();
    	    ajout.setVisible(true);
    	}
    	if(tableauBoutons[1].isSelected())	{	// modifier/supprimer produit
    		fenetrePrincipale.dispose();
    		Supprimer sup = new Supprimer();
    	    sup.setVisible(true);
    	}
    }
}