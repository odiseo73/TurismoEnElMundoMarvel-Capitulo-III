package modelos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.Crypt;

//import java.util.List;

public class Usuario {
	private int id;
	private String username;
	private double tiempoDisponible;
	private double dineroDisponible;
	private List<Atraccion> atraccionesCompradas;
	private HashMap<String, String> errores;
	private Itinerario itinerario;
	private String password;
	private Boolean admin;
	// private List<Atraccion> atraccionesCompradas;

	public Usuario(String username, String password, Double dinero, Double tiempoEnHoras) {
		this.username = username;
		this.password = password;
		this.tiempoDisponible = tiempoEnHoras;
		this.dineroDisponible = dinero;
		this.atraccionesCompradas = new LinkedList<Atraccion>();
		this.itinerario = new Itinerario(this.getUsername(), "", 0, 0);
		this.admin = true;
	}

	public Usuario(int id, String username, String password, Double dinero, Double tiempoEnHoras) {
		this(username,password, tiempoEnHoras, tiempoEnHoras);
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public double getTiempoEnHoras() {
		return tiempoDisponible;
	}
	public boolean esAdmin() {
		return admin;
	}

	public double getDinero() {
		return dineroDisponible;
	}

	public int getId() {
		return id;
	}
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}
	public boolean isNull() {
		return false;
	}
	public Itinerario getItinerario() {
		return this.itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public boolean tieneComprado(Producto productoOfrecido) {
		boolean bandera = false;
		if (productoOfrecido.esPromocion()) {
			for (Atraccion atraccion : productoOfrecido.getAtracciones()) {
				if (atraccion.compararNombresIguales(this.atraccionesCompradas, atraccion)) {
					bandera = true;
				}
			}
		} else {
			if (productoOfrecido.compararNombresIguales(this.atraccionesCompradas, productoOfrecido)) {
				bandera = true;
			}
		}
		return bandera;
	}

	public void setAtraccionesCompradas(List<Producto> productosComprados) {
		for (Producto productoComprado : productosComprados) {
			if (productoComprado.esPromocion()) {
				for (Atraccion atraccion : productoComprado.getAtracciones()) {
					this.atraccionesCompradas.add(atraccion);
				}
			}
			if (!productoComprado.esPromocion()) {
				this.atraccionesCompradas.add((Atraccion) productoComprado);
			}
		}
	}

	public List<Atraccion> getAtraccionesCompradas() {
		return atraccionesCompradas;
	}

	public boolean comprarProducto(Producto o) {

		this.dineroDisponible -= o.getPrecioConDescuento();
		this.tiempoDisponible -= o.getTiempoEnHoras();

		if (o.esPromocion()) {
			for (Atraccion atraccion : o.getAtracciones()) {
				this.atraccionesCompradas.add(atraccion);
			}
		}
		if (!o.esPromocion()) {
			atraccionesCompradas.add((Atraccion) o);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Usuario [Nombre=" + username + ", TiempoEnHoras=" + tiempoDisponible + ", Dinero=" + dineroDisponible
				+ "]" + "\n";
	}

	public boolean tieneDinero(Producto o) {
		if (this.dineroDisponible >= o.getPrecio()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean tieneTiempo(Producto o) {
		if (this.tiempoDisponible >= o.getTiempoEnHoras()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (dineroDisponible < 0) {
			errores.put("dineroDisponible", "No debe ser negativo");
		}
		if (tiempoDisponible < 0) {
			errores.put("tiempoDisponible", "No debe ser negativo");
		}
	}

	public Map<String, String> getErrores() {
		return errores;
	}

}
