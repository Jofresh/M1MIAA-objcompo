package ihmconvertisseurtr;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activator implements BundleActivator {

	private BundleContext context;
	
	private ConvertisseurFrame ihm;
	
	private ServiceReference<Convertisseur> refc;
	private Convertisseur c;
	
	private ServiceTracker<Convertisseur, Convertisseur> traqueur;

	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		
		ihm = new ConvertisseurFrame("Convertisseur Tr") {
			protected void closingOperation() {
				try { context.getBundle().stop(); }
				catch (BundleException e) {}
			}
		};
		
		traqueur = new ServiceTracker<Convertisseur,Convertisseur>(context, Convertisseur.class, null) {
			@Override
			public Convertisseur addingService(ServiceReference<Convertisseur> reference) {
				// Ex: Fournisseur - propose farine
				System.out.println("Appel addingService.");
				
				if (refc == null) {
					System.out.println("Signature d'un nouveau contrat.");
					refc = reference;
					c = context.getService(refc);
					ihm.setConvertisseur(c);
				}
				
				return context.getService(reference);
			}
			
			@Override
			public void removedService(ServiceReference<Convertisseur> reference, Convertisseur service) {
				System.out.println("Appel removedService.");
				
				if (reference.equals(refc)) {
					context.ungetService(refc);
					System.out.println("Rupture de contrat.");
					refc = context.getServiceReference(Convertisseur.class);
					
					if (refc == null) {
						c = null;
					} else {						
						c = context.getService(refc);
					}
					ihm.setConvertisseur(c);
				}
			}
		};
		System.out.println("Ouverture du traqueur");
		traqueur.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Fermeture du traqueur");
		traqueur.close();
		ihm.dispose();
		ihm = null;
		if (refc != null) {
			c = null;
			context.ungetService(refc);
		}
		context = null;
	}

}
