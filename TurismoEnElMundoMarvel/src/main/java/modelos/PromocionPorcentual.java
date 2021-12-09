package modelos;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int descuentoPorcentual;
	private double precio;

	public PromocionPorcentual(Integer id, String nombre, Integer descuento, String tipo
			//, List<Atraccion> atracciones
			) {
		super(id, nombre, tipo);
		this.descuentoPorcentual = descuento;
	}

	
	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio();
		precio -= super.getPrecio() * descuentoPorcentual / 100;
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
