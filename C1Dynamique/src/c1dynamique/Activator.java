package c1dynamique;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import Types.TypeService1;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	private ServiceReference<TypeService1> refa;
	private TypeService1 a;
	
	private Ecouteur ec;

	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		ec = new Ecouteur();
		context.addServiceListener(ec);
		rechercheService();
	}
	
	private void rechercheService() throws Exception {
		refa = context.getServiceReference(TypeService1.class);

		if (refa == null) {
			context.removeServiceListener(ec);
			throw new Exception("Service de type TypeService1 non disponible.");
		}
		
		a = context.getService(refa);
		System.out.println("Service de type TypeService1 OK");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		context.ungetService(refa);
		context.removeServiceListener(ec);
	}

	private class Ecouteur implements ServiceListener {
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
		
		private void traitementNouveauService(ServiceReference<TypeService1> sr) {
			// rien à faire
		}
		
		private void traitementModificationService(ServiceReference<TypeService1> sr) {
			// rien à faire
		}

		private void traitementDepartService(ServiceReference<TypeService1> sr) {
			if (sr.equals(refa)) {
				a = null;
				context.ungetService(refa);
				refa = null;
				try {
					rechercheService();
				} catch (Exception e) {
					context.getBundle().stop(); // lève une exception aussi, pas important à savoir
				}
			}
		}
	}
}
