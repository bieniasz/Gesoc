package main.java.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Condicion extends RepositorioEgresos {

	public List<Egreso> getEgresos() {
		return egresos;
	}
	
	
}
