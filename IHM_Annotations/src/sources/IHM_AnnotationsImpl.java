package sources;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

@Component(name = "IHMAnnotations")
public class IHM_AnnotationsImpl {
	private ConvertisseurFrame ihm;
	
	// cardinality multiple puis comparer
	@Reference(policy = ReferencePolicy.DYNAMIC, target="(Temps=*)", bind="setLien", unbind="setLien", cardinality = ReferenceCardinality.OPTIONAL)
	private Convertisseur c;
	
	public IHM_AnnotationsImpl() {
		BundleContext context  = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ihm = new ConvertisseurFrame("Convertisseur") {
			protected void closingOperation() {
				try { context.getBundle().stop();
				} catch (BundleException e) {}
			}
		};
		ihm.setConvertisseur(c);
	}
	
	public void deactivate() {
		ihm.dispose();
	}
	
	public void setLien(Convertisseur s, Map<String, Integer> props) {
		System.out.println("Temps = " + props.get("Temps"));
		c = s;
		ihm.setConvertisseur(c);
	}
	
	public void unsetLien(Convertisseur s) {
		if (c.equals(s)) {
			c = null;
			ihm.setConvertisseur(c);
		}
	}
}
