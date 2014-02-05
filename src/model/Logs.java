package model;

import java.util.ArrayList;
import java.util.List;

public class Logs {

	private List<LogAtendente> listaLogAtendentes;
	
	public Logs(){
		listaLogAtendentes = new ArrayList<>();
		}
	
	public void addLogAtendente(LogAtendente lg){
		listaLogAtendentes.add(lg);
		//lg = listaLogAtendentes.get(listaLogAtendentes.size()-1);
		//System.out.println("LG "+" : "+lg.getAtendente()+" : "+lg.getSenha()+" : "+lg.getReclamacoes());
	}

	public List<LogAtendente> getListaLogAtendentes() {
		return listaLogAtendentes;
	}

	public void setListaLogAtendentes(List<LogAtendente> listaLogAtendentes) {
		this.listaLogAtendentes = listaLogAtendentes;
	}
	
	
	
}
