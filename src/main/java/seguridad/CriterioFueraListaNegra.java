package seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriterioFueraListaNegra implements CriterioValidacion {

	// TODO si es un List va a admitir repedidos. No tiene sentido una lista negra con valores repetidos.
	// TODO revisen como usar collections en java.
	List<String> listaNegra;
	

	public CriterioFueraListaNegra(){
		this.listaNegra = new ArrayList<String>();
		this.listaNegra.add("123456");
	}
	
	//para agregar la lista negra desde un archivo
	//lista completa de archivos: https://github.com/danielmiessler/SecLists/tree/master/Passwords

	// TODO revisen como trabajar correctamente con archivos en java. No estan liberando bien los recursos.
	public  void agregarListaNegra(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
        	this.listaNegra.add(cadena);
        }
        b.close();
    }


	@Override
	public void validar(Usuario usuario, List<String> mensajesDeError) {
		if (listaNegra.contains(usuario.getContrasenia())) {
			mensajesDeError.add("Contrasenia pertenece a lista negra");
		}
	}
}


