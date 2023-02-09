package convertisseureurodollar;

import contratconvertisseur.Convertisseur;

public class ConvertisseurImpl implements Convertisseur {
	@Override
	public String fromUnit() {
		return "Euro";
	}

	@Override
	public String toUnit() {
		return "Dollar";
	}

	@Override
	public double u12u2(double e) {
		return e * (1.09);
	}
}
