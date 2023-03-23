package sources;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class ComposantInterneIHMImpl {
	private ConvertisseurFrame ihm;
	private Convertisseur c;
	private ServiceReference<Convertisseur> refc;
	
	public ComposantInterneIHMImpl() {
		
	}
	
	public void activate(BundleContext context) {
		ihm = new ConvertisseurFrame("Convertisseur") {
			protected void closingOperation() {
				try {
					context.getBundle().stop();
				} catch (BundleException e) {}
			}
		};
		ihm.setConvertisseur(c);
	}
	
	public void deactivate() {
		
	}
	
	public void setLien(Convertisseur os) {
		c = os;
		ihm.setConvertisseur(c);
	}
	
	public void unsetLien(ServiceReference<?> ref) {
		
	}
}
