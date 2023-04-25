package sources;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component(name = "NomComposant", property = "Langue=FR", service = {Runnable.class, Serializable.class})
public class ComposantAnnotationsImpl implements Runnable, Serializable {
	// simple
	@Reference(
			target = "(Langue=*)", 
			bind = "setLien", 
			unbind = "unsetLien", 
			cardinality = ReferenceCardinality.OPTIONAL, 
			policy = ReferencePolicy.DYNAMIC)
	private Runnable service;
	
	
	// multiples
	@Reference
	private Collection<Runnable> services;
	
	@Activate
	public void start() {
		
	}
	
	@Deactivate
	public void stop() {
		
	}
	
	@Modified
	public void update() {
		
	}
	
	public void setLien(Runnable r/*, Map<String, Object> props*/) {
		
	}
	
	public void unsetLien(/*Runnable r*/ ServiceReference<Runnable> r) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
