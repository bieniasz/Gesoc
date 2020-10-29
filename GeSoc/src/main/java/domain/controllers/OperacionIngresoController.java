package domain.controllers;

import db.DAOs.*;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.Organizacion;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.stream.Stream;

public class OperacionIngresoController {

    private OperacionIngresoDAO operacionIngresoDAO = new OperacionIngresoDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();

    public ModelAndView nuevoIngreso(Request request, Response response) throws Exception {

        //TODO: validacion por FE para no hacer guardar con campos vacios
        //TODO: boton cancelar para volver a la vista anterior

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", request.queryParams("usuarioId"));

        return new ModelAndView( parametros, "operacionIngresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {
        OperacionIngreso operacionIngreso = new OperacionIngreso();

        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        String descripcion = request.queryParams("descripcion");
        Float monto = new Float(request.queryParams("monto"));

        String userId = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(userId);
        Organizacion organizacion = usuario.getRol().getOrganizacion();

        operacionIngreso.setFecha(fecha);
        operacionIngreso.setDescripcion(descripcion);
        operacionIngreso.setMonto(monto);
        operacionIngreso.setOrganizacion(organizacion);

        this.operacionIngresoDAO.guardarOperacionIngreso(operacionIngreso);

        response.redirect("/operacionIngreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

    public ModelAndView editarIngreso(Request request, Response response) throws Exception {

        Integer id = new Integer(request.queryParams("id"));
        OperacionIngreso operacionIngreso = this.operacionIngresoDAO.buscarOperacionIngreso(id);

        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView( parametros, "operacionIngresoNuevo.hbs");
    }

    public Response guardarEditarIngreso(Request request, Response response) throws Exception {
        Integer id = new Integer(request.queryParams("id"));
        OperacionIngreso ingreso = this.operacionIngresoDAO.buscarOperacionIngreso(id);

        ingreso.setFecha(LocalDate.parse(request.queryParams("fecha")));
        ingreso.setDescripcion(request.queryParams("descripcion"));
        ingreso.setMonto(new Float(request.queryParams("monto")));

        response.redirect("/operacionesIngreso?usuarioId=" + request.queryParams("usuarioId"));
        return response;
    }

}
