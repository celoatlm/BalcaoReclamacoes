package model;

import java.util.ArrayList;
import java.util.List;
public class LogAtendente {

	//classe criada para os logs dos atendentes
	
	private String atendente;
	private String senha;
	private String prioritaria;
	private Long data;
	
	private List<String> tempoSenhas;
	
	private ArrayList<Reclamacao> reclamacoes;
	
	public LogAtendente() {
		reclamacoes = new ArrayList<>();
	}
		
	public LogAtendente(String atendente, String senha,
			List<String> tempoSenhas, String prioritaria, Long data) {
		this.atendente = atendente;
		this.senha = senha;
		this.tempoSenhas = tempoSenhas;
		this.prioritaria = prioritaria;
		this.data = data;
		
	}
	
	public LogAtendente(String atendente, String senha,
			ArrayList<Reclamacao> reclamacoes, String prioritaria, Long data) {
		this.atendente = atendente;
		this.senha = senha;
		this.reclamacoes = reclamacoes;
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

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		String aux = "Atendente: "+atendente+ " Usuario:Senha " + senha+ " ";
//		if(prioritaria.equals("0") ){
//			aux += "senha-prioritaria";
//		}else{
//			aux += "senha-não-prioritaria";
//		}
//		for(Reclamacao i : reclamacoes){
//			aux += i+" ";
//		}
//		return aux;
//	}
	
//	public LogAtendente(String atendente, String senha, String prioritaria,
//		Long data, ArrayList<Reclamacao> reclamacoes) {
//	this.atendente = atendente;
//	this.senha = senha;
//	this.prioritaria = prioritaria;
//	this.data = data;
//	this.reclamacoes = reclamacoes;
//}

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

	public void addReclamacao(Reclamacao reclamacao){
		reclamacoes.add(reclamacao);
	}
	
	public ArrayList<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	public void setReclamacoes(ArrayList<Reclamacao> reclamacoes) {
		this.reclamacoes = reclamacoes;
	}
		
}
