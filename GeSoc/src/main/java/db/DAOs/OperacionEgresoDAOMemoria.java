package db.DAOs;

import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.ProveedorDocComer.TipoComprobante;
import domain.entities.operacionComercial.*;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import domain.entities.organizacion.EntidadJuridica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import db.EntityManagerHelper;


public class OperacionEgresoDAOMemoria implements OperacionEgresoDAO {
    @Override
    public OperacionEgreso buscarEgreso(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionEgreso.class, id);
    }

    @Override
    public void guardarOperacionEgreso(OperacionEgreso egreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(egreso.getOrganizacion());
        EntityManagerHelper.getEntityManager().persist(egreso);
        EntityManagerHelper.commit();
    }

    @Override
    public OperacionEgreso buscarOperacionEgresoPorId(Integer id) throws Exception {

        DetalleEgreso unDetalle = new DetalleEgreso();
        Item item = new Item();
        item.setId(55);
        item.setDescripcion("Coca");
        unDetalle.setItem(item);
        unDetalle.valorTotal = 5.0;
        unDetalle.cantidad = 2;

        DetalleEgreso otroDetalle = new DetalleEgreso();
        Item otroitem = new Item();
        otroitem.setId(23);
        otroitem.setDescripcion("Papitas");
        otroDetalle.setItem(otroitem);
        otroDetalle.valorTotal = 6.0;
        unDetalle.cantidad = 3;

        List<DetalleEgreso> detalles = new ArrayList<>();
        detalles.add(unDetalle);
        detalles.add(otroDetalle);

        EntidadJuridica organizacion = new EntidadJuridica();
        organizacion.setNombreFicticio("Alfombritas SRL");

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreApellido_RazonSocial("Pepsico SRL");

        MedioDePago medioDePago = new MedioDePago();
        medioDePago.setTipoMercadoPago("Tarjeta");
        medioDePago.setIdMercadoPago("5522334");
        medioDePago.setDescMercadoPago("Mercado pago debito");

        DocumentoComercial documentoComercial = new DocumentoComercial();
        TipoComprobante tipoComprobante = new TipoComprobante();
        tipoComprobante.setDescripcion("Ticket");
        documentoComercial.setTipoDocumentoComercial(tipoComprobante);
        documentoComercial.setContent("sdaffag");
        documentoComercial.setNumeroDocumentoComercial((long)45235);

        CategoriaDeOperaciones categoria = new CategoriaDeOperaciones();
        categoria.setDescripcion("Alimentos");
        categoria.setId(1);
        CategoriaDeOperaciones categoriaDeOperaciones = new CategoriaDeOperaciones();
        categoriaDeOperaciones.setDescripcion("Bebidas");
        categoria.setId(2);
        List<CategoriaDeOperaciones> categoriaDeOperaciones1 = new ArrayList<>();
        categoriaDeOperaciones1.add(categoria);
        categoriaDeOperaciones1.add(categoriaDeOperaciones);

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setDetalle(detalles);
        builder.setMedioDePago(medioDePago);
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(proveedor);
        builder.setOrganizacion(organizacion);
        builder.setCantEsperadaPresupuestos(7);
        builder.setFecha(LocalDate.now());
        builder.setCategoriasAsociadas(categoriaDeOperaciones1);
        builder.setDocumentoComercial(documentoComercial);
        OperacionEgreso operacion = builder.build();
        operacion.setId(3);

        return operacion;
    }
}
