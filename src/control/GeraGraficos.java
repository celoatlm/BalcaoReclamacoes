package control;
 
 import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;
 
 import java.io.File;
import java.io.IOException;
 
import model.LogAtendente;
 import model.Logs;
 
 import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
 
 public class GeraGraficos {
 
   private Logs l;
  
   public GeraGraficos(){
    recuperaDados();
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
       File srcFile = new File(dataFileName);
       digester.parse(srcFile);
     } catch (IOException | SAXException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       Logger.getLogger(GeraGraficos.class.getName()).error(e.getMessage());
     }
     System.out.println("##############");
     for(LogAtendente lg : l.getListaLogAtendentes()){
    	 System.out.println(lg.getAtendente());
     }
   }
     
}