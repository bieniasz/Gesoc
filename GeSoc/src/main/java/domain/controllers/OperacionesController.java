package domain.controllers;

import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.Direccion;
import domain.entities.direccion.DireccionBuilder;
import domain.entities.direccion.ExcepcionesDireccion.FaltaLocacionException;
import domain.entities.direccion.ExcepcionesDireccion.LocacionNoValidaException;
import domain.entities.direccion.MLDireccionesService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesController {

    public ModelAndView mostrarOperaciones(Request request, Response response)  throws Exception{
        //TODO: Necesito bindear con la lista de proveedores
        Proveedor proveedor = this.getProveedor1();
        Proveedor proveedor2 = this.getProveedor2();

        List<Proveedor> proveedores = new ArrayList<>();
        proveedores.add(proveedor);
        proveedores.add(proveedor2);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("proveedores", proveedores);

        return new ModelAndView(parametros, "operaciones.hbs");
    }

    public Proveedor getProveedor1() throws Exception{
        DireccionBuilder builder = new DireccionBuilder();
        builder.setProveedorDatosDirecciones(new MLDireccionesService());

        Direccion direccion = builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreApellido_RazonSocial("Coca cola SRL");
        proveedor.setTipoIdentificacion("CUIT");
        proveedor.setNumeroIdentificacion((long) 11234511);
        proveedor.setDireccionPostal(direccion);
        proveedor.setEstado("Soltera");
        proveedor.setActivo(true);

        return proveedor;
    }

    public Proveedor getProveedor2() throws Exception {
        DireccionBuilder builder = new DireccionBuilder();
        builder.setProveedorDatosDirecciones(new MLDireccionesService());

        Direccion direccion = builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();

        Proveedor proveedor2 = new Proveedor();
        proveedor2.setNombreApellido_RazonSocial("Naranju");
        proveedor2.setTipoIdentificacion("CUIT");
        proveedor2.setNumeroIdentificacion((long) 11234511);
        proveedor2.setDireccionPostal(direccion);
        proveedor2.setEstado("Soltera");
        proveedor2.setActivo(true);

        return proveedor2;
    }


}
