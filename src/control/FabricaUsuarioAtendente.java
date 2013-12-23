package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Atendente;
import model.FilaSenhas;
import model.Painel;
import model.Reclamacao;
import model.Senha;
import model.Usuario;

public class FabricaUsuarioAtendente {

	//classe responsavel por fabricar os usuarios e atendentes
	
	private Integer colldown;// tempo de intervalo para cada criação dos
								// usuarios
	private Boolean ativo;// manter trabalhando ou não
	private Boolean kill;// manter ativo ou não
	private FilaSenhas filaSenhas;
	private Painel painel;// painel responsavel pela chamada dos usuarios
	private Integer quantidadeMaximaReclamaca = 4;
	private Integer quantidadeMinimaReclamacao = 1;
	private Integer tempoMaximoReclamacao = 4;
	private Integer tempoMinimoReclamacao = 1;
	private Integer senha;
	private static FabricaUsuarioAtendente fabricaUsuarioAtendente = null;
	private List<Atendente> atendentes;
	private FabricaUsuario fabricaUsuario;
	private FabricaAtendente fabricaAtendente;

	private FabricaUsuarioAtendente() {

		filaSenhas = FilaSenhas.getInstance();
		this.painel = Painel.getInstance();
		
		colldown = 1000;
		ativo = true;
		senha = 0;
		this.kill = true;
		this.atendentes = new ArrayList<>();

		fabricaUsuario = new FabricaUsuario();
		new Thread(fabricaUsuario, "Clientes").start();

//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		fabricaAtendente = new FabricaAtendente();
		new Thread(fabricaAtendente, "Atendentes").start();

	}

	public static FabricaUsuarioAtendente getInstance() {
		if (fabricaUsuarioAtendente == null) {
			fabricaUsuarioAtendente = new FabricaUsuarioAtendente();
		}
		return FabricaUsuarioAtendente.fabricaUsuarioAtendente;
	}

	public Integer getColldown() {
		return colldown;
	}

	public void setColldown(Integer colldown) {
		this.colldown = colldown;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void addAtendente() {
		fabricaAtendente.addAtendente();
	}

	public void removeAtendente(Integer remove) {
		fabricaAtendente.removeAtendente(remove);
	}

	public void pausaAtendente(Integer pausa, Boolean a) {
		fabricaAtendente.pausaAtendente(pausa, a);

	}

	protected class FabricaUsuario implements Runnable {
		//classe Thread responsavel pela criação dos usuarios
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (kill) {
				while (ativo) {
					try {
						Integer quantidadeReclamacoes = quantidadeMinimaReclamacao
								+ new Random()
										.nextInt(quantidadeMaximaReclamaca);

						List<Reclamacao> reclamacoes = new ArrayList<Reclamacao>();

						for (int i = 0; i < quantidadeReclamacoes; i++) {
							int tempo = (tempoMinimoReclamacao + new Random()
									.nextInt(tempoMaximoReclamacao)) * 1000;
							Reclamacao r = new Reclamacao(tempo);
							reclamacoes.add(r);
						}
						senha++;
						Boolean atendimentoPrioritario = new Random()
								.nextBoolean();
						Senha s = new Senha(senha, atendimentoPrioritario);
						filaSenhas.inserirSenha(s);
						 new Usuario(painel, reclamacoes, s);
						Thread.sleep(colldown);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	protected class FabricaAtendente implements Runnable {
		//classe Thread responsavel pela criação dos Atendentes
		private Integer contAtendente = 0;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 6; i++) {
				addAtendente();
			}

		}

		protected void addAtendente() {

			Atendente atendente = new Atendente(contAtendente.toString());
			new Thread(atendente, contAtendente.toString()).start();
			atendentes.add(atendente);
			painel.addAtendente(atendente);
			contAtendente++;
		
		}

		protected void removeAtendente(Integer remove) {

			atendentes.remove(remove);
		}

		protected void pausaAtendente(Integer pausa, Boolean a) {

			atendentes.get(pausa).setAtivo(a);

		}

	}

}
