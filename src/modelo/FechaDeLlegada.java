package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FechaDeLlegada implements Comparable<FechaDeLlegada> {
	private int dia;
	private int mes;
	private int anio;

	public FechaDeLlegada() {
		Random random = new Random();
		this.dia = random.nextInt(30) + 1;
		this.mes = random.nextInt(12) + 1;
		this.anio = random.nextInt(5) + 2015;
	}

	/**
	 * Este metodo permite obtene la fecha de llegada en el formato AA-MM-DD
	 */
	@Override
	public String toString() {
		return anio + "-" + agregarCero(mes) + "-" + agregarCero(dia);
	}

	/**
	 * Este metodo permite agregar un cero adelante si el numero de la fecha es
	 * menos a 10
	 * 
	 * @param n
	 * @return
	 */
	public String agregarCero(int n) {
		String mensaje = "";
		if (n < 10) {
			mensaje = "0" + n;
		} else {
			mensaje = "" + n;
		}
		return mensaje;
	}

	/**
	 * este metodo permite obtener el dia de la fecha de llegada del producto
	 * 
	 * @return
	 */
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	/**
	 * este metodo permite obtener el mes de la fecha de llegada de producto
	 * 
	 * @return
	 */
	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * este metodo permite obtener el año de la fecha de llegada de producto
	 * 
	 * @return
	 */
	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Este metodo permite obtener el formato de la fecha de llegada
	 * 
	 * @return
	 */
	public String compare() {
		return anio + "-" + mes + "-" + dia;
	}

	// COMPARA POR FECHA DE LLEGADA DEL PRODUCTO
	/**
	 * este metodo permite comparar dos fechas, retorna 1 si esta es mayor a la del
	 * parametro, -1 si esta es menor y 0 si son iguales
	 */
	@Override
	public int compareTo(FechaDeLlegada o) {
		int comparar = 0;
		SimpleDateFormat simpledatef = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaUno;
		Date fechaDos;
		try {
			fechaUno = simpledatef.parse(compare());
			fechaDos = simpledatef.parse(o.compare());
			comparar = fechaUno.compareTo(fechaDos);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return comparar;
	}
}
