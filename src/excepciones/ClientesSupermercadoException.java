package excepciones;

public class ClientesSupermercadoException extends Exception {

	public ClientesSupermercadoException() {
		super("El cliente que desea agregar ya se encuentra en el sistema del supermercado");
	}
}
