package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BalcaoAppJavaFX extends Application implements Runnable {

	private Stage primaryStage;
	private Boolean ativo = false;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Balcao Reclamações");
		
		Parent root = FXMLLoader
				.load(getClass().getResource("TelaBalcao.fxml"));

		this.primaryStage.setScene(new Scene(root));
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				ativo = false;
				System.out.println("fecho");
			}
		});
		primaryStage.setResizable(false);
		this.primaryStage.show();
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!ativo) {
			
			ativo = true;
			launch(this.toString());
			
		}
		
		
	}

}
