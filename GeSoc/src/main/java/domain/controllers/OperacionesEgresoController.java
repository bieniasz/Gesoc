package domain.controllers;

import db.DAOs.*;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesEgresoController {
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMySQL();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        //String usuarioID = request.queryParams("usuarioId");
        //System.out.println("query" + usuarioID);
        String usuarioIDSpark = request.session().attribute("id");

        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        List<OperacionEgreso> egresos = this.operacionEgresoDAO.getOperacionesEgresoPorOrganizacion(usuario.getRol().getOrganizacion().getId());
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("egresos", egresos);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }

}
