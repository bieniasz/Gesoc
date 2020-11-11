package domain.controllers;


import db.DAOs.UserDAO;
import db.DAOs.UserDAOMySQL;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.bandejaDeResultado.BandejaDeResultado;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BandejaDeMensajesController {


    private UserDAO usuarioDao = new UserDAOMySQL();


    private UserDAO userDAO = new UserDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();


    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        String usuarioIDSpark = request.session().attribute("id");

        Usuario usuario = usuarioDao.buscarUsuarioPoruserId(usuarioIDSpark);
        int numberid = usuario.getId();
        Integer bandejaDeResultadoId = (Integer) EntityManagerHelper.getEntityManager().createNativeQuery("select distinct bandejaDeResultado_id from usuario u, rol r, bandejaderesultado br, resultadodevalidacion rv where u.rol_id=r.id and r.bandejaDeResultado_id=br.id and br.id = rv.BandejaDeResultado and u.id="+numberid).getSingleResult();
        //String contrasenia = usuario.getContrasenia();
        BandejaDeResultado bandeja = EntityManagerHelper.getEntityManager().find(BandejaDeResultado.class, bandejaDeResultadoId);

        String ape = usuario.getApellido();
        String usua = usuario.getUsuarioId();
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("usuarioId",usuarioIDSpark);
        parametros.put("usua", usua);
        parametros.put("ape", ape);
        parametros.put("bandeja", bandeja);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);
        //parametros.put("contrasenia",contrasenia);
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }



    /*public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) throws Exception{
        //Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId(request.params("id"));
        Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId(request.queryParams("usuarioId"));
        //List<ResultadoDeValidacion> listaResultado = EntityManagerHelper.createQuery("select distinct rvr.resultados from usuario u, rol r, bandejaderesultado br, resultadodevalidacion rv, resultadodevalidacion_resultados rvr where u.rol_id=r.id and r.bandejaDeResultado_id=br.id and br.id = rv.BandejaDeResultado and rv.id=rvr.ResultadoDeValidacion_id and u.id=usuario").getResultList();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario",usuario);
        //parametros.put("listaResultado",listaResultado);
        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }
    */


    /*public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) throws Exception{

        Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId(request.queryParams("usuarioId"));

        //List<String> listaResultado = EntityManagerHelper.createQuery("select distinct rvr.resultados from usuario u, rol r, bandejaderesultado br, resultadodevalidacion rv, resultadodevalidacion_resultados rvr where u.rol_id=r.id and r.bandejaDeResultado_id=br.id and br.id = rv.BandejaDeResultado and rv.id=rvr.ResultadoDeValidacion_id and u.id=2").getResultList();
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("usuario",usuario);
        //parametros.put("lista",listaResultado);

        return new ModelAndView(parametros,"bandejaDeMensajes.hbs");

    }
    */
}

