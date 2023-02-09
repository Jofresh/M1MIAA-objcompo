package c3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import Types.TypeService2;

public class ActivatorC3 implements BundleActivator {
	private ServiceRegistration<TypeService2> srd;
	public void start(BundleContext context) throws Exception {
		// j'offre le service TypeService2
		// type de service (.class), objet de service ,propriété
		// objet de service : class qui implémente le type
		srd = context.registerService(TypeService2.class, new TypeService2Impl(), null);
		System.out.println("Un service de type TypeService2 enregistré.");
	}

	public void stop(BundleContext context) throws Exception {
		srd.unregister();
		System.out.println("Le service a été désenregistré.");
	}

}
