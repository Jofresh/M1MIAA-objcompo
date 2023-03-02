package c1dynamiquetr;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import Types.TypeService1;

public class Activator implements BundleActivator {

	private BundleContext context;
	
	private TypeService1 a;
	private ServiceReference<TypeService1> refa;
	
	private ServiceTracker<TypeService1, TypeService1> traqueur;

	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		
		traqueur = new ServiceTracker<TypeService1, TypeService1>(context, TypeService1.class, null) {
			@Override
			public TypeService1 addingService(ServiceReference<TypeService1> reference) {
				System.out.println("Appel addingService.");
				
				if (refa == null) {
					refa = reference;
					a = context.getService(refa);
				}
				
				return context.getService(reference);
			}
			
			@Override
			public void removedService(ServiceReference<TypeService1> reference, TypeService1 service) {
				System.out.println("Appel removedService.");
				// TODO
			}
		};
		
		System.out.println("Ouverture du traqueur");
		traqueur.open();
		if (refa == null) {
			traqueur.close();
			context = null;
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Fermeture du traqueur");
		traqueur.close();
		context = null;
	}

}
