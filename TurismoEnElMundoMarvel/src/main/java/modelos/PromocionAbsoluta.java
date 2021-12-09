package modelos;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private int precio;
	public PromocionAbsoluta(Integer id, String nombre, Integer precio, String tipo 
			//, List<Atraccion> atracciones
			) {
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

	public boolean compararNombresIguales(List<Atraccion> atraccionesCompradas, Producto productoOfrecido) {
		// TODO Auto-generated method stub
		return false;
	}



	

	



}
