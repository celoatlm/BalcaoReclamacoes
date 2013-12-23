package model;

public class Senha {
	
	private Integer senha;
	private Boolean senhaPrioritaria;
	
	public Senha(Integer senha, Boolean senhaPrioritaria) {
		this.senha = senha;
		this.senhaPrioritaria = senhaPrioritaria;
	}

	public Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
		this.senha = senha;
	}

	public Boolean getSenhaPrioritaria() {
		return senhaPrioritaria;
	}

	public void setSenhaPrioritaria(Boolean senhaPrioritaria) {
		this.senhaPrioritaria = senhaPrioritaria;
	}
	
	
	

}
