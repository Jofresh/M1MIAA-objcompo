package sources;

import org.osgi.framework.ServiceReference;

import Types.TypeService1;

public class ComposantInterneImpl {
	public ComposantInterneImpl() {
		System.out.println("creation du composant de service CI1_DS");
	}
	
	public void activate() {
		System.out.println("activation du composant de service CI1_DS");
	}
	
	public void deactivate() {
		System.out.println("desactivation du composant de service CI1_DS");
	}
	
	public void setLien(TypeService1 s) {
		System.out.println("CI1_DS : arrivee d'un service de type TypeService1");
	}
	
	public void unsetLien(ServiceReference<TypeService1> ref) {
		System.out.println("CI1_DS : depart d'un service de type TypeService1");
	}
}
