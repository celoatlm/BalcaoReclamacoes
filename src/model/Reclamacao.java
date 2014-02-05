package model;

public class Reclamacao {
	//classe simples contendo o tempo da reclamação
	private Integer tempo;

	public Reclamacao(Integer tempo) {
		this.tempo = tempo;
	}
	
	public Reclamacao(){
		
	}
	public void setTempo(Integer tempo){
		this.tempo = tempo;
	}

	public Integer getTempo() {
		return tempo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tempo.toString();
	}

}
