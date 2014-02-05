package control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import model.Atendente;
import model.ConfigFabricaUsuarioAtendente;
import model.Painel;
import model.Reclamacao;
import model.Senha;
import model.Usuario;
import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class FabricaUsuarioAtendente extends Observable {

	// classe responsavel por fabricar os usuarios e atendentes

	private Boolean ativoFabricaUsuario;// manter trabalhando ou não
	private Boolean kill;// manter ativo ou não
	private FilaSenhas filaSenhas;
	private Painel painel;// painel responsavel pela chamada dos usuarios
	private Integer senha;
	private static FabricaUsuarioAtendente fabricaUsuarioAtendente = null;
	private Map<Integer, Atendente> atendentesMap;
	private FabricaUsuario fabricaUsuario;
	private FabricaAtendente fabricaAtendente;
	private ConfigFabricaUsuarioAtendente configFabricaUsuarioAtendente;
	private Logger log;
	private Boolean ativo;

	private FabricaUsuarioAtendente() {
		// construtor da classe
		// a chamada do construtor instancia todas a classes nescessarias
		// mas não start sozinha as fabricas individuais

		filaSenhas = FilaSenhas.getInstance();
		painel = Painel.getInstance();

		ativoFabricaUsuario = true;
		ativo = true;
		senha = 0;
		kill = true;
		atendentesMap = new HashMap<Integer, Atendente>();

		configFabricaUsuarioAtendente = new ConfigFabricaUsuarioAtendente();
		recuperaConfig();

		fabricaAtendente = new FabricaAtendente();
		fabricaUsuario = new FabricaUsuario();

		log = Logger.getLogger(FabricaUsuarioAtendente.class.getName());

	}

	public void pausaFabricaUsuario() {
		// pausar fabrica de usuario
		ativoFabricaUsuario = !ativoFabricaUsuario;
	}

	public void startFabrica() {
		// startar ambas as fabricas
		if (ativo) {
			new Thread(fabricaAtendente, "Atendentes").start();
			new Thread(fabricaUsuario, "Clientes").start();
			ativo = false;
		}

	}

	public static FabricaUsuarioAtendente getInstance() {
		// aff
		if (fabricaUsuarioAtendente == null) {
			fabricaUsuarioAtendente = new FabricaUsuarioAtendente();
		}

		return FabricaUsuarioAtendente.fabricaUsuarioAtendente;
	}

	public void addAtendente() {
		fabricaAtendente.addAtendente();
	}

	public void removeAtendente(Integer remove) {
		atendentesMap.get(remove).removeAtendente();
		atendentesMap.remove(remove);
		notificaObservers();
	}

	public void pausaAtendente(Integer pausa) {
		atendentesMap.get(pausa).pausaAtendente();
		notificaObservers();
	}

	public void listaAtendentes() {
		String aux = "Atendentes";
		for (Atendente a : atendentesMap.values()) {
			aux += "\n : " + a.getNome();
		}
		System.out.println(aux);
	}

	public List<Atendente> getAtendentes() {
		List<Atendente> atendentes = new ArrayList<>(atendentesMap.values());
		return atendentes;
	}

	public void setColldownUsuario(String colldownUsuario) {
		configFabricaUsuarioAtendente.setColldownUsuario(Integer
				.parseInt(colldownUsuario));
		notificaObservers();
	}

	public void setQuantidadeMinimaReclamacoes(String qmr) {
		configFabricaUsuarioAtendente.setQuantidadeMinimaReclamacao(Integer
				.parseInt(qmr));
		notificaObservers();
	}

	public void setQuantidadeMaximaReclamacoes(String qmxr) {
		configFabricaUsuarioAtendente.setQuantidadeMaximaReclamacao(Integer
				.parseInt(qmxr));
		notificaObservers();
	}

	public void setTempoMinimoReclamacoes(String tmr) {
		configFabricaUsuarioAtendente.setTempoMinimoReclamacao(Integer
				.parseInt(tmr));
		notificaObservers();
	}

	public void setTempoMaximoReclamacies(String tmxr) {
		configFabricaUsuarioAtendente.setTempoMaximoReclamacao(Integer
				.parseInt(tmxr));
		notificaObservers();
	}

	private void recuperaConfig() {
		// função para leitura do xml contendo as configurações inicias default
		final String rulesFileName = "./xmlrulesConfig.xml";
		String dataFileName = "./configFabricaUsuarioAtendente.xml";

		Digester digester = newLoader(new FromXmlRulesModule() {

			@Override
			protected void loadRules() {
				// TODO Auto-generated method stub
				loadXMLRules(new File(rulesFileName));
			}
		}).newDigester();

		digester.push(configFabricaUsuarioAtendente);

		try {
			File srcFile = new java.io.File(dataFileName);
			digester.parse(srcFile);

		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(FabricaUsuarioAtendenteTest.class.getName()).error(e.getMessage());
		}

	}

	public ConfigFabricaUsuarioAtendente getConfigFabricaUsuarioAtendente() {
		return configFabricaUsuarioAtendente;
	}

	private synchronized void notificaObservers() {
		setChanged();
		notifyObservers();
	}

	protected class FabricaUsuario implements Runnable {
		// classe Thread responsavel pela criação dos usuarios
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (kill) {
				while (ativoFabricaUsuario) {
					try {
						Integer quantidadeReclamacoes = configFabricaUsuarioAtendente
								.getQuantidadeMinimaReclamacao()
								+ new Random()
										.nextInt(configFabricaUsuarioAtendente
												.getQuantidadeMaximaReclamacao());

						List<Reclamacao> reclamacoes = new ArrayList<Reclamacao>();

						for (int i = 0; i < quantidadeReclamacoes; i++) {
							int tempo = (configFabricaUsuarioAtendente
									.getTempoMinimoReclamacao() + new Random()
									.nextInt(configFabricaUsuarioAtendente
											.getTempoMaximoReclamacao())) * 1000;
							Reclamacao r = new Reclamacao(tempo);
							reclamacoes.add(r);
						}
						senha++;
						Integer atendimentoPrioritario = new Random()
								.nextInt(2);
						Senha s = new Senha(senha, atendimentoPrioritario);
						filaSenhas.inserirSenha(s);
						new Usuario(reclamacoes, s);
						Thread.sleep(configFabricaUsuarioAtendente
								.getColldownUsuario());

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}
			}
		}

	}

	protected class FabricaAtendente implements Runnable {
		// classe Thread responsavel pela criação dos Atendentes
		private Integer contAtendente = 0;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < configFabricaUsuarioAtendente
					.getQuantidadeAtendentes(); i++) {
				addAtendente();
				try {
					Thread.sleep(500);// pra da um descanso e um intervalo entre
										// cada criação de atendente
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}

		}

		protected void addAtendente() {

			Atendente atendente = new Atendente(contAtendente.toString());
			new Thread(atendente, contAtendente.toString()).start();
			// atendentes.add(atendente);
			atendentesMap.put(contAtendente, atendente);
			painel.addAtendente(atendente);
			contAtendente++;
			notificaObservers();

		}
	}

}
