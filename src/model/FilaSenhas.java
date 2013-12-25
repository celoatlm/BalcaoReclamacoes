package model;

public class FilaSenhas {
	// clsse que gerencia a fila de senhas para que se mantenha a ordem da fila
	// FIFO
	private static FilaSenhas filaSenhas = null;
	private Senha primeiraSenha = null;

	private FilaSenhas() {
		// TODO Auto-generated constructor stub
	}

	public static FilaSenhas getInstance() {
		if (filaSenhas == null) {
			filaSenhas = new FilaSenhas();
		}
		return filaSenhas;
	}

	public synchronized void inserirSenha(Senha senha) {
		if (primeiraSenha == null) {
			primeiraSenha = senha;
		} else {
			inserinaFila(primeiraSenha, senha);
		}
	}

	public synchronized Senha pegaPrimeira() {
		Senha s = primeiraSenha;
		if (primeiraSenha != null) {
			primeiraSenha = primeiraSenha.getProximaSenha();
		}
		return s;
	}

	private void inserinaFila(Senha senha, Senha pSenha) {

		if (pSenha.getSenhaPrioritaria() < senha.getSenhaPrioritaria()) {

			pSenha.setProximaSenha(senha);

		} else {
			if (senha.getProximaSenha() == null) {

				senha.setProximaSenha(pSenha);

			} else {
				inserinaFila(senha.getProximaSenha(), pSenha);
			}
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Senha s = primeiraSenha;
		String aux = "";
		while(s != null){
			aux += "Senha: "+s.getSenha() +" : Prioridade:" +s.getSenhaPrioritaria();
			s = s.getProximaSenha();
		}
		return aux;
	}
	
}
