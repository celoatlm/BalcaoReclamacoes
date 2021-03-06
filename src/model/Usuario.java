package model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer {
	// classe responsavel pela implementação do usuario

	//private Observable painel;// lembrar do padão de projeto Observer
	private Senha senha;
	private List<Reclamacao> reclamacoes;

	public Usuario(List<Reclamacao> reclamacoes, Senha senha) {
		// Construtor da classe
		//this.painel = painel;
		this.reclamacoes = reclamacoes;
		this.senha = senha;
		//this.painel.addObserver(this);
		Painel.getInstance().addObserver(this);

	}

	@Override
	public void update(Observable painel, Object senha) {
		// metodo responsavel por receber o aviso do painel
		// saber quando foi chamado uma nova senha
		// TODO Auto-generated method stub

		if (painel instanceof Painel) {
			Senha s = (Senha) senha;
			if (s.getSenha() == this.senha.getSenha()) {
				((Painel) painel).addUsuario(this);
			}
		}
	}

	public Senha getSenha() {
		return senha;
	}

	public List<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//esse metodo retorna todo o usuario em forma de String
		String usuario = "Usuario: "+ senha.toString();
		usuario += " Reclamações: ";
		for (Reclamacao r : reclamacoes) {
			usuario += + r.getTempo() + " ";
		}
		return usuario;
	}

}
