package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Painel extends Observable implements Observer {

	// classe responsavel por ouvir o chamado dos atendentes e comunicar os
	// usuarios
	private List<Senha> senhas;// lista de todas as senha
	private static Painel painel = null;
	private Map<Integer, Atendente> mapAtendente; // map contendo os atendentes
													// por senha

	private Painel() {
		senhas = new ArrayList<Senha>();
		mapAtendente = new HashMap<Integer, Atendente>();
	}

	public static Painel getInstance() {
		if (painel == null) {
			painel = new Painel();
		}
		
		return painel;
		
	}

	@Override
	public void update(Observable atendente, Object senha) {
		// TODO Auto-generated method stub
		// função que recebe o chamado de um atendente e chama os usuarios
		if (atendente instanceof Atendente) {
			Atendente a = (Atendente) atendente;
			senhas.add((Senha) senha);
			mapAtendente.put(((Senha) senha).getSenha(), a);
			setChanged();
			notifyObservers(senha);
		}
	}

	public void addAtendente(Atendente atendente) {

		atendente.addObserver(this);

	}

	public void addUsuario(Usuario usuario) {

		mapAtendente.get(usuario.getSenha().getSenha()).setUsuario(usuario);
		mapAtendente.remove(usuario.getSenha().getSenha());

	}

}
