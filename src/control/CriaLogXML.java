package control;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import model.LogAtendente;

public class CriaLogXML {

	// private LogAtendente logAtendente;
	private ArrayList<LogAtendente> logAtendentes;
	private static CriaLogXML criaLogXML = null;
	private Logger log = Logger.getLogger(this.getClass().getName());
	StringWriter outputWriter;

	private CriaLogXML() {

		outputWriter = new StringWriter();
		outputWriter.write("<?xml version='1.0' ?>");

		logAtendentes = new ArrayList<LogAtendente>();
	}

	public static CriaLogXML getInstance() {
		if (criaLogXML == null) {
			criaLogXML = new CriaLogXML();
		}
		return criaLogXML;
	}

	public synchronized void criaLogXML(LogAtendente logAtendente) {

		BeanWriter beanWriter = new BeanWriter(outputWriter);

		beanWriter.getXMLIntrospector().getConfiguration()
				.setAttributesForPrimitives(false);
		beanWriter.getBindingConfiguration().setMapIDs(false);
		beanWriter.enablePrettyPrint();

		try {
			beanWriter.write("logAtendente", logAtendente);
			beanWriter.close();
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
