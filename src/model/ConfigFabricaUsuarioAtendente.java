package model;

public class ConfigFabricaUsuarioAtendente {
	// classe que sera instanciada quando o arquivo xml contendo
	// as config é carregada,
	// e a aqui sera armazenado as config para as
	// fabricas de usuarios e atendentes,
	// as variaveis são setadas nullas para que
	// na primeira vez que ela é carregada na leitura do xml,
	// não haja comparação entre variaveis nulls;
	private Integer colldownUsuario = null;
	private Integer quantidadeMaximaReclamacao = null;
	private Integer quantidadeMinimaReclamacao = null;
	private Integer tempoMaximoReclamacao = null;
	private Integer tempoMinimoReclamacao = null;
	private Integer quantidadeAtendentes = null;

	public Integer getColldownUsuario() {
		return colldownUsuario;
	}

	public void setColldownUsuario(Integer colldownUsuario) {
		if (colldownUsuario > 199
				&& colldownUsuario < 10001) {
			this.colldownUsuario = colldownUsuario;
		}
	}

	public Integer getQuantidadeMaximaReclamacao() {
		return quantidadeMaximaReclamacao;
	}

	public void setQuantidadeMaximaReclamacao(Integer quantidadeMaximaReclamaca) {
		if (this.quantidadeMaximaReclamacao != null) {
			if (quantidadeMaximaReclamaca >= this.quantidadeMinimaReclamacao
					&& quantidadeMaximaReclamaca < 11) {
				this.quantidadeMaximaReclamacao = quantidadeMaximaReclamaca;
			}
		} else {
			this.quantidadeMaximaReclamacao = quantidadeMaximaReclamaca;
		}
	}

	public Integer getQuantidadeMinimaReclamacao() {
		return quantidadeMinimaReclamacao;
	}

	public void setQuantidadeMinimaReclamacao(Integer quantidadeMinimaReclamacao) {
		if (this.quantidadeMaximaReclamacao != null) {
			if (this.quantidadeMaximaReclamacao >= quantidadeMinimaReclamacao
					&& quantidadeMinimaReclamacao > 0) {
				this.quantidadeMinimaReclamacao = quantidadeMinimaReclamacao;
			}
		} else {
			this.quantidadeMinimaReclamacao = quantidadeMinimaReclamacao;
		}
	}

	public Integer getTempoMaximoReclamacao() {
		return tempoMaximoReclamacao;
	}

	public void setTempoMaximoReclamacao(Integer tempoMaximoReclamacao) {
		if (this.tempoMinimoReclamacao != null) {
			if (tempoMaximoReclamacao >= this.tempoMinimoReclamacao
					&& tempoMaximoReclamacao < 11) {
				this.tempoMaximoReclamacao = tempoMaximoReclamacao;
			}
		} else {
			this.tempoMaximoReclamacao = tempoMaximoReclamacao;
		}
	}

	public Integer getTempoMinimoReclamacao() {
		return tempoMinimoReclamacao;
	}

	public void setTempoMinimoReclamacao(Integer tempoMinimoReclamacao) {
		if (this.tempoMaximoReclamacao != null) {
			if (this.tempoMaximoReclamacao >= tempoMinimoReclamacao
					&& tempoMinimoReclamacao > 0) {
				this.tempoMinimoReclamacao = tempoMinimoReclamacao;
			}
		} else {
			this.tempoMinimoReclamacao = tempoMinimoReclamacao;
		}
	}

	public Integer getQuantidadeAtendentes() {
		return quantidadeAtendentes;
	}

	public void setQuantidadeAtendentes(Integer quantidadeAtendentes) {
		if (quantidadeAtendentes > 0
				&& quantidadeAtendentes < 11) {
			this.quantidadeAtendentes = quantidadeAtendentes;
		}
	}

}
