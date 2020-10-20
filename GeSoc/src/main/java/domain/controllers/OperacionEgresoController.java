package domain.controllers;

import db.DAOs.ProveedorDAOMySQL;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import domain.entities.organizacion.EntidadJuridica;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;

public class OperacionEgresoController {

    public String saluda(Request request, Response response) {
        return "Saludos humano";
    }

    public ModelAndView mostrarEgresos(Request request, Response response) {

        return new ModelAndView(null, "operacionEgresoNuevo.hbs");
    }

    public ModelAndView guardar(Request request, Response response) throws Exception {

        Proveedor proveedor = new ProveedorDAOMySQL().buscarProveedorPorNombre(request.queryParams("proveedor"));
        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setProveedor(proveedor);
        builder.setCantEsperadaPresupuestos(Integer.parseInt(request.queryParams("cantidadEsperadaPresupuestos")));
        builder.setFecha(fecha);
        builder.setNumeroIdentificadorMedioPago(request.queryParams("numeroIdentificadorMedioPago"));/*
        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        ;
        //TODO: para la organizacion necesito la organizacion del usuario que ya esta logueado
        builder.setOrganizacion(new EntidadJuridica());
        */
        OperacionEgreso operacion = builder.build();

        return new ModelAndView(null, "operacionEgresoNuevo.hbs");
    }
}
