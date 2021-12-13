package modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Promocion implements Producto {

	private String nombre;
	private List<Atraccion> atracciones;
	private Integer id;
	private String tipo;
	private Integer descuento;
	protected HashMap<String,String> errores;

	public Promocion(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = new LinkedList<Atraccion>();
	}
	
	public Promocion(Integer id, String nombre, String tipo) {
		this(nombre, tipo);
		this.id = id;
	}

	public void setAtracciones(List<Atraccion> atraccionesNuevas) {
		for (Atraccion atraccion : atraccionesNuevas) {
			
		this.atracciones.add(atraccion);
		}

	}
	public void agregarAtraccion(Atraccion atraccion) {
		this.atracciones.add(atraccion);
	}
	public abstract double getPrecioConDescuento();

	public String getNombre() {
		return nombre;
	}
	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}
	public double getPrecio() {
		double precio = 0;
		for (Atraccion atraccion : atracciones) {
			precio += atraccion.getPrecio();
		}
		return precio;
	}
	public String getTipo() {
		return tipo;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<String> getNombresAtracciones() {
		ArrayList<String> nombreAtracciones = new ArrayList<String>();
		for (Atraccion atraccion : atracciones) {
			nombreAtracciones.add(atraccion.getNombre());
		}
		return nombreAtracciones;
	}

	public boolean verificarCupo(List<Atraccion> atraccionesCompradas) {
		boolean hayCupo = true;
		List<Atraccion> atraccionesDePromocion = this.getAtracciones();

		for (Atraccion atraccion : atraccionesCompradas) {
			for (Atraccion atraccionDePromo : atraccionesDePromocion) {
				if (atraccion.equals(atraccionDePromo)) {
					if (!atraccion.verificarCupo()) {
						hayCupo = false;
					}

				}

			}
		}
		return hayCupo;
	}
	public boolean verificarCupo() {
		boolean hayCupo = true;
		List<Atraccion> atraccionesDePromocion = this.getAtracciones();
		
			for (Atraccion atraccionDePromo : atraccionesDePromocion) {
					if (!atraccionDePromo.verificarCupo()) {
						hayCupo = false;
					}

		

		}
		return hayCupo;
	}

	public void restarCupo(List<Atraccion> atraccionesCompradas) {
		for (Atraccion atraccion : atraccionesCompradas) {
			atraccion.restarCupo();
		}
	}

	public boolean esPromocion() {
		return true;
	}

	public double getTiempoEnHoras() {
		double tiempo = 0;
		for (Atraccion atraccion : atracciones) {
			tiempo += atraccion.getTiempoEnHoras();
		}
		return tiempo;
	}

	@Override
	public String toString() {
		return "Promocion: " + "\n" + "Nombre: " + nombre + "\n" + "Atracciones incluidas: " + getNombresAtracciones()
				+ "\n" + "Duracion: " + getTiempoEnHoras() + " horas" + "\n" + "Precio Original: " + getPrecio()
				+ " Puntos Marvel" + "\n" + "Precio con descuento:" + getPrecioConDescuento() + " Puntos Marvel" + "\n";
	}

	public boolean verificarRepetidos(List<Producto> productosComprados) {
		boolean bandera = false;
		for (Atraccion atraccion : this.atracciones) {
			if (productosComprados.contains(atraccion)) {
				bandera = true;
			}
		}
		return bandera;
	}

	public Integer getId() {
		return id;
	}
	public boolean esValida() {
		validar();
		
		return errores.isEmpty();
	}
	
	public void validar() {
		errores = new HashMap<String, String>();
	}

	public void setNombre(String name) {
		this.nombre = name;
		
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
