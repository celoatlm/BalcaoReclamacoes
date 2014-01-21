package control;

import java.util.Scanner;

public class MainApp {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// fabricaUsuarioAtendente = FabricaUsuarioAtendente.getInstance();
		while (true) {
			///la bla
			
			System.out.println("Esperando comando");
			CLIComandos.getInstance().executaComando(
					new Scanner(System.in).nextLine());

		}
	}
}
