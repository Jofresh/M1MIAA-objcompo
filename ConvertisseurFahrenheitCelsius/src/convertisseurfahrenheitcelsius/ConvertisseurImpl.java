package convertisseurfahrenheitcelsius;

import contratconvertisseur.Convertisseur;

public class ConvertisseurImpl implements Convertisseur {
	@Override
	public String fromUnit() {
		return "Fahrenheit";
	}

	@Override
	public String toUnit() {
		return "Celsius";
	}

	@Override
	public double u12u2(double f) {
		return (( 5 * (f - 32.0)) / 9.0);
	}
}
