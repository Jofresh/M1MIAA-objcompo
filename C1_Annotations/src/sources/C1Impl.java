package sources;

import Types.TypeService1;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(name = "C1")
public class C1Impl {
	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	private TypeService1 a;
}
