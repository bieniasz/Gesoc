package seguridad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriterioFueraListaNegra implements CriterioValidacion {

	List<String> listaNegra;
	String archivo; // ruta de la lista negra

	public CriterioFueraListaNegra(){
		this.listaNegra = new ArrayList<String>();
		listaNegra.add("123456789");
	}
	
	//Agrego la lista negra
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


