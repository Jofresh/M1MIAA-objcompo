package convertisseurihm;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class ActivatorIHM implements BundleActivator {
	private ServiceReference<Convertisseur> refc;
	private Convertisseur c;
	
	private ConvertisseurFrame ihm;

	public void start(BundleContext context) throws Exception {
		refc = context.getServiceReference(Convertisseur.class);
		
		// Convertisseur optionnel
		if (refc == null) {
			// System.out.println("Aucun service Convertisseur disponible.");
			// Convertisseur obligatoire
			throw new Exception("Aucun service Convertisseur disponible.");
		} else {
			c = context.getService(refc);
			System.out.println("Service de type Convertisseur disponible");
		}

		ihm = new ConvertisseurFrame("Convertisseur") {
			@Override
			protected void closingOperation() {
				try {
					context.getBundle().stop();
				} catch (BundleException e) {
					e.printStackTrace();
				}
			}
		};
		ihm.setConvertisseur(c);
	}

	public void stop(BundleContext context) throws Exception {
		ihm.dispose();
		ihm = null;
		//if (refc != null) { // Pas besoin du if si Convertisseur obligatoire
			c = null;
			context.ungetService(refc);
		//}
	}

}
