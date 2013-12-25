package model;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FilaSenhas {
	// clsse que gerencia a fila de senhas para que se mantenha a ordem da fila
	// FIFO
	private static FilaSenhas filaSenhas = null;
	private PriorityQueue<Senha> priorityQueueSenhas;
	private FilaSenhas() {
		// TODO Auto-generated constructor stub
		priorityQueueSenhas = new PriorityQueue<Senha>();
	}

	public static FilaSenhas getInstance() {
		if (filaSenhas == null) {
			filaSenhas = new FilaSenhas();
		}
		return filaSenhas;
	}

	public synchronized void inserirSenha(Senha senha) {
		priorityQueueSenhas.add(senha);
	}

	public synchronized Senha pegaPrimeira() {
		return priorityQueueSenhas.poll();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux = "Senhas Esperando:";
		ArrayList<Senha> senhas = new ArrayList<>(priorityQueueSenhas);
		for(Senha s : senhas){
			aux += s.getSenha()+" : ";
			if(s.getSenhaPrioritaria() == 0){
				aux += " senha prioritaria";
			}else{
				aux += " não prioritaria ";
			}
			aux += "\n";
		}
		return aux;
	}

}
