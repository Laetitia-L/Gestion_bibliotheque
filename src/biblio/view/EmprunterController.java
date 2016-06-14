package biblio.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import biblio.control.EmprunterCtl;
import biblio.control.RetourCtl;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class EmprunterController implements Initializable
{
		/////////////	
		// VIEWS ///
		///////////
	
		private EmprunterMain main;
		private int  idExemplaire;
		private int  idUtilisateur;
		private int idExemplaire_retour;
		private int idUtilisateur_consulter;
		
	
		@FXML
	    private JFXTextField txt_exemplaire;

	    @FXML
	    private AnchorPane cat_user;

	    @FXML
	    private AnchorPane pane_emprunter;

	    @FXML
	    private JFXButton btn_annuler;
	    
	    @FXML
	    private JFXButton btn_deconnexion;


	    @FXML
	    private Label lbl_exemplaire;

	    @FXML
	    private JFXButton btn_emprunter;

	    @FXML
	    private AnchorPane window_emprunter;

	    @FXML
	    private Label lbl_user;

	    @FXML
	    private JFXTextField txt_user;

	    @FXML
	    private JFXButton btn_retourner;

	    @FXML
	    private Label field_cat_user;

	    @FXML
	    private Label lbl_exemplaire_retour;

	    @FXML
	    private JFXButton btn_annuler_retour;

	    @FXML
	    private GridPane grid_retour;
	    
	    @FXML
	    private JFXTextField txt_exemplaire_retour;
	    
	    @FXML
	    private GridPane grid_consulter;

	    @FXML
	    private JFXButton btn_annuler_consulter;
	    
	    @FXML
	    private JFXTextField txt_user_consulter;
	    
	    @FXML
	    private Label lbl_user_consulter;
	    
	    @FXML
	    private JFXButton btn_consulter;
	    

	    //Consructeur
	    public EmprunterController() 
	    {
	    }
	  
	    //Initialise la classe constructeur
	    @FXML
	    private void initialize() 
	    {
	       
	    }
	    
	    public  void setMain(EmprunterMain main) {
			this.main = main;
		}
	    
	    public void handleButton(){
	    	
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
		
			//les boutons pour effacer les champs	
			btn_annuler.setOnAction(new EventHandler<ActionEvent>(){
		            @Override
		            public void handle(ActionEvent event) 
		            {
		            	txt_user.clear();
		            	txt_exemplaire.clear();
		            }
				});
				
				
				btn_annuler_retour.setOnAction(new EventHandler<ActionEvent>(){
	            @Override
	            public void handle(ActionEvent event) {
	            	txt_exemplaire_retour.clear();
	            }
				});	
				
		
		
				btn_annuler_consulter.setOnAction(new EventHandler<ActionEvent>(){
		            @Override
		            public void handle(ActionEvent event) {
		            	txt_user_consulter.clear();
		            }
				});
		
				
				//Les boutons de validation d'action
				 
				btn_emprunter.setOnAction(new EventHandler<ActionEvent>() {
						
			            @Override
			            public void handle(ActionEvent event) {
			            idExemplaire = Integer.parseInt(txt_exemplaire.getText());
			    	    idUtilisateur = Integer.parseInt(txt_user.getText());
			            EmprunterCtl emprunterCtl;
						
			            try {
							emprunterCtl = new EmprunterCtl(ConnectionFactory.getConnection());
							emprunterCtl.entrerUtilisateur(idUtilisateur, idExemplaire);
						} catch (ClassNotFoundException | SQLException | IOException e) {
							e.printStackTrace();
						}
			            }
			        });
				
				
				btn_retourner.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	            idExemplaire_retour = Integer.parseInt(txt_exemplaire_retour.getText());
	            RetourCtl RetourCtl;
				
	            try {
					RetourCtl = new RetourCtl(ConnectionFactory.getConnection());
					RetourCtl.RetourEmprunt(idExemplaire_retour);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
				}
	            }
	        });
		
			 btn_consulter.setOnAction(new EventHandler<ActionEvent>() {
	
		            @Override
		            public void handle(ActionEvent event) {
		    	    idUtilisateur_consulter = Integer.parseInt(txt_user_consulter.getText());
		            EmpruntEnCoursDao eecDao;
					try {
						eecDao = new  EmpruntEnCoursDao(ConnectionFactory.getConnection());
						eecDao.findByIdUtilisateur(idUtilisateur_consulter);
					} catch (ClassNotFoundException | SQLException | IOException e) {
						e.printStackTrace();
					}
		            }
		        });
		}
}
