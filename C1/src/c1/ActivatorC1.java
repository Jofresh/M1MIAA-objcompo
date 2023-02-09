package c1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import Types.TypeService1;

public class ActivatorC1 implements BundleActivator {
	private ServiceReference<TypeService1> refa;
	private TypeService1 a;
	
	public void start(BundleContext context) throws Exception {
		refa = context.getServiceReference(TypeService1.class);

		if (refa == null) {
			throw new Exception("Service de type TypeService1 non disponible.");
		}
		
		/*
		while (refa == null) {
			refa = context.getServiceReference(TypeService1.class);
		}
		*/
		
		a = context.getService(refa);
		System.out.println("Service de type TypeService1 OK");
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(refa);
	}
}
