package domain.controllers;

import db.DAOs.*;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.CriterioDeOperaciones;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.usuario.Rol;
import domain.entities.usuario.Usuario;
import domain.entities.usuario.UsuarioRevisor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesEgresoController {
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMySQL();
    private OperacionIngresoDAO operacionIngresoDAO = new OperacionIngresoDAOMySQL();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();
    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOMySQL();
    private ImagenesDAO imagenesDAO = new ImagenesDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();
    private CriterioDeOperacionesDAO criterioDeOperacionesDAO = new CriterioDeOperacionesDAOMySQL();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        List<OperacionEgreso> egresos = this.operacionEgresoDAO.getOperacionesEgresoPorOrganizacion(usuario.getRol().getOrganizacion().getId());
        this.arreglarImagenEgresos(egresos);
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();
        List<CriterioDeOperaciones> criteriosDeOperaciones = this.criterioDeOperacionesDAO.getTodasLosCriterios();

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("categorias", categorias);
        parametros.put("egresos", egresos);
        parametros.put("tiposCombantes", tipoComprobanteList);
        parametros.put("criteriosDeOperaciones", criteriosDeOperaciones);


        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }

    private void arreglarImagenEgresos(List<OperacionEgreso> egresos) {
        egresos.forEach( egreso -> {
                try {
                    String content = this.imagenesDAO.buscarContenido(egreso.getId());
                    System.out.println("DOCUMENTO: " + content);

                    egreso.getDocumentoComercial().setContentDeserealizado(content);
                } catch (Exception e){}

            });

    }


    public ModelAndView mostrarEgresosAsociados(Request request, Response response) {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Integer idIngreso = Integer.parseInt(request.queryParams("ingresoId"));
        List<OperacionEgreso> egresos = this.operacionIngresoDAO.buscarOperacionIngreso(idIngreso).getEgresosAsociados();
        this.arreglarImagenEgresos(egresos);
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();
        List<CriterioDeOperaciones> criteriosDeOperaciones = this.criterioDeOperacionesDAO.getTodasLosCriterios();

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("categorias", categorias);
        parametros.put("egresos", egresos);
        parametros.put("tiposCombantes", tipoComprobanteList);
        parametros.put("criteriosDeOperaciones", criteriosDeOperaciones);


        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }
}
