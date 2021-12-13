package modelos;

import java.util.List;

public class PromocionAxB extends Promocion {
public PromocionAxB(Integer id, String nombre, String tipo) {
		super(id, nombre, tipo);
		
	}
public PromocionAxB( String nombre, String tipo) {
	super(nombre, tipo);
	
}

private double precio;


	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio() - super.getAtracciones().get(0).getPrecio();
		return precio;
		
	}


	public void restarCupo() {
		// TODO Auto-generated method stub
		
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

//	public String toString() {
//		return null;
//	
//	}
}

