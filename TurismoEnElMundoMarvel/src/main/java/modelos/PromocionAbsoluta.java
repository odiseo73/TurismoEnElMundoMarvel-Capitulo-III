package modelos;

import java.util.HashMap;
import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private int precio;
	public PromocionAbsoluta( String nombre, Integer precio, String tipo) {
		super(nombre, tipo);
		this.precio = precio;
	}
	public PromocionAbsoluta(Integer id, String nombre, Integer precio, String tipo) {
		super(id, nombre, tipo);
		this.precio = precio;
	}
	@Override
	public double getPrecioConDescuento() {
		return precio;
		
		
	}

	public void restarCupo() {
		// TODO Auto-generated method stub
		
	}
	public void validar() {
		if (precio <= 0) {
			errores.put("precio", "El precio absoluto debe ser positivo");
		}
	}
	public boolean compararNombresIguales(List<Atraccion> atraccionesCompradas, Producto productoOfrecido) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		return false;
	}



	

	



}
