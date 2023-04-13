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
		ihm.dispose();
	}
	
	// TODO
	// public void setLien(ServiceReference<Convertisseur> ref)
	public void setLien(Convertisseur os/*, ou Map<> props*/) {
		c = os;
		ihm.setConvertisseur(c);
	}
	
	public void unsetLien(ServiceReference<?> os) {
		if (c.equals(os)) {
			c = null;
			ihm.setConvertisseur(c);
		}
	}
}
