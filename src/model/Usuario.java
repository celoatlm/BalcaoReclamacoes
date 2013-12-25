package model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer {
	//classe responsavel pela implementação do usuario
	
	private Observable painel;//lembrar do padão deprojeto Observer
	private Senha senha;
	private List<Reclamacao> reclamacoes;

	public Usuario(Observable painel, List<Reclamacao> reclamacoes, Senha senha) {
		//Construtor da classe
		this.painel = painel;
		this.reclamacoes = reclamacoes;
		this.senha = senha;

		this.painel.addObserver(this);

	}

	@Override
	public void update(Observable painel, Object senha) {
		//função responsavel por receber o aviso do painel
		//saber quando foi chamado uma nova senha
		// TODO Auto-generated method stub

		if (painel instanceof Painel) {
			Senha s = (Senha)senha;
			if(s.getSenha() == this.senha.getSenha()){
				((Painel) painel).addUsuario(this);
			}
			

		}
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public List<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	public void setReclamacoes(List<Reclamacao> reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String usuario = "Senha: " + senha.getSenha();
		if (senha.getSenhaPrioritaria() == 0) {
			usuario += " :senha prioritaria";
		} else {
			usuario += " :senha não prioritaria";
		}
		usuario += " :Reclamações: ";
		for (Reclamacao r : reclamacoes) {
			usuario += "-" + r.getTempo() + "-";
		}
		usuario += ":";
		return usuario;
	}

}
