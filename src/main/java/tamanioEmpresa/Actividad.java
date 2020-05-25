package tamanioEmpresa;

public enum Actividad {

	AGROPECUARIO(12890000,48480000,345430000,547890000),
	INDUSTRIA_Y_MINERIA(26540000,190410000,1190330000,1739590000),
	SERVICIOS(8500000,50950000,425170000,607210000),
	CONSTRUCCION(15230000,90310000,	503880000,755740000),
	COMERCIO(29740000,178860000,1502750000,2146810000);

	private final double topeMicro;
	private final double topePequenia;
	private final double topeMedianaT1;
	private final double topeMedianaT2;

	Actividad (double micro, double pequenia, double mediana_tramo_1, double mediana_tramo_2) {
        this.topeMicro = micro;
        this.topePequenia = pequenia;
        this.topeMedianaT1 = mediana_tramo_1;
        this.topeMedianaT2 = mediana_tramo_2;
    }

	public double getTopeMicro() { return topeMicro; }
	public double getTopePequenia() { return topePequenia; }
	public double getTopeMedianaT1() { return topeMedianaT1; }
	public double getTopeMedianaT2() { return topeMedianaT2; }
}
