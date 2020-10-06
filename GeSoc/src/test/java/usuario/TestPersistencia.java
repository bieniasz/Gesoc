package usuario;

import db.EntityManagerHelper;
import org.junit.Test;


public class TestPersistencia {

    @Test
    public void CreacionRolAdmin() {
    UsuarioAdmin administrador1 = new UsuarioAdmin();
    administrador1.setActivo(true);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(administrador1);
        EntityManagerHelper.commit();
    }

    @Test
    public void CreacionUsuario() {

        /*UsuarioAdmin administrador1 = new UsuarioAdmin();
        administrador1.setActivo(true);*/

        EntityManagerHelper.beginTransaction();

        UsuarioAdmin administradorBase = EntityManagerHelper.getEntityManager().find(UsuarioAdmin.class,1);
        EntityManagerHelper.commit();
        Usuario usuarioEjemplo = new Usuario("Nico");
        usuarioEjemplo.setRol(administradorBase);
        usuarioEjemplo.setActivo(true);

        EntityManagerHelper.beginTransaction();
        /*EntityManagerHelper.getEntityManager().persist(administradorBase);*/
        EntityManagerHelper.getEntityManager().persist(usuarioEjemplo);
        EntityManagerHelper.commit();
    }
}
