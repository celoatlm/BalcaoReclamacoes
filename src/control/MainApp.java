package control;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		fabricaUsuarioAtendente =  FabricaUsuarioAtendente.getInstance();
		criaOptions();
		
		
	}

	public void executaComando(String commando){
		
		CommandLineParser parse = new PosixParser();
		try {
			CommandLine cmd = parse.parse(options, commando.split(" "));
			
			if(cmd.hasOption("addAtendente")){
				fabricaUsuarioAtendente.addAtendente();
			}
			
			if(cmd.hasOption("removeAtendente")){
				fabricaUsuarioAtendente.removeAtendente(
						Integer.parseInt(cmd.getOptionValue("removeAtendente")));
			}
			
			if(cmd.hasOption("removeAtendente")){
				fabricaUsuarioAtendente.removeAtendente(
						Integer.parseInt(cmd.getOptionValue("pausaAtendente")));
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("static-access")
	public static void criaOptions() {

		Option addAtendente = OptionBuilder.withArgName("addAtendente")
				.hasArg(false).withDescription("Adiciona um novo Atendente")
				.create("addAtendente");
		Option removeAtendente = OptionBuilder.withArgName("removeAtendente")
				.hasArgs(1).withValueSeparator()
				.withDescription("remove um Atendente X")
				.create("removeAtendente");
		Option pausaAtendente = OptionBuilder.withArgName("pausaAtendente")
				.hasArgs(1).withValueSeparator()
				.withDescription("pausa um Atendente X")
				.create("pausaAtendente");
		
		Option startx = OptionBuilder.withArgName("startx")
				.hasArgs(1).withValueSeparator()
				.withDescription("start interface grafica")
				.create("startx");
		
		
		options = new Options();
		
		options.addOption(addAtendente);
		options.addOption(removeAtendente);
		options.addOption(pausaAtendente);
		options.addOption(startx);
		
		formatter = new HelpFormatter();
		formatter.printHelp("MainApp", options);

	}

}
