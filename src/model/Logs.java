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
	}

	public List<LogAtendente> getListaLogAtendentes() {
		return listaLogAtendentes;
	}
	
	
	
}
