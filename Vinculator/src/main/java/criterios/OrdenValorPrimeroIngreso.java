package main.java.criterios;

import com.google.gson.Gson;
import main.java.condiciones.PeriodoAceptabilidad;
import main.java.condiciones.PreCondicion;
import main.java.dominio.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenValorPrimeroIngreso extends CriterioEjecucion {

    @Override
    public RepositorioIngresosVinculados ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos) {
        List<Ingreso> ingresosOrdenados = repositorioIngresos.getIngresos().stream()
                .sorted(Comparator.comparingDouble(Ingreso::getValorTotal).reversed())
                .collect(Collectors.toList());

        //Necesito hacer varias pasadas, y en cada una vincular una sola vez cada ingreso
        //Entonces mantengo este contador de vinculaciones
        //Siempre que se haya hecho al menos una vinculacion, vuelvo a pasar
        //Si en una pasada no se hicieron vinculaciones, no tiene sentido seguir pasando
        int cantidadVinculaciones;

        do {
            cantidadVinculaciones = 0;
            for(Ingreso ingreso : ingresosOrdenados) {
                PreCondicion pcFechaAceptable = new PeriodoAceptabilidad(ingreso.getFecha(), ingreso.getFecha_hasta());
                List<Egreso> egresosAceptados = pcFechaAceptable.filtrarEgresos(repositorioEgresos)
                        .stream()
                        .sorted(Comparator.comparingDouble(Egreso::getValorTotal).reversed())
                        .collect(Collectors.toList());
                for(Egreso egreso : egresosAceptados) {
                    if(!egreso.isAsignado() && montoEgresoNoSuperaTotal(egreso, ingreso)){
                        ingreso.acumularMonto(egreso.getValorTotal());
                        egreso.setAsignado(true);
                        generarVinculacion(ingreso, egreso);
                        cantidadVinculaciones++;
                        break;
                    }
                }
            }
        } while (cantidadVinculaciones != 0);

        return ingresosVinculados;
    }
}