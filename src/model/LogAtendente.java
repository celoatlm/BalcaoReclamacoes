package model;

import java.util.List;

public class LogAtendente {

	private Integer atendente;
	private Integer senha;
	private List<Integer> tempoSenhas;
	private Integer prioritaria;
		
	public LogAtendente() {
	}
	
	public LogAtendente(Integer atendente, Integer senha,
			List<Integer> tempoSenhas, Integer prioritaria) {
		this.atendente = atendente;
		this.senha = senha;
		this.tempoSenhas = tempoSenhas;
		this.prioritaria = prioritaria;
		
	}
	public Integer getAtendente() {
		return atendente;
	}
	public void setAtendente(Integer atendente) {
		this.atendente = atendente;
	}
	public Integer getSenha() {
		return senha;
	}
	public void setSenha(Integer senha) {
		this.senha = senha;
	}
	public List<Integer> getTempoSenhas() {
		return tempoSenhas;
	}
	public void setTempoSenhas(List<Integer> tempoSenhas) {
		this.tempoSenhas = tempoSenhas;
	}
	public Integer getPrioritaria() {
		return prioritaria;
	}
	public void setPrioritaria(Integer prioritaria) {
		this.prioritaria = prioritaria;
	}
	
	
	
}
