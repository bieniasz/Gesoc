package domain.entities.seguridad.CriteriosContrasenia;

import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CriterioFueraListaNegra implements CriterioValidacion {
	Set<String> listaNegra;

	public CriterioFueraListaNegra(){
		this.listaNegra = new LinkedHashSet<>();
		this.listaNegra.add("123456");
	}
	
	//para agregar la lista negra desde un archivo
	//lista completa de archivos: https://github.com/danielmiessler/SecLists/tree/master/Passwords

	public  void cargarListaNegra(String rutaArchivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader br = new BufferedReader(fr);
        while((cadena = br.readLine()) != null) {
        	this.listaNegra.add(cadena);
        }
        br.close();
        fr.close();
    }


	@Override
	public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
		if (listaNegra.contains(contrasenia)) {
			mensajesDeError.add("Contrasenia pertenece a lista negra");
		}
	}
}


