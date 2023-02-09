package c2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import Types.TypeService1;
import Types.TypeService2;

public class ActivatorC2 implements BundleActivator {
	private ServiceReference<TypeService2> refc;
	private TypeService2 c;
	
	private ServiceRegistration<TypeService1> srb;
	
	public void start(BundleContext context) throws Exception {
		// ce que j'offre peut utiliser ce dont j'ai besoin
		// donc je recupere ce dont j'ai besoin avant
		refc = context.getServiceReference(TypeService2.class);
		
		if (refc == null) {
			c = null;
			System.out.println("Service non disponible.");
		} else {
			c = context.getService(refc);
			System.out.println("Service de type TypeService2 disponible.");
		}
		
		srb = context.registerService(TypeService1.class, new TypeService1Impl(), null);
		System.out.println("Un service de type TypeService1 enregistré.");
	}

	public void stop(BundleContext context) throws Exception {
		// je retire ce que j'offre avant de retirer ce dont j'avais besoin
		srb.unregister();
		System.out.println("Le service a été désenregistré.");
		
		if (refc != null) {
			context.ungetService(refc);
		}
	}

}
