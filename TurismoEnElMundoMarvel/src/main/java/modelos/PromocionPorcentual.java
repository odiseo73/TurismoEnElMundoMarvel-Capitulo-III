package modelos;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private int descuentoPorcentual;
	private double precio;

	public PromocionPorcentual(Integer id, String nombre, Integer descuento, String tipo,String descripcion) {
		super(id, nombre, tipo);
		this.descuentoPorcentual = descuento;
		this.descripcion = descripcion;
	}
	
	public PromocionPorcentual(String nombre, Integer descuento, String tipo,String descripcion) {
		super(nombre, tipo);
		this.descuentoPorcentual = descuento;
		this.descripcion = descripcion;
	}
	
	@Override
	public double getPrecioConDescuento() {
		this.precio = super.getPrecio();
		precio -= super.getPrecio() * descuentoPorcentual / 100;
		return precio;

	}

	public void setInfoDeDescuento() {
		String info = "El descuento es de " + this.descuentoPorcentual + " %";
		this.infoDeDescuento = info;
	}

	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		return false;
	}


	

}
