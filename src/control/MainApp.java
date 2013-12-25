package control;

import java.util.Scanner;

import model.FilaSenhas;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class MainApp {

	private static Options options;
	private static HelpFormatter formatter;
	private static FabricaUsuarioAtendente fabricaUsuarioAtendente;
	private static Boolean exit = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		criaOptions();
		fabricaUsuarioAtendente = FabricaUsuarioAtendente.getInstance();
		while (exit) {

			System.out.println("Esperando comando");
			executaComando(new Scanner(System.in).nextLine());

		}

	}

	public static void executaComando(String commando) {

		CommandLineParser parse = new PosixParser();
		try {
			CommandLine cmd = parse.parse(options, commando.split(" "));

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
				formatter = new HelpFormatter();
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
			if(cmd.hasOption("coldownUsuario")){
				fabricaUsuarioAtendente.setColdownUsuario(cmd.getOptionValue("coldownUsuario"));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-access")
	public static void criaOptions() {

		Option startApp = OptionBuilder.withArgName("startApp")
				.withDescription("inicia o balcao").create("startApp");
		Option addAtendente = OptionBuilder.withArgName("addAtendente")
				.withDescription("Adiciona um novo Atendente")
				.create("addAtendente");
		Option removeAtendente = OptionBuilder.withArgName("removeAtendente")
				.hasArgs(1).withValueSeparator()
				.withDescription("remove um Atendente X")
				.create("removeAtendente");
		Option pausaAtendente = OptionBuilder.withArgName("pausaAtendente")
				.hasArgs(2).withValueSeparator()
				.withDescription("pausa um Atendente X")
				.create("pausaAtendente");
		Option coldownUsuario = OptionBuilder.withArgName("coldownUsuario")
				.hasArgs(1).withValueSeparator()
				.withDescription("altera o tempo entre cria�ao de usuarios")
				.create("coldownUsuario");
		Option qmr = OptionBuilder.withArgName("qmr").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera a quantidade minima de reclama��es")
				.create("qmr");
		Option qmxr = OptionBuilder.withArgName("qmxr").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera a quantidade minima de reclama��es")
				.create("coldownUsuario");
		Option tmr = OptionBuilder.withArgName("tmr").hasArgs(1)
				.withValueSeparator()
				.withDescription("altera o tempo minima de reclama��es")
				.create("tmr");
		Option tmxr = OptionBuilder.withArgName("tmxr").hasArgs(1)
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

	}

}
