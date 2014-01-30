package view;

import model.AtendenteGrafico;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PaneAtendente extends Pane {
	// classe utilizada para mostrar o funcionamento do atendente para a parte
	// grafica

	private AtendenteGrafico ag;
	private Label lNome;
	private Label lSenha;
	private Label lSenhaPrioritaria;
	private VBox layout;

	public PaneAtendente(AtendenteGrafico ag) {
		this.ag = ag;
		atualizaPane();

	}

	private void atualizaPane() {
		this.setMaxSize(200, 150);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				layout = new VBox();

				lNome = new Label("Atendente: " + ag.getAtendente());
				
				lSenha = new Label("Senha: " + ag.getSenha());

				switch (ag.getSenhaPrioritaria()) {
				case 0:
					lSenhaPrioritaria = new Label("senha prioritaria");
					break;
				case 1:
					lSenhaPrioritaria = new Label("senha normal");
					break;
				case 2:
					lSenhaPrioritaria = new Label("");
				default:
					break;
				}
				if (ag.getAtivo()) {
					setStyle("-fx-background-color: green;");
				} else {
					setStyle("-fx-background-color: red;");
				}
				
				lNome.setFont(new Font(20));
				lSenha.setFont(new Font(15));
				lSenhaPrioritaria.setFont(new Font(15));
				
				layout.setMaxSize(200, 150);
				layout.getChildren().clear();
				layout.getChildren().add(lNome);
				layout.getChildren().add(lSenha);
				layout.getChildren().add(lSenhaPrioritaria);

				getChildren().add(layout);
			}
		});
	}

	public AtendenteGrafico getAg() {
		return ag;
	}

	public void setAg(AtendenteGrafico ag) {
		this.ag = ag;
		atualizaPane();
	}

}
