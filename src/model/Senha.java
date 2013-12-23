package model;

public class Senha {
	
	private Integer senha; //a senha propriamente dita
	private Integer senhaPrioritaria; //a prioridade 0 para mais prioritaria
	private Senha proximaSenha;// usada para criar a fila
	
	public Senha(Integer senha, Integer senhaPrioritaria) {
		this.senha = senha;
		this.senhaPrioritaria = senhaPrioritaria;
		proximaSenha = null;
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

	public Senha getProximaSenha() {
		return proximaSenha;
	}

	public void setProximaSenha(Senha proximaSenha) {
		this.proximaSenha = proximaSenha;
	}
	
	
	
	
	

}
