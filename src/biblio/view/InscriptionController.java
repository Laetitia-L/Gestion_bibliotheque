package biblio.view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InscriptionController implements Initializable 
{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
	}
	
	private InscriptionMain main;

	@FXML
    private AnchorPane root_pane;

    @FXML
    private JFXButton btn_connexion;

    @FXML
    private JFXPasswordField txt_pwd;

    @FXML
    private JFXButton btn_inscription;

    @FXML
    private JFXTextField txt_pseudo;

    @FXML
    private GridPane login_box;

    @FXML
    private Label lbl_accueil;
    
    public InscriptionController() {
	}
    
    //Initialise la classe constructeur
    @FXML
    private void initialize() 
    {
       
    }
    
    public  void setMain(InscriptionMain main) {
		this.main = main;
	}
    
    @FXML
    void save_psw(ActionEvent event) {

    }

    
    @FXML
    void save_pseudo(ActionEvent event) {

    }

   
    @FXML
    void login(ActionEvent event) throws IOException {
    	String pseudo = txt_pseudo.getText();
    	String psw = txt_pwd.getText(); 
    	
    	if ( pseudo.equals("biblio") && psw.equals("biblio"))
    	{
    		//JOptionPane.showMessageDialog(null, "Bienvenue");
    		
    		lbl_accueil.setText("Bienvenue");
    		Stage primaryStage  = new Stage();
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation(EmprunterMain.class.getResource("EmprunterView2.fxml"));
			AnchorPane window_emprunter = (AnchorPane)loader.load();
			
			Scene scene = new Scene(window_emprunter);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bibliothèque des Marmusots");
			primaryStage.show();
    	}else
    	{
    		//JOptionPane.showMessageDialog(null, "Connection impossible");
    		lbl_accueil.setText("Connection impossible");
    	}
    }

    @FXML
    void signup(ActionEvent event) {

    }



}