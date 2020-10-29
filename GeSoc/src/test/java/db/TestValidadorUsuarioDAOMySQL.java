package db;

import db.DAOs.*;
import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.ValidadorDeUsuario;
import domain.entities.usuario.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestValidadorUsuarioDAOMySQL {
    private ValidadorDeUsuario validador;
    private AlmacenContrasenias almacen;
    private Usuario usuario;

    @Before
    public void init(){
        this.almacen = new AlmacenContrasenias();
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMySQL());
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMySQL());
        UserDAO usuarioDao = new UserDAOMySQL();

        this.usuario = usuarioDao.buscarUsuarioPoruserId("aos123");
        new IntentosFallidosDAOMySQL().eliminarIntentosFallidos(this.usuario);

        this.validador = new ValidadorDeUsuario();
        this.validador.setUsuarioDao(usuarioDao);
        this.validador.setAlmacenContrasenias(this.almacen);
    }

    @Test
    public void validarMetodoContraseniaLoginConError(){
        //TODO: los criterios de login todavia los tengo harcodeados, los tengo que mover a setters
        List<String> errores = this.validador.validarContraseniaLogin("aos123","password");
        Assert.assertEquals(1,errores.size());
    }

    @Test
    public void validarMetodoContraseniaLoginSinError(){
        //this.almacen.registrarContrasenia(this.usuario,"password");
        List<String> errores = this.validador.validarContraseniaLogin("aos123","aos");
        Assert.assertEquals(0,errores.size());
    }

    @Test
    public void validarMetodoContraseniaLoginMultiple() throws InterruptedException {
        //this.almacen.registrarContrasenia(this.usuario,"password");
        List<String> errores;

        //Intento 1 fallido
        errores = this.validador.validarContraseniaLogin("aos123","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertNotNull(this.almacen.getIntentosFallidosDeUsuario(this.usuario));

        //Intento 2 fallido
        errores = this.validador.validarContraseniaLogin("aos123","Incorrecta");
        Assert.assertEquals(1,errores.size());

        //Intento 3 fallido
        errores = this.validador.validarContraseniaLogin("aos123","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertEquals(3,this.almacen.getIntentosFallidosDeUsuario(usuario).getCantidadIntentos());

        //Intento 4 correcto sin tiempo de espera
        errores = this.validador.validarContraseniaLogin("aos123","aos");
        Assert.assertEquals(1,errores.size());
        System.out.println(errores);

        //Intento 4 correcto con tiempo de espera
        Thread.sleep(5000);
        errores = this.validador.validarContraseniaLogin("aos123","aos");
        Assert.assertEquals(0,errores.size());
    }

}
