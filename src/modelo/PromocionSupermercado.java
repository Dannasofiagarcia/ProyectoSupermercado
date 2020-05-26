package modelo;

public class PromocionSupermercado {

	String promocion;
	String codigo;

	public PromocionSupermercado(String promocion, String codigo) {
		this.promocion = promocion;
		this.codigo = codigo;
	}

	public String getPromocion() {
		return promocion;
	}

	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
