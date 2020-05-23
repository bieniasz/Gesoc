package tamanioEmpresa;

public enum Actividad {
	
	
	AGROPECUARIO(12890000,48480000,345430000,547890000),
	INDUSTRIA_Y_MINERIA(26540000,190410000,1190330000,1739590000),
	SERVICIOS(8500000,50950000,425170000,607210000),
	CONSTRUCCION(15230000,90310000,	503880000,755740000),
	COMERCIO(29740000,178860000,1502750000,2146810000);
	private final double  micro; 
	private final double pequenia; 
	private final double mediana_tramo_1; 
	private final double mediana_tramo_2; 
	Actividad (double micro, double pequeña,double mediana_tramo_1,double mediana_tramo_2) { 

        this.micro = micro;
        this.pequenia = pequeña;
        this.mediana_tramo_1 = mediana_tramo_1;
        this.mediana_tramo_2 = mediana_tramo_2;

        

    } 
	public double getMicro() { return micro; }
	public double getPequenia() { return pequenia; }
	public double getmediana_tramo_1() { return mediana_tramo_1; }
	public double getmediana_tramo_2() { return mediana_tramo_2; }
}
