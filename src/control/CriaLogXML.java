package control;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import model.LogAtendente;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class CriaLogXML {
	// classe responsavel por criar e gerenciar os logs em xml

	private ArrayList<LogAtendente> logAtendentes;
	private static CriaLogXML criaLogXML = null;
	private Logger log = Logger.getLogger(this.getClass().getName());

	private File file;
	private Boolean existe;

	private CriaLogXML() {

		file = new File("./src/logAtendentes.xml");
		existe = file.exists();
		if (!existe) {
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				bw.newLine();
				bw.write("<logs>");

				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logAtendentes = new ArrayList<LogAtendente>();
	}

	public static CriaLogXML getInstance() {
		if (criaLogXML == null) {
			criaLogXML = new CriaLogXML();
		}
		return criaLogXML;
	}

	public synchronized void criaLogXML(LogAtendente logAtendente) {

		StringWriter outputWriter = new StringWriter();
		BeanWriter beanWriter = new BeanWriter(outputWriter);

		beanWriter.getXMLIntrospector().getConfiguration()
				.setAttributesForPrimitives(false);
		beanWriter.getBindingConfiguration().setMapIDs(false);
		beanWriter.enablePrettyPrint();

		try {
			beanWriter.write("logAtendente", logAtendente);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String aux = "";
			
			while(br.ready()){
				aux += br.readLine().replace("</logs>", "");
				aux += "\n";
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(aux + outputWriter.toString()+"</logs>");
			bw.newLine();
			bw.close();
			fw.close();
			fr.close();
			br.close();
			beanWriter.close();
			beanWriter.flush();
			outputWriter.close();
			outputWriter.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}

	}

	public synchronized ArrayList<LogAtendente> listLogAtendentes() {
		return logAtendentes;
	}
	
}
