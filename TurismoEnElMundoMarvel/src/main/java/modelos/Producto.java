package modelos;

import java.util.List;

public abstract class  Producto {
	double getPrecio() {
		return 0;
		
	}
double getPrecioConDescuento() {
	return 0;
}
	double getTiempoEnHoras() {
		return 0;
	}
	public void restarCupo() {
	}

public	 String getNombre() {
		return null;
	}
	public Integer getId() {
		return null;
		
	}
	public	 boolean esPromocion() {
		return false;
	}
	 boolean compararNombresIguales(List<Atraccion> atraccionesCompradas, Producto productoOfrecido) {
		return false;
	}

public	List<Atraccion> getAtracciones() {
		return null;
	}
	boolean isNull() {
		return false;
	}
}
