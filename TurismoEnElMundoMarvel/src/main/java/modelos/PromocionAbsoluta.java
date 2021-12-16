package modelos;

import java.util.HashMap;
import java.util.List;

public class PromocionAbsoluta extends Promocion {
	private int precio;
	public PromocionAbsoluta( String nombre, Integer precio, String tipo, String descripcion) {
		super(nombre, tipo);
		this.precio = precio;
		this.descripcion = descripcion;
	}
	public PromocionAbsoluta(Integer id, String nombre, Integer precio, String tipo, String descripcion) {
		super(id, nombre, tipo);
		this.precio = precio;
		this.descripcion = descripcion;
	}
	@Override
	public double getPrecioConDescuento() {
		return precio;
		
		
	}

	public void restarCupo() {
		// TODO Auto-generated method stub
		
	}
	
	public void setInfoDeDescuento() {
		String info = "El precio total es " + this.precio + " puntos";
		this.infoDeDescuento = info;
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
