package control;

import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

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
import model.Logs;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class CriaLogXML {
	// classe responsavel por criar e gerenciar os logs dos atendentes em xml

	private ArrayList<LogAtendente> logAtendentes;
	private static CriaLogXML criaLogXML = null;
	private Logger log = Logger.getLogger(this.getClass().getName());
	private String fileName = "";
	private Logs l;
	private File file;

	private CriaLogXML() {
		
		new Thread(new GravaLogAtendentes()).start();
		
		logAtendentes = new ArrayList<LogAtendente>();
		
		l = new Logs();
		
		criaFile();
	}

	public static CriaLogXML getInstance() {
		if (criaLogXML == null) {
			criaLogXML = new CriaLogXML();
		}
		return criaLogXML;
	}

	public synchronized void addLogAtendente(LogAtendente logAtendente) {
		//logAtendentes.add(logAtendente);
		l.addLogAtendente(logAtendente);
		
	}

	private void criaFile(){
		
		//Date data = new Date();
		//String[] d1 = data.toString().split(" ");
        fileName = "./logAtendentes.xml";
//		fileName = "./src/"+d1[5]+"/"+d1[1]+"/"+d1[2]+"/logAtendentes.xml";
	//	fileName = "./src/"+d1[1]+"/"+d1[2]+"/logAtendentes.xml";

		file = new File(fileName);

		if (!file.exists()) {
			try {
				//new File("./src/"+d1[5]+"/"+d1[1]+"/"+d1[2]+"/").mkdirs();
				//new File("./src/"+d1[1]+"/"+d1[2]+"/").mkdirs();
				file.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}else{
			recuperaDados();
		}
	}

	public synchronized ArrayList<LogAtendente> listLogAtendentes() {
		
		return logAtendentes;
	}
	
	private void recuperaDados(){
		

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
			File srcFile = new java.io.File(dataFileName);
			digester.parse(srcFile);
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(GeraGraficos.class.getName())
					.error(e.getMessage());
		}
		
		
	}
	
	protected class GravaLogAtendentes implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while (true) {
				

				try {
					Thread.sleep(30000);
					StringWriter outputWriter = new StringWriter();
					BeanWriter beanWriter = new BeanWriter(outputWriter);

					beanWriter.getXMLIntrospector().getConfiguration()
							.setAttributesForPrimitives(false);
					beanWriter.getBindingConfiguration().setMapIDs(false);
					beanWriter.enablePrettyPrint();
					beanWriter.write("logs", l.getListaLogAtendentes());
					
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);

					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write(outputWriter.toString());
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
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error(e.getMessage());
				}

			}
		}

	}

}
