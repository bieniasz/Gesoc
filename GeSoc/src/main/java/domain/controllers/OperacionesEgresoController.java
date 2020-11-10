package domain.controllers;

import db.DAOs.*;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
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
    private CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();
    private TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOMySQL();
    private ImagenesDAO imagenesDAO = new ImagenesDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        List<OperacionEgreso> egresos = this.operacionEgresoDAO.getOperacionesEgresoPorOrganizacion(usuario.getRol().getOrganizacion().getId());
        this.arreglarImagenEgresos(egresos);
        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();
        List<TipoComprobante> tipoComprobanteList = this.tipoComprobanteDAO.buscarTodosLosTiposDeComprobantes();

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("categorias", categorias);
        parametros.put("egresos", egresos);
        parametros.put("tiposCombantes", tipoComprobanteList);


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



}
