package model;

public class FilaSenhas {

	private static FilaSenhas filaSenhas = null;
	// private static Queue<Senha> senhas;
	private Senha primeiraSenha = null;

	private FilaSenhas() {
		// TODO Auto-generated constructor stub
		// senhas = new LinkedBlockingDeque<Senha>();
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

		// senhas.offer(senha);
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
				// if(){
				senha.setProximaSenha(pSenha);
				// }//parei aqui tenho que arruma a prioridade
			} else {
				inserinaFila(senha.getProximaSenha(), pSenha);
			}
		}
	}
}
