package backend;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Utilisateur {
	
	private String nom;
	private String prenom;
	private String noEmploye;
	private String motDePasse;
	private String dateCreationCompte;
	private int typeUser;	//1=commis 0=gestionnaire
	
	public Utilisateur() {
		
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getTypeUser() {
		return typeUser;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getNoEmploye() {
		return noEmploye;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public String getDateCreationCompte() {
		return dateCreationCompte;
	}
	
	public void setNom(String lenom) {
		this.nom = lenom;
	}
	
	public void setPrenom(String leprenom) {
		this.prenom = leprenom;
	}
	
	public void setMotDePasse(String mdp) {
		this.motDePasse = mdp;
	}
	
	public void setUser(String x) {
		this.noEmploye=x;
	}
	
	public Utilisateur seConnecter(String noEmploye, String mdp) {
		Utilisateur user = new Utilisateur();
		boolean vide = true;
		Connection conn = Connexion.connecter();
        try{
            Statement stmt = conn.createStatement();
                ResultSet rs;

                rs = stmt.executeQuery("SELECT * FROM Utilisateur WHERE noEmploye='" + noEmploye + "' AND motDePasse='"+ mdp +"';");
                while ( rs.next() ) {
                    
                    user.noEmploye = (rs.getString("noEmploye"));
                    user.nom = rs.getString("nom");
                    user.prenom = rs.getString("prenom");
                    user.motDePasse = rs.getString("motDePasse");
                    user.dateCreationCompte = rs.getString("dateCreationCompte");
                    user.typeUser = rs.getInt("typeUser");
                    vide = false;
                    
                }
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        Connexion.fermer(conn);
        
        if(vide) user = null;
        
        return user;
	}
}
