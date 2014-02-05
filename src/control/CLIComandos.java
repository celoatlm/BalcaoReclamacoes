package control;

import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.log4j.Logger;

import view.BalcaoAppJavaFX;

public class CLIComandos {
	//classe responsavel pelos comando cli
	private static CLIComandos cliComandos = null;
	private static Options options;
	private static FabricaUsuarioAtendente fabricaUsuarioAtendente;
	
	private CLIComandos(){
		criaOptions();
		fabricaUsuarioAtendente = FabricaUsuarioAtendente.getInstance();
	}
	
	public static CLIComandos getInstance(){
		if(cliComandos == null){
			cliComandos = new CLIComandos();
		}
		return cliComandos;
	}
	public void executaComando(String commando) {

		CommandLineParser parse = new PosixParser();
		try {
			CommandLine cmd = null;
			try{
				cmd = parse.parse(options, commando.split(" "));
			}catch(UnrecognizedOptionException u){
				String[] aux = {"-error"};
				cmd = parse.parse(options, aux);
				Logger.getLogger(MainApp.class.getName()).error(u.getMessage());
			}
			if (cmd.hasOption("addAtendente")) {
				fabricaUsuarioAtendente.addAtendente();
			}

			if (cmd.hasOption("pausaAtendente")) {
				fabricaUsuarioAtendente.pausaAtendente(Integer.parseInt(cmd
						.getOptionValue("pausaAtendente")));
			}

			if (cmd.hasOption("removeAtendente")) {
				
				fabricaUsuarioAtendente.removeAtendente(Integer.parseInt(cmd
						.getOptionValue("removeAtendente")));
			}
			if (cmd.hasOption("exit")) {
				System.exit(0);
			}
			if (cmd.hasOption("help")) {
				
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("MainApp", options);
			}
			if (cmd.hasOption("startApp")) {
				fabricaUsuarioAtendente.startFabrica();
			}
			if(cmd.hasOption("listaAtendentes")){
				fabricaUsuarioAtendente.listaAtendentes();
			}
			if(cmd.hasOption("listaSenhas")){
				System.out.println(FilaSenhas.getInstance().toString());
			}
			if(cmd.hasOption("colldownUsuario")){
				fabricaUsuarioAtendente.setColldownUsuario(cmd.getOptionValue("colldownUsuario"));
			}
			if(cmd.hasOption("error")){
				System.out.println("Comando invalido");
			}
			if(cmd.hasOption("qmr")){
				fabricaUsuarioAtendente.setQuantidadeMinimaReclamacoes(cmd.getOptionValue("qmr"));
			}
			if(cmd.hasOption("qmxr")){
				fabricaUsuarioAtendente.setQuantidadeMaximaReclamacoes(cmd.getOptionValue("qmxr"));				
			}
			if(cmd.hasOption("tmr")){
				fabricaUsuarioAtendente.setTempoMinimoReclamacoes(cmd.getOptionValue("tmr"));
			}
			if(cmd.hasOption("tmxr")){
				fabricaUsuarioAtendente.setTempoMaximoReclamacies(cmd.getOptionValue("tmxr"));
			}
			if(cmd.hasOption("pfu")){
				fabricaUsuarioAtendente.pausaFabricaUsuario();
			}
			if(cmd.hasOption("startx")){
				new Thread(new BalcaoAppJavaFX()).start();
				
			}
			if(cmd.hasOption("grafico")){
				new GeraGraficos();
			}
			if(cmd.hasOption("data")){
				Date d = new Date();
				String[] data = d.toString().split(" ");
				for(int  i = 0; i < data.length; i++){
					System.out.println(data[i]);
				}
				String[] aux = data[3].split(":");
				for(int  i = 0; i < aux.length; i++){
					System.out.println(aux[i]);
				}
				System.out.println(d.toString());
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(MainApp.class.getName()).error(e.getMessage());
		}

	}
	
	@SuppressWarnings("static-access")
	private void criaOptions(){
		
		Option startApp = OptionBuilder.withArgName("startApp")
				.withDescription("inicia o balcao").create("startApp");
		Option addAtendente = OptionBuilder.withArgName("addAtendente")
				.withDescription("Adiciona um novo Atendente")
				.create("addAtendente");
		Option removeAtendente = OptionBuilder.withArgName("atendente")
				.hasArgs(1).withValueSeparator()
				.withDescription("remove um Atendente X")
				.create("removeAtendente");
		Option pausaAtendente = OptionBuilder.withArgName("atendente")
				.hasArgs(1).withValueSeparator()
				.withDescription("pausa um Atendente X")
				.create("pausaAtendente");
		Option coldownUsuario = OptionBuilder.withArgName("tempo")
				.hasArgs(1).withValueSeparator()
				.withDescription("altera o tempo entre cria�ao de usuarios")
				.create("colldownUsuario");
		Option qmr = OptionBuilder.withArgName("quantidade").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera a quantidade minima de reclama��es")
				.create("qmr");
		Option qmxr = OptionBuilder.withArgName("quantidade").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera a quantidade minima de reclama��es")
				.create("qmxr");
		Option tmr = OptionBuilder.withArgName("tempo").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera o tempo minima de reclama��es")
				.create("tmr");
		Option tmxr = OptionBuilder.withArgName("tempo").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera o tempo maximo de reclama��es")
				.create("tmxr");
		Option startx = OptionBuilder.withArgName("startx")
				.withDescription("start interface grafica").create("startx");
		Option exit = OptionBuilder.withArgName("exit")
				.withDescription("fecha app").create("exit");
		Option help = OptionBuilder.withArgName("help")
				.withDescription("ajuda").create("help");
		Option pfu = OptionBuilder.withArgName("pfu")
				.withDescription("pausa a fabrica de usuarios").create("pfu");

		Option listaAtendentes = OptionBuilder.withArgName("listaAtendentes")
				.withDescription("lista todos os atendentes").create("listaAtendentes");
		Option listaSenhas = OptionBuilder.withArgName("listaSenhas")
				.withDescription("lista todos as Senhas").create("listaSenhas");
		
		Option error = OptionBuilder.withArgName("error")
				.withDescription("").create("error");
		
		Option data = OptionBuilder.withArgName("data")
				.withDescription("Data do sistema").create("data");
		
		Option grafico = OptionBuilder.withArgName("grafico")
				.withDescription("gera grafico para teste")
				.create("grafico");
		options = new Options();

		options.addOption(startApp);
		options.addOption(coldownUsuario);
		options.addOption(qmr);
		options.addOption(qmxr);
		options.addOption(tmr);
		options.addOption(tmxr);
		options.addOption(addAtendente);
		options.addOption(removeAtendente);
		options.addOption(pausaAtendente);
		options.addOption(startx);
		options.addOption(help);
		options.addOption(pfu);
		options.addOption(listaAtendentes);
		options.addOption(listaSenhas);
		options.addOption(exit);
		options.addOption(data);
		options.addOption(error);
		options.addOption(grafico);

		
	}
	
}
