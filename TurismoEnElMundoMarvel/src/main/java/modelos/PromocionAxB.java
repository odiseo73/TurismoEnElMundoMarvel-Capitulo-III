package modelos;

import java.util.List;

public class PromocionAxB extends Promocion {
public PromocionAxB(Integer id, String nombre, String tipo
		// , List<Atraccion> atracciones
		) {
		super(id, nombre, tipo);
		
	}


private double precio;
	

	@Override
	public double getPrecioConDescuento() {
		
		return super.getPrecio() - super.getAtracciones().get(0).getPrecio();
		
	}


	public void restarCupo() {
		// TODO Auto-generated method stub
		
	}


	public boolean compararNombresIguales(List<Atraccion> atraccionesCompradas, Producto productoOfrecido) {
		// TODO Auto-generated method stub
		return false;
	}

//	public String toString() {
//		return null;
//	
//	}
}

