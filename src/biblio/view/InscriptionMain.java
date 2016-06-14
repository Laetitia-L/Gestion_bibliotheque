package biblio.view;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InscriptionMain extends Application {

	Button btn_connexion;
	Button btn_inscription;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InscriptionMain.class.getResource("InscriptionView.fxml"));
			AnchorPane root_pane = (AnchorPane)loader.load();
			
			Scene scene = new Scene(root_pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bibliothèque des Marmusots");
			primaryStage.show();
			
			//btn_connexion.setOnAction(e -> JOptionPane.showMessageDialog(null, "Vous êtes connecté."));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
/*	@Override
	public void handle(ActionEvent event) 
	{
		if(event.getSource()==btn_connexion)
		{
			JOptionPane.showMessageDialog(null, "Vous êtes connecté.");
		}
		if(event.getSource()==btn_inscription)
		{
			JOptionPane.showMessageDialog(null, "Vous êtes connecté.");
		}
	}
	*/
	
	 
	
	

}
