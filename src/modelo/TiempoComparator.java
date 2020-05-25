package modelo;

import java.util.Comparator;

public class TiempoComparator implements Comparator<Producto> {

	/**
	 * Este método permite comparar dos productos a partir del horario 
	 * de llegada de cada uno a la farmacia
	 */
	@Override
	public int compare(Producto p1, Producto p2) {

		int comparation = p1.getHorario().compareTo(p2.getHorario());
		return comparation;
	}

}
