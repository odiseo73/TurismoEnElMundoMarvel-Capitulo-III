package modelos;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import comparador.ComparadorPorNombres;

public class Atraccion implements Producto {

	private Integer id;
	private String nombre;
	private Double precio;
	private Double tiempoEnHoras;
	private final int CUPO_INICIAL;
	private Integer cupoDisponible;
	private HashMap<String,String> errores;


	public Atraccion(String nombre, Double precio, Double tiempoEnHoras, Integer cupo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.tiempoEnHoras = tiempoEnHoras;
		this.CUPO_INICIAL = cupo;
		this.cupoDisponible = cupo;
	}
	public Atraccion(Integer id, String nombre, Double precio, Double tiempoEnHoras, Integer cupo) {
		this(nombre,precio,tiempoEnHoras,cupo);
		this.id = id;
		
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public void setTiempoEnHoras(Double tiempoEnHoras) {
		this.tiempoEnHoras = tiempoEnHoras;
	}
	public void setCupoDisponible(Integer cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}
	public String getNombre() {
		return nombre;
	}

	public double getTiempoEnHoras() {
		return tiempoEnHoras;
	}

	public int getCupoInicial() {
		return CUPO_INICIAL;
	}

	public int getCupoDisponible() {

		return cupoDisponible;
	}

	public double getPrecio() {

		return precio;
	}
	public boolean compararNombresIguales(List<Atraccion> atraccionesCompradas, Producto producto) {
		boolean bandera = false;
		
		for (Atraccion atraccion : atraccionesCompradas) {
			if (atraccion.equals(producto)) {
				bandera = true;
			}
		}
		return bandera;
	}

	public boolean verificarCupo() {
		return this.cupoDisponible > 0;
	}

	public void restarCupo() {
		this.cupoDisponible -= 1;
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "Atraccion: " + "\n" + "Nombre: " + nombre + "\n" + "Puntos Marvel: " + precio + "\n" + "Duracion:"
				+ tiempoEnHoras + " horas" + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CUPO_INICIAL, cupoDisponible, tiempoEnHoras, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return CUPO_INICIAL == other.CUPO_INICIAL
				&& Double.doubleToLongBits(tiempoEnHoras) == Double.doubleToLongBits(other.tiempoEnHoras)
				&& Objects.equals(nombre, other.nombre) && precio == other.precio;
	}


	public Integer getId() {
		return id;
	}

	public List<Atraccion> getAtracciones() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getPrecioConDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean esValida() {
		validar();
		
		return errores.isEmpty();
	}
	
	public void validar() {
		errores = new HashMap<String, String>();

		if (precio <= 0) {
			errores.put("precio", "Debe ser positivo");
		}
		if (tiempoEnHoras <= 0) {
			errores.put("tiempoEnHoras", "Debe ser positivo");
		}
	
		if (cupoDisponible <= 0) {
			errores.put("cupoDisponible", "Debe ser positivo");
		}
	}
	@Override
	public boolean isNull() {
		return false;
	}



}
