package frontend;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import backend.Utilisateur;

import java.awt.event.*;


public class Identification extends JFrame implements ActionListener {

    /*********Composants de la fenetre**************/
    private static JFrame fenetrePrincipale;
    private static JPanel menuGauche;
    private static JPanel menuBas;
    private static JPanel menuCentre;
    private static JTextField Tuser;
    private static JLabel Luser;
    private static JPasswordField Tmdp;
    private static JLabel Lmdp;
    private static JLabel entete;
    private static JButton Executer;
   
    /***********************************************/
    
    public Identification () {
    	
    	/**********Creation Fenetre********************/
    	fenetrePrincipale = new JFrame("GestionPlus identification");
    	fenetrePrincipale.setSize(400, 300);
    	fenetrePrincipale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	fenetrePrincipale.setLocationRelativeTo(null);
    	/**********************************************/
    	/*************JPanel gauche********************/
    	menuGauche = new JPanel();
    	menuGauche.setBorder(BorderFactory.createLineBorder(Color.white));
    	menuGauche.setBackground(Color.WHITE);
    	/**********************************************/
        
    	/************JPanel du bas*********************/
    	menuBas = new JPanel();

        Luser = new JLabel("Identifiant");
        Tuser = new JTextField(10);
        Tuser.addActionListener(this);
        Lmdp = new JLabel("Mot de passe");
        Tmdp = new JPasswordField(10);
        Tmdp.addActionListener(this);
    	/**********************************************/
    	
    	/************JPanel du centre******************/
        menuCentre = new JPanel(new GridLayout(2, 2, 10, 10));
        
        Border border = BorderFactory.createTitledBorder("<html><i>GestionPlus</i></html>");
        menuCentre.setBorder(border);
    	/**********************************************/
        menuCentre.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        menuCentre.add(Luser, c);
        c.gridx = 1;
        c.gridy = 0;
        menuCentre.add(Tuser, c);
        c.gridx = 0;
        c.gridy = 2;
        menuCentre.add(Lmdp, c);
        c.gridx = 1;
        c.gridy = 2;
        menuCentre.add(Tmdp, c);
    	/************Elements du Panel du Bas**********/
        entete = new JLabel("<html>IDENTIFICATION<br>");
    	Executer = new JButton ( "Connecter" );
    	Executer.addActionListener(this);
        
        /*****Ajout de tous les composants dans la fenetre**/
    	menuBas.add(entete);
    	menuBas.setBackground(Color.white);
        menuGauche.add(Executer);
        
    	fenetrePrincipale.setLayout(new BorderLayout());   	
        fenetrePrincipale.getContentPane().add(menuCentre, BorderLayout.CENTER);
        fenetrePrincipale.getContentPane().add(menuBas, BorderLayout.NORTH);
        fenetrePrincipale.getContentPane().add(menuGauche, BorderLayout.AFTER_LAST_LINE);
        
    	fenetrePrincipale.setVisible(true);
        /*****************************************************/
    }
    public void actionPerformed ( ActionEvent e ) {
    	Utilisateur luser = new Utilisateur();
    	String lid = Tuser.getText();
    	String mdpass = Tmdp.getText();
    	luser.setMotDePasse(mdpass);
    	luser.setUser(lid);
    	if(lid.equals("") || mdpass.equals("")) {
    		try {
                JOptionPane.showMessageDialog(null,
                    "Veuillez mettre in ID et Mot de passe",
                    "Erreur",
                    JOptionPane.INFORMATION_MESSAGE);
        	}catch (Exception oe) {}
    	}else{
    		fenetrePrincipale.dispose();
                Utilisateur user = luser.seConnecter(lid, mdpass);
    		if(user==null) {
    			try {
                	JOptionPane.showMessageDialog(null,
                    	"User invalide",
                    	"Erreur",
                    	JOptionPane.INFORMATION_MESSAGE);
        		}catch (Exception oe) {}
    		}else{
    			switch(user.getTypeUser()) {
    			case 0: //gestionnaire
    				ChoixGestionnaire choix = new ChoixGestionnaire(user);
    				break;
    			case 1: //commis
    				InterFacture inter = new InterFacture(user);
    				break;
    			}
    		}
    	}
    	
    }

}