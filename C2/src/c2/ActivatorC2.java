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

	@Override
	public void start(BundleContext context) throws Exception {
		refc = context.getServiceReference(TypeService2.class);
		
		// Pas besoin de lever d'exception, `c` est optionnel.
		if (refc == null) {
			c = null;
			System.out.println("Aucun service TypeService2 trouvé.");
		} else {
			c = context.getService(refc);
			// Traitement avec `c`...
		}
		
		context.registerService(TypeService1.class, new TypeService1Impl(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		srb.unregister();
		
		if (refc != null) {
			c = null;
			context.ungetService(refc);
			refc = null;
		}
	}
	
}
