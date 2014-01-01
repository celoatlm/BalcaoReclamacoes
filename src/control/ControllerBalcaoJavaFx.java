package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Atendente;
import model.Painel;
import model.Senha;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerBalcaoJavaFx implements Observer {
	// classe responsavel por controlar as ações da parte grafica da app

	private String commando;
	@FXML
	private Tab tBalcao;
	@FXML
	private TabPane tpPrincipal;
	@FXML
	private Tab tConfigs;
	@FXML
	private Tab tSobre;
	@FXML
	private Button startApp;
	@FXML
	private Button bAddAtendente;
	@FXML
	private Slider sColldownUsuario;
	@FXML
	private Slider sTMR;
	@FXML
	private Slider sTMXR;
	@FXML
	private Slider sQMR;
	@FXML
	private Slider sQMXR;
	@FXML
	private ComboBox<String> cbPausaAtendente;
	@FXML
	private ComboBox<String> cbParaAtendente;
	@FXML
	private Button bPausaAtendente;
	@FXML
	private Button bParaAtendente;
	@FXML
	private SplitPane spPrincipal;
	@FXML
	private AnchorPane apPainel;
	@FXML
	private VBox vbPainel;
	@FXML
	private Label lSenha;
	@FXML 
	private VBox vbLista;
	
	private Observable painel;

	private List<Senha> senhas = null;

	@FXML
	private void initialize() {

		FabricaUsuarioAtendente.getInstance().addObserver(this);
		FilaSenhas.getInstance().addObserver(this);
		painel = Painel.getInstance();
		painel.addObserver(this);
		commando = new String();

		setSlides();
		tpPrincipal.getSelectionModel().selectNext();
		sColldownUsuario.valueChangingProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> arg0,
							Boolean arg1, Boolean arg2) {
						// TODO Auto-generated method stub
						if (arg1) {
							commando = "-colldownUsuario "
									+ getString(sColldownUsuario.getValue());
							executaCommando(commando);
						}

					}

				});
		sTMR.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method
				if (arg1) {
					commando = "-tmr " + getString(sTMR.getValue());
					executaCommando(commando);
				}
			}
		});
		sTMXR.valueChangingProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> arg0,
							Boolean arg1, Boolean arg2) {
						// TODO Auto-generated method stub
						if (arg1) {
							commando = "-tmxr " + getString(sTMXR.getValue());
							executaCommando(commando);
						}
					}
				});
		sQMR.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (arg1) {
					commando = "-qmr " + getString(sQMR.getValue());
					executaCommando(commando);
				}
			}
		});
		sQMXR.valueChangingProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> arg0,
							Boolean arg1, Boolean arg2) {
						// TODO Auto-generated method stub
						if (arg1) {
							commando = "-qmxr " + getString(sQMXR.getValue());
							executaCommando(commando);
						}
					}
				});

		atualizaConfigs();
	}

	@FXML
	public void onCloseTab() {

	}

	private void atualizaComboBox() {
		cbParaAtendente.getItems().clear();
		cbPausaAtendente.getItems().clear();
		for (Atendente a : FabricaUsuarioAtendente.getInstance()
				.getAtendentes()) {
			cbParaAtendente.getItems().add(a.getNome());
			cbPausaAtendente.getItems().add(a.getNome());
		}
	}

	private String getString(Double stringDouble) {
		return String.format("%1$.0f", stringDouble);
	}

	private void executaCommando(String commando) {
		CLIComandos.getInstance().executaComando(commando);
	}

	@FXML
	private void pausaAtendente() {
		commando = "pausaAtendente " + cbPausaAtendente.getValue();
		executaCommando(commando);
	}

	@FXML
	private void paraAtendente() {
		commando = "removeAtendnete " + cbParaAtendente.getValue();
		executaCommando(commando);
	}

	@FXML
	private void startApp() {
		commando = "-startApp";
		CLIComandos.getInstance().executaComando(commando);
	}

	@FXML
	private void addAtendente() {
		commando = "-addAtendente";
		CLIComandos.getInstance().executaComando(commando);
	}

	private void atualizaConfigs() {
		setSlides();
		atualizaComboBox();
	}

	private void setSlides() {

		sColldownUsuario.setValue(Double.parseDouble(FabricaUsuarioAtendente
				.getInstance().getConfigFabricaUsuarioAtendente()
				.getColldownUsuario()));
		sTMR.setValue(Double.parseDouble(FabricaUsuarioAtendente.getInstance()
				.getConfigFabricaUsuarioAtendente().getTempoMinimoReclamacao()));
		sTMXR.setValue(Double.parseDouble(FabricaUsuarioAtendente.getInstance()
				.getConfigFabricaUsuarioAtendente().getTempoMaximoReclamacao()));
		sQMR.setValue(Double.parseDouble(FabricaUsuarioAtendente.getInstance()
				.getConfigFabricaUsuarioAtendente()
				.getQuantidadeMinimaReclamacao()));
		sQMXR.setValue(Double.parseDouble(FabricaUsuarioAtendente.getInstance()
				.getConfigFabricaUsuarioAtendente()
				.getQuantidadeMaximaReclamacao()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observable, Object arg1) {
		// TODO Auto-generated method stub
		if (observable instanceof FabricaUsuarioAtendente) {
			atualizaConfigs();
		}
		if (observable instanceof FilaSenhas) {
			int i = vbLista.getChildren().size();
			Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						vbLista.getChildren().clear();
					}
				});
			senhas = new ArrayList<>((List<Senha>)arg1);
			atualizaPainel();
		}
		if (observable instanceof Painel) {
			final String aux = ((Senha) arg1).getSenha().toString();

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					lSenha.setText(aux);
				}
			});
		}

	}

	private void atualizaPainel() {
		// TODO Auto-generated method stub
		if (senhas != null) {
			for (final Senha s : senhas) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						//vbPainel.getChildren().clear();
						vbLista.getChildren().add(
								new Label(s.getSenha().toString()));
					}
				});
			}
		}
	}

}
