package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;

public class ImagenesDAOMySQL implements ImagenesDAO {

    @Override
    public String buscarContenido(int id) {

        String contenido = (String) EntityManagerHelper.getEntityManager().createNativeQuery(
                "SELECT CONVERT(dc.content USING utf8) FROM operacioncomercial oc, DocumentoComercial dc WHERE oc.id = " + id + " AND oc.documentoComercial_id = dc.id;").getSingleResult();

        return contenido;
    }
}
