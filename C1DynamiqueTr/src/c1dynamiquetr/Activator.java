package c1dynamiquetr;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import Types.TypeService1;

public class Activator implements BundleActivator {
	private BundleContext context;
	private ServiceReference<TypeService1> refa;
	private TypeService1 a;
	
	private ServiceTracker<TypeService1, TypeService1> traqueur;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		
		traqueur = new ServiceTracker<TypeService1, TypeService1>(
				context,
				TypeService1.class,
				null
		) {
			@Override
			public TypeService1 addingService(ServiceReference<TypeService1> reference) {
				if (refa == null) {
					refa = reference;
					a = context.getService(refa);
				}
				
				// Toujours retourner le service associé à la référence (Convention)
				return context.getService(reference);
			}
			
			@Override
			public void modifiedService(ServiceReference<TypeService1> reference, TypeService1 service) {
				// Rien à faire
			}
			
			@Override
			public void removedService(ServiceReference<TypeService1> reference, TypeService1 service) {
				if (refa.equals(reference)) {
					a = null;
					context.ungetService(refa);
					
					// On cherche s'il y a un autre service
					try {
						chercherService();
					} catch (Exception e) {
						// Dans le cas où aucun service n'a été trouvé, on stoppe le composant.
						context.getBundle().stop(); // peut throws une `BundleException`
					}
				}
			}
		};
		
		traqueur.open();
		
		chercherService();
	}
	
	public void chercherService() throws Exception {
		refa = context.getServiceReference(TypeService1.class);
		
		if (refa == null) {
			traqueur.close();
			throw new Exception("Aucun TypeService1 trouvé.");
		}
		
		a = context.getService(refa);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		context.ungetService(refa);
		traqueur.close();
	}

	
}
