package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tiempo implements Comparable<Tiempo>{
	
	private int hora;
	private int minuto;
	private String formato;
	
	public Tiempo() {
		Random random = new Random(); 
		this.hora = random.nextInt(12)+1;
		this.minuto = random.nextInt(60)+1;
		String[] modos = {"AM","PM"};
		String rFormato = modos[hora%2==0?0:1];
		this.formato = rFormato;
	}
	/**
	 * este metodo permita obtener la informacion del tiempo del producto de la farmacia
	 */
	public String toString() {
		return agregarCero(hora)+":"+agregarCero(minuto)+" "+formato;
	}
	/**
	 * Este metodo permite agregar un cero antes de un numero de la hora
	 * @param n la hora
	 * @return la hora
	 */
	public String agregarCero(int n) {
		String msg="";
		if(n<10) {
			msg="0"+n;
		}else {
			msg=""+n;
		}
		return msg;
	}
	/**
	 * Este metodo permite obtener la hora de la llegada del producto de la farmacia
	 * @return
	 */
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	/**
	 * este metodo permite obtener el minuto de la llegada del producto de la farmacia
	 * @return
	 */
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	/**
	 * este metodo permite obtener le formato del tiempo de llegada dell producrto a la farmacia
	 * @return
	 */
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	//COMPARA POR HORARIO DE LLEGADA
	/**
	 * Este metodo permite comparar dos tiempos
	 */
	@Override
	public int compareTo(Tiempo t) {
		int comparation =0;
		DateFormat df = new SimpleDateFormat("HH:mm"); 
	    Date tiempo1;
	    Date tiempo2;
		try {
			
			tiempo1 = df.parse(toString());
			tiempo2 = df.parse(t.toString());
			comparation= tiempo1.compareTo(tiempo2);
		} catch (ParseException e) {
			e.printStackTrace();
		}  

		return comparation;
	}


}
