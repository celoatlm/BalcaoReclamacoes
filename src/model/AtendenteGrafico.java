package model;

public class AtendenteGrafico {
	//classe utilizada para facilitar a parte grafica
	private String atendente;
	private Boolean ativo;
	private String senha;
	private Integer senhaPrioritaria;
	
	public AtendenteGrafico(String atendente, Boolean ativo, String senha,
			Integer senhaPrioritaria) {
		this.atendente = atendente;
		this.ativo = ativo;
		this.senha = senha;
		this.senhaPrioritaria = senhaPrioritaria;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getSenhaPrioritaria() {
		return senhaPrioritaria;
	}

	public void setSenhaPrioritaria(Integer senhaPrioritaria) {
		this.senhaPrioritaria = senhaPrioritaria;
	}
	
	

}
