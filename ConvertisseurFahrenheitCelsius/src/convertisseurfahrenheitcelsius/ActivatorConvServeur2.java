package convertisseurfahrenheitcelsius;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import contratconvertisseur.Convertisseur;

public class ActivatorConvServeur2 implements BundleActivator {
	private ServiceRegistration<Convertisseur> sr;

	public void start(BundleContext context) throws Exception {
		sr = context.registerService(Convertisseur.class, new ConvertisseurImpl(), null);
		System.out.println("Service de type Convertisseur enregistré.");
	}

	public void stop(BundleContext context) throws Exception {
		sr.unregister();
		System.out.println("Le service Convertisseur a été désenregistré.");
	}

}
