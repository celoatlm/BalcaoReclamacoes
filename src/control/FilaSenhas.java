package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.PriorityBlockingQueue;

import model.Senha;

public class FilaSenhas extends Observable{
	// clsse que gerencia a fila de senhas para que se mantenha a ordem da fila
	// FIFO
	private static FilaSenhas filaSenhas = null;
	private PriorityBlockingQueue<Senha> priorityQueueSenhas;
	
	private FilaSenhas() {
		// TODO Auto-generated constructor stub
		priorityQueueSenhas = new PriorityBlockingQueue<Senha>(30);
	}

	public static FilaSenhas getInstance() {
		if (filaSenhas == null) {
			filaSenhas = new FilaSenhas();
		}
		return filaSenhas;
	}

	public synchronized void inserirSenha(Senha senha) {
		priorityQueueSenhas.add(senha);
		notificaObservers();
	}

	public synchronized Senha pegaPrimeira() {
		Senha s = priorityQueueSenhas.poll();
		notificaObservers();
		return s;
	}
	
	public synchronized List<Senha> getSenhas(){
		List<Senha> senhasList = new ArrayList<>(priorityQueueSenhas);
		return senhasList;
	}
	public void notificaObservers(){
		setChanged();
		notifyObservers(new ArrayList<Senha>(priorityQueueSenhas));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux = "Senhas:";
		ArrayList<Senha> senhas = new ArrayList<>(priorityQueueSenhas);
		for (Senha s : senhas) {
			aux += s.getSenha() + " : ";
			if (s.getSenhaPrioritaria() == 0) {
				aux += " senha prioritaria";
			} else {
				aux += " não prioritaria ";
			}
			aux += "\n";
		}
		return aux;
	}

}
