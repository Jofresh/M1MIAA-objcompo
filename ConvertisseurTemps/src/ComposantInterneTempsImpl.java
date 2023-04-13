import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;

public class ComposantInterneTempsImpl implements Convertisseur {
	private Convertisseur c;
	private ServiceReference<Convertisseur> refc;
	
	public ComposantInterneTempsImpl() {
		
	}
	
	/*public void activate(BundleContext context) {

	}
	
	public void deactivate() {
		
	}
	
	public void setLien(Convertisseur os) {
		
	}
	
	public void unsetLien(ServiceReference<?> os) {
		
	}*/

	@Override
	public String fromUnit() {
		return "seconde";
	}

	@Override
	public String toUnit() {
		return "heure";
	}

	@Override
	public double u12u2(double arg0) {
		return (double) (arg0 / (double) 3600);
	}
}
