package model;

import java.util.Observable;

public class Atendente extends Observable implements Runnable{

	private Boolean ativo;
	private Senha senha;
	private Usuario usuario;
	private Boolean kill;
	private FilaSenhas filaSenhas;
	private String nome;

	public Atendente(String nome) {
		this.kill = true;
		filaSenhas = FilaSenhas.getInstance();
		this.senha = filaSenhas.pegaPrimeira();
		this.nome = nome;
		ativo = false;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (kill) {
			if(ativo){
				
				String aux = "";
				for (Reclamacao r : usuario.getReclamacoes()) {
					try {
						aux += " : "+ r.getTempo().toString();
						Thread.sleep(r.getTempo());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				System.out.println("---------"+nome+"---------");
				System.out.println("Usuario: "+usuario.getSenha().getSenha()+" : "+
									aux+" : "+usuario.getSenha().getSenhaPrioritaria()+
									" : "+usuario.getReclamacoes().size());
				System.out.println("---------"+nome+"---------\n");
				//filaSenhas = FilaSenhas.getInstance();
				if(ativo){//essa verifica��o � nescessaria pois posso desativalo no meio do processo 
						// de atendimento,
							//e o atendente mesmo assim ainda chama um novo usuario
					senha = filaSenhas.pegaPrimeira();
				}
			}
			while(senha == null && kill){//while para esperar at� ter usuario na fila
				try {
					Thread.sleep(100);
					//filaSenhas = FilaSenhas.getInstance();
					senha = filaSenhas.pegaPrimeira();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			setMudanca();
			
		}
	}
	
	public void setMudanca(){
		setChanged();
		notifyObservers();
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		
		this.ativo = true;
		this.usuario = usuario;
		
	}

	public void removeAtendente() {
		ativo = false;
		kill = false;
	}
	
}
