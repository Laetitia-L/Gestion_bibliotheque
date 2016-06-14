package biblio.view;

import java.io.IOException;

import com.jfoenix.controls.JFXTabPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmprunterMain  extends Application
{
	

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
				loader.setLocation(EmprunterMain.class.getResource("EmprunterView2.fxml"));
				AnchorPane window_emprunter = (AnchorPane)loader.load();
				
				EmprunterController emprunterController = loader.getController();
				emprunterController.setMain(this);
				
				Scene scene = new Scene(window_emprunter);
				
				primaryStage.setScene(scene);
				primaryStage.setTitle("Bibliothèque des Marmusots");
				primaryStage.show();
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
				
		}
	
	
	
	
}
