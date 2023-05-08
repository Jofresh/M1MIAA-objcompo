package sources;

import Types.TypeService1;
import Types.TypeService2;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(name = "C2")
public class C2Impl implements TypeService1 {
	@Reference
	private TypeService2 c;
}
