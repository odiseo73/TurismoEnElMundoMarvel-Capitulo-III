package modelos;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int descuentoPorcentual;
	private double precio;

	public PromocionPorcentual(Integer id, String nombre, Integer descuento, String tipo) {
		super(id, nombre, tipo);
		this.descuentoPorcentual = descuento;
	}
	
	public PromocionPorcentual(String nombre, Integer descuento, String tipo) {
		super(nombre, tipo);
		this.descuentoPorcentual = descuento;
	}
	
	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio();
		precio -= super.getPrecio() * descuentoPorcentual / 100;
		return precio;

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
