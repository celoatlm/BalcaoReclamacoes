package model;

public class ConfigFabricaUsuarioAtendente {
	// classe que sera instanciada quando o arquivo xml contendo
	// as config é carregada,
	// e a aqui sera armazenado as config para as
	// fabricas de usuarios e atendentes,
	// as variaveis são setadas nullas para que
	// na primeira vez que ela é carregada na leitura do xml,
	// não haja comparação entre variaveis nullas;
	private String colldownUsuario = null;
	private String quantidadeMaximaReclamacao = null;
	private String quantidadeMinimaReclamacao = null;
	private String tempoMaximoReclamacao = null;
	private String tempoMinimoReclamacao = null;
	private String quantidadeAtendentes = null;

	public String getColldownUsuario() {
		return colldownUsuario;
	}

	public void setColldownUsuario(String colldownUsuario) {
		if (Integer.parseInt(colldownUsuario) > 0 && Integer.parseInt(colldownUsuario) < 10001) {
			this.colldownUsuario = colldownUsuario;
		}
	}

	public String getQuantidadeMaximaReclamacao() {
		return quantidadeMaximaReclamacao;
	}

	public void setQuantidadeMaximaReclamacao(String quantidadeMaximaReclamaca) {
		if (this.quantidadeMaximaReclamacao != null) {
			if (Integer.parseInt(quantidadeMaximaReclamaca) >= Integer
					.parseInt(this.quantidadeMinimaReclamacao)
					&& Integer.parseInt(quantidadeMaximaReclamaca) < 11) {
				this.quantidadeMaximaReclamacao = quantidadeMaximaReclamaca;
			}
		} else {
			this.quantidadeMaximaReclamacao = quantidadeMaximaReclamaca;
		}
	}

	public String getQuantidadeMinimaReclamacao() {
		return quantidadeMinimaReclamacao;
	}

	public void setQuantidadeMinimaReclamacao(String quantidadeMinimaReclamacao) {
		if (this.quantidadeMaximaReclamacao != null) {
			if (Integer.parseInt(this.quantidadeMaximaReclamacao) >= Integer
					.parseInt(quantidadeMinimaReclamacao)
					&& Integer.parseInt(quantidadeMinimaReclamacao) > 0) {
				this.quantidadeMinimaReclamacao = quantidadeMinimaReclamacao;
			}
		} else {
			this.quantidadeMinimaReclamacao = quantidadeMinimaReclamacao;
		}
	}

	public String getTempoMaximoReclamacao() {
		return tempoMaximoReclamacao;
	}

	public void setTempoMaximoReclamacao(String tempoMaximoReclamacao) {
		if (this.tempoMinimoReclamacao != null) {
			if (Integer.parseInt(tempoMaximoReclamacao) >= Integer
					.parseInt(this.tempoMinimoReclamacao)
					&& Integer.parseInt(tempoMaximoReclamacao) < 11) {
				this.tempoMaximoReclamacao = tempoMaximoReclamacao;
			}
		} else {
			this.tempoMaximoReclamacao = tempoMaximoReclamacao;
		}
	}

	public String getTempoMinimoReclamacao() {
		return tempoMinimoReclamacao;
	}

	public void setTempoMinimoReclamacao(String tempoMinimoReclamacao) {
		if (this.tempoMaximoReclamacao != null) {
			if (Integer.parseInt(this.tempoMaximoReclamacao) >= Integer
					.parseInt(tempoMinimoReclamacao)
					&& Integer.parseInt(tempoMinimoReclamacao) > 0) {
				this.tempoMinimoReclamacao = tempoMinimoReclamacao;
			}
		} else {
			this.tempoMinimoReclamacao = tempoMinimoReclamacao;
		}
	}

	public String getQuantidadeAtendentes() {
		return quantidadeAtendentes;
	}

	public void setQuantidadeAtendentes(String quantidadeAtendentes) {
		this.quantidadeAtendentes = quantidadeAtendentes;
	}

}
