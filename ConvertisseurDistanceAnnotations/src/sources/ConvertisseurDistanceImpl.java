package sources;

import contratconvertisseur.Convertisseur;
import org.osgi.service.component.annotations.Component;

@Component(name = "ConvertisseurDistance", property = { "Temps:Integer=2" })
public class ConvertisseurDistanceImpl implements Convertisseur { // implicite (pas explicite avec: service=Convertisseur.class)

	@Override
	public String fromUnit() {
		return "km";
	}

	@Override
	public String toUnit() {
		return "miles";
	}

	@Override
	public double u12u2(double arg0) {
		return arg0 / 1.609;
	}

}
