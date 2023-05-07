package c1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import Types.TypeService1;

public class ActivatorC1 implements BundleActivator {
	private ServiceReference<TypeService1> refa;
	private TypeService1 a;

	@Override
	public void start(BundleContext context) throws Exception {
		refa = context.getServiceReference(TypeService1.class);
		
		if (refa == null) {
			throw new Exception("Aucun service TypeService1 trouvé.");
		}
		
		/**
		 *	Autre solution : attendre qu'on trouve un service.
		 * 
		 *	while (refa == null) {
		 *		refa = context.getServiceReference(TypeService1.class);
		 *	}
		 */
		
		a = context.getService(refa);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		a = null;
		context.ungetService(refa);
		refa = null;
	}
}
