package modelos;

import java.util.List;

public class PromocionAxB extends Promocion {
public PromocionAxB(Integer id, String nombre, String tipo,String descripcion) {
		super(id, nombre, tipo);
	this.descripcion = descripcion;	
	}
public PromocionAxB( String nombre, String tipo,String descripcion) {
	super(nombre, tipo);
	this.descripcion = descripcion;
}

private double precio;


	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio() - super.getAtracciones().get(0).getPrecio();
		return precio;
		
	}
	public void setInfoDeDescuento() {
		String info = "Gratis " + super.getAtracciones().get(0).getNombre();
		this.infoDeDescuento = info;
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

