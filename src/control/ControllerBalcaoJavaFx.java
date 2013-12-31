package control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControllerBalcaoJavaFx {
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
	private void initialize() {
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

	}

	@FXML
	public void onCloseTab() {

	}

	private String getString(Double stringDouble) {
		return String.format("%1$.0f", stringDouble);
	}

	private void executaCommando(String commando) {
		CLIComandos.getInstance().executaComando(commando);
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

}
