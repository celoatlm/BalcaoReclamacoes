package control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Atendente;
import model.ConfigFabricaUsuarioAtendente;
import model.FilaSenhas;
import model.Painel;
import model.Reclamacao;
import model.Senha;
import model.Usuario;
import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.xml.sax.SAXException;

public class FabricaUsuarioAtendente {

	// classe responsavel por fabricar os usuarios e atendentes

	private Boolean ativoFabricaUsuario;// manter trabalhando ou n�o
	private Boolean kill;// manter ativo ou n�o
	private FilaSenhas filaSenhas;
	private Painel painel;// painel responsavel pela chamada dos usuarios
	private Integer senha;
	private static FabricaUsuarioAtendente fabricaUsuarioAtendente = null;
	private Map<Integer, Atendente> atendentesMap;
	private FabricaUsuario fabricaUsuario;
	private FabricaAtendente fabricaAtendente;
	private ConfigFabricaUsuarioAtendente configFabricaUsuarioAtendente;

	private FabricaUsuarioAtendente() {
		// construtor da classe
		// a chamada do construtor instancia todas a classes nescessarias
		// mas n�o start sozinha as fabricas individuais

		filaSenhas = FilaSenhas.getInstance();
		painel = Painel.getInstance();

		ativoFabricaUsuario = true;
		senha = 0;
		kill = true;
		atendentesMap = new HashMap<Integer, Atendente>();

		configFabricaUsuarioAtendente = new ConfigFabricaUsuarioAtendente();
		recuperaConfig();

		fabricaAtendente = new FabricaAtendente();
		fabricaUsuario = new FabricaUsuario();

	}

	public void pausaFabricaUsuario() {
		// pausar fabrica de usuario
		ativoFabricaUsuario = !ativoFabricaUsuario;
	}

	public void startFabrica() {
		// startar ambas as fabricas
		new Thread(fabricaAtendente, "Atendentes").start();
		new Thread(fabricaUsuario, "Clientes").start();
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
		System.out.println("----------------- removendo atendente "+remove+"----------------- ");
		atendentesMap.get(remove).removeAtendente();
		atendentesMap.remove(remove);
	}

	public void pausaAtendente(Integer pausa) {
		System.out.println("----------------- parando atendente "+pausa+"----------------- ");
		atendentesMap.get(pausa).pausaAtendente();
	}
	
	public void setColdownUsuario(String coldownUsuario){
		configFabricaUsuarioAtendente.setColldownUsuario(coldownUsuario);
	}
	
	public void listaAtendentes(){
		String aux = "Atendentes";
		for(Atendente a : atendentesMap.values()){
			aux += "\n : " + a.getNome();
		}
		System.out.println(aux);
	}

	private void recuperaConfig() {
		// fun��o para leitura do xml contendo as configura��es inicias default
		final String rulesFileName = "./src/model/xmlrules.xml";
		String dataFileName = "./src/model/configFabricaUsuarioAtendente.xml";

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
		}

	}

	protected class FabricaUsuario implements Runnable {
		// classe Thread responsavel pela cria��o dos usuarios
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (kill) {
				while (ativoFabricaUsuario) {
					try {
						Integer quantidadeReclamacoes = Integer
								.parseInt(configFabricaUsuarioAtendente
										.getQuantidadeMinimaReclamacao())
								+ new Random()
										.nextInt(Integer
												.parseInt(configFabricaUsuarioAtendente
														.getQuantidadeMaximaReclamacao()));

						List<Reclamacao> reclamacoes = new ArrayList<Reclamacao>();

						for (int i = 0; i < quantidadeReclamacoes; i++) {
							int tempo = (Integer
									.parseInt(configFabricaUsuarioAtendente
											.getTempoMinimoReclamacao()) + new Random()
									.nextInt(Integer
											.parseInt(configFabricaUsuarioAtendente
													.getTempoMaximoReclamacao()))) * 1000;
							Reclamacao r = new Reclamacao(tempo);
							reclamacoes.add(r);
						}
						senha++;
						Integer atendimentoPrioritario = new Random()
								.nextInt(2);
						Senha s = new Senha(senha, atendimentoPrioritario);
						filaSenhas.inserirSenha(s);
						new Usuario(painel, reclamacoes, s);
						Thread.sleep(Integer
								.parseInt(configFabricaUsuarioAtendente
										.getColldownUsuario()));

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	protected class FabricaAtendente implements Runnable {
		// classe Thread responsavel pela cria��o dos Atendentes
		private Integer contAtendente = 0;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < Integer.parseInt(configFabricaUsuarioAtendente
					.getQuantidadeAtendentes()); i++) {
				addAtendente();
				try {
					Thread.sleep(500);// pra da um descanso e um intervalo entre
										// cada cria��o de atendente
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		protected void addAtendente() {

			Atendente atendente = new Atendente(contAtendente.toString());
			new Thread(atendente, contAtendente.toString()).start();
			//atendentes.add(atendente);
			atendentesMap.put(contAtendente, atendente);
			painel.addAtendente(atendente);
			contAtendente++;

		}
	}
}
