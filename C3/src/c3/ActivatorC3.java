package c3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import Types.TypeService2;

public class ActivatorC3 implements BundleActivator {
	private ServiceRegistration<TypeService2> srd;
	
	@Override
	public void start(BundleContext context) throws Exception {
		srd = context.registerService(TypeService2.class, new TypeService2Impl(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		srd.unregister();
	}
}
