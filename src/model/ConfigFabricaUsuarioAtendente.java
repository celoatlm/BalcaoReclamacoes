package model;

public class ConfigFabricaUsuarioAtendente {
	//classe que sera instanciada quando o arquivo xml contendo
	//as config são carregadas,
	//e a aqui sera armazenado as config para as fabricas de usuarios e atendentes
	private String colldownUsuario;
	private String quantidadeMaximaReclamacao;
	private String quantidadeMinimaReclamacao;
	private String tempoMaximoReclamacao;
	private String tempoMinimoReclamacao;
	private String quantidadeAtendentes;

	public String getColldownUsuario() {
		return colldownUsuario;
	}

	public void setColldownUsuario(String colldownUsuario) {
		this.colldownUsuario = colldownUsuario;
	}

	public String getQuantidadeMaximaReclamacao() {
		return quantidadeMaximaReclamacao;
	}

	public void setQuantidadeMaximaReclamacao(String quantidadeMaximaReclamaca) {
		this.quantidadeMaximaReclamacao = quantidadeMaximaReclamaca;
	}

	public String getQuantidadeMinimaReclamacao() {
		return quantidadeMinimaReclamacao;
	}

	public void setQuantidadeMinimaReclamacao(String quantidadeMinimaReclamacao) {
		this.quantidadeMinimaReclamacao = quantidadeMinimaReclamacao;
	}

	public String getTempoMaximoReclamacao() {
		return tempoMaximoReclamacao;
	}

	public void setTempoMaximoReclamacao(String tempoMaximoReclamacao) {
		this.tempoMaximoReclamacao = tempoMaximoReclamacao;
	}

	public String getTempoMinimoReclamacao() {
		return tempoMinimoReclamacao;
	}

	public void setTempoMinimoReclamacao(String tempoMinimoReclamacao) {
		this.tempoMinimoReclamacao = tempoMinimoReclamacao;
	}

	public String getQuantidadeAtendentes() {
		return quantidadeAtendentes;
	}

	public void setQuantidadeAtendentes(String quantidadeAtendentes) {
		this.quantidadeAtendentes = quantidadeAtendentes;
	}

}
