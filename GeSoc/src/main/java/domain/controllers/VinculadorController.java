package domain.controllers;

import db.DAOs.OperacionEgresoDAO;
import db.DAOs.OperacionEgresoDAOMySQL;
import db.DAOs.OperacionIngresoDAO;
import db.DAOs.OperacionIngresoDAOMySQL;
import domain.entities.config.Constantes;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.vinculacionIngresoEgresos.VinculadorIngresoEgresos;
import spark.Request;
import spark.Response;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VinculadorController {
    OperacionIngresoDAO ingresosDAO = new OperacionIngresoDAOMySQL();
    OperacionEgresoDAO egresosDAO = new OperacionEgresoDAOMySQL();

    public Response ejecutarVinculador(Request request, Response response)  throws Exception {
        Integer organizacionID = Integer.valueOf(request.queryParams("organizacionID"));
        String strCriterio = request.queryParams("criterio");

        List<OperacionEgreso> egresosSinVincular = this.egresosDAO
                .getOperacionesEgresoPorOrganizacion(organizacionID)
                .stream()
                .filter(this::noEstaVinculado)
                .collect(Collectors.toList());
        List<OperacionIngreso> ingresosSinVincular = this.ingresosDAO
                .getOperacionesIngresoPorOrganizacion(organizacionID)
                .stream()
                .filter(this::noEstaVinculado)
                .collect(Collectors.toList());

        VinculadorIngresoEgresos vinculador = new VinculadorIngresoEgresos();
        vinculador.setOperacionEgresoList(egresosSinVincular);
        vinculador.setOperacionIngresoList(ingresosSinVincular);
        vinculador.setCriterioOrdenamiento(strCriterio);
        if (strCriterio.equals(Constantes.vinculador_criterios_Mix)){
            String[] strCriteriosAdic = request.queryParamsValues("criteriosAdicionales[]");
            vinculador.setCriteriosAdicionalesMix(strCriteriosAdic);
        }
        vinculador.vincularOperaciones();
        return response;
    }

    private boolean noEstaVinculado(OperacionEgreso egreso) {
        return egreso.getIngresoAsociado() == null;
    }

    private boolean noEstaVinculado(OperacionIngreso ingreso) {
        return ingreso.getEgresosAsociados() == null || ingreso.getEgresosAsociados().isEmpty();
    }
}
