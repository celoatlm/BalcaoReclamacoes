package control;

import java.util.Observable;

import model.AtendenteGrafico;

public class ObserverAtendenteGui extends Observable {

	private static ObserverAtendenteGui observerAtendenteGui = null;
	private AtendenteGrafico atendenteGrafico;	
	private ObserverAtendenteGui(){
		
	}
	
	public static ObserverAtendenteGui getInstance(){
		if(observerAtendenteGui == null){
			
			observerAtendenteGui = new ObserverAtendenteGui();
			
		}
		return observerAtendenteGui;
	}

	public AtendenteGrafico getAtendenteGrafico() {
		return atendenteGrafico;
	}

	public void setAtendenteGrafico(AtendenteGrafico atendenteGrafico) {
		this.atendenteGrafico = atendenteGrafico;
		notificaObservers();
	}
	private void notificaObservers(){
		setChanged();
		notifyObservers(atendenteGrafico);
	}
	
}
