package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Painel extends Observable implements Observer {

	private List<Atendente> atendentes;
	private Senha senha;
	private List<Senha> senhas;
	private static Painel painel = null;
	private Map<Integer, Atendente> mapAtendente;
	
	private Painel(){
		
		atendentes = new ArrayList<Atendente>();
		senhas = new ArrayList<Senha>();
		mapAtendente = new HashMap<Integer, Atendente>();
		
	}
	
	public static Painel getInstance(){
		if(painel == null){
			painel = new Painel();
		}
		return painel;
	}
	
	
	@Override
	public void update(Observable atendente, Object arg1) {
		// TODO Auto-generated method stub

		if(atendente instanceof Atendente){
			Atendente a = (Atendente)atendente;
			senha = a.getSenha();
			senhas.add(senha);
			mapAtendente.put(senha.getSenha(), a);
			setChanged();
			notifyObservers();
		}
	}
	
	public void addAtendente(Atendente atendente){
		
		atendente.addObserver(this);
		atendentes.add(atendente);
				
	}

	public void addUsuario(Usuario usuario){

		mapAtendente.get(usuario.getSenha().getSenha()).setUsuario(usuario);
		mapAtendente.remove(usuario.getSenha().getSenha());
		
	}	

	public Senha getSenha() {
		return senha;
	}


	public void setSenha(Senha senha) {
		this.senha = senha;
	}


	public List<Senha> getSenhas() {
		return senhas;
	}


	public void setSenhas(List<Senha> senhas) {
		this.senhas = senhas;
	}
	
	

}
