package domain.controllers;

import db.DAOs.*;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.Organizacion;
import domain.entities.usuario.Usuario;
import domain.entities.usuario.UsuarioEstandar;
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
        //String userId = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "operacionIngresoNuevo.hbs");
    }

    public Response guardar(Request request, Response response) throws Exception {

        OperacionIngreso operacionIngreso = new OperacionIngreso();

        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        LocalDate fechaHastaAceptable = LocalDate.parse(request.queryParams("fechaHastaAceptable"));
        String descripcion = request.queryParams("descripcion");
        Float monto = new Float(request.queryParams("monto"));

        //String userId = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        Organizacion organizacion = usuario.getRol().getOrganizacion();

        operacionIngreso.setFecha(fecha);
        operacionIngreso.setFechaHastaAceptable(fechaHastaAceptable);
        operacionIngreso.setDescripcion(descripcion);
        operacionIngreso.setMonto(monto);
        operacionIngreso.setOrganizacion(organizacion);

        this.operacionIngresoDAO.guardarOperacionIngreso(operacionIngreso);

        response.redirect("/operacionesIngreso");
        return response;
    }

    public ModelAndView editarIngreso(Request request, Response response) throws Exception {

        Integer id = new Integer(request.queryParams("ingresoId"));
        OperacionIngreso operacionIngreso = this.operacionIngresoDAO.buscarOperacionIngreso(id);
        //String userId = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();


        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ingreso", operacionIngreso);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("ingresoId", request.queryParams("ingresoId"));
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView( parametros, "operacionIngresoEditar.hbs");
    }

    public Response guardarEditarIngreso(Request request, Response response) throws Exception {
        Integer id = new Integer(request.queryParams("ingresoId"));
        OperacionIngreso operacionIngreso = this.operacionIngresoDAO.buscarOperacionIngreso(id);

        operacionIngreso.setFecha(LocalDate.parse(request.queryParams("fecha")));
        operacionIngreso.setFechaHastaAceptable(LocalDate.parse(request.queryParams("fechaHastaAceptable")));
        operacionIngreso.setDescripcion(request.queryParams("descripcion"));
        operacionIngreso.setMonto(new Float(request.queryParams("monto")));

        this.operacionIngresoDAO.modificarOperacionIngreso(operacionIngreso);

        response.redirect("/operacionesIngreso");
        return response;
    }

}
