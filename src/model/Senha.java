package model;

public class Senha implements Comparable<Senha> {
	// classe Senha que vira um nó da fila, ela implementa Comparable para poder
	// cria um PriorityQueue para ela na filaSenhas
	private Integer senha; // a senha propriamente dita
	private Integer senhaPrioritaria; // a prioridade 0 para mais prioritaria

	public Senha(Integer senha, Integer senhaPrioritaria) {
		this.senha = senha;
		this.senhaPrioritaria = senhaPrioritaria;
	}

	public Integer getSenha() {
		return senha;
	}

	public Integer getSenhaPrioritaria() {
		return senhaPrioritaria;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux = "Senha: "+senha.toString()+" ";
		if(senhaPrioritaria == 0){
			aux += "senha-prioritaria ";
		}else{
			aux += "senha-não-prioritaria ";
		}
		return aux;
	}

	@Override
	public int compareTo(Senha s) {
		// TODO Auto-generated method stub
		// esse metodo retorna um int conforme a comparação com outra senha
		if(this.senhaPrioritaria == s.getSenhaPrioritaria())
		{
			return this.senha.compareTo(s.getSenha());
		}else{
			return this.senhaPrioritaria.compareTo(s.senhaPrioritaria);
		}
		
	}
}
