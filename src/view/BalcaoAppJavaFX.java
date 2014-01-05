package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BalcaoAppJavaFX extends Application implements Runnable{

	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Balcao Reclamações");
		
		Parent root = FXMLLoader.load(getClass().getResource("TelaBalcao.fxml"));
        
		
        this.primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        this.primaryStage.show();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch(null);
	}
	
}
