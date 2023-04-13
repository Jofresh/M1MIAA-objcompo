package sources;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class ComposantInterneIHMImpl {
	private ConvertisseurFrame ihm;
	private Convertisseur c;
	
	// private Integer meilleurTemps;
	
	public ComposantInterneIHMImpl() {
		
	}
	
	public void activate(BundleContext context) {
		ihm = new ConvertisseurFrame("Convertisseur");
		ihm.setConvertisseur(c);
	}
	
	public void deactivate() {
		ihm.dispose();
	}
	
	public void setLien(Convertisseur os, Map<String, Integer> props) {
		System.out.println("IHM : arrivée d'un service de type Convertisseur");
		
		Integer temps = props.get("temps");
		
		System.out.println("prop = " + temps);
		
		// if (meilleurTemps == null || temps < meilleurTemps) {
		//	meilleurTemps = temps;
			c = os;
			ihm.setConvertisseur(c);
		// }
	}
	
	public void unsetLien(Convertisseur os) {
		System.out.println("IHM : départ d'un service de type Convertisseur");
		if (c.equals(os)) {
			c = null;
			ihm.setConvertisseur(c);
		}
	}
}
