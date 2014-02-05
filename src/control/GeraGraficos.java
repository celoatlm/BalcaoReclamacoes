package control;

import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.LogAtendente;
import model.Logs;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class GeraGraficos {

	private Logs l;
	public static String MEDIAATENDIMENTO = "ma";
	public static String MEDIARECLAMACOES = "mr";
	public static String ATENDIMENTOSDIARIOS = "ad";
	private Integer dia;
	private Integer mes;
	private Integer ano;

	public GeraGraficos() {

		// recuperaDados();
		new Data(new Date().getTime());
	}

	public GeraGraficos(Integer dia, Integer mes, Integer ano, String opcao) {

		this.ano = ano;
		this.mes = mes;
		this.dia = dia;

		switch (opcao) {
		case "ma":
			mediaAtendimento();
			break;
		case "mr":
			mediaReclamacao();
			break;
		case "ad":
			atendimentosDiarios();
			break;
		default:
			break;
		}

	}

	private void recuperaDados() {

		final String rulesFileName = "./xmlrulesLogAtendente.xml";
		String dataFileName = "./logAtendentes.xml";

		Digester digester = newLoader(new FromXmlRulesModule() {

			@Override
			protected void loadRules() {
				// TODO Auto-generated method stub
				loadXMLRules(new File(rulesFileName));
			}
		}).newDigester();

		l = new Logs();

		digester.push(l);

		try {
			File srcFile = new File(dataFileName);
			digester.parse(srcFile);
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(GeraGraficos.class.getName())
					.error(e.getMessage());
		}
		System.out.println("##############");
		for (LogAtendente lg : l.getListaLogAtendentes()) {
			System.out.println(lg.getAtendente());
		}
	}

	private void mediaAtendimento() {
		Map<String, Integer> media = new HashMap<String, Integer>();
		Integer cont = 0;
		if (dia != null) {

			for (LogAtendente la : l.getListaLogAtendentes()) {
				Data d = new Data(la.getData());
				if (d.getDia() == dia) {
					media = manipulaMap(media, la.getAtendente());
					cont++;
				}
			}
		} else {
			if (mes != null) {
				for (LogAtendente la : l.getListaLogAtendentes()) {
					Data d = new Data(la.getData());
					if (d.getMes() == mes) {
						media = manipulaMap(media, la.getAtendente());
						cont++;
					}
				}
			} else {
				if (ano != null) {
					for (LogAtendente la : l.getListaLogAtendentes()) {
						Data d = new Data(la.getData());
						if (d.getAno() == ano) {
							media = manipulaMap(media, la.getAtendente());
							cont++;
						}
					}
				}
			}
		}
		for(Integer i = 0; i < media.size(); ){
			if(media.containsKey(i.toString())){
				Integer valor = media.get(i.toString()) / cont;
				media.remove(i.toString());
				media.put(i.toString(), valor);
			}
			i++;
		}

	}

	private Map<String, Integer> manipulaMap(Map<String, Integer> media,
			String atendente) {
		if (media.get(atendente) == null) {
			media.put(atendente, 1);
		} else {
			int aux = media.get(atendente) + 1;
			media.remove(atendente);
			media.put(atendente, aux);
		}
		return media;
	}

	private void mediaReclamacao() {
		if (dia != null) {

		} else {
			if (mes != null) {

			} else {
				if (ano != null) {

				}
			}

		}
	}

	private void atendimentosDiarios() {
		if (dia != null) {

		} else {
			if (mes != null) {

			} else {
				if (ano != null) {

				}
			}

		}
	}

	protected class Data {

		private Date data;
		private String[] sData;
		private ArrayList<String> alData;

		public Data(Long data) {
			this.data = new Date(data);
			alData = new ArrayList<>();
			sData = this.data.toString().split(" ");

			addString(sData);
			addString(sData[3].split(":"));
			System.out.println(this.toString());
		}

		public Data() {

		}

		private void addString(String[] s) {
			for (int i = 0; i < s.length; i++) {
				alData.add(s[i]);
			}
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String aux = "";
			for (String s : alData) {
				aux += ":" + s;
			}
			return aux;
		}

		public Integer getSegundos() {
			return Integer.parseInt(alData.get(10));
		}

		public Integer getMinutos() {
			return Integer.parseInt(alData.get(9));
		}

		public Integer getHora() {
			return Integer.parseInt(alData.get(7));
		}

		public Integer getDia() {
			return Integer.parseInt(alData.get(2));

		}

		public Integer getMes() {

			switch (alData.get(1)) {
			case "Jan":
				return 1;
			case "Feb":
				return 2;
			case "Mar":
				return 3;
			case "Apr":
				return 4;
			case "Mai":
				return 5;
			case "Jun":
				return 6;
			case "Jul":
				return 7;
			case "Aug":
				return 8;
			case "Sep":
				return 9;
			case "Oct":
				return 10;
			case "Nov":
				return 11;
			case "Dec":
				return 12;

			default:
				break;
			}
			return 0;

		}

		public Integer getAno() {

			return Integer.parseInt(alData.get(7));
		}

	}

}