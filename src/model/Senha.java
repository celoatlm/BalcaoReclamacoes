package model;

public class Senha implements Comparable<Senha>{
	//classe Senha que vira um nó da fila, ela implementa Comparable para poder cria um PriorityQueue para ela na filaSenhas
	private Integer senha; //a senha propriamente dita
	private Integer senhaPrioritaria; //a prioridade 0 para mais prioritaria
	
	public Senha(Integer senha, Integer senhaPrioritaria) {
		this.senha = senha;
		this.senhaPrioritaria = senhaPrioritaria;
	}

	public Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
		this.senha = senha;
	}

	public Integer getSenhaPrioritaria() {
		return senhaPrioritaria;
	}

	public void setSenhaPrioritaria(Integer senhaPrioritaria) {
		this.senhaPrioritaria = senhaPrioritaria;
	}
	@Override
	public int compareTo(Senha s) {
		// TODO Auto-generated method stub
		if(this.senhaPrioritaria < s.getSenhaPrioritaria()){
			return -1;
		}
		if(this.senhaPrioritaria > s.getSenhaPrioritaria()){
			return 1;
		}
		return 0;
	}
}
