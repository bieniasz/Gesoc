package db;

import org.junit.Test;

public class TestRecuperarUnString {

    @Test
    public void TestNativeQuery() {
        Integer id = 27;
        String contenido = (String) EntityManagerHelper.getEntityManager().createNativeQuery(
                "SELECT CONVERT(dc.content USING utf8) FROM operacioncomercial oc, DocumentoComercial dc WHERE oc.id = " + id + " AND oc.documentoComercial_id = dc.id;").getSingleResult();

        System.out.println(contenido);
    }
}
