package model;

import java.util.Observable;

public class Atendente extends Observable implements Runnable {
	// Classe Atendente implementa um tipo Runnable
	private Boolean ativo;// pra manter ativo
	private Senha senha;// senha que esta executando na vez
	private Usuario usuario;// usuario que esta no atendimento
	private Boolean kill;// para matar a thread
	private FilaSenhas filaSenhas;
	private String nome;
	private Boolean chamaNovaSenha;

	public Atendente(String nome) {
		kill = true;
		filaSenhas = FilaSenhas.getInstance();
		senha = filaSenhas.pegaPrimeira();
		this.nome = nome;
		ativo = false;
		chamaNovaSenha = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (kill) {// mantem a thread viva até alguem mata la XD
			if (ativo) {// pausa a thread sem mata la :D

				for (Reclamacao r : usuario.getReclamacoes()) {
					try {
						Thread.sleep(r.getTempo());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}System.out.println("Atendente: " +nome+ " : Usuario: "
						+ usuario.toString());
				ativo = false;
				
			}
			if (chamaNovaSenha) {// essa verificação é nescessaria pois posso
				// desativalo no meio do processo
				// de atendimento, e o atendente ainda assim
				// chama um novo usuario (atendente burro)
				senha = filaSenhas.pegaPrimeira();
			}
			while (senha == null && chamaNovaSenha) {// while para esperar até
														// ter usuario
														// na fila
				try {
					Thread.sleep(100);
					senha = filaSenhas.pegaPrimeira();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (chamaNovaSenha) {
				setMudanca();
			}

		}
	}

	public void setMudanca() {
		setChanged();
		notifyObservers(senha);
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
		this.ativo = true;
	}

	public void removeAtendente() {
		kill = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void pausaAtendente() {
		chamaNovaSenha = !chamaNovaSenha;
	}

}
