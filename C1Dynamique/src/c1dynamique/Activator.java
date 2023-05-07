package c1dynamique;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import Types.TypeService1;

public class Activator implements BundleActivator, ServiceListener {
	private BundleContext context;
	private ServiceReference<TypeService1> refa;
	private TypeService1 a;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		context.addServiceListener(this);
		chercherService();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		context.ungetService(refa);
		context.removeServiceListener(this);
	}
	
	public void chercherService() throws Exception {
		refa = context.getServiceReference(TypeService1.class);
		
		if (refa == null) {
			context.removeServiceListener(this);
			throw new Exception("Aucun TypeService1 trouvé.");
		}
		
		a = context.getService(refa);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> r = event.getServiceReference();
		String[] objectClasses = (String[]) r.getProperty("objectClass");
		
		if (objectClasses[0].equals(TypeService1.class.getName())) {
			ServiceReference<TypeService1> sr = (ServiceReference<TypeService1>) r;
			switch (event.getType()) {
				case ServiceEvent.REGISTERED:
					traitementNouveauService(sr);
					break;
				case ServiceEvent.MODIFIED:
					traitementModificationService(sr);
					break;
				case ServiceEvent.UNREGISTERING:
					traitementDepartService(sr);
			}
		}
	}
	
	public void traitementNouveauService(ServiceReference<TypeService1> sr) {
		// On a rien à faire, on a déjà un service.
	}
	
	public void traitementModificationService(ServiceReference<TypeService1> sr) {
		// Idem (Pas de traitement sur les props à ce niveau)
	}
	
	public void traitementDepartService(ServiceReference<TypeService1> sr) {
		if (refa.equals(sr)) {
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
}
