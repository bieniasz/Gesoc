package domain.controllers;


import db.DAOs.UserDAO;
import db.DAOs.UserDAOMySQL;
import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.bandejaDeResultado.BandejaDeResultado;
import domain.entities.bandejaDeResultado.filtroDeResultado.FiltroFecha;
import domain.entities.bandejaDeResultado.filtroDeResultado.FiltroLeido;
import domain.entities.usuario.Usuario;
import domain.entities.validadorTransparencia.ResultadoDeValidacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BandejaDeMensajesController {


    private UserDAO usuarioDao = new UserDAOMySQL();


    private UserDAO userDAO = new UserDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();

    public Response cambiarEstadoMensaje(Request request, Response response) {

        System.out.println("Entro al metodo");
        System.out.println(request.queryParams("idResultado"));

        Integer idMensaje = Integer.parseInt(request.queryParams("idResultado"));
        System.out.println(idMensaje+"recibido");

        ResultadoDeValidacion resultado =  EntityManagerHelper.getEntityManager().find(ResultadoDeValidacion.class, idMensaje);
        resultado.setLeido(true);

        System.out.println("Seteo el leido");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(resultado);
        EntityManagerHelper.commit();

        System.out.println("persistio");

        return  response;

    }


    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        String usuarioIDSpark = request.session().attribute("id");

        Usuario usuario = usuarioDao.buscarUsuarioPoruserId(usuarioIDSpark);
        int numberid = usuario.getId();
        Integer bandejaDeResultadoId = (Integer) EntityManagerHelper.getEntityManager().createNativeQuery("select distinct bandejaDeResultado_id from usuario u, rol r, bandejaderesultado br, resultadodevalidacion rv where u.rol_id=r.id and r.bandejaDeResultado_id=br.id and br.id = rv.BandejaDeResultado and u.id="+numberid).getSingleResult();
        BandejaDeResultado bandeja = EntityManagerHelper.getEntityManager().find(BandejaDeResultado.class, bandejaDeResultadoId);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId",usuarioIDSpark);
        parametros.put("resultadosDeValidacion", bandeja.getResultadosDeValidacion());
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }

    public ModelAndView filtrarMensajes(Request request, Response response) {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = usuarioDao.buscarUsuarioPoruserId(usuarioIDSpark);

        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();
        List<ResultadoDeValidacion> resultadosFiltrados = this.obtenerResultadosFiltrados(request, usuario.getId());

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId",usuarioIDSpark);
        parametros.put("resultadosDeValidacion", resultadosFiltrados);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }

    private List<ResultadoDeValidacion> obtenerResultadosFiltrados(Request request, int id) {
        Integer bandejaDeResultadoId = (Integer) EntityManagerHelper.getEntityManager().createNativeQuery("select distinct bandejaDeResultado_id from usuario u, rol r, bandejaderesultado br, resultadodevalidacion rv where u.rol_id=r.id and r.bandejaDeResultado_id=br.id and br.id = rv.BandejaDeResultado and u.id="+id).getSingleResult();
        BandejaDeResultado bandeja = EntityManagerHelper.getEntityManager().find(BandejaDeResultado.class, bandejaDeResultadoId);

        LocalDate fechaDesde;
        try {
            fechaDesde = LocalDate.parse(request.queryParams("fechaDesde"));
        } catch (Exception e) {
            fechaDesde = LocalDate.parse("1980-11-11");
        }

        LocalDate fechaHasta;
        try {
            fechaHasta = LocalDate.parse(request.queryParams("fechaHasta"));
        } catch (Exception e) {
            fechaHasta = LocalDate.parse("2090-11-11");
        }

        System.out.println("Fecha desde: " + fechaDesde.toString() + "   Fecha hasta: " + fechaHasta.toString());
        FiltroFecha filtroFecha = new FiltroFecha(fechaDesde, fechaHasta);
        bandeja.agregarFiltro(filtroFecha);

        String leidoNoLeido = request.queryParams("leidoNoLeido");
        if(leidoNoLeido == "Leido") {
            bandeja.agregarFiltro(new FiltroLeido());
        }

        return bandeja.filtrarResultados();
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

