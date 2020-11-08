package domain.controllers;

import db.DAOs.*;
import domain.entities.ProveedorDocComer.TipoComprobante;
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
    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOMySQL();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        //String usuarioID = request.queryParams("usuarioId");
        //System.out.println("query" + usuarioID);
        String usuarioIDSpark = request.session().attribute("id");

        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        List<OperacionEgreso> egresos = this.operacionEgresoDAO.getOperacionesEgresoPorOrganizacion(usuario.getRol().getOrganizacion().getId());
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", usuarioIDSpark);
        parametros.put("egresos", egresos);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);
        parametros.put("tiposCombantes", tipoComprobanteList);

        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }

}
