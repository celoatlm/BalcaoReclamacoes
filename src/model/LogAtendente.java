package model;

import java.util.List;
public class LogAtendente {

	//classe criada para os logs dos atendentes
	
	private String atendente;
	private String senha;
	private List<String> tempoSenhas;
	private String prioritaria;
	private Long data;
	
	public LogAtendente() {
	}
		
	public LogAtendente(String atendente, String senha,
			List<String> tempoSenhas, String prioritaria, Long data) {
		this.atendente = atendente;
		this.senha = senha;
		this.tempoSenhas = tempoSenhas;
		this.prioritaria = prioritaria;
		this.data = data;
		
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux = "Atendente: "+atendente+ " Usuario:Senha " + senha+ " ";
		if(prioritaria.equals("0") ){
			aux += "senha-prioritaria";
		}else{
			aux += "senha-não-prioritaria";
		}
		for(String i : tempoSenhas){
			aux += i+" ";
		}
		return aux;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getTempoSenhas() {
		return tempoSenhas;
	}

	public void setTempoSenhas(List<String> tempoSenhas) {
		this.tempoSenhas = tempoSenhas;
	}

	public String getPrioritaria() {
		return prioritaria;
	}

	public void setPrioritaria(String prioritaria) {
		this.prioritaria = prioritaria;
	}
	public void addReclamacao(String reclamacao){
		tempoSenhas.add(reclamacao);
	}
	
	public void stringData(String data){
		this.data = Long.parseLong(data);
	}
	
	public Long getData() {
		return data;
	}
	public void setData(Long data) {
		this.data = data;
	}
		
}
