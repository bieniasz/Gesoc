package tamanioEmpresa;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
public class Actividad extends EntidadPersistente {
/*
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
*/

	@Column
	private double topeMicro;
	@Column
	private double topePequenia;
	@Column
	private double topeMedianaT1;
	@Column
	private double topeMedianaT2;

	@Column
	private int topeCantPersonalMicro;
	@Column
	private int topeCantPersonalPequenia;
	@Column
	private int topeCantPersonalMedianaTramo1;
	@Column
	private int topeCantPersonalMedianaTramo2;


	public double getTopePromVentasMicro() { return topeMicro; }
	public double getTopePromVentasPequenia() { return topePequenia; }
	public double getTopePromVentasMedianaT1() { return topeMedianaT1; }
	public double getTopePromVentasMedianaT2() { return topeMedianaT2; }

	public int getTopeCantPersonalMicro() {	return topeCantPersonalMicro; }
	public int getTopeCantPersonalPequenia() { return topeCantPersonalPequenia;	}
	public int getTopeCantPersonalMedianaTramo1() {	return topeCantPersonalMedianaTramo1; }
	public int getTopeCantPersonalMedianaTramo2() {	return topeCantPersonalMedianaTramo2; }

	public void setTopeMicro(double topeMicro) {
		this.topeMicro = topeMicro;
	}
	public void setTopePequenia(double topePequenia) {
		this.topePequenia = topePequenia;
	}
	public void setTopeMedianaT1(double topeMedianaT1) {
		this.topeMedianaT1 = topeMedianaT1;
	}
	public void setTopeMedianaT2(double topeMedianaT2) {
		this.topeMedianaT2 = topeMedianaT2;
	}

	public void setTopeCantPersonalMicro(int topeCantPersonalMicro) {
		this.topeCantPersonalMicro = topeCantPersonalMicro;
	}
	public void setTopeCantPersonalPequenia(int topeCantPersonalPequenia) {
		this.topeCantPersonalPequenia = topeCantPersonalPequenia;
	}
	public void setTopeCantPersonalMedianaTramo1(int topeCantPersonalMedianaTramo1) {
		this.topeCantPersonalMedianaTramo1 = topeCantPersonalMedianaTramo1;
	}
	public void setTopeCantPersonalMedianaTramo2(int topeCantPersonalMedianaTramo2) {
		this.topeCantPersonalMedianaTramo2 = topeCantPersonalMedianaTramo2;
	}

}
