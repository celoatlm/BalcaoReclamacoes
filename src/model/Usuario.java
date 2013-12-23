package model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer{

	private Observable painel;
	private Senha senha;
	private List<Reclamacao> reclamacoes;
	
	public Usuario(Observable painel,List<Reclamacao> reclamacoes, Senha senha) {
		
		this.painel = painel;
		this.reclamacoes = reclamacoes;
		this.senha = senha;
		
		this.painel.addObserver(this);
			
	}

	@Override
	public void update(Observable painel, Object arg1) {
		// TODO Auto-generated method stub
		
		if(painel instanceof Painel){
			int senha = ((Painel) painel).getSenha().getSenha();
			if(senha == this.senha.getSenha()){
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

}
