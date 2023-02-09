package sources;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activateur implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("Bonjour!");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Au revoir!");
	}

}
