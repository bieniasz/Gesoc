package tamanioEmpresa;

public enum Actividad {

	AGROPECUARIO(12890000,48480000,345430000,547890000,
			5, 10, 50, 215),
	INDUSTRIA_Y_MINERIA(26540000,190410000,1190330000,1739590000,
			15, 60, 235, 655),
	SERVICIOS(8500000,50950000,425170000,607210000,
			7, 30, 165, 535),
	CONSTRUCCION(15230000,90310000,	503880000,755740000,
			12, 45, 200, 590),
	COMERCIO(29740000,178860000,1502750000,2146810000,
			7, 35, 135, 345);

	private final double topeMicro;
	private final double topePequenia;
	private final double topeMedianaT1;
	private final double topeMedianaT2;

	private final int topeCantPersonalMicro;
	private final int topeCantPersonalPequenia;
	private final int topeCantPersonalMedianaTramo1;
	private final int topeCantPersonalMedianaTramo2;

	Actividad (double micro, double pequenia, double mediana_tramo_1, double mediana_tramo_2,
			   int personalMicro, int personalPequenia, int personalMedT1, int personalMedT2) {
        this.topeMicro = micro;
        this.topePequenia = pequenia;
        this.topeMedianaT1 = mediana_tramo_1;
        this.topeMedianaT2 = mediana_tramo_2;

		this.topeCantPersonalMicro = personalMicro;
		this.topeCantPersonalPequenia = personalPequenia;
		this.topeCantPersonalMedianaTramo1 = personalMedT1;
		this.topeCantPersonalMedianaTramo2 = personalMedT2;
    }

	public double getTopePromVentasMicro() { return topeMicro; }
	public double getTopePromVentasPequenia() { return topePequenia; }
	public double getTopePromVentasMedianaT1() { return topeMedianaT1; }
	public double getTopePromVentasMedianaT2() { return topeMedianaT2; }

	public int getTopeCantPersonalMicro() {	return topeCantPersonalMicro; }
	public int getTopeCantPersonalPequenia() { return topeCantPersonalPequenia;	}
	public int getTopeCantPersonalMedianaTramo1() {	return topeCantPersonalMedianaTramo1; }
	public int getTopeCantPersonalMedianaTramo2() {	return topeCantPersonalMedianaTramo2; }
}
