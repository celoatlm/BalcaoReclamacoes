package model;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;


public class FilaSenhas {
	
	private static FilaSenhas filaSenhas = null;
	private static Queue<Senha> senhas; 
	
	
	
	private FilaSenhas() {
		// TODO Auto-generated constructor stub
		senhas = new LinkedBlockingDeque<Senha>();
	}
	
	public static FilaSenhas getInstance(){
		if (filaSenhas == null) {
			filaSenhas = new FilaSenhas();
		}
		return filaSenhas;
	}
	
	public synchronized void inserirSenha(Senha senha){
		senhas.offer(senha);
	}
	
	public synchronized Senha pegaPrimeira(){
		
		return senhas.poll();
	}

}
