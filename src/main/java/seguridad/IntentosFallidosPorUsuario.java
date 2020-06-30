package seguridad;

import java.time.LocalTime;

public class IntentosFallidosPorUsuario {

    private String nombreUsuario;
    private int cantidadIntentos;
    private LocalTime horaDelIntentoMaximo;

    public int getCantidadIntentos() {
        return cantidadIntentos;
    }

    public LocalTime getHoraDelIntentoMaximo() {
        return horaDelIntentoMaximo;
    }

    public void setNombreUsuario(String nombre){
        this.nombreUsuario = nombre;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public void nuevoInentoFallido(){
        this.cantidadIntentos ++;
    }

    public void reiniciarIntentos(){
        this.cantidadIntentos = 0;
    }

    public void setHoraActual(){
        this.horaDelIntentoMaximo = LocalTime.now();
    }


}
