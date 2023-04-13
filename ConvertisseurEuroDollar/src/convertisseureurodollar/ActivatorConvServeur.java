package convertisseureurodollar;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import contratconvertisseur.Convertisseur;

public class ActivatorConvServeur implements BundleActivator {
	private ServiceRegistration<Convertisseur> sr;
	
	public void start(BundleContext context) throws Exception {
		// Niveau le plus bas, à faire à la main (contrairement au DS où on a pas à faire ça)
		Dictionary<String, Integer> props = new Hashtable<>();
		props.put("Temps", Integer.valueOf(7));

		sr = context.registerService(Convertisseur.class, new ConvertisseurImpl(), props/*null*/); // Initialement null
		System.out.println("Service de type Convertisseur enregistré.");
	}

	public void stop(BundleContext context) throws Exception {
		sr.unregister();
		System.out.println("Le service Convertisseur a été désenregistré.");
	}
}
